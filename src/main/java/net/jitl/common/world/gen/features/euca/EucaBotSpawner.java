package net.jitl.common.world.gen.features.euca;

import com.mojang.serialization.Codec;
import net.jitl.common.world.gen.features.featureconfig.EucaSpawnerFeatureConfig;
import net.jitl.common.world.gen.features.featureconfig.EucaTreeFeatureConfig;
import net.jitl.init.JBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.feature.Feature;
import org.jetbrains.annotations.NotNull;
import ru.timeconqueror.timecore.api.util.GenHelper;

import java.util.Random;

public class EucaBotSpawner extends Feature<EucaSpawnerFeatureConfig> {

    public EucaBotSpawner(Codec<EucaSpawnerFeatureConfig> codec) {
        super(codec);
    }

    @Override
    public boolean place(ISeedReader reader, ChunkGenerator generator, Random random, BlockPos pos, EucaSpawnerFeatureConfig config) {
        int xPos = pos.getX() + random.nextInt(8) - random.nextInt(8);
        int zPos = pos.getZ() + random.nextInt(8) - random.nextInt(8);
        int yPos = reader.getHeight(Heightmap.Type.WORLD_SURFACE_WG, xPos, zPos) - 1;

        BlockPos.Mutable placement = new BlockPos.Mutable(xPos, yPos, zPos);

        if (!config.spawnBlock.test(reader.getBlockState(pos.below()), random)) {
            return false;
        } else {
            int height = random.nextInt(config.minHeight) + random.nextInt(config.maxHeight) + 3;
            for(int i = 0; i < height; i++) {
                placeShaft(reader, placement.move(Direction.UP));
            }
            BlockPos spawnerPos = placement.above(2);
            setBlock(reader, spawnerPos, config.spawnerBlock.getState(random, spawnerPos));
            addRectangle(7, 3, 1, xPos - 3, yPos + height + 1, zPos - 1, JBlocks.EUCA_TILE, reader);
            addRectangle(3, 7, 1, xPos - 1, yPos + height + 1, zPos - 3, JBlocks.EUCA_TILE, reader);
            addRectangle(5, 5, 1, xPos - 2, yPos + height + 1, zPos - 2, JBlocks.EUCA_TILE, reader);
            return true;
        }
    }

    public void addRectangle(int east, int south, int height, int x, int y, int z, Block b, ISeedReader reader) {
        for (int x1 = 0; x1 < east; x1++) {
            for (int z1 = 0; z1 < south; z1++) {
                for (int y1 = 0; y1 < height; y1++) {
                    setBlock(reader, new BlockPos(x + x1, y + y1, z + z1), b.defaultBlockState());
                }
            }
        }
    }

    public void placeShaft(ISeedReader reader, BlockPos pos) {
        int xPos = pos.getX();
        int zPos = pos.getZ();
        int yPos = pos.getY();
        setBlock(reader, new BlockPos(xPos + 1, yPos, zPos), JBlocks.EUCA_BRICK.defaultBlockState());
        setBlock(reader, new BlockPos(xPos - 1, yPos, zPos), JBlocks.EUCA_BRICK.defaultBlockState());
        setBlock(reader, new BlockPos(xPos, yPos, zPos + 1), JBlocks.EUCA_BRICK.defaultBlockState());
        setBlock(reader, new BlockPos(xPos, yPos, zPos - 1), JBlocks.EUCA_BRICK.defaultBlockState());
    }
}
