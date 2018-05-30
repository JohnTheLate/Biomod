package john.mod.world.types;

import john.mod.init.BiomeInit;
import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.BiomeProvider;
import net.minecraft.world.biome.BiomeProviderSingle;

public class WorldTypeSingleBiome extends WorldType
{
	public WorldTypeSingleBiome()
	{
		super("SingleBiome");
	}

	@Override
	public BiomeProvider getBiomeProvider(World world)
	{
		return new BiomeProviderSingle(BiomeInit.ONUWAHI);
	}
}
