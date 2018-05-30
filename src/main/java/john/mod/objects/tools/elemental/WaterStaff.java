package john.mod.objects.tools.elemental;

import john.mod.BioElements;
import john.mod.objects.tools.BioWeapon;
import john.mod.util.interfaces.IHasModel;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class WaterStaff extends BioWeapon implements IHasModel
{
	public WaterStaff(String name)
	{
		super(name, 5, -2.5F, BioElements.WATER);
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

	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn)
	{
		boolean flag = true;
		ItemStack itemstack = playerIn.getHeldItem(handIn);
		RayTraceResult raytraceresult = this.rayTrace(worldIn, playerIn, flag);

		if (raytraceresult == null)
		{
			return new ActionResult<ItemStack>(EnumActionResult.PASS, itemstack);
		}
		else if (raytraceresult.typeOfHit != RayTraceResult.Type.BLOCK)
		{
			return new ActionResult<ItemStack>(EnumActionResult.PASS, itemstack);
		}
		else
		{
			BlockPos blockpos = raytraceresult.getBlockPos();

			if (!worldIn.isBlockModifiable(playerIn, blockpos))
			{
				return new ActionResult<ItemStack>(EnumActionResult.FAIL, itemstack);
			}
			else
			{
				if (!playerIn.canPlayerEdit(blockpos.offset(raytraceresult.sideHit), raytraceresult.sideHit, itemstack))
				{
					return new ActionResult<ItemStack>(EnumActionResult.FAIL, itemstack);
				}
				else
				{
					IBlockState iblockstate = worldIn.getBlockState(blockpos);
					Material material = iblockstate.getMaterial();
					BlockPos blockposfacing = blockpos.offset(raytraceresult.sideHit);

					if (material == Material.WATER && ((Integer)iblockstate.getValue(BlockLiquid.LEVEL)).intValue() == 0)
					{
						worldIn.setBlockToAir(blockpos);
						playerIn.playSound(SoundEvents.BLOCK_WATER_AMBIENT, 0.5F, 1.0F);
						return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, itemstack);
					}
					else if (worldIn.isAirBlock(blockposfacing) || worldIn.getBlockState(blockposfacing).getBlock() == Blocks.FLOWING_WATER || worldIn.getBlockState(blockposfacing).getBlock() == Blocks.WATER)
					{
						worldIn.playSound(playerIn, blockposfacing, SoundEvents.BLOCK_WATER_AMBIENT, SoundCategory.BLOCKS, 1.2F, itemRand.nextFloat() * 0.4F + 0.8F);
						worldIn.setBlockState(blockposfacing, Blocks.FLOWING_WATER.getDefaultState(), 11);
						return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, itemstack);
					}
					else
					{
						return new ActionResult<ItemStack>(EnumActionResult.FAIL, itemstack);
					}
				}
			}
		}
	}
}