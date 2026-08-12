package net.tlotd.util;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.tlotd.networking.ClientGlobalConfig;
import net.tlotd.networking.PlayerDataSyncNetworking;
import net.tlotd.networking.PlayerDataSyncPacket;
import net.tlotd.world.ModGlobalState;

import java.awt.*;

public class ItemHeatHelper {

    public static void setTemperatureUnit(ServerPlayerEntity player, TemperatureUnit unit) {
        EntityDataSaver saver = (EntityDataSaver) player;
        NbtCompound data = saver.getPersistentData();
        data.putString("TemperatureUnit", unit.asString());
        PlayerDataSyncNetworking.sendToClient(player, new PlayerDataSyncPacket(data));
    }

    public static void setGlobalTemperatureUnit(MinecraftServer server, TemperatureUnit unit) {
        ModGlobalState globalState = ModGlobalState.get(server);
        globalState.setDefaultTemperatureUnit(unit);
    }

    public static TemperatureUnit getTemperatureUnit(PlayerEntity player) {
        EntityDataSaver saver = (EntityDataSaver) player;
        NbtCompound data = saver.getPersistentData();
        TemperatureUnit unit = TemperatureUnit.fromString(data.getString("TemperatureUnit"));
        return unit != null ? unit : TemperatureUnit.fromString(ClientGlobalConfig.defaultTemperatureUnit);
    }

    public static void editTemperature(ItemStack stack, int increase) {
        int newTemp = getTemperature(stack) + increase;
        if (newTemp <= 0) {
            stack.removeSubNbt("temperature");
            return;
        }
        stack.getOrCreateNbt().putInt("temperature", newTemp);
    }

    public static void setTemperature(ItemStack stack, int value) {
        NbtCompound nbt = stack.getOrCreateNbt();
        nbt.putInt("temperature", value);
    }

    public static int getTemperature(ItemStack stack) {
        NbtCompound nbt = stack.getNbt();
        if (nbt != null && nbt.contains("temperature", NbtCompound.INT_TYPE)) {
            return nbt.getInt("temperature");
        }
        return 0;
    }

    public static boolean hasTemperature(ItemStack stack) {
        NbtCompound nbt = stack.getNbt();
        return nbt != null && nbt.contains("temperature", NbtCompound.INT_TYPE) && !(nbt.getInt("temperature") == 0);
    }

    public static int getSmithingTemperature(ItemStack stack) {
        int max = 0;
        if (stack.isIn(ModTags.Items.SMITHING_HEAT_5800)) {
            max = 5800;
        } else if (stack.isIn(ModTags.Items.SMITHING_HEAT_4200)) {
            max = 4200;
        } else if (stack.isIn(ModTags.Items.SMITHING_HEAT_2600)) {
            max = 2600;
        } else if (stack.isIn(ModTags.Items.SMITHING_HEAT_1800)) {
            max = 1800;
        }
        return max;
    }

    public static int getForgingTemperature(ItemStack stack) {
        int max = 0;
        if (stack.isIn(ModTags.Items.FORGING_HEAT_6000)) {
            max = 6000;
        } else if (stack.isIn(ModTags.Items.FORGING_HEAT_4500)) {
            max = 4500;
        } else if (stack.isIn(ModTags.Items.FORGING_HEAT_3000)) {
            max = 3000;
        } else if (stack.isIn(ModTags.Items.FORGING_HEAT_2200)) {
            max = 2200;
        }
        return max;
    }

    public static int getMinBurningTemperature(ItemStack stack) {
        int min = 0;
        if (stack.isIn(ModTags.Items.BURNS_3000)) {
            min = 1400;
        } else if (stack.isIn(ModTags.Items.BURNS_6000)) {
            min = 3000;
        }
        return min;
    }

    public static int getMaxBurningTemperature(ItemStack stack) {
        int max = 0;
        if (stack.isIn(ModTags.Items.BURNS_650)) {
            max = 650;
        } else if (stack.isIn(ModTags.Items.BURNS_1350)) {
            max = 1350;
        } else if (stack.isIn(ModTags.Items.BURNS_1400)) {
            max = 1400;
        } else if (stack.isIn(ModTags.Items.BURNS_3000)) {
            max = 3000;
        } else if (stack.isIn(ModTags.Items.BURNS_6000)) {
            max = 6000;
        }
        return max;
    }

    public static int getMinBurningBaseTemperature(ItemStack stack) {
        int min = 0;
        if (stack.isIn(ModTags.Items.FIRE_BASE_3000)) {
            min = 1400;
        } else if (stack.isIn(ModTags.Items.FIRE_BASE_6000)) {
            min = 3000;
        }
        return min;
    }

