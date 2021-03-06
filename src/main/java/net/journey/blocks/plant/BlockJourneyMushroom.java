package net.journey.blocks.plant;

import net.journey.init.JourneyTabs;
import net.minecraft.block.Block;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.slayer.api.EnumMaterialTypes;
import net.slayer.api.block.BlockMod;

import java.util.Random;

public class BlockJourneyMushroom extends BlockMod {

    public static final PropertyEnum<BlockJourneyMushroom.EnumType> VARIANT = PropertyEnum.create(
            "variant", BlockJourneyMushroom.EnumType.class);

    private final Block smallBlock;

    public BlockJourneyMushroom(EnumMaterialTypes type, String name, String finalName, float hardness, Block block) {
        super(type, name, finalName, hardness, JourneyTabs.BLOCKS);
        this.setDefaultState(this.blockState.getBaseState().withProperty(VARIANT, BlockJourneyMushroom.EnumType.ALL_OUTSIDE));
        this.smallBlock = block;

    }

    public int quantityDropped(Random random) {
        return Math.max(0, random.nextInt(10) - 7);
    }

    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        return Item.getItemFromBlock(this.smallBlock);
    }

    public ItemStack getItem(World worldIn, BlockPos pos, IBlockState state) {
        return new ItemStack(this.smallBlock);
    }

    public IBlockState getStateForPlacement(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY,
                                            float hitZ, int meta, EntityLivingBase placer) {
        return this.getDefaultState();
    }

    public IBlockState getStateFromMeta(int meta) {
        return this.getDefaultState().withProperty(VARIANT, BlockJourneyMushroom.EnumType.byMetadata(meta));
    }

    public int getMetaFromState(IBlockState state) {
        return state.getValue(VARIANT).getMetadata();
    }

    public IBlockState withRotation(IBlockState state, Rotation rot) {
        switch (rot) {
            case CLOCKWISE_180:

                switch (state.getValue(VARIANT)) {
                    case STEM:
                        break;
                    case NORTH_WEST:
                        return state.withProperty(VARIANT, BlockJourneyMushroom.EnumType.SOUTH_EAST);
                    case NORTH:
                        return state.withProperty(VARIANT, BlockJourneyMushroom.EnumType.SOUTH);
                    case NORTH_EAST:
                        return state.withProperty(VARIANT, BlockJourneyMushroom.EnumType.SOUTH_WEST);
                    case WEST:
                        return state.withProperty(VARIANT, BlockJourneyMushroom.EnumType.EAST);
                    case EAST:
                        return state.withProperty(VARIANT, BlockJourneyMushroom.EnumType.WEST);
                    case SOUTH_WEST:
                        return state.withProperty(VARIANT, BlockJourneyMushroom.EnumType.NORTH_EAST);
                    case SOUTH:
                        return state.withProperty(VARIANT, BlockJourneyMushroom.EnumType.NORTH);
                    case SOUTH_EAST:
                        return state.withProperty(VARIANT, BlockJourneyMushroom.EnumType.NORTH_WEST);
                    default:
                        return state;
                }

            case COUNTERCLOCKWISE_90:

                switch (state.getValue(VARIANT)) {
                    case STEM:
                        break;
                    case NORTH_WEST:
                        return state.withProperty(VARIANT, BlockJourneyMushroom.EnumType.SOUTH_WEST);
                    case NORTH:
                        return state.withProperty(VARIANT, BlockJourneyMushroom.EnumType.WEST);
                    case NORTH_EAST:
                        return state.withProperty(VARIANT, BlockJourneyMushroom.EnumType.NORTH_WEST);
                    case WEST:
                        return state.withProperty(VARIANT, BlockJourneyMushroom.EnumType.SOUTH);
                    case EAST:
                        return state.withProperty(VARIANT, BlockJourneyMushroom.EnumType.NORTH);
                    case SOUTH_WEST:
                        return state.withProperty(VARIANT, BlockJourneyMushroom.EnumType.SOUTH_EAST);
                    case SOUTH:
                        return state.withProperty(VARIANT, BlockJourneyMushroom.EnumType.EAST);
                    case SOUTH_EAST:
                        return state.withProperty(VARIANT, BlockJourneyMushroom.EnumType.NORTH_EAST);
                    default:
                        return state;
                }

            case CLOCKWISE_90:

                switch (state.getValue(VARIANT)) {
                    case STEM:
                        break;
                    case NORTH_WEST:
                        return state.withProperty(VARIANT, BlockJourneyMushroom.EnumType.NORTH_EAST);
                    case NORTH:
                        return state.withProperty(VARIANT, BlockJourneyMushroom.EnumType.EAST);
                    case NORTH_EAST:
                        return state.withProperty(VARIANT, BlockJourneyMushroom.EnumType.SOUTH_EAST);
                    case WEST:
                        return state.withProperty(VARIANT, BlockJourneyMushroom.EnumType.NORTH);
                    case EAST:
                        return state.withProperty(VARIANT, BlockJourneyMushroom.EnumType.SOUTH);
                    case SOUTH_WEST:
                        return state.withProperty(VARIANT, BlockJourneyMushroom.EnumType.NORTH_WEST);
                    case SOUTH:
                        return state.withProperty(VARIANT, BlockJourneyMushroom.EnumType.WEST);
                    case SOUTH_EAST:
                        return state.withProperty(VARIANT, BlockJourneyMushroom.EnumType.SOUTH_WEST);
                    default:
                        return state;
                }

            default:
                return state;
        }
    }

    @SuppressWarnings("incomplete-switch")
    public IBlockState withMirror(IBlockState state, Mirror mirrorIn) {
        BlockJourneyMushroom.EnumType blockhugemushroom$enumtype = state
                .getValue(VARIANT);

        switch (mirrorIn) {
            case LEFT_RIGHT:

                switch (blockhugemushroom$enumtype) {
                    case NORTH_WEST:
                        return state.withProperty(VARIANT, BlockJourneyMushroom.EnumType.SOUTH_WEST);
                    case NORTH:
                        return state.withProperty(VARIANT, BlockJourneyMushroom.EnumType.SOUTH);
                    case NORTH_EAST:
                        return state.withProperty(VARIANT, BlockJourneyMushroom.EnumType.SOUTH_EAST);
                    case WEST:
                    case EAST:
                    default:
                        return super.withMirror(state, mirrorIn);
                    case SOUTH_WEST:
                        return state.withProperty(VARIANT, BlockJourneyMushroom.EnumType.NORTH_WEST);
                    case SOUTH:
                        return state.withProperty(VARIANT, BlockJourneyMushroom.EnumType.NORTH);
                    case SOUTH_EAST:
                        return state.withProperty(VARIANT, BlockJourneyMushroom.EnumType.NORTH_EAST);
                }

            case FRONT_BACK:

                switch (blockhugemushroom$enumtype) {
                    case NORTH_WEST:
                        return state.withProperty(VARIANT, BlockJourneyMushroom.EnumType.NORTH_EAST);
                    case NORTH:
                    case SOUTH:
                    default:
                        break;
                    case NORTH_EAST:
                        return state.withProperty(VARIANT, BlockJourneyMushroom.EnumType.NORTH_WEST);
                    case WEST:
                        return state.withProperty(VARIANT, BlockJourneyMushroom.EnumType.EAST);
                    case EAST:
                        return state.withProperty(VARIANT, BlockJourneyMushroom.EnumType.WEST);
                    case SOUTH_WEST:
                        return state.withProperty(VARIANT, BlockJourneyMushroom.EnumType.SOUTH_EAST);
                    case SOUTH_EAST:
                        return state.withProperty(VARIANT, BlockJourneyMushroom.EnumType.SOUTH_WEST);
                }
        }

        return super.withMirror(state, mirrorIn);
    }

    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, VARIANT);
    }

    public boolean rotateBlock(World world, BlockPos pos, EnumFacing axis) {
        IBlockState state = world.getBlockState(pos);
        for (IProperty prop : state.getProperties().keySet()) {
            if (prop.getName().equals("variant")) {
                world.setBlockState(pos, state.cycleProperty(prop));
                return true;
            }
        }
        return false;
    }

    public enum EnumType implements IStringSerializable {
        NORTH_WEST(1, "north_west"),
        NORTH(2, "north"),
        NORTH_EAST(3, "north_east"),
        WEST(4, "west"),
        CENTER(5, "center"),
        EAST(6, "east"),
        SOUTH_WEST(7, "south_west"),
        SOUTH(8, "south"),
        SOUTH_EAST(9, "south_east"),
        STEM(10, "stem"),
        ALL_INSIDE(0, "all_inside"),
        ALL_OUTSIDE(14, "all_outside"),
        ALL_STEM(15, "all_stem");

        private static final BlockJourneyMushroom.EnumType[] META_LOOKUP = new BlockJourneyMushroom.EnumType[16];

        static {
            for (BlockJourneyMushroom.EnumType blockhugemushroom$enumtype : values()) {
                META_LOOKUP[blockhugemushroom$enumtype.getMetadata()] = blockhugemushroom$enumtype;
            }
        }

        private final int meta;
        private final String name;

        EnumType(int meta, String name) {
            this.meta = meta;
            this.name = name;
        }

        public static BlockJourneyMushroom.EnumType byMetadata(int meta) {
            if (meta < 0 || meta >= META_LOOKUP.length) {
                meta = 0;
            }

            BlockJourneyMushroom.EnumType blockhugemushroom$enumtype = META_LOOKUP[meta];
            return blockhugemushroom$enumtype == null ? META_LOOKUP[0] : blockhugemushroom$enumtype;
        }

        public int getMetadata() {
            return this.meta;
        }

        public String toString() {
            return this.name;
        }

        public String getName() {
            return this.name;
        }
    }
}