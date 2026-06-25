package net.tlotd.util;

import com.mojang.authlib.GameProfile;
import com.mojang.brigadier.arguments.BoolArgumentType;
import com.mojang.brigadier.arguments.DoubleArgumentType;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.context.CommandContext;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.command.argument.GameProfileArgumentType;
import net.minecraft.command.argument.RegistryEntryArgumentType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.ClickEvent;
import net.minecraft.text.HoverEvent;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import net.tlotd.networking.JoinDataSync;
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
                    .then(CommandManager.literal("temperatureUnit")
                            .executes(ctx -> {
                                String temp = ItemHeatHelper.getTemperatureUnit(ctx.getSource().getPlayer()).asString();
                                String globalTemp = ModGlobalState.get(ctx.getSource().getServer()).defaultTemperatureUnit().asString();
                                ctx.getSource().sendFeedback(() -> Text.literal("Temperature is displayed in " + temp + ". The default is " + globalTemp + "."), false);
                                return 1;
                            })
                            .then(CommandManager.literal("Celsius")
                                    .executes(ctx -> {
                                        ItemHeatHelper.setTemperatureUnit(ctx.getSource().getPlayer(), TemperatureUnit.CELSIUS);
                                        ctx.getSource().sendFeedback(() -> Text.literal("Now using Celsius temperature units."), false);
                                        return 1;
                                    })
                                    .then(CommandManager.literal("setDefault")
                                            .requires(src -> src.hasPermissionLevel(2))
                                            .executes(ctx -> {
                                                ItemHeatHelper.setGlobalTemperatureUnit(ctx.getSource().getServer(), TemperatureUnit.CELSIUS);
                                                JoinDataSync.syncAll(ctx.getSource().getWorld());
                                                ctx.getSource().sendFeedback(() -> Text.literal("Celsius is now the global default temperature unit."), false);
                                                return 1;
                                            })))
                            .then(CommandManager.literal("Fahrenheit")
                                    .executes(ctx -> {
                                        ItemHeatHelper.setTemperatureUnit(ctx.getSource().getPlayer(), TemperatureUnit.FAHRENHEIT);
                                        ctx.getSource().sendFeedback(() -> Text.literal("Now using Fahrenheit temperature units."), false);
                                        return 1;
                                    })
                                    .then(CommandManager.literal("setDefault")
                                            .requires(src -> src.hasPermissionLevel(2))
                                            .executes(ctx -> {
                                                ItemHeatHelper.setGlobalTemperatureUnit(ctx.getSource().getServer(), TemperatureUnit.FAHRENHEIT);
                                                JoinDataSync.syncAll(ctx.getSource().getWorld());
                                                ctx.getSource().sendFeedback(() -> Text.literal("Fahrenheit is now the global default temperature unit."), false);
                                                return 1;
                                            })))
                            .then(CommandManager.literal("Kelvin")
                                    .executes(ctx -> {
                                        ItemHeatHelper.setTemperatureUnit(ctx.getSource().getPlayer(), TemperatureUnit.KELVIN);
                                        ctx.getSource().sendFeedback(() -> Text.literal("Now using Kelvin temperature units."), false);
                                        return 1;
                                    }).then(CommandManager.literal("setDefault")
                                            .requires(src -> src.hasPermissionLevel(2))
                                            .executes(ctx -> {
                                                ItemHeatHelper.setGlobalTemperatureUnit(ctx.getSource().getServer(), TemperatureUnit.KELVIN);
                                                JoinDataSync.syncAll(ctx.getSource().getWorld());
                                                ctx.getSource().sendFeedback(() -> Text.literal("Kelvin is now the global default temperature unit."), false);
                                                return 1;
                                            })))
                            .then(CommandManager.literal("TerraFirmaCraft")
                                    .executes(ctx -> {
                                        ItemHeatHelper.setTemperatureUnit(ctx.getSource().getPlayer(), TemperatureUnit.TERRAFIRMACRAFT);
                                        ctx.getSource().sendFeedback(() -> Text.literal("Now using TerraFirmaCraft temperature names."), false);
                                        return 1;
                                    })
                                    .then(CommandManager.literal("setDefault")
                                            .requires(src -> src.hasPermissionLevel(2))
                                            .executes(ctx -> {
                                                ItemHeatHelper.setGlobalTemperatureUnit(ctx.getSource().getServer(), TemperatureUnit.TERRAFIRMACRAFT);
                                                JoinDataSync.syncAll(ctx.getSource().getWorld());
                                                ctx.getSource().sendFeedback(() -> Text.literal("TerraFirmaCraft temperature names are now the global default temperature unit."), false);
                                                return 1;
                                            })))
                    )
                    .then(CommandManager.literal("wiki")
                            .executes(ctx -> {
                                Text message = Text.literal("https://tlotd.net/wiki/mc-mod/")
                                        .styled(style -> style
                                                .withColor(Formatting.GOLD)
                                                .withUnderline(true)
                                                .withClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://tlotd.net/wiki/mc-mod/"))
                                                .withHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, Text.literal("Open in browser")))
                                        );
                                ctx.getSource().sendFeedback(() -> message, false);
                                return 1;
                            })
                    )
                    .then(CommandManager.literal("version")
                            .executes(ctx -> {
                                String version = getModVersion();
                                ctx.getSource().sendFeedback(() -> Text.literal("TLOTD version: " + version), false);
                                return 1;
                            })
                    )
                    .then(CommandManager.literal("debug")
                            .requires(source -> source.hasPermissionLevel(2))
                            .executes(ctx -> {
                                    TelevisionSignalRegistry.debugDump();
                                    VideoGameRegistry.debugDump();
                                    ctx.getSource().sendFeedback(() -> Text.literal("Dumped TV signal & Video Game registries to console."), false);
                                    return 1;
                            })
                    )
                    .then(CommandManager.literal("augment")
                            .requires(source -> source.hasPermissionLevel(2))
                            .then(CommandManager.literal("add")
                                    .then(CommandManager.argument("id", StringArgumentType.string())
                                            .then(CommandManager.argument("level", IntegerArgumentType.integer(1))
                                                    .executes(ModCommands::addAugment)
                                            )))
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
                    .then(CommandManager.literal("rules")
                        .then(CommandManager.literal("strippingDropsBark")
                                .executes(ctx -> {
                                    boolean dropsBark = ModGlobalState.get(ctx.getSource().getServer()).strippingDropsBark();
                                    ctx.getSource().sendFeedback(() -> Text.literal("Stripping wood with an axe " + (dropsBark ? "dropps" : "doesn't drop") + " bark."), false);
                                    return 1;
                                })
                                .then(CommandManager.argument("value", BoolArgumentType.bool())
                                        .requires(src -> src.hasPermissionLevel(2))
                                        .executes(ctx -> {
                                            boolean value = BoolArgumentType.getBool(ctx, "value");
                                            ModGlobalState state = ModGlobalState.get(ctx.getSource().getServer());
                                            state.setStrippingDropsBark(value);
                                            ctx.getSource().sendFeedback(() -> Text.literal("Stripping wood with an axe " + (value ? "will" : "won't") + " drop bark."), true);
                                            return 1;
                                        })
                                )
                        )
                        .then(CommandManager.literal("extractionOreCompat")
                                .executes(ctx -> {
                                    boolean extraction = ModGlobalState.get(ctx.getSource().getServer()).extractionOreCompat();
                                    ctx.getSource().sendFeedback(() -> Text.literal("Experimental extraction compat " + (extraction ? "is" : "isn't") + " enabled."), false);
                                    return 1;
                                })
                                .then(CommandManager.argument("value", BoolArgumentType.bool())
                                        .requires(src -> src.hasPermissionLevel(2))
                                        .executes(ctx -> {
                                            boolean value = BoolArgumentType.getBool(ctx, "value");
                                            ModGlobalState state = ModGlobalState.get(ctx.getSource().getServer());
                                            state.setExtractionOreCompat(value);
                                            ctx.getSource().sendFeedback(() -> Text.literal("Experimental extraction compat " + (value ? "will" : "won't") + " be enabled."), true);
                                            return 1;
                                        })
                                )
                        )
                        .then(CommandManager.literal("elevatorMaxDistance")
                                .executes(ctx -> {
                                    int distance = ModGlobalState.get(ctx.getSource().getServer()).elevatorMaxDistance();
                                    ctx.getSource().sendFeedback(() -> Text.literal("The elevator can raise players up to " + distance + " blocks."), false);
                                    return 1;
                                })
                                .then(CommandManager.argument("value", IntegerArgumentType.integer(0, 8192))
                                        .requires(src -> src.hasPermissionLevel(2))
                                        .executes(ctx -> {
                                            int value = IntegerArgumentType.getInteger(ctx, "value");
                                            ModGlobalState state = ModGlobalState.get(ctx.getSource().getServer());
                                            state.setElevatorMaxDistance(value);
                                            ctx.getSource().sendFeedback(() -> Text.literal("The elevator will raise players up to " + value + " blocks."), true);
                                            return 1;
                                        })
                                )
                        )
                        .then(CommandManager.literal("starlightAnvil")
                                .executes(ctx -> {
                                    boolean moonlight = ModGlobalState.get(ctx.getSource().getServer()).starlightAnvil();
                                    ctx.getSource().sendFeedback(() -> Text.literal("Mithril Anvils " + (moonlight ? "require" : "don't require") + " direct moonlight exposure to smith."), false);
                                    return 1;
                                })
                                .then(CommandManager.argument("value", BoolArgumentType.bool())
                                        .requires(src -> src.hasPermissionLevel(2))
                                        .executes(ctx -> {
                                            boolean value = BoolArgumentType.getBool(ctx, "value");
                                            ModGlobalState state = ModGlobalState.get(ctx.getSource().getServer());
                                            state.setStarlightAnvil(value);
                                            JoinDataSync.syncAll(ctx.getSource().getWorld());
                                            ctx.getSource().sendFeedback(() -> Text.literal("Mithril Anvils now " + (value ? "will" : "won't") + " require direct moonlight exposure to smith."), true);
                                            return 1;
                                        })
                                )
                        )
                        .then(CommandManager.literal("bloodWitching")
                                .executes(ctx -> {
                                    boolean blood = ModGlobalState.get(ctx.getSource().getServer()).bloodWitching();
                                    ctx.getSource().sendFeedback(() -> Text.literal("Witching Tables " + (blood ? "require" : "don't require") + " blood to witch."), false);
                                    return 1;
                                })
                                .then(CommandManager.argument("value", BoolArgumentType.bool())
                                        .requires(src -> src.hasPermissionLevel(2))
                                        .executes(ctx -> {
                                            boolean value = BoolArgumentType.getBool(ctx, "value");
                                            ModGlobalState state = ModGlobalState.get(ctx.getSource().getServer());
                                            state.setBloodWitching(value);
                                            JoinDataSync.syncAll(ctx.getSource().getWorld());
                                            ctx.getSource().sendFeedback(() -> Text.literal("Witching Tables now " + (value ? "will" : "won't") + " require blood to witch."), true);
                                            return 1;
                                        })
                                )
                        )
                        .then(CommandManager.literal("soulWitching")
                                .executes(ctx -> {
                                    boolean souls = ModGlobalState.get(ctx.getSource().getServer()).soulWitching();
                                    ctx.getSource().sendFeedback(() -> Text.literal("Witching Tables " + (souls ? "require" : "don't require") + " souls to witch."), false);
                                    return 1;
                                })
                                .then(CommandManager.argument("value", BoolArgumentType.bool())
                                        .requires(src -> src.hasPermissionLevel(2))
                                        .executes(ctx -> {
                                            boolean value = BoolArgumentType.getBool(ctx, "value");
                                            ModGlobalState state = ModGlobalState.get(ctx.getSource().getServer());
                                            state.setSoulWitching(value);
                                            JoinDataSync.syncAll(ctx.getSource().getWorld());
                                            ctx.getSource().sendFeedback(() -> Text.literal("Witching Tables now " + (value ? "will" : "won't") + " require souls to witch."), true);
                                            return 1;
                                        })
                                )
                        )
                        .then(CommandManager.literal("warpHeightOutOfTerra")
                            .executes(ctx -> {
                                int distance = ModGlobalState.get(ctx.getSource().getServer()).warpHeightOutOfTerra();
                                ctx.getSource().sendFeedback(() -> Text.literal("Players need to be " + distance + " blocks in the air to be teleported away from the Overworld."), false);
                                    return 1;
                                })
                                .then(CommandManager.argument("value", IntegerArgumentType.integer(-8192, 8192))
                                        .requires(src -> src.hasPermissionLevel(2))
                                        .executes(ctx -> {
                                            int value = IntegerArgumentType.getInteger(ctx, "value");
                                            ModGlobalState state = ModGlobalState.get(ctx.getSource().getServer());
                                            int into = ModGlobalState.get(ctx.getSource().getServer()).warpHeightIntoTerra();
                                            if (value > into) {
                                                state.setWarpHeightOutOfTerra(value);
                                                ctx.getSource().sendFeedback(() -> Text.literal("Players will now need to be " + value + " blocks in the air to be teleported away from the Overworld."), true);
                                                return 1;
                                            } else {
                                                ctx.getSource().sendFeedback(() -> Text.literal("Players will need to be more than " + into + " blocks in the air to be teleported away from the Overworld to prevent teleportation loops"), false);
                                                return 0;
                                            }
                                        })
                                )
                        )
                        .then(CommandManager.literal("warpHeightOutOfLuna")
                                .executes(ctx -> {
                                    int distance = ModGlobalState.get(ctx.getSource().getServer()).warpHeightOutOfLuna();
                                    ctx.getSource().sendFeedback(() -> Text.literal("Players need to be " + distance + " blocks in the air to be teleported away from the Moon."), false);
                                    return 1;
                                })
                                .then(CommandManager.argument("value", IntegerArgumentType.integer(-8192, 8192))
                                        .requires(src -> src.hasPermissionLevel(2))
                                        .executes(ctx -> {
                                            int value = IntegerArgumentType.getInteger(ctx, "value");
                                            ModGlobalState state = ModGlobalState.get(ctx.getSource().getServer());
                                            int into = ModGlobalState.get(ctx.getSource().getServer()).warpHeightIntoLuna();
                                            if (value > into) {
                                                state.setWarpHeightOutOfLuna(value);
                                                ctx.getSource().sendFeedback(() -> Text.literal("Players will now need to be " + value + " blocks in the air to be teleported away from the Moon."), true);
                                                return 1;
                                            } else {
                                                ctx.getSource().sendFeedback(() -> Text.literal("Players will need to be more than " + into + " blocks in the air to be teleported away from the Moon to prevent teleportation loops"), false);
                                                return 0;
                                            }
                                        })
                                )
                        )
                        .then(CommandManager.literal("WarpHeightEnteringTerra")
                                .executes(ctx -> {
                                    int distance = ModGlobalState.get(ctx.getSource().getServer()).warpHeightIntoTerra();
                                    ctx.getSource().sendFeedback(() -> Text.literal("Players get teleported to y " + distance + " when arriving in the Overworld."), false);
                                    return 1;
                                })
                                .then(CommandManager.argument("value", IntegerArgumentType.integer(-8192, 8192))
                                        .requires(src -> src.hasPermissionLevel(2))
                                        .executes(ctx -> {
                                            int value = IntegerArgumentType.getInteger(ctx, "value");
                                            ModGlobalState state = ModGlobalState.get(ctx.getSource().getServer());
                                            int outOf = ModGlobalState.get(ctx.getSource().getServer()).warpHeightOutOfTerra();
                                            if (value < outOf) {
                                                state.setWarpHeightIntoTerra(value);
                                                ctx.getSource().sendFeedback(() -> Text.literal("Players will now get teleported to y " + value + " when arriving in the Overworld."), true);
                                                return 1;
                                            } else {
                                                ctx.getSource().sendFeedback(() -> Text.literal("Players will need to arrive less than y " + outOf + " when arriving in the Overworld to prevent teleportation loops."), false);
                                                return 0;
                                            }
                                        })
                                )
                        )
                        .then(CommandManager.literal("warpHeightEnteringLuna")
                                .executes(ctx -> {
                                    int distance = ModGlobalState.get(ctx.getSource().getServer()).warpHeightIntoLuna();
                                    ctx.getSource().sendFeedback(() -> Text.literal("Players get teleported to y " + distance + " when arriving on the Moon."), false);
                                    return 1;
                                })
                                .then(CommandManager.argument("value", IntegerArgumentType.integer(-8192, 8192))
                                        .requires(src -> src.hasPermissionLevel(2))
                                        .executes(ctx -> {
                                            int value = IntegerArgumentType.getInteger(ctx, "value");
                                            ModGlobalState state = ModGlobalState.get(ctx.getSource().getServer());
                                            int outOf = ModGlobalState.get(ctx.getSource().getServer()).warpHeightOutOfLuna();
                                            if (value < outOf) {
                                                state.setWarpHeightIntoLuna(value);
                                                ctx.getSource().sendFeedback(() -> Text.literal("Players will now get teleported to y " + value + " when arriving on the Moon."), true);
                                                return 1;
                                            } else {
                                                ctx.getSource().sendFeedback(() -> Text.literal("Players will need to arrive less than y " + outOf + " when arriving on the Moon to prevent teleportation loops."), false);
                                                return 0;
                                            }
                                        })
                                )
                        )
                        .then(CommandManager.literal("terraResistance")
                                .executes(ctx -> {
                                    int distance = ModGlobalState.get(ctx.getSource().getServer()).terraResistance();
                                    ctx.getSource().sendFeedback(() -> Text.literal("Players get " + distance + " ticks of the Resistance effect when arriving in the Overworld."), false);
                                    return 1;
                                })
                                .then(CommandManager.argument("value", IntegerArgumentType.integer(0, 1000))
                                        .requires(src -> src.hasPermissionLevel(2))
                                        .executes(ctx -> {
                                            int value = IntegerArgumentType.getInteger(ctx, "value");
                                            ModGlobalState state = ModGlobalState.get(ctx.getSource().getServer());
                                            state.setTerraResistance(value);
                                            ctx.getSource().sendFeedback(() -> Text.literal("Players will now get " + value + " ticks of the Resistance effect when arriving in the Overworld."), true);
                                            return 1;
                                        })
                                )
                        )
                        .then(CommandManager.literal("vanishedRepresentativeRewards")
                                .executes(ctx -> {
                                    boolean rewards = ModGlobalState.get(ctx.getSource().getServer()).formerTlotdRewards();
                                    ctx.getSource().sendFeedback(() -> Text.literal("Vanished representatives " + (rewards ? "are" : "aren't") + " rewarded."), false);
                                    return 1;
                                })
                                .then(CommandManager.argument("value", BoolArgumentType.bool())
                                        .requires(src -> src.hasPermissionLevel(2))
                                        .executes(ctx -> {
                                              boolean value = BoolArgumentType.getBool(ctx, "value");
                                              ModGlobalState state = ModGlobalState.get(ctx.getSource().getServer());
                                              state.setFormerTlotdRewards(value);
                                              JoinDataSync.syncAll(ctx.getSource().getWorld());
                                              ctx.getSource().sendFeedback(() -> Text.literal("Vanished representatives " + (value ? "will" : "won't") + " be rewarded."), true);
                                              return 1;
                                        })
                                )
                        )
                        .then(CommandManager.literal("noClipChance")
                                .executes(ctx -> {
                                    double chance = ModGlobalState.get(ctx.getSource().getServer()).noClipChance();
                                    ctx.getSource().sendFeedback(() -> Text.literal("Players have a " + chance*100 + "% chance to noclip into or out of the Backrooms when suffocating."), false);
                                    return 1;
                                })
                                .then(CommandManager.argument("value", DoubleArgumentType.doubleArg(0, 1))
                                        .requires(src -> src.hasPermissionLevel(2))
                                        .executes(ctx -> {
                                            double value = DoubleArgumentType.getDouble(ctx, "value");
                                            ModGlobalState state = ModGlobalState.get(ctx.getSource().getServer());
                                            state.setNoClipChance(value);
                                            ctx.getSource().sendFeedback(() -> Text.literal("Players will now have a " + value*100 + "% chance to noclip into or out of the Backrooms when suffocating."), true);
                                            return 1;
                                        })
                                )
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
                                        source.sendFeedback(() -> Text.literal("Your custom texture ID is " + id + "."), false);
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
                                                    source.sendFeedback(() -> Text.literal(profile.getName() + " has custom texture ID " + id + " assigned."), false);
                                                } else {
                                                    source.sendFeedback(() -> Text.literal(profile.getName() + " has no custom texture ID assigned."), false);
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
                                                    JoinDataSync.syncAll(ctx.getSource().getWorld());
                                                    source.sendFeedback(() -> Text.literal("Set custom texture ID to " + id + " for " + profiles.size() + " player(s)."), true);
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
                                        source.sendFeedback(() -> Text.literal("- " + name + ": ").append(Text.literal(String.valueOf(entry.getValue())).formatted(Formatting.AQUA)), false);
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
                                                    JoinDataSync.syncAll(ctx.getSource().getWorld());
                                                    source.sendFeedback(() -> Text.literal("Removed custom texture ID for " + profile.getName() + "."), true);
                                                } else {
                                                    source.sendFeedback(() -> Text.literal(profile.getName() + " had no custom texture ID assigned."), false);
                                                }
                                            }
                                            if (removedCount[0] == 0) {
                                                source.sendFeedback(() -> Text.literal("No entries were removed."), false);
                                            } else if (removedCount[0] > 1) {
                                                int finalCount = removedCount[0];
                                                source.sendFeedback(() -> Text.literal("Removed " + finalCount + " player entries."), true);
                                            }
                                            return 1;
                                        })
                                )
                        )
                    )
            );
        });
    }

    private static int addAugment(CommandContext<ServerCommandSource> ctx) {
        ServerPlayerEntity player = ctx.getSource().getPlayer();
        ItemStack stack = player.getMainHandStack();
        if (stack.isEmpty()) {
            ctx.getSource().sendError(Text.literal("Hold an item first."));
            return 0;
        }
        String id = StringArgumentType.getString(ctx, "id");
        int level = IntegerArgumentType.getInteger(ctx, "level");
        AugmentNbtHelper.addOrUpdateAugment(stack, id, level, 127);
        ctx.getSource().sendFeedback(
                () -> Text.literal("Added augment " + id + " level " + level),
                false
        );
        return 1;
    }

    private static String getModVersion() {
        return FabricLoader.getInstance()
                .getModContainer("tlotd")
                .map(mod -> mod.getMetadata().getVersion().getFriendlyString())
                .orElse("unknown");
    }
}