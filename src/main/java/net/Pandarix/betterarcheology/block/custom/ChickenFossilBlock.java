package net.Pandarix.betterarcheology.block.custom;

import com.google.common.collect.ImmutableMap;
import net.Pandarix.betterarcheology.block.entity.FleeFromBlockEntity;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import org.jetbrains.annotations.Nullable;

import java.util.Map;
import java.util.stream.Stream;

public class ChickenFossilBlock extends FossilBaseBlock {
    private static final Map<Direction, VoxelShape> CHICKEN_SHAPES_FOR_DIRECTION = ImmutableMap.of(
            Direction.NORTH, Stream.of(
                    Block.createCuboidShape(5, 0, 1.5, 11, 11.25, 12.25),
                    Block.createCuboidShape(6.5, 11.25, -4, 9.5, 16, 3),
                    Block.createCuboidShape(7, 8.25, 12.25, 9, 10, 22.25)).reduce(VoxelShapes::union).get(),
            Direction.SOUTH, Stream.of(
                    Block.createCuboidShape(5, 0, 3.75, 11, 11.25, 14.5),
                    Block.createCuboidShape(6.5, 11.25, 13, 9.5, 16, 20),
                    Block.createCuboidShape(7, 8.25, -6.25, 9, 10, 3.75)).reduce(VoxelShapes::union).get(),
            Direction.WEST, Stream.of(
                    Block.createCuboidShape(1.5, 0, 5, 12.25, 11.25, 11),
                    Block.createCuboidShape(-4, 11.25, 6.5, 3, 16, 9.5),
                    Block.createCuboidShape(12.25, 8.25, 7, 22.25, 10, 9)).reduce(VoxelShapes::union).get(),
            Direction.EAST, Stream.of(
                    Block.createCuboidShape(3.75, 0, 5, 14.5, 11.25, 11),
                    Block.createCuboidShape(13, 11.25, 6.5, 20, 16, 9.5),
                    Block.createCuboidShape(-6.25, 8.25, 7, 3.75, 10, 9)).reduce(VoxelShapes::union).get());

    public ChickenFossilBlock(Settings settings) {
        super(settings);
    }

    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return CHICKEN_SHAPES_FOR_DIRECTION.get(state.get(FACING));
    }
}
