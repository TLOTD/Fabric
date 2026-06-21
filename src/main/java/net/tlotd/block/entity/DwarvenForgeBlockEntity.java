package net.tlotd.block.entity;

import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.recipe.Ingredient;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.tlotd.block.custom.DwarvenForgeBlock;
import net.tlotd.block.enum_property.ForgeLit;
import net.tlotd.gui.DwarvenForgeGUIHandler;
import net.tlotd.recipe.DwarvenForgingRecipe;
import net.tlotd.util.ItemHeatHelper;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

public class DwarvenForgeBlockEntity extends BlockEntity implements ExtendedScreenHandlerFactory, ImplementedInventory {

    private final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(10, ItemStack.EMPTY);

    private static final int FUEL_START = 0;
    private static final int FUEL_END = 3;

    private static final int BASE_START = 4;
    private static final int BASE_END = 6;

    private static final int RECIPE_START = 7;
    private static final int RECIPE_END = 9;

    protected final PropertyDelegate propertyDelegate;
    private int temperature = 0;
    private int targetTemperature = 0;
    private int fuelTime = 0;

    public DwarvenForgeBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.DWARVEN_FORGE_BLOCK_ENTITY, pos, state);
        this.propertyDelegate = new PropertyDelegate() {
            @Override
            public int get(int index) {
                return switch (index) {
                    case 0 -> DwarvenForgeBlockEntity.this.temperature;
                    case 1 -> DwarvenForgeBlockEntity.this.targetTemperature;
                    case 2 -> DwarvenForgeBlockEntity.this.fuelTime;
                    default -> 0;
                };
            }

            @Override
            public void set(int index, int value) {
                switch (index) {
                    case 0 -> DwarvenForgeBlockEntity.this.temperature = value;
                    case 1 -> DwarvenForgeBlockEntity.this.targetTemperature = value;
                    case 2 -> DwarvenForgeBlockEntity.this.fuelTime = value;
                }
            }

            @Override
            public int size() {
                return 3;
            }
        };
    }

    public ItemStack getRenderStack() {
        if(!this.getStack(1).isEmpty()) {
            return this.getStack(1);
        } else if (!this.getStack(2).isEmpty()) {
            return this.getStack(2);
        } else if (!this.getStack(3).isEmpty()) {
            return this.getStack(3);
        } else if (!this.getStack(4).isEmpty()) {
            return this.getStack(4);
        } else if (!this.getStack(5).isEmpty()) {
            return this.getStack(5);
        } else if (!this.getStack(1).isEmpty()) {
            return this.getStack(1);
        } else {
            return this.getStack(0);
        }
    }

    @Override
    public void markDirty() {
        world.updateListeners(pos,getCachedState(),getCachedState(),3);
        super.markDirty();
    }

    @Override
    public void writeScreenOpeningData(ServerPlayerEntity serverPlayerEntity, PacketByteBuf packetByteBuf) {
        packetByteBuf.writeBlockPos(this.pos);
    }

    @Override
    public Text getDisplayName() {
        return Text.translatable("block.tlotd.dwarven_forge");
    }

    @Override
    public DefaultedList<ItemStack> getItems() {
        return inventory;
    }

    @Override
    protected void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        Inventories.writeNbt(nbt, inventory);
        nbt.putInt("dwarven_forge.temperature", temperature);
        nbt.putInt("dwarven_forge.target_temperature", targetTemperature);
        nbt.putInt("dwarven_forge.fuel_time", fuelTime);
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        Inventories.readNbt(nbt, inventory);
        temperature = nbt.getInt("dwarven_forge.temperature");
        targetTemperature = nbt.getInt("dwarven_forge.target_temperature");
        fuelTime = nbt.getInt("dwarven_forge.fuel_time");
    }

    public @Nullable int getCurrentTemperature() {
        return temperature;
    }
    public @Nullable int getTemperatureTarget() {
        return targetTemperature;
    }
    public @Nullable int getRemainingBurnTime() {
        return fuelTime;
    }

    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player) {
        return new DwarvenForgeGUIHandler(syncId, playerInventory, this, this.propertyDelegate);
    }

    public void tick(World world, BlockPos pos, BlockState state) {
        if (world.isClient()) {
            return;
        }
        tryUpgradeFuel();
        tryCrafting();
        if (fuelTime > 0) {
            heatForge();
        } else if (canUseFuel()) {
            consumeFuel();
        } else {
            coolForge();
        }
        updateLitState();
        markDirty(world, pos, state);
    }

    private int getBaseTemperatureCap() {
        int lowest = Integer.MAX_VALUE;
        for (int i = BASE_START; i <= BASE_END; i++) {
            ItemStack stack = getStack(i);
            if (stack.isEmpty()) {
                return 0;
            }
            int max = ItemHeatHelper.getMaxBurningBaseTemperature(stack);
            if (max <= 0) {
                return 0;
            }
            lowest = Math.min(lowest, max);
        }
        return lowest;
    }

    private boolean canUseFuel() {
        return findBestFuelSlot() != -1;
    }

    private void tryUpgradeFuel() {
        int slot = findBestFuelSlot();
        if (slot == -1) {
            return;
        }
        ItemStack fuel = getStack(slot);
        int newTarget = getFuelTarget(fuel);
        if (newTarget <= targetTemperature) {
            return;
        }
        targetTemperature = newTarget;
        fuelTime = targetTemperature;
        fuel.decrement(1);
    }

    private Optional<DwarvenForgingRecipe> getCurrentRecipe() {
        if (world == null) {
            return Optional.empty();
        }
        SimpleInventory inventory = new SimpleInventory(3);
        inventory.setStack(0, getStack(RECIPE_START));
        inventory.setStack(1, getStack(RECIPE_START + 1));
        inventory.setStack(2, getStack(RECIPE_END));
        return world.getRecipeManager()
                .getFirstMatch(
                        DwarvenForgingRecipe.Type.INSTANCE,
                        inventory,
                        world
                );
    }

    private void heatRecipeItems() {
        for (int i = RECIPE_START; i <= RECIPE_END; i++) {
            ItemStack stack = getStack(i);
            if (!stack.isEmpty() && !stack.isOf(Items.OBSIDIAN)) {
                ItemHeatHelper.setTemperature(
                        stack,
                        Math.min(
                                ItemHeatHelper.getTemperature(stack) + 1,
                                temperature
                        )
                );
            }
        }
    }

    private boolean canCraft(DwarvenForgingRecipe recipe) {
        List<ItemStack> remainingStacks = new ArrayList<>();
        for (int i = RECIPE_START; i <= RECIPE_END; i++) {
            ItemStack stack = getStack(i);
            if (!stack.isEmpty()) {
                remainingStacks.add(stack);
            }
        }
        List<Ingredient> ingredients = recipe.getIngredients()
                .stream()
                .filter(i -> !i.isEmpty())
                .toList();
        if (remainingStacks.size() != ingredients.size()) {
            return false;
        }
        for (Ingredient ingredient : ingredients) {
            boolean matched = false;
            Iterator<ItemStack> iterator = remainingStacks.iterator();
            while (iterator.hasNext()) {
                ItemStack stack = iterator.next();
                if (ingredient.test(stack)) {
                    int requiredTemp =
                            ItemHeatHelper.getForgingTemperature(stack);
                    if (requiredTemp > 0 &&
                            ItemHeatHelper.getTemperature(stack) < requiredTemp) {
                        return false;
                    }
                    iterator.remove();
                    matched = true;
                    break;
                }
            }
            if (!matched) {
                return false;
            }
        }
        return true;
    }

    private record MatchedIngredient(int slot, ItemStack stack) {}

    private List<MatchedIngredient> matchRecipeIngredients(DwarvenForgingRecipe recipe) {
        List<MatchedIngredient> matches = new ArrayList<>();
        boolean[] used = new boolean[3];
        for (Ingredient ingredient : recipe.getIngredients()) {
            if (ingredient.isEmpty()) {
                continue;
            }
            boolean found = false;
            for (int slot = RECIPE_START; slot <= RECIPE_END; slot++) {
                int idx = slot - RECIPE_START;
                if (used[idx]) {
                    continue;
                }
                ItemStack stack = getStack(slot);
                if (ingredient.test(stack)) {
                    used[idx] = true;
                    matches.add(new MatchedIngredient(slot, stack));
                    found = true;
                    break;
                }
            }
            if (!found) {
                return List.of();
            }
        }
        return matches;
    }

    private void craft(DwarvenForgingRecipe recipe) {
        List<MatchedIngredient> matches = matchRecipeIngredients(recipe);
        if (matches.isEmpty()) {
            return;
        }
        int crafts = matches.stream()
                .mapToInt(m -> m.stack().getCount())
                .min()
                .orElse(0);
        if (crafts <= 0) {
            return;
        }
        int outputSlot = -1;
        for (MatchedIngredient match : matches) {
            if (match.stack().getCount() == crafts) {
                outputSlot = match.slot();
                break;
            }
        }
        if (outputSlot == -1) {
            return;
        }
        for (MatchedIngredient match : matches) {
            ItemStack stack = getStack(match.slot());
            stack.decrement(crafts);
            if (stack.isEmpty()) {
                setStack(match.slot(), ItemStack.EMPTY);
            }
        }
        ItemStack output = recipe.getOutput(null).copy();
        output.setCount(output.getCount() * crafts);
        int maxTemp = getRecipeTemperature(recipe);
        if (maxTemp > 0) {
            ItemHeatHelper.setTemperature(output, maxTemp);
        }
        setStack(outputSlot, output);
        markDirty();
    }

    private int getRecipeTemperature(DwarvenForgingRecipe recipe) {
        int max = 0;
        for (Ingredient ingredient : recipe.getIngredients()) {
            for (ItemStack stack : ingredient.getMatchingStacks()) {
                max = Math.max(
                        max,
                        ItemHeatHelper.getForgingTemperature(stack)
                );
            }
        }
        return max;
    }

    private void tryCrafting() {
        heatRecipeItems();
        Optional<DwarvenForgingRecipe> recipeOpt =
                getCurrentRecipe();
        if (recipeOpt.isEmpty()) {
            return;
        }
        DwarvenForgingRecipe recipe = recipeOpt.get();
        if (!canCraft(recipe)) {
            return;
        }
        craft(recipe);
    }

    private int getFuelTarget(ItemStack fuel) {
        if (fuel.isEmpty()) {
            return 0;
        }
        int fuelMin = ItemHeatHelper.getMinBurningTemperature(fuel);
        int fuelMax = ItemHeatHelper.getMaxBurningTemperature(fuel);
        if (fuelMax <= 1500) {
            return fuelMax;
        }
        int baseCap = getBaseTemperatureCap();
        if (baseCap <= 0) {
            return 0;
        }
        if (temperature < fuelMin) {
            return 0;
        }
        return Math.min(fuelMax, baseCap);
    }

    private int findBestFuelSlot() {
        int bestSlot = -1;
        int bestTarget = 0;
        for (int i = FUEL_START; i <= FUEL_END; i++) {
            ItemStack fuel = getStack(i);
            int target = getFuelTarget(fuel);
            if (target > bestTarget) {
                bestTarget = target;
                bestSlot = i;
            }
        }
        return bestSlot;
    }

    private void consumeFuel() {
        int slot = findBestFuelSlot();
        if (slot == -1) {
            return;
        }
        ItemStack fuel = getStack(slot);
        targetTemperature = getFuelTarget(fuel);
        fuelTime = targetTemperature;
        fuel.decrement(1);
    }

    private void heatForge() {
        temperature = Math.min(temperature + 1, targetTemperature);
        fuelTime--;
        if (fuelTime <= 0) {
            targetTemperature = 0;
        }
    }

    private void coolForge() {
        temperature = Math.max(temperature - 1, 0);
    }

    private void updateLitState() {
        if (world == null || world.isClient()) {
            return;
        }
        ForgeLit current = getCachedState().get(DwarvenForgeBlock.FIRE);
        ForgeLit target = getForgeLitState();
        if (current != target) {
            world.setBlockState(
                    pos,
                    getCachedState().with(DwarvenForgeBlock.FIRE, target),
                    Block.NOTIFY_ALL
            );
        }
    }

    private ForgeLit getForgeLitState() {
        if (temperature <= 0) {
            return ForgeLit.EXTINGUISHED;
        }
        if (temperature > 3000) {
            return ForgeLit.DRAGON;
        }
        if (temperature > 1400) {
            return ForgeLit.SOUL;
        }
        return ForgeLit.BURNING;
    }

    @Override
    public @Nullable Packet<ClientPlayPacketListener> toUpdatePacket() {
        return BlockEntityUpdateS2CPacket.create(this);
    }

    @Override
    public NbtCompound toInitialChunkDataNbt() {
        return createNbt();
    }
}