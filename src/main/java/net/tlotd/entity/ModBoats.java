package net.tlotd.entity;

import com.terraformersmc.terraform.boat.api.TerraformBoatType;
import com.terraformersmc.terraform.boat.api.TerraformBoatTypeRegistry;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.Identifier;
import net.tlotd.TLOTD;
import net.tlotd.block.ModBlocks;
import net.tlotd.item.ModItems;

public class ModBoats {
    public static final Identifier GINKGO_BOAT_ID = new Identifier(TLOTD.MOD_ID, "ginkgo_boat");
    public static final Identifier GINKGO_CHEST_BOAT_ID = new Identifier(TLOTD.MOD_ID, "ginkgo_chest_boat");

    public static final RegistryKey<TerraformBoatType> GINKGO_BOAT_KEY = TerraformBoatTypeRegistry.createKey(GINKGO_BOAT_ID);

    public static void registerBoats() {
        TerraformBoatType ginkgoBoat = new TerraformBoatType.Builder()
                .item(ModItems.GINKGO_BOAT)
                .chestItem(ModItems.GINKGO_CHEST_BOAT)
                .planks(ModBlocks.GINKGO_PLANKS.asItem())
                .build();

        Registry.register(TerraformBoatTypeRegistry.INSTANCE, GINKGO_BOAT_KEY, ginkgoBoat);
    }
}
