package john.mod.init;

import java.util.ArrayList;
import java.util.List;

import john.mod.objects.blocks.BlockBase;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BlockInit 
{
	public static final List<Block> BLOCKS = new ArrayList<Block>();
	
	public static final Block BLOCK_METEOR = new BlockBase("block_meteor", Material.IRON);
}
