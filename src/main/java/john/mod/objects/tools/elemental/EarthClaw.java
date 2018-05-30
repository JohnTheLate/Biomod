package john.mod.objects.tools.elemental;

import john.mod.BioElements;
import john.mod.objects.tools.BioWeapon;
import john.mod.util.interfaces.IHasModel;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;

public class EarthClaw extends BioWeapon implements IHasModel
{
	public EarthClaw(String name)
	{
		super(name, 9, -2.0F, BioElements.EARTH);
	}

	@Override
	public float getDestroySpeed(ItemStack stack, IBlockState state)
	{
		Block block = state.getBlock();

		Material material = state.getMaterial();

		if (material == Material.ROCK || material == Material.GROUND || material == Material.WEB || material == Material.SAND || material == Material.GRASS)
		{
			return 15.0F;
		}
		else
		{
			return 3;
		}
	}

	@Override
	public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker)
	{
		//stack.damageItem(1, attacker);
		return true;
	}

	@Override
	public int getHarvestLevel(ItemStack stack, String toolClass, @javax.annotation.Nullable net.minecraft.entity.player.EntityPlayer player, @javax.annotation.Nullable IBlockState blockState)
	{
		return 3;
	}

 	/*
	public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
	{

	}
	*/
}