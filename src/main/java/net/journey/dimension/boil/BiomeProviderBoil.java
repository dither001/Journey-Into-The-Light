package net.journey.dimension.boil;

import net.journey.dimension.base.DimensionHelper;
import net.minecraft.world.biome.BiomeProviderSingle;

public class BiomeProviderBoil extends BiomeProviderSingle {

    public BiomeProviderBoil() {
        super(DimensionHelper.BOILING_BIOME);
    }
}