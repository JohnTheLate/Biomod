package john.mod.init;

import java.util.ArrayList;
import java.util.List;

import john.mod.objects.blocks.BlockBase;
import john.mod.objects.blocks.BlockOres;
import john.mod.objects.blocks.machines.specialfurnace.BlockSpecialFurnace;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BlockInit 
{
	public static final List<Block> BLOCKS = new ArrayList<Block>();

	public static final Block BLOCK_METEOR = new BlockBase("block_meteor", Material.IRON,5.0F,30.0F);
	public static final Block ORE_METEOR = new BlockBase("ore_meteor", Material.ROCK,3.0F,15.0F);

	public static final Block SPECIAL_FURNACE = new BlockSpecialFurnace("special_furnace");
	
	//public static final Block ORE_END = new BlockOres("ore_end", "end");
	//public static final Block ORE_OVERWORLD = new BlockOres("ore_overworld", "overworld");
	//public static final Block ORE_NETHER = new BlockOres("ore_nether", "nether");
}
