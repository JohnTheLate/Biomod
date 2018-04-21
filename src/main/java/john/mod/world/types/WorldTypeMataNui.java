package john.mod.world.types;

import john.mod.init.BiomeInit;
import john.mod.world.types.layer.GenLayerCustom;
import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.BiomeProvider;
import net.minecraft.world.biome.BiomeProviderSingle;
import net.minecraft.world.gen.ChunkGeneratorSettings;
import net.minecraft.world.gen.layer.GenLayer;

public class WorldTypeMataNui extends WorldType
{
	public WorldTypeMataNui()
	{
		super("MataNui");
	}

	@Override
	public GenLayer getBiomeLayer(long worldSeed, GenLayer parentLayer, ChunkGeneratorSettings chunkSettings)
	{
		return new GenLayerCustom(worldSeed, parentLayer, this, chunkSettings);
	}
}
