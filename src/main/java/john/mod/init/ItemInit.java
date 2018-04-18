package john.mod.init;

import java.util.ArrayList;
import java.util.List;

import john.mod.objects.armor.ArmorBase;
import john.mod.objects.items.ItemBase;
import john.mod.objects.tools.ToolAxe;
import john.mod.objects.tools.ToolHoe;
import john.mod.objects.tools.ToolPickaxe;
import john.mod.objects.tools.ToolShovel;
import john.mod.objects.tools.ToolSword;
import john.mod.util.Reference;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraftforge.common.util.EnumHelper;

public class ItemInit 
{
	public static final List<Item> ITEMS = new ArrayList<Item>();
	
	//Materials
	public static final ToolMaterial TOOL_METEOR = EnumHelper.addToolMaterial("tool_meteor", 2, 625, 7.0F, 2.5F, 28);
	public static final ArmorMaterial ARMOR_METEOR = EnumHelper.addArmorMaterial("armor_meteor", Reference.MODID+":meteor", 22, new int[]{2, 5, 7, 3}, 28, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 1.0F);
	public static final ToolMaterial TOOL_MAGIC_WOOD = EnumHelper.addToolMaterial("tool_magic_wood", 0, 128, 2.0F, 0.0F, 40);
	
	//Items
	public static final Item INGOT_METEOR = new ItemBase("ingot_meteor");
	
	//Tools
	
	public static final Item AXE_METEOR = new ToolAxe("axe_meteor", TOOL_METEOR);
	public static final Item HOE_METEOR = new ToolHoe("hoe_meteor", TOOL_METEOR);
	public static final Item PICKAXE_METEOR = new ToolPickaxe("pickaxe_meteor", TOOL_METEOR);
	public static final Item SHOVEL_METEOR = new ToolShovel("shovel_meteor", TOOL_METEOR);
	public static final Item SWORD_METEOR = new ToolSword("sword_meteor", TOOL_METEOR);
	
	public static final Item ENCHANT_STAFF = new ToolSword("enchant_staff", TOOL_MAGIC_WOOD);
	
	//Armor
	public static final Item HELMET_METEOR = new ArmorBase("helmet_meteor", ARMOR_METEOR, 1, EntityEquipmentSlot.HEAD);
	public static final Item CHESTPLATE_METEOR = new ArmorBase("chestplate_meteor", ARMOR_METEOR, 1, EntityEquipmentSlot.CHEST);
	public static final Item LEGGINGS_METEOR = new ArmorBase("leggings_meteor", ARMOR_METEOR, 2, EntityEquipmentSlot.LEGS);
	public static final Item BOOTS_METEOR = new ArmorBase("boots_meteor", ARMOR_METEOR, 1, EntityEquipmentSlot.FEET);
	
}
