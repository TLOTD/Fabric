package net.tlotd.entity.client.variant;

import net.minecraft.util.StringIdentifiable;
import net.minecraft.util.Util;
import net.minecraft.util.function.ValueLists;
import net.minecraft.util.math.random.Random;

import java.util.Arrays;
import java.util.function.IntFunction;

public enum TRexVariant implements StringIdentifiable {
    BROWN(0, "brown", true),
    GREEN(1, "green", true),
    GRAY(2, "gray", true),
    SCULK(3, "cyan", false),
    INFECTED(4, "blue", false),
    SICKENED(5, "blue", false);

    private static final IntFunction<TRexVariant> BY_ID;
    public static final Codec<TRexVariant> CODEC;
    private final int id;
    private final String name;
    private final boolean natural;

    TRexVariant(int id, String name, boolean natural) {
        this.id = id;
        this.name = name;
        this.natural = natural;
    }

    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    @Override
    public String asString() {
        return this.name;
    }

    public static TRexVariant byId(int id) {
        return BY_ID.apply(id);
    }

    public static TRexVariant getRandomNatural(Random random) {
        return TRexVariant.getRandom(random, true);
    }

    public static TRexVariant getRandomUnnatural(Random random) {
        return TRexVariant.getRandom(random, false);
    }

    private static TRexVariant getRandom(Random random, boolean natural) {
        TRexVariant[] variants = Arrays.stream(TRexVariant.values()).filter(variant -> variant.natural == natural).toArray(TRexVariant[]::new);
        return Util.getRandom(variants, random);
    }

    static {
        BY_ID = ValueLists.createIdToValueFunction(TRexVariant::getId, TRexVariant.values(), ValueLists.OutOfBoundsHandling.ZERO);
        CODEC = StringIdentifiable.createCodec(TRexVariant::values);
    }
}
