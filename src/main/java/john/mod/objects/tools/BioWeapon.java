package john.mod.objects.tools;

import com.google.common.collect.Multimap;
import john.mod.BioElements;
import john.mod.Main;
import john.mod.init.ItemInit;
import john.mod.util.interfaces.IHasModel;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BioWeapon extends Item implements IHasModel
{
	private float attackDamage;
	private float attackSpeed;
	private BioElements element;

	public BioWeapon(String name, float attackDamage, float attackSpeed, BioElements element)
	{
		this.maxStackSize = 1;
		this.attackDamage = attackDamage;
		this.attackSpeed = attackSpeed;
		this.element = element;
		//this.setMaxDamage(material.getMaxUses());
		setUnlocalizedName(name);
		//setRegistryName(name);
		this.setCreativeTab(Main.BIOTAB);

		this.setRegistryName(new ResourceLocation("ntm","tools/" + name));

		ItemInit.ITEMS.add(this);
	}

	public BioWeapon(String name, float attackDamage, float attackSpeed)
	{
		this(name, attackDamage, attackSpeed, BioElements.NONE);
	}

	public BioWeapon(String name)
	{
		this(name, 0.0F, -3.0F, BioElements.NONE);
	}

	/**
	 * Returns the amount of damage this item will deal. One heart of damage is equal to 2 damage points.
	 */
	public float getAttackDamage()
	{
		return this.attackDamage;
	}

	public float getDestroySpeed(ItemStack stack, IBlockState state)
	{
		Block block = state.getBlock();
		Material material = state.getMaterial();

		return 1;
	}

	public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker)
	{
		//stack.damageItem(1, attacker);
		return true;
	}

	/**
	 * Check whether this Item can harvest the given Block
	 */
	public boolean canHarvestBlock(IBlockState blockIn)
	{
		return false; // blockIn.getBlock() == Blocks.WEB;
	}

	/**
	 * Returns True is the item is renderer in full 3D when hold.
	 */
	@SideOnly(Side.CLIENT)
	public boolean isFull3D()
	{
		return true;
	}

	/**
	 * Return the enchantability factor of the item, most of the time is based on material.
	 */
	public int getItemEnchantability()
	{
		return 0; //this.material.getEnchantability();
	}

	/**
	 * Return the name for this tool's material.
	 */
	/*public String getToolMaterialName()
	{
		return ToolMaterial.IRON.toString();
	} */

	/**
	 * Return whether this item is repairable in an anvil.
	 *
	 * @param toRepair the {@code ItemStack} being repaired
	 * @param repair the {@code ItemStack} being used to perform the repair
	 */
	public boolean getIsRepairable(ItemStack toRepair, ItemStack repair)
	{
		return false;
	}

	/**
	 * Gets a map of item attribute modifiers, used by ItemSword to increase hit damage.
	 */
	public Multimap<String, AttributeModifier> getItemAttributeModifiers(EntityEquipmentSlot equipmentSlot)
	{
		Multimap<String, AttributeModifier> multimap = super.getItemAttributeModifiers(equipmentSlot);

		if (equipmentSlot == EntityEquipmentSlot.MAINHAND)
		{
			multimap.put(SharedMonsterAttributes.ATTACK_DAMAGE.getName(), new AttributeModifier(ATTACK_DAMAGE_MODIFIER, "Weapon modifier", (double)this.attackDamage, 0));
			multimap.put(SharedMonsterAttributes.ATTACK_SPEED.getName(), new AttributeModifier(ATTACK_SPEED_MODIFIER, "Weapon modifier", (double)this.attackSpeed, 0));
		}

		return multimap;
	}

	/**
	 * Called when a Block is destroyed using this Item. Return true to trigger the "Use Item" statistic.
	 */
	public boolean onBlockDestroyed(ItemStack stack, World worldIn, IBlockState state, BlockPos pos, EntityLivingBase entityLiving)
	{
		if ((double)state.getBlockHardness(worldIn, pos) != 0.0D)
		{
			//stack.damageItem(2, entityLiving);
		}

		return true;
	}

	@Override
	public void registerModels()
	{
		Main.proxy.registerItemRenderer(this, 0, "inventory");
	}
}
