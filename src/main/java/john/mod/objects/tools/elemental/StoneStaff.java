package john.mod.objects.tools.elemental;

import john.mod.BioElements;
import john.mod.objects.tools.BioWeapon;
import john.mod.util.interfaces.IHasModel;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;

public class StoneStaff extends BioWeapon implements IHasModel
{
	public StoneStaff(String name)
	{
		super(name, 4, -2.5F, BioElements.STONE);
	}

	@Override
	public float getDestroySpeed(ItemStack stack, IBlockState state)
	{
		Block block = state.getBlock();

		Material material = state.getMaterial();

		if (material == Material.ROCK || material == Material.ANVIL || material == Material.PISTON || material == Material.CORAL || material == Material.SAND)
		{
			return 12.0F;
		}
		else
		{
			return 2;
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