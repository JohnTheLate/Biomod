package john.mod.objects.tools.elemental;

import john.mod.BioElements;
import john.mod.objects.tools.BioWeapon;
import john.mod.util.interfaces.IHasModel;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class FireStaff extends BioWeapon implements IHasModel
{
	public FireStaff(String name)
	{
		super(name, 7, -2.5F, BioElements.FIRE);
	}

	@Override
	public float getDestroySpeed(ItemStack stack, IBlockState state)
	{
		return 1;
	}

	@Override
	public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker)
	{
		//stack.damageItem(1, attacker);
		return true;
	}

	public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
	{
		pos = pos.offset(facing);
		ItemStack itemstack = player.getHeldItem(hand);

		if (!player.canPlayerEdit(pos, facing, itemstack))
		{
			return EnumActionResult.FAIL;
		}
		else
		{
			if (worldIn.isAirBlock(pos))
			{
				worldIn.playSound(player, pos, SoundEvents.BLOCK_FIRE_AMBIENT, SoundCategory.BLOCKS, 1.0F, itemRand.nextFloat() * 0.4F + 0.8F);
				worldIn.setBlockState(pos, Blocks.FIRE.getDefaultState(), 11);

				if (worldIn.isAirBlock(new BlockPos(pos.getX()+1, pos.getY(), pos.getZ())))
				{
					worldIn.setBlockState(new BlockPos(pos.getX()+1, pos.getY(), pos.getZ()), Blocks.FIRE.getDefaultState(), 11);
				}

				if (worldIn.isAirBlock(new BlockPos(pos.getX()-1, pos.getY(), pos.getZ())))
				{
					worldIn.setBlockState(new BlockPos(pos.getX()-1, pos.getY(), pos.getZ()), Blocks.FIRE.getDefaultState(), 11);
				}

				if (worldIn.isAirBlock(new BlockPos(pos.getX(), pos.getY(), pos.getZ()+1)))
				{
					worldIn.setBlockState(new BlockPos(pos.getX(), pos.getY(), pos.getZ()+1), Blocks.FIRE.getDefaultState(), 11);
				}

				if (worldIn.isAirBlock(new BlockPos(pos.getX(), pos.getY(), pos.getZ()-1)))
				{
					worldIn.setBlockState(new BlockPos(pos.getX(), pos.getY(), pos.getZ()-1), Blocks.FIRE.getDefaultState(), 11);
				}
			}
			else if (worldIn.getBlockState(pos).getBlock() == Blocks.FIRE)
			{
				worldIn.playSound(player, pos, SoundEvents.BLOCK_FIRE_EXTINGUISH, SoundCategory.BLOCKS, 1.0F, itemRand.nextFloat() * 0.4F + 0.8F);
				worldIn.setBlockToAir(pos);

				if (worldIn.getBlockState(new BlockPos(pos.getX()+1, pos.getY(), pos.getZ())).getBlock() == Blocks.FIRE)
				{
					worldIn.setBlockToAir(new BlockPos(pos.getX()+1, pos.getY(), pos.getZ()));
				}

				if (worldIn.getBlockState(new BlockPos(pos.getX()-1, pos.getY(), pos.getZ())).getBlock() == Blocks.FIRE)
				{
					worldIn.setBlockToAir(new BlockPos(pos.getX()-1, pos.getY(), pos.getZ()));
				}

				if (worldIn.getBlockState(new BlockPos(pos.getX(), pos.getY(), pos.getZ()+1)).getBlock() == Blocks.FIRE)
				{
					worldIn.setBlockToAir(new BlockPos(pos.getX(), pos.getY(), pos.getZ()+1));
				}

				if (worldIn.getBlockState(new BlockPos(pos.getX(), pos.getY(), pos.getZ()-1)).getBlock() == Blocks.FIRE)
				{
					worldIn.setBlockToAir(new BlockPos(pos.getX(), pos.getY(), pos.getZ()-1));
				}
			}

			if (player instanceof EntityPlayerMP)
			{
				CriteriaTriggers.PLACED_BLOCK.trigger((EntityPlayerMP)player, pos, itemstack);
			}

			itemstack.damageItem(1, player);
			return EnumActionResult.SUCCESS;
		}
	}
}