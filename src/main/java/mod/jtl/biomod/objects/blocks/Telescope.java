package mod.jtl.biomod.objects.blocks;

import mod.jtl.biomod.objects.blocks.tileentity.WickerBinTileEntity;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.item.ExperienceOrbEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.Direction;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.Random;

public class Telescope extends DirectionalBlock
{
	public Telescope()
	{
		super(Block.Properties.create(Material.ROCK)
				.sound(SoundType.STONE)
				.hardnessAndResistance(4.0F, 1200.0F)
				.lightValue(5)
				.tickRandomly()
		);
		this.setDefaultState((BlockState)((BlockState)((BlockState)this.stateContainer.getBaseState()).with(FACING, Direction.NORTH)));
	}

	@Override
	public BlockState getStateForPlacement(BlockItemUseContext ctx) {
		return (BlockState)this.getDefaultState().with(FACING, ctx.getPlacementHorizontalFacing().getOpposite());
	}

	@Override
	protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder)
	{
		builder.add(BlockStateProperties.FACING);
	}

	public BlockRenderType getRenderType(BlockState p_149645_1_) {
		return BlockRenderType.MODEL;
	}

	public BlockState rotate(BlockState p_185499_1_, Rotation p_185499_2_) {
		return (BlockState)p_185499_1_.with(FACING, p_185499_2_.rotate((Direction)p_185499_1_.get(FACING)));
	}

	//Telescopes randomly create XP
	@Override
	public void tick(BlockState state, World world, BlockPos pos, Random random)
	{
		if (random.nextInt(5) == 0)
		{
			int expAmount = 10;
			world.addEntity(new ExperienceOrbEntity(world, pos.getX(), pos.getY() + 1, pos.getZ(), expAmount));
		}

		//This creates multiple low-xp orbs
		/* while(expAmount > 0)
		{
			int expFraction = ExperienceOrbEntity.getXPSplit(expAmount);
			expAmount -= expFraction;
			world.addEntity(new ExperienceOrbEntity(world, pos.getX(), pos.getY(), pos.getZ() + 1, expFraction));
		} */
	}

	@Override
	public BlockRenderLayer getRenderLayer()
	{
		return BlockRenderLayer.CUTOUT_MIPPED;
	}
}