    public static int getMaxBurningBaseTemperature(ItemStack stack) {
        int max = 0;
        if (stack.isIn(ModTags.Items.FIRE_BASE_3000)) {
            max = 3000;
        } else if (stack.isIn(ModTags.Items.FIRE_BASE_6000)) {
            max = 6000;
        }
        return max;
    }

    public static int getTemperatureColor(ItemStack stack) {
        int temp = getTemperature(stack);
        return getTemperatureColor(temp);
    }

    public static int getTemperatureColor(int temp) {
        if (temp >= 5500) {
            return 0xe2deff;
        } else if (temp >= 4500) {
            return 0xff55ff;
        } else if (temp >= 3500) {
            return 0xaa00aa;
        } else if (temp >= 3000) {
            return 0x00aaaa;
        } else if (temp >= 2000) {
            return 0x55ffff;
        } else if (temp >= 1500) {
            return 0xffffff;
        } else if (temp >= 1400) {
            return 0xffffe6;
        } else if (temp >= 1300) {
            return 0xffff99;
        } else if (temp >= 1100) {
            return 0xffff00;
        } else if (temp >= 930) {
            return 0xffa500;
        } else if (temp >= 730) {
            return 0xff0000;
        } else if (temp >= 580) {
            return 0x990000;
        } else if (temp >= 480) {
            return 0xff8080;
        } else if (temp >= 210) {
            return 0xdddddd;
        }
        return 0xeeeeee;
    }

