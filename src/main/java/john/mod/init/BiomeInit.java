package john.mod.init;

import john.mod.world.biomes.BiomeOnuWahi;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.common.BiomeManager.BiomeEntry;
import net.minecraftforge.common.BiomeManager.BiomeType;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class BiomeInit
{
	public static final Biome ONUWAHI = new BiomeOnuWahi();

	public static void registerBiomes()
	{
		initBiome(ONUWAHI, "Onu-Wahi", BiomeType.WARM, Type.PLAINS, Type.MOUNTAIN, Type.LUSH, Type.DRY);
	}

	private static Biome initBiome(Biome biome, String name, BiomeType biomeType, Type... types)
	{
		biome.setRegistryName(name);
		ForgeRegistries.BIOMES.register(biome);
		System.out.println("Biome registered!");
		BiomeDictionary.addTypes(biome, types);
		BiomeManager.addBiome(biomeType, new BiomeEntry(biome, 10));
		BiomeManager.addSpawnBiome(biome);
		System.out.println("Biome added!");
		return biome;
	}
}
