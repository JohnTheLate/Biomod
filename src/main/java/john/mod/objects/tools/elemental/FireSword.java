package john.mod.objects.tools.elemental;

import john.mod.BioElements;
import john.mod.objects.tools.BioWeapon;
import john.mod.util.interfaces.IHasModel;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
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

public class FireSword extends BioWeapon implements IHasModel
{
	public FireSword(String name)
	{
		super(name, 15, -3.2F, BioElements.FIRE);
	}

	@Override
	public float getDestroySpeed(ItemStack stack, IBlockState state)
	{
		Block block = state.getBlock();

		Material material = state.getMaterial();

		if (material == Material.ROCK || material == Material.IRON)
		{
			return 15.0F;
		}
		else if (material == Material.GRASS || material == Material.GROUND)
		{
			return 8;
		}
		else
		{
			return 4;
		}
	}

	@Override
	public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker)
	{
		//stack.damageItem(1, attacker);
		target.setFire(5);
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
				worldIn.playSound(player, pos, SoundEvents.ITEM_FIRECHARGE_USE, SoundCategory.BLOCKS, 1.0F, itemRand.nextFloat() * 0.4F + 0.8F);
				worldIn.setBlockState(pos, Blocks.FIRE.getDefaultState(), 11);
			}

			if (player instanceof EntityPlayerMP)
			{
				CriteriaTriggers.PLACED_BLOCK.trigger((EntityPlayerMP)player, pos, itemstack);
			}

			itemstack.damageItem(1, player);
			return EnumActionResult.SUCCESS;
		}
	}

	@Override
	public int getHarvestLevel(ItemStack stack, String toolClass, @javax.annotation.Nullable net.minecraft.entity.player.EntityPlayer player, @javax.annotation.Nullable IBlockState blockState)
	{
		return -1;
	}
}