    public static Text getTemperatureText(int temp, TemperatureUnit unit) {
        if (unit == TemperatureUnit.TERRAFIRMACRAFT) {
            String TempRange;
            if (temp >= 6000) {
                TempRange = "6000";
                //return Text.translatable("temperature.tlotd.terrafirmacraft.6000.tengwar").setStyle(Style.EMPTY.withFont(new Identifier("tlotd", "tengwar")).withColor(Formatting.WHITE));
            } else if (temp >= 5900) {
                TempRange = "5900";
            } else if (temp >= 5800) {
                TempRange = "5800";
            } else if (temp >= 5700) {
                TempRange = "5700";
            } else if (temp >= 5600) {
                TempRange = "5600";
            } else if (temp >= 5500) {
                TempRange = "5500";
            } else if (temp >= 5400) {
                TempRange = "5400";
            } else if (temp >= 5300) {
                TempRange = "5300";
            } else if (temp >= 5200) {
                TempRange = "5200";
            } else if (temp >= 5100) {
                TempRange = "5100";
            } else if (temp >= 5000) {
                TempRange = "5000";
            } else if (temp >= 4900) {
                TempRange = "4900";
            } else if (temp >= 4800) {
                TempRange = "4800";
            } else if (temp >= 4700) {
                TempRange = "4700";
            } else if (temp >= 4600) {
                TempRange = "4600";
            } else if (temp >= 4500) {
                TempRange = "4500";
            } else if (temp >= 4400) {
                TempRange = "4400";
            } else if (temp >= 4300) {
                TempRange = "4300";
            } else if (temp >= 4200) {
                TempRange = "4200";
            } else if (temp >= 4100) {
                TempRange = "4100";
            } else if (temp >= 4000) {
                TempRange = "4000";
            } else if (temp >= 3900) {
                TempRange = "3900";
            } else if (temp >= 3800) {
                TempRange = "3800";
            } else if (temp >= 3700) {
                TempRange = "3700";
            } else if (temp >= 3600) {
                TempRange = "3600";
            } else if (temp >= 3500) {
                TempRange = "3500";
            } else if (temp >= 3400) {
                TempRange = "3400";
            } else if (temp >= 3300) {
                TempRange = "3300";
            } else if (temp >= 3200) {
                TempRange = "3200";
            } else if (temp >= 3100) {
                TempRange = "3100";
            } else if (temp >= 3000) {
                TempRange = "3000";
            } else if (temp >= 2900) {
                TempRange = "2900";
            } else if (temp >= 2800) {
                TempRange = "2800";
            } else if (temp >= 2700) {
                TempRange = "2700";
            } else if (temp >= 2600) {
                TempRange = "2600";
            } else if (temp >= 2500) {
                TempRange = "2500";
            } else if (temp >= 2400) {
                TempRange = "2400";
            } else if (temp >= 2300) {
                TempRange = "2300";
            } else if (temp >= 2200) {
                TempRange = "2200";
            } else if (temp >= 2100) {
                TempRange = "2100";
            } else if (temp >= 2000) {
                TempRange = "2000";
            } else if (temp >= 1900) {
                TempRange = "1900";
            } else if (temp >= 1800) {
                TempRange = "1800";
            } else if (temp >= 1700) {
                TempRange = "1700";
            } else if (temp >= 1600) {
                TempRange = "1600";
            } else if (temp >= 1500) {
                TempRange = "1500";
            } else if (temp >= 1480) {
                TempRange = "1480";
            } else if (temp >= 1460) {
                TempRange = "1460";
            } else if (temp >= 1440) {
                TempRange = "1440";
            } else if (temp >= 1420) {
                TempRange = "1420";
            } else if (temp >= 1400) {
                TempRange = "1400";
            } else if (temp >= 1380) {
                TempRange = "1380";
            } else if (temp >= 1360) {
                TempRange = "1360";
            } else if (temp >= 1340) {
                TempRange = "1340";
            } else if (temp >= 1320) {
                TempRange = "1320";
            } else if (temp >= 1300) {
                TempRange = "1300";
            } else if (temp >= 1260) {
                TempRange = "1260";
            } else if (temp >= 1220) {
                TempRange = "1220";
            } else if (temp >= 1180) {
                TempRange = "1180";
            } else if (temp >= 1140) {
                TempRange = "1140";
            } else if (temp >= 1100) {
                TempRange = "1100";
            } else if (temp >= 1066) {
                TempRange = "1066";
            } else if (temp >= 1032) {
                TempRange = "1032";
            } else if (temp >= 998) {
                TempRange = "998";
            } else if (temp >= 964) {
                TempRange = "964";
            } else if (temp >= 930) {
                TempRange = "930";
            } else if (temp >= 890) {
                TempRange = "890";
            } else if (temp >= 850) {
                TempRange = "850";
            } else if (temp >= 810) {
                TempRange = "810";
            } else if (temp >= 770) {
                TempRange = "770";
            } else if (temp >= 730) {
                TempRange = "730";
            } else if (temp >= 700) {
                TempRange = "700";
            } else if (temp >= 670) {
                TempRange = "670";
            } else if (temp >= 640) {
                TempRange = "640";
            } else if (temp >= 610) {
                TempRange = "610";
            } else if (temp >= 580) {
                TempRange = "580";
            } else if (temp >= 560) {
                TempRange = "560";
            } else if (temp >= 540) {
                TempRange = "540";
            } else if (temp >= 520) {
                TempRange = "520";
            } else if (temp >= 500) {
                TempRange = "500";
            } else if (temp >= 480) {
                TempRange = "480";
            } else if (temp >= 426) {
                TempRange = "426";
            } else if (temp >= 372) {
                TempRange = "372";
            } else if (temp >= 318) {
                TempRange = "318";
            } else if (temp >= 264) {
                TempRange = "264";
            } else if (temp >= 210) {
                TempRange = "210";
            } else if (temp >= 184) {
                TempRange = "184";
            } else if (temp >= 158) {
                TempRange = "158";
            } else if (temp >= 132) {
                TempRange = "132";
            } else if (temp >= 106) {
                TempRange = "106";
            } else if (temp >= 80) {
                TempRange = "80";
            } else if (temp >= 64) {
                TempRange = "64";
            } else if (temp >= 48) {
                TempRange = "48";
            } else if (temp >= 32) {
                TempRange = "32";
            } else if (temp >= 16) {
                TempRange = "16";
            } else {
                TempRange = "1";
            }
            return Text.translatable("temperature.tlotd.terrafirmacraft." + TempRange).setStyle(Style.EMPTY.withColor(getTemperatureColor(temp)));
        } else if (unit == TemperatureUnit.FAHRENHEIT) {
            return Text.translatable("temperature.tlotd.fahrenheit", (int) ((temp * 9.0 / 5.0) + 32)).setStyle(Style.EMPTY.withColor(getTemperatureColor(temp)));
        } else if (unit == TemperatureUnit.KELVIN) {
            return Text.translatable("temperature.tlotd.kelvin", (int) (temp + 273.15)).setStyle(Style.EMPTY.withColor(getTemperatureColor(temp)));
        } else {
            return Text.translatable("temperature.tlotd.celsius", temp).setStyle(Style.EMPTY.withColor(getTemperatureColor(temp)));
        }
    }

    public static Text getBurningTime(int temp) {
        int time = temp / 20;
        int minutes = time / 60;
        int seconds = time % 60;
        return Text.literal(String.format("%d:%02d", minutes, seconds)).formatted(Formatting.WHITE);
    }
}