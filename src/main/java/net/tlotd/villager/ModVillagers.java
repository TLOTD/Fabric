package net.tlotd.villager;

import com.google.common.collect.ImmutableSet;
import net.fabricmc.fabric.api.object.builder.v1.world.poi.PointOfInterestHelper;
import net.minecraft.block.Block;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import net.minecraft.village.VillagerProfession;
import net.minecraft.world.poi.PointOfInterestType;
import net.tlotd.TLOTD;
import net.tlotd.block.ModBlocks;

public class ModVillagers {

    public static final RegistryKey<PointOfInterestType> ARCHAEOLOGIST_POI_KEY = poiKey("archaeologistpoi");
    public static final PointOfInterestType ARCHAEOLOGIST_POI = registerPoi("archaeologistpoi", ModBlocks.ARCHAEOLOGY_TABLE);
    public static final VillagerProfession ARCHAEOLOGIST = registerProfession("archaeologist", ARCHAEOLOGIST_POI_KEY);

    public static final RegistryKey<PointOfInterestType> CULTIST_POI_KEY = poiKey("cultistpoi");
    public static final PointOfInterestType CULTIST_POI = registerPoi("cultistpoi", ModBlocks.WITCHING_TABLE);
    public static final VillagerProfession CULTIST = registerProfession("cultist", CULTIST_POI_KEY);

    public static final RegistryKey<PointOfInterestType> ENGINEER_POI_KEY = poiKey("engineerpoi");
    public static final PointOfInterestType ENGINEER_POI = registerPoi("engineerpoi", ModBlocks.MITHRIL_ANVIL);
    public static final VillagerProfession ENGINEER = registerProfession("engineer", ENGINEER_POI_KEY);

    private static VillagerProfession registerProfession(String name, RegistryKey<PointOfInterestType> type) {
        return Registry.register(Registries.VILLAGER_PROFESSION, new Identifier(TLOTD.MOD_ID, name),
                new VillagerProfession(name, entry -> entry.matchesKey(type), entry -> entry.matchesKey(type),
                        ImmutableSet.of(), ImmutableSet.of(), SoundEvents.ENTITY_VILLAGER_WORK_CARTOGRAPHER));
    }

    private static PointOfInterestType registerPoi(String name, Block block) {
        return PointOfInterestHelper.register(new Identifier(TLOTD.MOD_ID, name), 1, 1, block);
    }

    private static RegistryKey<PointOfInterestType> poiKey(String name) {
        return RegistryKey.of(RegistryKeys.POINT_OF_INTEREST_TYPE, new Identifier(TLOTD.MOD_ID, name));
    }

    public static void registerVillagers() {
        TLOTD.LOGGER.info("Registering Villagers for " + TLOTD.MOD_ID);
    }
}
