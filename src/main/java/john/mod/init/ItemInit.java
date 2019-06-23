package john.mod.init;

import java.util.ArrayList;
import java.util.List;

import john.mod.objects.armor.ArmorBase;
import john.mod.objects.items.BioFireItem;
import john.mod.objects.items.BioItemBase;
import john.mod.objects.items.ItemBase;
import john.mod.objects.kanohi.*;
import john.mod.objects.tools.*;
import john.mod.objects.tools.elemental.*;
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
	public static final ToolMaterial TOOL_MAGIC_WOOD = EnumHelper.addToolMaterial("tool_magic_wood", 0, 128, 2.0F, -1.0F, 40);

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


	//================ BIONICLE ================
	//Toa Weapons
	public static final Item SWORD_FIRE = new FireSword("bioweap_fire_sword");
	public static final Item AXE_AIR = new AirAxe("bioweap_air_axe");
	public static final Item SWORD_ICE = new IceSword("bioweap_ice_sword");
	public static final Item HOOK_WATER = new WaterHook("bioweap_water_hook");
	public static final Item CLAW_EARTH = new EarthClaw("bioweap_earth_claw");
	public static final Item HAND_STONE = new StoneHand("bioweap_stone_hand");

	//Staffs
	public static final Item STAFF_FIRE = new FireStaff("bioweap_fire_staff");
	public static final Item STAFF_AIR = new AirStaff("bioweap_air_staff");
	public static final Item STAFF_ICE = new IceStaff("bioweap_ice_staff");
	public static final Item STAFF_WATER = new WaterStaff("bioweap_water_staff");
	public static final Item STAFF_EARTH = new EarthStaff("bioweap_earth_staff");
	public static final Item STAFF_STONE = new StoneStaff("bioweap_stone_staff");

	//Kanohi
	public static final Item KANOHI_HAU = new KanohiHau("kanohi_hau");
	public static final Item KANOHI_MIRU = new KanohiMiru("kanohi_miru");
	public static final Item KANOHI_PAKARI = new KanohiPakari("kanohi_pakari");
	public static final Item KANOHI_AKAKU = new KanohiAkaku("kanohi_akaku");
	public static final Item KANOHI_KAUKAU = new KanohiKaukau("kanohi_kaukau");
	public static final Item KANOHI_KAKAMA = new KanohiKakama("kanohi_kakama");

	public static final Item KANOHI_HUNA = new KanohiHuna("kanohi_huna");
	public static final Item KANOHI_MAHIKI = new KanohiMahiki("kanohi_mahiki");
	public static final Item KANOHI_RURU = new KanohiRuru("kanohi_ruru");
	public static final Item KANOHI_MATATU = new KanohiMatatu("kanohi_matatu");
	public static final Item KANOHI_RAU = new KanohiRau("kanohi_rau");
	public static final Item KANOHI_KOMAU = new KanohiKomau("kanohi_komau");

	//Bionicle Items
	public static final Item BIO_GEAR = new BioItemBase("biogear");
	public static final Item KOLI_BALL = new BioItemBase("biokoliball");
	public static final Item FLUTE_AIR = new BioItemBase("bioflute");
	public static final Item BIO_FIRE_ITEM = new BioFireItem("biofireitem");

	public static final Item DRILL_WOOD = new BioDrill("biodrill_wood", ToolMaterial.WOOD);
	public static final Item DRILL_STONE = new BioDrill("biodrill_stone", ToolMaterial.STONE);
	public static final Item DRILL_IRON = new BioDrill("biodrill_iron", ToolMaterial.IRON);
	public static final Item DRILL_DIAMOND = new BioDrill("biodrill_diamond", ToolMaterial.DIAMOND);
	public static final Item DRILL_GOLD = new BioDrill("biodrill_gold", ToolMaterial.GOLD);

	public static final Item SAW_WOOD = new BioSaw("biosaw_wood", ToolMaterial.WOOD);
	public static final Item SAW_STONE = new BioSaw("biosaw_stone", ToolMaterial.STONE);
	public static final Item SAW_IRON = new BioSaw("biosaw_iron", ToolMaterial.IRON);
	public static final Item SAW_DIAMOND = new BioSaw("biosaw_diamond", ToolMaterial.DIAMOND);
	public static final Item SAW_GOLD = new BioSaw("biosaw_gold", ToolMaterial.GOLD);
}
