package john.mod.objects.tools.elemental;

import john.mod.BioElements;
import john.mod.objects.tools.BioWeapon;
import john.mod.util.interfaces.IHasModel;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class IceStaff extends BioWeapon implements IHasModel
{
	public IceStaff(String name)
	{
		super(name, 4, -2.5F, BioElements.ICE);
	}

	@Override
	public float getDestroySpeed(ItemStack stack, IBlockState state)
	{
		Block block = state.getBlock();

		Material material = state.getMaterial();

		if (material == Material.SNOW || material == Material.ICE || material == Material.PACKED_ICE)
		{
			return 10.0F;
		}
		else
		{
			return 1;
		}
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
						worldIn.setBlockState(blockpos, Blocks.ICE.getDefaultState(), 11);
						playerIn.playSound(SoundEvents.BLOCK_GLASS_BREAK, 0.3F, 1.0F);
						return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, itemstack);
					}
					else if (material == Material.LAVA && ((Integer)iblockstate.getValue(BlockLiquid.LEVEL)).intValue() == 0)
					{
						playerIn.playSound(SoundEvents.BLOCK_GLASS_HIT, 0.6F, 1.0F);
						worldIn.setBlockState(blockpos, Blocks.OBSIDIAN.getDefaultState(), 11);
						return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, itemstack);
					}
					else if (worldIn.isAirBlock(blockposfacing) && worldIn.isBlockFullCube(new BlockPos(blockposfacing.getX(), blockposfacing.getY()-1, blockposfacing.getZ())))
					{
						worldIn.playSound(playerIn, blockposfacing, SoundEvents.BLOCK_SNOW_PLACE, SoundCategory.BLOCKS, 1.0F, itemRand.nextFloat() * 0.4F + 0.8F);
						worldIn.setBlockState(blockposfacing, Blocks.SNOW_LAYER.getDefaultState(), 11);
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