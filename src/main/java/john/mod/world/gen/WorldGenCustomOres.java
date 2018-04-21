package john.mod.world.gen;

import java.util.Random;

import john.mod.init.BlockInit;
import net.minecraft.block.state.pattern.BlockMatcher;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.fml.common.IWorldGenerator;

public class WorldGenCustomOres implements IWorldGenerator
{
	private WorldGenerator ore_meteor;
	
	public WorldGenCustomOres()
	{
		ore_meteor = new WorldGenMinable(BlockInit.ORE_METEOR.getDefaultState(), 4, BlockMatcher.forBlock(Blocks.STONE));
	}
	
	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider)
	{
		switch(world.provider.getDimension())
		{
		case -1:
			//runGenerator(ore_meteor, world, random, chunkX, chunkZ, 50, 70, 125);
			break;
			
		case 0:
			runGenerator(ore_meteor, world, random, chunkX, chunkZ, 50, 70, 125);
			break;
			
		case 1:
			//runGenerator(ore_meteor, world, random, chunkX, chunkZ, 50, 70, 125);
			break;
		}
	}
	
	public void runGenerator(WorldGenerator gen, World world, Random rand, int chunkX, int chunkZ, int chance, int minHeight, int maxHeight)
	{
		if(minHeight > maxHeight || minHeight < 0 || maxHeight > 256) throw new IllegalArgumentException("Ore generation min or max heights are out of bounds (or reversed)");
		
		int heightDiff = maxHeight - minHeight + 1;
		for (int i = 0; i < chance; i++)
		{
			int x = chunkX * 16 + rand.nextInt(16);
			int y = minHeight + rand.nextInt(heightDiff);
			int z = chunkZ * 16 + rand.nextInt(16);
			
			gen.generate(world, rand, new BlockPos(x,y,z));
		}
	}
}
