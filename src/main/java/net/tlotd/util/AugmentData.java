package net.tlotd.util;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

public record AugmentData(String id, int lvl) {
    public static final Codec<AugmentData> CODEC = RecordCodecBuilder.create(instance -> instance.group(Codec.STRING.fieldOf("id").forGetter(AugmentData::id), Codec.INT.fieldOf("lvl").forGetter(AugmentData::lvl)).apply(instance, AugmentData::new));
}