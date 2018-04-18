package john.mod.init;

import java.util.ArrayList;
import java.util.List;

import john.mod.objects.blocks.BlockBase;
import john.mod.objects.blocks.BlockOres;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BlockInit 
{
	public static final List<Block> BLOCKS = new ArrayList<Block>();
	
	public static final Block BLOCK_METEOR = new BlockBase("block_meteor", Material.IRON);
	
	public static final Block ORE_END = new BlockOres("ore_end", "end");
	public static final Block ORE_OVERWORLD = new BlockOres("ore_end", "overworld");
	public static final Block ORE_NETHER = new BlockOres("ore_end", "nether");
}
