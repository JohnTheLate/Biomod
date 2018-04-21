package john.mod.world.biomes;

import john.mod.init.BlockInit;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.passive.EntityParrot;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenBigTree;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenShrub;

import java.util.Random;

public class BiomeOnuWahi extends Biome
{
	protected static final WorldGenAbstractTree TREE = new WorldGenShrub(Blocks.LOG.getDefaultState(),Blocks.LEAVES.getDefaultState()); //Bei eigenen Bäumen erneut Tutorial zu Biomen bei 11:30 ansehen.

	public BiomeOnuWahi()
	{
		super(new BiomeProperties("Onu-Wahi").setBaseHeight(0.3F).setHeightVariation(0.025F).setTemperature(0.6F).setRainfall(0.4F).setWaterColor(9302513));

		topBlock = Blocks.GRASS.getDefaultState();
		//Wenn mit Variant:
		// = BlockInit.ORE_OVERWORLD.getDefaultState().withProperty(BlockOres.VARIANT, EnumHandler.EnumType.METEOR);
		fillerBlock = Blocks.DIRT.getDefaultState();

		this.decorator.coalGen = new WorldGenMinable(BlockInit.ORE_METEOR.getDefaultState(),10);

		this.decorator.treesPerChunk = 1;

		this.spawnableCaveCreatureList.clear();
		this.spawnableCreatureList.clear();
		this.spawnableMonsterList.clear();
		this.spawnableWaterCreatureList.clear();

		this.spawnableCreatureList.add(new SpawnListEntry(EntityParrot.class, 10, 1,5));
		this.spawnableCreatureList.add(new SpawnListEntry(EntityChicken.class, 5, 2,3));
	}

	/*@Override
	public WorldGenAbstractTree getRandomTreeFeature(Random rand)
	{
		return TREE;
	} */
}
