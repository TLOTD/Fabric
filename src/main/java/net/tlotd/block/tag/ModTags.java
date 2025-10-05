package net.tlotd.block.tag;

import net.minecraft.core.block.Block;
import net.minecraft.core.block.tag.BlockTags;
import net.minecraft.core.data.tag.Tag;

public class ModTags {

	public static final Tag<Block<?>> MINEABLE_BY_PAXEL = Tag.of("mineable_by_paxel");

	public void registerTags (){
		BlockTags.TAG_LIST.add(MINEABLE_BY_PAXEL);
	}
}
