package net.tlotd.util;

import com.mojang.brigadier.arguments.StringArgumentType;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.tlotd.world.SignalTrackingArray;

import java.util.Collection;
import java.util.concurrent.atomic.AtomicInteger;

public class ModCommands {
    public static void registerCommands() {
        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> {
            dispatcher.register(CommandManager.literal("signal")
                    .requires(source -> source.hasPermissionLevel(2))
                    .then(CommandManager.literal("add")
                            .then(CommandManager.argument("signal", StringArgumentType.string())
                                    .suggests((context, builder) -> {
                                        ServerWorld world = context.getSource().getWorld();
                                        SignalTrackingArray tracker = SignalTrackingArray.get(world);
                                        Collection<Item> itemsInTag = world.getRegistryManager()
                                                .get(RegistryKeys.ITEM)
                                                .streamEntries()
                                                .filter(entry -> entry.isIn(ModTags.Items.TRANSMITTABLE_SIGNALS))
                                                .map(RegistryEntry::value)
                                                .toList();
                                        for (Item item : itemsInTag) {
                                            String key = item.getTranslationKey();
                                            if (!tracker.hasSignal(key)) {
                                                builder.suggest(key);
                                            }
                                        }
                                        return builder.buildFuture();
                                    })
                                    .executes(ctx -> {
                                        ServerWorld world = ctx.getSource().getWorld();
                                        SignalTrackingArray tracker = SignalTrackingArray.get(world);
                                        String signal = StringArgumentType.getString(ctx, "signal");
                                        tracker.addSignal(signal);
                                        ctx.getSource().sendFeedback(() -> Text.literal("Added signal: " + signal), false);
                                        return 1;
                                    })
                            )
                            .then(CommandManager.literal("*")
                                    .executes(ctx -> {
                                        ServerWorld world = ctx.getSource().getWorld();
                                        SignalTrackingArray tracker = SignalTrackingArray.get(world);
                                        Collection<Item> itemsInTag = world.getRegistryManager()
                                                .get(RegistryKeys.ITEM)
                                                .streamEntries()
                                                .filter(entry -> entry.isIn(ModTags.Items.TRANSMITTABLE_SIGNALS))
                                                .map(RegistryEntry::value)
                                                .toList();
                                        AtomicInteger addedCount = new AtomicInteger(0);
                                        itemsInTag.forEach(item -> {
                                            String key = item.getTranslationKey();
                                            if (!tracker.hasSignal(key)) {
                                                tracker.addSignal(key);
                                                addedCount.getAndIncrement();
                                            }
                                        });
                                        ctx.getSource().sendFeedback(
                                                () -> Text.literal("Added " + addedCount.get() + " signals"),
                                                false
                                        );
                                        return addedCount.get();
                                    })
                            )
                    )
                    .then(CommandManager.literal("remove")
                            .then(CommandManager.argument("signal", StringArgumentType.string())
                                    .suggests((context, builder) -> {
                                        ServerWorld world = context.getSource().getWorld();
                                        SignalTrackingArray tracker = SignalTrackingArray.get(world);
                                        for (String sig : tracker.getAllSignals()) {
                                            builder.suggest(sig);
                                        }
                                        return builder.buildFuture();
                                    })
                                    .executes(ctx -> {
                                        ServerWorld world = ctx.getSource().getWorld();
                                        SignalTrackingArray tracker = SignalTrackingArray.get(world);
                                        String signal = StringArgumentType.getString(ctx, "signal");
                                        tracker.removeSignal(signal);
                                        ctx.getSource().sendFeedback(() -> Text.literal("Removed signal: " + signal), false);
                                        return 1;
                                    })
                            )
                    )
                    .then(CommandManager.literal("list")
                            .executes(ctx -> {
                                ServerWorld world = ctx.getSource().getWorld();
                                SignalTrackingArray tracker = SignalTrackingArray.get(world);
                                String list = String.join(", ", tracker.getAllSignals());
                                ctx.getSource().sendFeedback(() -> Text.literal("The " + tracker.getSignalCount() + " current signals are:" + list), false);
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
            );
        });
    }
}