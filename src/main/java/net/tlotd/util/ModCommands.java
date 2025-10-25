package net.tlotd.util;

import com.mojang.authlib.GameProfile;
import com.mojang.brigadier.arguments.BoolArgumentType;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.command.argument.GameProfileArgumentType;
import net.minecraft.command.argument.RegistryEntryArgumentType;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import net.tlotd.world.CustomTextureManager;
import net.tlotd.world.ModGlobalState;
import net.tlotd.world.SignalTrackingArray;

import java.util.Collection;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

public class ModCommands {
    public static void registerCommands() {
        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> {
            dispatcher.register(CommandManager.literal("tlotd")
                            .then(CommandManager.literal("debug")
                                    .requires(source -> source.hasPermissionLevel(2))
                                    .executes(context -> {
                                        TelevisionSignalRegistry.debugDump();
                                        VideoGameRegistry.debugDump();
                                        context.getSource().sendFeedback(() -> Text.literal("Dumped TV signal & Video Game registries to console."), false);
                                        return 1;
                                    })
                            )
                    .then(CommandManager.literal("signal")
                            .requires(source -> source.hasPermissionLevel(2))
                            .then(CommandManager.literal("add")
                                    .then(CommandManager.argument("signal", RegistryEntryArgumentType.registryEntry(registryAccess, RegistryKeys.ITEM))
                                            .executes(ctx -> {
                                                RegistryEntry<Item> entry = RegistryEntryArgumentType.getRegistryEntry(ctx, "signal", RegistryKeys.ITEM);
                                                Item item = entry.value();
                                                ServerWorld world = ctx.getSource().getWorld();
                                                SignalTrackingArray tracker = SignalTrackingArray.get(world);
                                                tracker.addSignal(item);
                                                ctx.getSource().sendFeedback(() -> Text.literal("Added signal: " + item.getTranslationKey()), false);
                                                return 1;
                                            })
                                    )
                            )
                            .then(CommandManager.literal("remove")
                                    .then(CommandManager.argument("signal", RegistryEntryArgumentType.registryEntry(registryAccess, RegistryKeys.ITEM))
                                            .executes(ctx -> {
                                                RegistryEntry<Item> entry = RegistryEntryArgumentType.getRegistryEntry(ctx, "signal", RegistryKeys.ITEM);
                                                Item item = entry.value();
                                                Identifier id = Registries.ITEM.getId(item);
                                                ServerWorld world = ctx.getSource().getWorld();
                                                SignalTrackingArray tracker = SignalTrackingArray.get(world);
                                                if (!tracker.hasSignal(id)) {
                                                    ctx.getSource().sendError(Text.literal("No such signal is currently active: " + id));
                                                    return 0;
                                                }
                                                tracker.removeSignal(id);
                                                ctx.getSource().sendFeedback(() ->
                                                        Text.literal("Removed signal: ").append(Text.translatable(item.getTranslationKey())), false
                                                );
                                                return 1;
                                            })
                                    )
                            )
                            .then(CommandManager.literal("list")
                                    .executes(ctx -> {
                                        ServerWorld world = ctx.getSource().getWorld();
                                        SignalTrackingArray tracker = SignalTrackingArray.get(world);
                                        String list = tracker.getAllSignals().stream()
                                                .map(Identifier::toString)
                                                .collect(Collectors.joining(", "));
                                        ctx.getSource().sendFeedback(() -> Text.literal("The " + tracker.getSignalCount() + " current signals are: " + list), false);
                                        return 1;
                                    })
                            )
                            .then(CommandManager.literal("clear")
                                    .executes(ctx -> {
                                        ServerWorld world = ctx.getSource().getWorld();
                                        SignalTrackingArray tracker = SignalTrackingArray.get(world);
                                        tracker.clearSignals();
                                        ctx.getSource().sendFeedback(() -> Text.literal("Cleared all signals"), false);
                                        return 1;
                                    })
                            )
                    )
                    .then(CommandManager.literal("strippingDropsBark")
                            .executes(ctx -> {
                                boolean dropsBark = ModGlobalState.get(ctx.getSource().getServer()).strippingDropsBark();
                                ctx.getSource().sendFeedback(() ->
                                        Text.literal("Stripping wood with an axe " + (dropsBark ? "dropps" : "doesn't drop") + " bark."), false);
                                return 1;
                            })
                            .then(CommandManager.argument("value", BoolArgumentType.bool())
                                    .requires(src -> src.hasPermissionLevel(2))
                                    .executes(ctx -> {
                                        boolean value = BoolArgumentType.getBool(ctx, "value");
                                        ModGlobalState state = ModGlobalState.get(ctx.getSource().getServer());
                                        state.setStrippingDropsBark(value);
                                        ctx.getSource().sendFeedback(() ->
                                                Text.literal("Stripping wood with an axe " + (value ? "will" : "won't") + " drop bark."), true);
                                        return 1;
                                    })
                            )
                    )
                    .then(CommandManager.literal("extractionOreCompat")
                            .executes(ctx -> {
                                boolean extraction = ModGlobalState.get(ctx.getSource().getServer()).extractionOreCompat();
                                ctx.getSource().sendFeedback(() ->
                                        Text.literal("Experimental extraction compat " + (extraction ? "is" : "isn't") + " enabled."), false);
                                return 1;
                            })
                            .then(CommandManager.argument("value", BoolArgumentType.bool())
                                    .requires(src -> src.hasPermissionLevel(2))
                                    .executes(ctx -> {
                                        boolean value = BoolArgumentType.getBool(ctx, "value");
                                        ModGlobalState state = ModGlobalState.get(ctx.getSource().getServer());
                                        state.setExtractionOreCompat(value);
                                        ctx.getSource().sendFeedback(() ->
                                                Text.literal("Experimental extraction compat " + (value ? "will" : "won't") + " be enabled."), true);
                                        return 1;
                                    })
                            )
                    )
                    .then(CommandManager.literal("elevatorMaxDistance")
                            .executes(ctx -> {
                                int distance = ModGlobalState.get(ctx.getSource().getServer()).elevatorMaxDistance();
                                ctx.getSource().sendFeedback(() ->
                                        Text.literal("The elevator can raise players up to " + distance + " blocks."), false);
                                return 1;
                            })
                            .then(CommandManager.argument("value", IntegerArgumentType.integer(0, 1000))
                                    .requires(src -> src.hasPermissionLevel(2))
                                    .executes(ctx -> {
                                        int value = IntegerArgumentType.getInteger(ctx, "value");
                                        ModGlobalState state = ModGlobalState.get(ctx.getSource().getServer());
                                        state.setElevatorMaxDistance(value);
                                        ctx.getSource().sendFeedback(() ->
                                                Text.literal("The elevator will raise players up to " + value + " blocks."), true);
                                        return 1;
                                    })
                            )
                    )
                    .then(CommandManager.literal("vanishedRepresentativeRewards")
                            .executes(ctx -> {
                                boolean rewards = ModGlobalState.get(ctx.getSource().getServer()).formerTlotdRewards();
                                ctx.getSource().sendFeedback(() ->
                                        Text.literal("Vanished representatives " + (rewards ? "are" : "aren't") + " rewarded."), false);
                                return 1;
                            })
                            .then(CommandManager.argument("value", BoolArgumentType.bool())
                                    .requires(src -> src.hasPermissionLevel(2))
                                    .executes(ctx -> {
                                          boolean value = BoolArgumentType.getBool(ctx, "value");
                                          ModGlobalState state = ModGlobalState.get(ctx.getSource().getServer());
                                          state.setFormerTlotdRewards(value);
                                          ctx.getSource().sendFeedback(() ->
                                                  Text.literal("Vanished representatives " + (value ? "will" : "won't") + " be rewarded."), true);
                                          return 1;
                                    })
                            )
                    )
                    .then(CommandManager.literal("textureID")
                        .then(CommandManager.literal("get")
                                .executes(ctx -> {
                                    ServerCommandSource source = ctx.getSource();
                                    ServerPlayerEntity player = source.getPlayer();
                                    if (player == null) {
                                        source.sendError(Text.literal("You must be a player to use this without arguments."));
                                        return 0;
                                    }
                                    CustomTextureManager manager = CustomTextureManager.get(source.getServer());
                                    int id = manager.getTexture(player.getUuid());
                                    if (id >= 0) {
                                        source.sendFeedback(() ->
                                                Text.literal("Your custom texture ID is ").append(Text.literal(String.valueOf(id)).formatted(Formatting.AQUA)), false);
                                    } else {
                                        source.sendFeedback(() -> Text.literal("You don’t have a custom texture ID assigned."), false);
                                    }
                                    return 1;
                                })
                                .then(CommandManager.argument("player", GameProfileArgumentType.gameProfile())
                                        .executes(ctx -> {
                                            ServerCommandSource source = ctx.getSource();
                                            Collection<GameProfile> profiles = GameProfileArgumentType.getProfileArgument(ctx, "player");
                                            CustomTextureManager manager = CustomTextureManager.get(source.getServer());
                                            for (GameProfile profile : profiles) {
                                                int id = manager.getTexture(profile.getId());
                                                if (id >= 0) {
                                                    source.sendFeedback(() ->
                                                            Text.literal("🎨 ").append(Text.literal(profile.getName() + " → ID " + id)
                                                                    .formatted(Formatting.AQUA)), false);
                                                } else {
                                                    source.sendFeedback(() ->
                                                            Text.literal(profile.getName() + " has no assigned texture ID."), false);
                                                }
                                            }
                                            return 1;
                                        })
                                )
                        )
                        .then(CommandManager.literal("set")
                                .requires(source -> source.hasPermissionLevel(2))
                                .then(CommandManager.argument("player", GameProfileArgumentType.gameProfile())
                                        .then(CommandManager.argument("id", IntegerArgumentType.integer(0, 127))
                                                .executes(ctx -> {
                                                    ServerCommandSource source = ctx.getSource();
                                                    Collection<GameProfile> profiles = GameProfileArgumentType.getProfileArgument(ctx, "player");
                                                    int id = IntegerArgumentType.getInteger(ctx, "id");
                                                    CustomTextureManager manager = CustomTextureManager.get(source.getServer());
                                                    for (GameProfile profile : profiles) {
                                                        manager.setTexture(profile.getId(), id);
                                                    }
                                                    source.sendFeedback(() ->
                                                            Text.literal("Set custom texture ID to " + id + " for " + profiles.size() + " player(s)."), true);
                                                    return 1;
                                                })
                                        )
                                )
                        )
                        .then(CommandManager.literal("list")
                                .requires(source -> source.hasPermissionLevel(2))
                                .executes(ctx -> {
                                    ServerCommandSource source = ctx.getSource();
                                    CustomTextureManager manager = CustomTextureManager.get(source.getServer());
                                    Map<UUID, Integer> skins = manager.getAll();
                                    if (skins.isEmpty()) {
                                        source.sendFeedback(() -> Text.literal("No custom texture IDs have been assigned yet."), false);
                                        return 1;
                                    }
                                    source.sendFeedback(() -> Text.literal("Assigned custom texture IDs:").formatted(Formatting.GOLD), false);
                                    MinecraftServer server = source.getServer();
                                    for (Map.Entry<UUID, Integer> entry : skins.entrySet()) {
                                        String name = server.getUserCache().getByUuid(entry.getKey())
                                                .map(GameProfile::getName)
                                                .orElse(entry.getKey().toString());
                                        source.sendFeedback(() ->
                                                        Text.literal("- " + name + ": ").append(Text.literal(String.valueOf(entry.getValue())).formatted(Formatting.AQUA)),
                                                false);
                                    }
                                    return 1;
                                })
                        )
                        .then(CommandManager.literal("remove")
                                .requires(source -> source.hasPermissionLevel(2))
                                .then(CommandManager.argument("player", GameProfileArgumentType.gameProfile())
                                        .executes(ctx -> {
                                            ServerCommandSource source = ctx.getSource();
                                            Collection<GameProfile> profiles = GameProfileArgumentType.getProfileArgument(ctx, "player");
                                            CustomTextureManager manager = CustomTextureManager.get(source.getServer());
                                            int[] removedCount = {0};
                                            for (GameProfile profile : profiles) {
                                                if (manager.removeTexture(profile.getId())) {
                                                    removedCount[0]++;
                                                    source.sendFeedback(
                                                            () -> Text.literal("Removed custom texture ID for " + profile.getName() + "."),
                                                            true
                                                    );
                                                } else {
                                                    source.sendFeedback(
                                                            () -> Text.literal(profile.getName() + " had no custom texture ID assigned."),
                                                            false
                                                    );
                                                }
                                            }
                                            if (removedCount[0] == 0) {
                                                source.sendFeedback(() -> Text.literal("No entries were removed."), false);
                                            } else if (removedCount[0] > 1) {
                                                int finalCount = removedCount[0];
                                                source.sendFeedback(
                                                        () -> Text.literal("Removed " + finalCount + " player entries."),
                                                        true
                                                );
                                            }
                                            return 1;
                                        })
                                )
                        )
                    )
            );
        });
    }
}