package net.tlotd.util;

import net.minecraft.advancement.criterion.Criteria;
import net.tlotd.util.triggers.RadioTrigger;

public class ModAdvancementTriggers {
    public static final HoldItemPlayerTrigger HOLD_ITEM = Criteria.register(new HoldItemPlayerTrigger());
    public static final RadioTrigger PLAY_RADIO = Criteria.register(new RadioTrigger());
    public static void registerCriteria() {
    }
}