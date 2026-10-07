package net.tlotd.bta.compat.btwaila;

import net.tlotd.bta.TLOTD;
import toufoumaster.btwaila.entryplugins.waila.BTWailaCustomTooltipPlugin;
import toufoumaster.btwaila.tooltips.TooltipRegistry;

import org.slf4j.Logger;

public class TLOTDBTWailaPlugin implements BTWailaCustomTooltipPlugin {
	@Override
	public void initializePlugin(TooltipRegistry tooltipRegistry, Logger logger) {
		logger.info("Loading tooltips from " + TLOTD.MOD_ID + "..");
		tooltipRegistry.register(new DwarvenForgeTooltip());
	}
}
