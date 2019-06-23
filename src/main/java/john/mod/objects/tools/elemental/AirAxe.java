package john.mod.objects.tools.elemental;

import john.mod.BioElements;
import john.mod.objects.tools.BioWeapon;
import john.mod.util.interfaces.IHasModel;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

public class AirAxe extends BioWeapon implements IHasModel
{
	public AirAxe(String name)
	{
		super(name, 11, -2.8F, BioElements.AIR);
		this.setMaxDamage(1000);
	}

	@Override
	public float getDestroySpeed(ItemStack stack, IBlockState state)
	{
		Block block = state.getBlock();

		Material material = state.getMaterial();

		if (material == Material.WOOD || material == Material.PLANTS || material == Material.LEAVES || material == Material.VINE || material == Material.CACTUS)
		{
			return 8.0F;
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

	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn)
	{
		return super.onItemRightClick(worldIn, playerIn, handIn);
	}

 	@Override
	public void onUsingTick(ItemStack stack, EntityLivingBase player, int count)
	{
		System.out.println("used"); 
		System.out.println("used");
		stack.damageItem(1, player);
	}

	//-1784338777788894343 nice Beta map seed
}