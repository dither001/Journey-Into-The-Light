package net.journey.dimension.cloudia;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.chunk.ChunkPrimer;

import java.util.ArrayList;
import java.util.List;

public class CloudiaChunkPrimer extends ChunkPrimer {

    public List<BlockPos> chunkTileEntityPositions = new ArrayList<BlockPos>();

    @Override
    public void setBlockState(int x, int y, int z, IBlockState state) {
        super.setBlockState(x, y, z, state);
        if (state.getBlock().hasTileEntity(state)) {
            this.chunkTileEntityPositions.add(new BlockPos(x, y, z));
        }
    }
}