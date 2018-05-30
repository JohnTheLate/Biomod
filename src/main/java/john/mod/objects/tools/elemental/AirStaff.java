package john.mod.objects.tools.elemental;

import john.mod.BioElements;
import john.mod.objects.tools.BioWeapon;
import john.mod.util.interfaces.IHasModel;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;

public class AirStaff extends BioWeapon implements IHasModel
{
	public AirStaff(String name)
	{
		super(name, 7, -2.5F, BioElements.AIR);
	}

	@Override
	public float getDestroySpeed(ItemStack stack, IBlockState state)
	{
		Block block = state.getBlock();

		Material material = state.getMaterial();

		if (material == Material.WOOD || material == Material.PLANTS || material == Material.LEAVES || material == Material.VINE || material == Material.CACTUS)
		{
			return 18.0F;
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
 	/*
	public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
	{

	}
	*/
}