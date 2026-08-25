package net.tlotd.util;

import com.mojang.authlib.GameProfile;
import com.mojang.brigadier.arguments.BoolArgumentType;
import com.mojang.brigadier.arguments.DoubleArgumentType;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.context.CommandContext;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.command.argument.BlockPosArgumentType;
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
import net.minecraft.text.ClickEvent;
import net.minecraft.text.HoverEvent;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.tlotd.networking.JoinDataSync;
import net.tlotd.world.CustomTextureManager;
import net.tlotd.world.ModGlobalState;
import net.tlotd.world.RadioStation;
import net.tlotd.world.SignalTrackingArray;

import java.util.Collection;
import java.util.Map;
import java.util.UUID;

public class ModCommands {
    public static void registerCommands() {
        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> {
            dispatcher.register(CommandManager.literal("tlotd").then(CommandManager.literal("temperatureUnit").executes(ctx -> {
                String temp = ItemHeatHelper.getTemperatureUnit(ctx.getSource().getPlayer()).asString();
                String globalTemp = ModGlobalState.get(ctx.getSource().getServer()).defaultTemperatureUnit().asString();
                ctx.getSource().sendFeedback(() -> Text.literal("Temperature is displayed in " + temp + ". The default is " + globalTemp + "."), false);
                return 1;
            }).then(CommandManager.literal("Celsius").executes(ctx -> {
                ItemHeatHelper.setTemperatureUnit(ctx.getSource().getPlayer(), TemperatureUnit.CELSIUS);
                ctx.getSource().sendFeedback(() -> Text.literal("Now using Celsius temperature units."), false);
                return 1;
            }).then(CommandManager.literal("setDefault").requires(src -> src.hasPermissionLevel(2)).executes(ctx -> {
                ItemHeatHelper.setGlobalTemperatureUnit(ctx.getSource().getServer(), TemperatureUnit.CELSIUS);
                JoinDataSync.syncAll(ctx.getSource().getWorld());
                ctx.getSource().sendFeedback(() -> Text.literal("Celsius is now the global default temperature unit."), false);
                return 1;
            }))).then(CommandManager.literal("Fahrenheit").executes(ctx -> {
                ItemHeatHelper.setTemperatureUnit(ctx.getSource().getPlayer(), TemperatureUnit.FAHRENHEIT);
                ctx.getSource().sendFeedback(() -> Text.literal("Now using Fahrenheit temperature units."), false);
                return 1;
            }).then(CommandManager.literal("setDefault").requires(src -> src.hasPermissionLevel(2)).executes(ctx -> {
                ItemHeatHelper.setGlobalTemperatureUnit(ctx.getSource().getServer(), TemperatureUnit.FAHRENHEIT);
                JoinDataSync.syncAll(ctx.getSource().getWorld());
                ctx.getSource().sendFeedback(() -> Text.literal("Fahrenheit is now the global default temperature unit."), false);
                return 1;
            }))).then(CommandManager.literal("Kelvin").executes(ctx -> {
                ItemHeatHelper.setTemperatureUnit(ctx.getSource().getPlayer(), TemperatureUnit.KELVIN);
                ctx.getSource().sendFeedback(() -> Text.literal("Now using Kelvin temperature units."), false);
                return 1;
            }).then(CommandManager.literal("setDefault").requires(src -> src.hasPermissionLevel(2)).executes(ctx -> {
                ItemHeatHelper.setGlobalTemperatureUnit(ctx.getSource().getServer(), TemperatureUnit.KELVIN);
                JoinDataSync.syncAll(ctx.getSource().getWorld());
                ctx.getSource().sendFeedback(() -> Text.literal("Kelvin is now the global default temperature unit."), false);
                return 1;
            }))).then(CommandManager.literal("TerraFirmaCraft").executes(ctx -> {
                ItemHeatHelper.setTemperatureUnit(ctx.getSource().getPlayer(), TemperatureUnit.TERRAFIRMACRAFT);
                ctx.getSource().sendFeedback(() -> Text.literal("Now using TerraFirmaCraft temperature names."), false);
                return 1;
            }).then(CommandManager.literal("setDefault").requires(src -> src.hasPermissionLevel(2)).executes(ctx -> {
                ItemHeatHelper.setGlobalTemperatureUnit(ctx.getSource().getServer(), TemperatureUnit.TERRAFIRMACRAFT);
                JoinDataSync.syncAll(ctx.getSource().getWorld());
                ctx.getSource().sendFeedback(() -> Text.literal("TerraFirmaCraft temperature names are now the global default temperature unit."), false);
                return 1;
            })))).then(CommandManager.literal("wiki").executes(ctx -> {
                Text message = Text.literal("https://tlotd.net/projects/minecraft/tlotd/wiki").styled(style -> style.withColor(Formatting.GOLD).withUnderline(true).withClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://tlotd.net/projects/minecraft/tlotd/wiki")).withHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, Text.translatable("tlotd.command.hover_text.open_in_browser"))));
                ctx.getSource().sendFeedback(() -> message, false);
                return 1;
            })).then(CommandManager.literal("version").executes(ctx -> {
                String version = getModVersion();
                ctx.getSource().sendFeedback(() -> Text.literal("TLOTD version: " + version), false);
                return 1;
            })).then(CommandManager.literal("debug").requires(source -> source.hasPermissionLevel(2)).executes(ctx -> {
                TelevisionSignalRegistry.debugDump();
                VideoGameRegistry.debugDump();
                ctx.getSource().sendFeedback(() -> Text.literal("Dumped TV signal & Video Game registries to console."), false);
                return 1;
            })).then(CommandManager.literal("augment").requires(source -> source.hasPermissionLevel(2)).then(CommandManager.literal("add").then(CommandManager.argument("id", StringArgumentType.string()).then(CommandManager.argument("level", IntegerArgumentType.integer(1)).executes(ModCommands::addAugment))))).then(CommandManager.literal("station").requires(source -> source.hasPermissionLevel(2)).then(CommandManager.literal("addSignal").then(CommandManager.argument("signal", RegistryEntryArgumentType.registryEntry(registryAccess, RegistryKeys.ITEM)).executes(ctx -> {
                RegistryEntry<Item> entry = RegistryEntryArgumentType.getRegistryEntry(ctx, "signal", RegistryKeys.ITEM);
                Identifier id = Registries.ITEM.getId(entry.value());
                SignalTrackingArray tracker = SignalTrackingArray.get(ctx.getSource().getWorld());
                if (!tracker.hasStations()) {
                    ctx.getSource().sendError(Text.literal("No stations exist"));
                    return 0;
                }
                tracker.addTrackToAll(id);
                ctx.getSource().sendFeedback(() -> Text.literal("Added ").append(Text.translatable(entry.value().getTranslationKey())).append(Text.literal(" to all stations")), false);

                return 1;
            })).then(CommandManager.argument("station", BlockPosArgumentType.blockPos()).then(CommandManager.argument("signal", RegistryEntryArgumentType.registryEntry(registryAccess, RegistryKeys.ITEM)).executes(ctx -> {
                BlockPos pos = BlockPosArgumentType.getBlockPos(ctx, "station");
                RegistryEntry<Item> entry = RegistryEntryArgumentType.getRegistryEntry(ctx, "signal", RegistryKeys.ITEM);
                Identifier id = Registries.ITEM.getId(entry.value());
                SignalTrackingArray tracker = SignalTrackingArray.get(ctx.getSource().getWorld());
                RadioStation station = tracker.getStation(pos);
                if (station == null) {
                    ctx.getSource().sendError(Text.literal("No station exists here"));
                    return 0;
                }
                tracker.addSignal(pos, id);
                ctx.getSource().sendFeedback(() -> Text.literal("Added ").append(Text.translatable(entry.value().getTranslationKey())).append(Text.literal(" to station " + pos.getX() + " " + pos.getY() + " " + pos.getZ())), false);
                return 1;
            })))).then(CommandManager.literal("dimensionalSignals").then(CommandManager.literal("add").then(CommandManager.argument("signal", RegistryEntryArgumentType.registryEntry(registryAccess, RegistryKeys.ITEM)).executes(ctx -> {
                RegistryEntry<Item> entry = RegistryEntryArgumentType.getRegistryEntry(ctx, "signal", RegistryKeys.ITEM);
                Item item = entry.value();
                Identifier id = Registries.ITEM.getId(item);
                SignalTrackingArray tracker = SignalTrackingArray.get(ctx.getSource().getWorld());
                if (tracker.hasDimensionSignal(id)) {
                    ctx.getSource().sendError(Text.literal("This signal is already active in this dimension: ").append(Text.translatable(item.getTranslationKey())));
                    return 0;
                }
                tracker.addDimensionSignal(id);
                ctx.getSource().sendFeedback(() -> Text.literal("Added ").append(Text.translatable(item.getTranslationKey())).append(Text.literal(" as a dimensional signal")), false);
                return 1;
            }))).then(CommandManager.literal("remove").then(CommandManager.argument("signal", RegistryEntryArgumentType.registryEntry(registryAccess, RegistryKeys.ITEM)).executes(ctx -> {
                RegistryEntry<Item> entry = RegistryEntryArgumentType.getRegistryEntry(ctx, "signal", RegistryKeys.ITEM);
                Item item = entry.value();
                Identifier id = Registries.ITEM.getId(item);
                SignalTrackingArray tracker = SignalTrackingArray.get(ctx.getSource().getWorld());
                if (!tracker.hasDimensionSignal(id)) {
                    ctx.getSource().sendError(Text.literal("This signal is not active in this dimension: ").append(Text.translatable(item.getTranslationKey())));
                    return 0;
                }
                tracker.removeDimensionSignal(id);
                ctx.getSource().sendFeedback(() -> Text.literal("Removed ").append(Text.translatable(item.getTranslationKey())).append(Text.literal(" from the dimensional signals")), false);
                return 1;
            }))).then(CommandManager.literal("clear").executes(ctx -> {
                SignalTrackingArray tracker = SignalTrackingArray.get(ctx.getSource().getWorld());
                if (!tracker.hasDimensionSignals()) {
                    ctx.getSource().sendFeedback(() -> Text.literal("There are no dimensional signals to clear"), false);
                    return 1;
                }
                int count = tracker.getDimensionSignals().size();
                tracker.clearDimensionSignals();
                ctx.getSource().sendFeedback(() -> Text.literal("Cleared " + count + " dimensional signal" + (count == 1 ? "" : "s")), false);
                return 1;
            })).then(CommandManager.literal("list").executes(ctx -> {
                SignalTrackingArray tracker = SignalTrackingArray.get(ctx.getSource().getWorld());
                if (!tracker.hasDimensionSignals()) {
                    ctx.getSource().sendFeedback(() -> Text.literal("No dimensional signals are active"), false);
                    return 1;
                }
                int count = tracker.getDimensionSignals().size();
                ctx.getSource().sendFeedback(() -> Text.literal("Dimensional signals (" + count + "):"), false);
                for (Identifier id : tracker.getDimensionSignals()) {
                    Text deleteSignal = Text.literal("\uD83D\uDDD1").formatted(Formatting.RED).styled(style -> style.withClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/tlotd station dimensionalSignals remove " + id)));
                    ctx.getSource().sendFeedback(() -> Text.literal(" ").append(deleteSignal).append(Text.literal(" | ").formatted(Formatting.WHITE).append(Text.translatable("item." + id.getNamespace() + "." + id.getPath())).append(Text.literal(": ")).append(Text.translatable("item." + id.getNamespace() + "." + id.getPath() + ".desc")).append(Text.literal(" (" + id + ")").formatted(Formatting.DARK_GRAY)).formatted(Formatting.GRAY)), false);
                }
                return 1;
            }))).then(CommandManager.literal("removeSignal").then(CommandManager.argument("signal", RegistryEntryArgumentType.registryEntry(registryAccess, RegistryKeys.ITEM)).executes(ctx -> {
                RegistryEntry<Item> entry = RegistryEntryArgumentType.getRegistryEntry(ctx, "signal", RegistryKeys.ITEM);
                Identifier id = Registries.ITEM.getId(entry.value());
                SignalTrackingArray tracker = SignalTrackingArray.get(ctx.getSource().getWorld());
                if (!tracker.hasStations()) {
                    ctx.getSource().sendError(Text.literal("No stations exist"));
                    return 0;
                }
                tracker.removeTrackFromAll(id);
                ctx.getSource().sendFeedback(() -> Text.literal("Removed ").append(Text.translatable(entry.value().getTranslationKey())).append(Text.literal(" from all stations")), false);
                return 1;
            })).then(CommandManager.argument("station", BlockPosArgumentType.blockPos()).then(CommandManager.argument("signal", RegistryEntryArgumentType.registryEntry(registryAccess, RegistryKeys.ITEM)).executes(ctx -> {
                BlockPos pos = BlockPosArgumentType.getBlockPos(ctx, "station");
                RegistryEntry<Item> entry = RegistryEntryArgumentType.getRegistryEntry(ctx, "signal", RegistryKeys.ITEM);
                Identifier id = Registries.ITEM.getId(entry.value());
                SignalTrackingArray tracker = SignalTrackingArray.get(ctx.getSource().getWorld());
                RadioStation station = tracker.getStation(pos);
                if (station == null) {
                    ctx.getSource().sendError(Text.literal("No station exists here"));
                    return 0;
                }
                tracker.removeSignal(pos, id);
                ctx.getSource().sendFeedback(() -> Text.literal("Removed ").append(Text.translatable(entry.value().getTranslationKey())).append(Text.literal(" from station " + pos.getX() + " " + pos.getY() + " " + pos.getZ())), false);
                return 1;
            })))).then(CommandManager.literal("clearSignals").executes(ctx -> {
                SignalTrackingArray tracker = SignalTrackingArray.get(ctx.getSource().getWorld());
                if (!tracker.hasStations()) {
                    ctx.getSource().sendError(Text.literal("No stations exist"));
                    return 0;
                }
                tracker.clearAllTracks();
                ctx.getSource().sendFeedback(() -> Text.literal("Cleared all signals from all stations"), true);
                return 1;
            }).then(CommandManager.argument("station", BlockPosArgumentType.blockPos()).executes(ctx -> {
                BlockPos pos = BlockPosArgumentType.getBlockPos(ctx, "station");
                RadioStation station = SignalTrackingArray.get(ctx.getSource().getWorld()).getStation(pos);
                if (station == null) {
                    ctx.getSource().sendError(Text.literal("No station exists here"));
                    return 0;
                }
                station.clearSignals();
                ctx.getSource().sendFeedback(() -> Text.literal("Cleared all signals from station " + pos.getX() + " " + pos.getY() + " " + pos.getZ()), true);
                return 1;
            }))).then(CommandManager.literal("clear").executes(ctx -> {
                SignalTrackingArray tracker = SignalTrackingArray.get(ctx.getSource().getWorld());
                if (!tracker.hasStations()) {
                    ctx.getSource().sendError(Text.literal("No stations exist"));
                    return 0;
                }
                tracker.clearStations();
                ctx.getSource().sendFeedback(() -> Text.literal("Cleared all stations"), true);
                return 1;
            }).then(CommandManager.argument("station", BlockPosArgumentType.blockPos()).executes(ctx -> {
                BlockPos pos = BlockPosArgumentType.getBlockPos(ctx, "station");
                SignalTrackingArray tracker = SignalTrackingArray.get(ctx.getSource().getWorld());
                RadioStation station = tracker.getStation(pos);
                if (station == null) {
                    ctx.getSource().sendError(Text.literal("No station exists here"));
                    return 0;
                }
                tracker.removeStation(pos);
                ctx.getSource().sendFeedback(() -> Text.literal("Cleared station " + pos.getX() + " " + pos.getY() + " " + pos.getZ()), true);
                return 1;
            }))).then(CommandManager.literal("list").executes(ctx -> {
                SignalTrackingArray tracker = SignalTrackingArray.get(ctx.getSource().getWorld());
                if (!tracker.hasStations()) {
                    ctx.getSource().sendFeedback(() -> Text.literal("No stations exist"), false);
                    return 1;
                }
                ctx.getSource().sendFeedback(() -> Text.literal("Stations (" + tracker.getStationCount() + "):"), false);
                for (RadioStation station : tracker.getStations()) {
                    Text questionMark = Text.literal("?").formatted(Formatting.YELLOW).styled(style -> style.withClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/tlotd station list " + station.getPos().getX() + " " + station.getPos().getY() + " " + station.getPos().getZ())));
                    Text delete = Text.literal("\uD83D\uDDD1").formatted(Formatting.RED).styled(style -> style.withClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/tlotd station clear " + station.getPos().getX() + " " + station.getPos().getY() + " " + station.getPos().getZ())));
                    ctx.getSource().sendFeedback(() -> Text.literal(" ").append(questionMark).append(Text.literal(" ")).append(delete).append(Text.literal(" | ").formatted(Formatting.WHITE)).append(station.getName().isEmpty() ? Text.translatable("block.tlotd.signal_transmitter.unnamed").formatted(Formatting.GRAY) : Text.literal(station.getName()).formatted(Formatting.GRAY)).append(Text.literal(" | ").formatted(Formatting.WHITE)).append(Text.literal(station.getPos().getX() + " " + station.getPos().getY() + " " + station.getPos().getZ()).formatted(Formatting.GRAY)).append(Text.literal(" | ").formatted(Formatting.WHITE)).append(station.isActive() ? Text.translatable("block.tlotd.signal_transmitter.active").formatted(Formatting.GREEN) : Text.translatable("block.tlotd.signal_transmitter.inactive").formatted(Formatting.RED)).append(Text.literal(" | ").formatted(Formatting.WHITE)).append(Text.literal("Strength: " + station.getStrength()).formatted(Formatting.GRAY)).append(Text.literal(" | ").formatted(Formatting.WHITE)).append(Text.literal("Range: " + station.getRange()).formatted(Formatting.GRAY)).append(Text.literal(" | ").formatted(Formatting.WHITE)).append(Text.literal("Signals: " + station.getSignals().size()).formatted(Formatting.GRAY)), false);
                }
                return 1;
            }).then(CommandManager.argument("pos", BlockPosArgumentType.blockPos()).executes(ctx -> {
                BlockPos pos = BlockPosArgumentType.getBlockPos(ctx, "pos");
                SignalTrackingArray tracker = SignalTrackingArray.get(ctx.getSource().getWorld());
                RadioStation station = tracker.getStation(pos);
                if (station == null) {
                    ctx.getSource().sendError(Text.literal("No station exists at " + pos));
                    return 0;
                }
                Text deleteStation = Text.literal("\uD83D\uDDD1").formatted(Formatting.RED).styled(style -> style.withClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/tlotd station clear " + station.getPos().getX() + " " + station.getPos().getY() + " " + station.getPos().getZ())));
                ctx.getSource().sendFeedback(() -> Text.literal("Station:"), false);
                ctx.getSource().sendFeedback(() -> Text.empty().append(deleteStation).append(Text.literal(" | ").formatted(Formatting.WHITE)).append(station.getName().isEmpty() ? Text.translatable("block.tlotd.signal_transmitter.unnamed").formatted(Formatting.GRAY) : Text.literal(station.getName()).formatted(Formatting.GRAY)).append(Text.literal(" | ").formatted(Formatting.WHITE)).append(Text.literal(station.getPos().getX() + " " + station.getPos().getY() + " " + station.getPos().getZ()).formatted(Formatting.GRAY).append(Text.literal(" | ").formatted(Formatting.WHITE)).append(station.isActive() ? Text.translatable("block.tlotd.signal_transmitter.active").formatted(Formatting.GREEN) : Text.translatable("block.tlotd.signal_transmitter.inactive").formatted(Formatting.RED)).append(Text.literal(" | ").formatted(Formatting.WHITE)).append(Text.literal("Strength: " + station.getStrength()).formatted(Formatting.GRAY)).append(Text.literal(" | ").formatted(Formatting.WHITE)).append(Text.literal("Range: " + station.getRange()).formatted(Formatting.GRAY)).append(Text.literal(" | ").formatted(Formatting.WHITE)).append(Text.literal("Signals: " + station.getSignals().size()).formatted(Formatting.GRAY))), false);
                if (station.getSignals().isEmpty()) {
                    ctx.getSource().sendFeedback(() -> Text.literal("No tracks uploaded"), false);
                } else {
                    ctx.getSource().sendFeedback(() -> Text.literal("Signals (" + station.getSignals().size() + "):"), false);
                    for (Identifier id : station.getSignals()) {
                        Text deleteSignal = Text.literal("\uD83D\uDDD1").formatted(Formatting.RED).styled(style -> style.withClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/tlotd station removeSignal " + station.getPos().getX() + " " + station.getPos().getY() + " " + station.getPos().getZ() + " " + id)));
                        ctx.getSource().sendFeedback(() -> Text.literal(" ").append(deleteSignal).append(Text.literal(" | ").formatted(Formatting.WHITE)).append(Text.translatable("item." + id.getNamespace() + "." + id.getPath())).append(Text.literal(": ")).append(Text.translatable("item." + id.getNamespace() + "." + id.getPath() + ".desc")).append(Text.literal(" (" + id + ")").formatted(Formatting.DARK_GRAY)).formatted(Formatting.GRAY), false);
                    }
                }
                return 1;
            }))).then(CommandManager.literal("testAt").then(CommandManager.argument("pos", BlockPosArgumentType.blockPos()).executes(ctx -> {
                BlockPos pos = BlockPosArgumentType.getBlockPos(ctx, "pos");
                SignalTrackingArray tracker = SignalTrackingArray.get(ctx.getSource().getWorld());
                RadioStation best = tracker.getBestStation(pos);
                ctx.getSource().sendFeedback(() -> Text.literal("Signal debug for " + pos.getX() + " " + pos.getY() + " " + pos.getZ()), false);
                ctx.getSource().sendFeedback(() -> Text.literal("Best station: " + (best == null ? "None" : best.getPos().getX() + " " + best.getPos().getY() + " " + best.getPos().getZ())), false);
                for (RadioStation station : tracker.getStations()) {
                    double distance = Math.sqrt(pos.getSquaredDistance(station.getPos()));
                    int range = SignalTrackingArray.getRange(station.getRange());
                    double quality = tracker.calculateSignalQuality(pos, station);
                    MutableText line = Text.literal((quality >= 0 ? "✓ " : "✗ ") + station.getPos().getX() + " " + station.getPos().getY() + " " + station.getPos().getZ() + " | Strength " + station.getStrength() + " | Range " + station.getRange() + " | Distance " + String.format("%.1f", distance) + " | Range " + range + " | Quality " + (quality >= 0 ? String.format("%.1f", quality) : "OUT OF RANGE"));
                    if (station == best) {
                        line.formatted(Formatting.GREEN);
                    } else if (quality >= 0) {
                        line.formatted(Formatting.GRAY);
                    } else {
                        line.formatted(Formatting.DARK_GRAY);
                    }
                    ctx.getSource().sendFeedback(() -> line, false);
                }
                return 1;
            })))).then(CommandManager.literal("rules").then(CommandManager.literal("easterEggs").executes(ctx -> {
                boolean rewards = ModGlobalState.get(ctx.getSource().getServer()).easterEggs();
                ctx.getSource().sendFeedback(() -> Text.literal("Easter Eggs " + (rewards ? "are" : "aren't") + " toggled on."), false);
                return 1;
            }).then(CommandManager.argument("value", BoolArgumentType.bool()).requires(src -> src.hasPermissionLevel(2)).executes(ctx -> {
                boolean value = BoolArgumentType.getBool(ctx, "value");
                ModGlobalState state = ModGlobalState.get(ctx.getSource().getServer());
                state.setEasterEggs(value);
                JoinDataSync.syncAll(ctx.getSource().getWorld());
                ctx.getSource().sendFeedback(() -> Text.literal("Easter Eggs " + (value ? "will" : "won't") + " toggled on."), true);
                return 1;
            }))).then(CommandManager.literal("strippingDropsBark").executes(ctx -> {
                boolean dropsBark = ModGlobalState.get(ctx.getSource().getServer()).strippingDropsBark();
                ctx.getSource().sendFeedback(() -> Text.literal("Stripping wood with an axe " + (dropsBark ? "dropps" : "doesn't drop") + " bark."), false);
                return 1;
            }).then(CommandManager.argument("value", BoolArgumentType.bool()).requires(src -> src.hasPermissionLevel(2)).executes(ctx -> {
                boolean value = BoolArgumentType.getBool(ctx, "value");
                ModGlobalState state = ModGlobalState.get(ctx.getSource().getServer());
                state.setStrippingDropsBark(value);
                ctx.getSource().sendFeedback(() -> Text.literal("Stripping wood with an axe " + (value ? "will" : "won't") + " drop bark."), true);
                return 1;
            }))).then(CommandManager.literal("extractionOreCompat").executes(ctx -> {
                boolean extraction = ModGlobalState.get(ctx.getSource().getServer()).extractionOreCompat();
                ctx.getSource().sendFeedback(() -> Text.literal("Experimental extraction compat " + (extraction ? "is" : "isn't") + " enabled."), false);
                return 1;
            }).then(CommandManager.argument("value", BoolArgumentType.bool()).requires(src -> src.hasPermissionLevel(2)).executes(ctx -> {
                boolean value = BoolArgumentType.getBool(ctx, "value");
                ModGlobalState state = ModGlobalState.get(ctx.getSource().getServer());
                state.setExtractionOreCompat(value);
                ctx.getSource().sendFeedback(() -> Text.literal("Experimental extraction compat " + (value ? "will" : "won't") + " be enabled."), true);
                return 1;
            }))).then(CommandManager.literal("elevatorMaxDistance").executes(ctx -> {
                int distance = ModGlobalState.get(ctx.getSource().getServer()).elevatorMaxDistance();
                ctx.getSource().sendFeedback(() -> Text.literal("The elevator can raise players up to " + distance + " blocks."), false);
                return 1;
            }).then(CommandManager.argument("value", IntegerArgumentType.integer(0, 8192)).requires(src -> src.hasPermissionLevel(2)).executes(ctx -> {
                int value = IntegerArgumentType.getInteger(ctx, "value");
                ModGlobalState state = ModGlobalState.get(ctx.getSource().getServer());
                state.setElevatorMaxDistance(value);
                ctx.getSource().sendFeedback(() -> Text.literal("The elevator will raise players up to " + value + " blocks."), true);
                return 1;
            }))).then(CommandManager.literal("starlightAnvil").executes(ctx -> {
                boolean moonlight = ModGlobalState.get(ctx.getSource().getServer()).starlightAnvil();
                ctx.getSource().sendFeedback(() -> Text.literal("Mithril Anvils " + (moonlight ? "require" : "don't require") + " direct moonlight exposure to smith."), false);
                return 1;
            }).then(CommandManager.argument("value", BoolArgumentType.bool()).requires(src -> src.hasPermissionLevel(2)).executes(ctx -> {
                boolean value = BoolArgumentType.getBool(ctx, "value");
                ModGlobalState state = ModGlobalState.get(ctx.getSource().getServer());
                state.setStarlightAnvil(value);
                JoinDataSync.syncAll(ctx.getSource().getWorld());
                ctx.getSource().sendFeedback(() -> Text.literal("Mithril Anvils now " + (value ? "will" : "won't") + " require direct moonlight exposure to smith."), true);
                return 1;
            }))).then(CommandManager.literal("bloodWitching").executes(ctx -> {
                boolean blood = ModGlobalState.get(ctx.getSource().getServer()).bloodWitching();
                ctx.getSource().sendFeedback(() -> Text.literal("Witching Tables " + (blood ? "require" : "don't require") + " blood to witch."), false);
                return 1;
            }).then(CommandManager.argument("value", BoolArgumentType.bool()).requires(src -> src.hasPermissionLevel(2)).executes(ctx -> {
                boolean value = BoolArgumentType.getBool(ctx, "value");
                ModGlobalState state = ModGlobalState.get(ctx.getSource().getServer());
                state.setBloodWitching(value);
                JoinDataSync.syncAll(ctx.getSource().getWorld());
                ctx.getSource().sendFeedback(() -> Text.literal("Witching Tables now " + (value ? "will" : "won't") + " require blood to witch."), true);
                return 1;
            }))).then(CommandManager.literal("soulWitching").executes(ctx -> {
                boolean souls = ModGlobalState.get(ctx.getSource().getServer()).soulWitching();
                ctx.getSource().sendFeedback(() -> Text.literal("Witching Tables " + (souls ? "require" : "don't require") + " souls to witch."), false);
                return 1;
            }).then(CommandManager.argument("value", BoolArgumentType.bool()).requires(src -> src.hasPermissionLevel(2)).executes(ctx -> {
                boolean value = BoolArgumentType.getBool(ctx, "value");
                ModGlobalState state = ModGlobalState.get(ctx.getSource().getServer());
                state.setSoulWitching(value);
                JoinDataSync.syncAll(ctx.getSource().getWorld());
                ctx.getSource().sendFeedback(() -> Text.literal("Witching Tables now " + (value ? "will" : "won't") + " require souls to witch."), true);
                return 1;
            }))).then(CommandManager.literal("warpHeightOutOfTerra").executes(ctx -> {
                int distance = ModGlobalState.get(ctx.getSource().getServer()).warpHeightOutOfTerra();
                ctx.getSource().sendFeedback(() -> Text.literal("Players need to be " + distance + " blocks in the air to be teleported away from the Overworld."), false);
                return 1;
            }).then(CommandManager.argument("value", IntegerArgumentType.integer(-8192, 8192)).requires(src -> src.hasPermissionLevel(2)).executes(ctx -> {
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
            }))).then(CommandManager.literal("warpHeightOutOfLuna").executes(ctx -> {
                int distance = ModGlobalState.get(ctx.getSource().getServer()).warpHeightOutOfLuna();
                ctx.getSource().sendFeedback(() -> Text.literal("Players need to be " + distance + " blocks in the air to be teleported away from the Moon."), false);
                return 1;
            }).then(CommandManager.argument("value", IntegerArgumentType.integer(-8192, 8192)).requires(src -> src.hasPermissionLevel(2)).executes(ctx -> {
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
            }))).then(CommandManager.literal("warpHeightEnteringTerra").executes(ctx -> {
                int distance = ModGlobalState.get(ctx.getSource().getServer()).warpHeightIntoTerra();
                ctx.getSource().sendFeedback(() -> Text.literal("Players get teleported to y " + distance + " when arriving in the Overworld."), false);
                return 1;
            }).then(CommandManager.argument("value", IntegerArgumentType.integer(-8192, 8192)).requires(src -> src.hasPermissionLevel(2)).executes(ctx -> {
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
            }))).then(CommandManager.literal("warpHeightEnteringLuna").executes(ctx -> {
                int distance = ModGlobalState.get(ctx.getSource().getServer()).warpHeightIntoLuna();
                ctx.getSource().sendFeedback(() -> Text.literal("Players get teleported to y " + distance + " when arriving on the Moon."), false);
                return 1;
            }).then(CommandManager.argument("value", IntegerArgumentType.integer(-8192, 8192)).requires(src -> src.hasPermissionLevel(2)).executes(ctx -> {
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
            }))).then(CommandManager.literal("terraResistance").executes(ctx -> {
                int distance = ModGlobalState.get(ctx.getSource().getServer()).terraResistance();
                ctx.getSource().sendFeedback(() -> Text.literal("Players get " + distance + " ticks of the Resistance effect when arriving in the Overworld."), false);
                return 1;
            }).then(CommandManager.argument("value", IntegerArgumentType.integer(0, 1000)).requires(src -> src.hasPermissionLevel(2)).executes(ctx -> {
                int value = IntegerArgumentType.getInteger(ctx, "value");
                ModGlobalState state = ModGlobalState.get(ctx.getSource().getServer());
                state.setTerraResistance(value);
                ctx.getSource().sendFeedback(() -> Text.literal("Players will now get " + value + " ticks of the Resistance effect when arriving in the Overworld."), true);
                return 1;
            }))).then(CommandManager.literal("vanishedRepresentativeRewards").executes(ctx -> {
                boolean rewards = ModGlobalState.get(ctx.getSource().getServer()).formerTlotdRewards();
                ctx.getSource().sendFeedback(() -> Text.literal("Vanished representatives " + (rewards ? "are" : "aren't") + " rewarded."), false);
                return 1;
            }).then(CommandManager.argument("value", BoolArgumentType.bool()).requires(src -> src.hasPermissionLevel(2)).executes(ctx -> {
                boolean value = BoolArgumentType.getBool(ctx, "value");
                ModGlobalState state = ModGlobalState.get(ctx.getSource().getServer());
                state.setFormerTlotdRewards(value);
                JoinDataSync.syncAll(ctx.getSource().getWorld());
                ctx.getSource().sendFeedback(() -> Text.literal("Vanished representatives " + (value ? "will" : "won't") + " be rewarded."), true);
                return 1;
            }))).then(CommandManager.literal("noClipChance").executes(ctx -> {
                double chance = ModGlobalState.get(ctx.getSource().getServer()).noClipChance();
                ctx.getSource().sendFeedback(() -> Text.literal("Players have a " + chance * 100 + "% chance to noclip into or out of the Backrooms when suffocating."), false);
                return 1;
            }).then(CommandManager.argument("value", DoubleArgumentType.doubleArg(0, 1)).requires(src -> src.hasPermissionLevel(2)).executes(ctx -> {
                double value = DoubleArgumentType.getDouble(ctx, "value");
                ModGlobalState state = ModGlobalState.get(ctx.getSource().getServer());
                state.setNoClipChance(value);
                ctx.getSource().sendFeedback(() -> Text.literal("Players will now have a " + value * 100 + "% chance to noclip into or out of the Backrooms when suffocating."), true);
                return 1;
            })))).then(CommandManager.literal("textureID").then(CommandManager.literal("get").executes(ctx -> {
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
            }).then(CommandManager.argument("player", GameProfileArgumentType.gameProfile()).executes(ctx -> {
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
            }))).then(CommandManager.literal("set").requires(source -> source.hasPermissionLevel(2)).then(CommandManager.argument("player", GameProfileArgumentType.gameProfile()).then(CommandManager.argument("id", IntegerArgumentType.integer(0, 127)).executes(ctx -> {
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
            })))).then(CommandManager.literal("list").requires(source -> source.hasPermissionLevel(2)).executes(ctx -> {
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
                    String name = server.getUserCache().getByUuid(entry.getKey()).map(GameProfile::getName).orElse(entry.getKey().toString());
                    source.sendFeedback(() -> Text.literal("- " + name + ": ").append(Text.literal(String.valueOf(entry.getValue())).formatted(Formatting.AQUA)), false);
                }
                return 1;
            })).then(CommandManager.literal("remove").requires(source -> source.hasPermissionLevel(2)).then(CommandManager.argument("player", GameProfileArgumentType.gameProfile()).executes(ctx -> {
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
            })))));
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
        ctx.getSource().sendFeedback(() -> Text.literal("Added augment " + id + " level " + level), false);
        return 1;
    }

    private static String getModVersion() {
        return FabricLoader.getInstance().getModContainer("tlotd").map(mod -> mod.getMetadata().getVersion().getFriendlyString()).orElse("unknown");
    }
}