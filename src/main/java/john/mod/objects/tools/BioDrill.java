package john.mod.objects.tools;

import com.google.common.collect.Sets;
import java.util.Set;

import john.mod.Main;
import john.mod.init.ItemInit;
import john.mod.util.interfaces.IHasModel;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;

public class BioDrill extends ItemTool implements IHasModel
{
	private static final Set<Block> EFFECTIVE_ON = Sets.newHashSet(Blocks.ACTIVATOR_RAIL, Blocks.COAL_ORE, Blocks.COBBLESTONE, Blocks.DETECTOR_RAIL, Blocks.DIAMOND_BLOCK, Blocks.DIAMOND_ORE, Blocks.DOUBLE_STONE_SLAB, Blocks.GOLDEN_RAIL, Blocks.GOLD_BLOCK, Blocks.GOLD_ORE, Blocks.ICE, Blocks.IRON_BLOCK, Blocks.IRON_ORE, Blocks.LAPIS_BLOCK, Blocks.LAPIS_ORE, Blocks.LIT_REDSTONE_ORE, Blocks.MOSSY_COBBLESTONE, Blocks.NETHERRACK, Blocks.PACKED_ICE, Blocks.RAIL, Blocks.REDSTONE_ORE, Blocks.SANDSTONE, Blocks.RED_SANDSTONE, Blocks.STONE, Blocks.STONE_SLAB, Blocks.STONE_BUTTON, Blocks.STONE_PRESSURE_PLATE);

	public BioDrill(String name, Item.ToolMaterial material)
	{
		super(3.0F, -3.2F, material, EFFECTIVE_ON);
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(Main.biotab);
		this.efficiency = ((material.getEfficiency() * 1.5F) + 2.0F);
		this.setMaxDamage(material.getMaxUses() * 2);

		ItemInit.ITEMS.add(this);
	}

	/**
	 * Check whether this Item can harvest the given Block
	 */
	public boolean canHarvestBlock(IBlockState blockIn)
	{
		Block block = blockIn.getBlock();

		if (block == Blocks.OBSIDIAN)
		{
			return this.toolMaterial.getHarvestLevel() == 3;
		}
		else if (block != Blocks.DIAMOND_BLOCK && block != Blocks.DIAMOND_ORE)
		{
			if (block != Blocks.EMERALD_ORE && block != Blocks.EMERALD_BLOCK)
			{
				if (block != Blocks.GOLD_BLOCK && block != Blocks.GOLD_ORE)
				{
					if (block != Blocks.IRON_BLOCK && block != Blocks.IRON_ORE)
					{
						if (block != Blocks.LAPIS_BLOCK && block != Blocks.LAPIS_ORE)
						{
							if (block != Blocks.REDSTONE_ORE && block != Blocks.LIT_REDSTONE_ORE)
							{
								if (block == Blocks.STONE || block == Blocks.DIRT)
								{
									if (Math.random() > 0.50F)
									{
										return true;
									}
									else
									{
										return false;
									}
								}
								else
								{
									Material material = blockIn.getMaterial();

									if (material == Material.ROCK)
									{
										return true;
									}
									else if (material == Material.IRON)
									{
										return true;
									}
									else
									{
										return material == Material.ANVIL;
									}
								}
							}
							else
							{
								return this.toolMaterial.getHarvestLevel() >= 2;
							}
						}
						else
						{
							return this.toolMaterial.getHarvestLevel() >= 1;
						}
					}
					else
					{
						return this.toolMaterial.getHarvestLevel() >= 1;
					}
				}
				else
				{
					return this.toolMaterial.getHarvestLevel() >= 2;
				}
			}
			else
			{
				return this.toolMaterial.getHarvestLevel() >= 2;
			}
		}
		else
		{
			return this.toolMaterial.getHarvestLevel() >= 2;
		}
	}

	public float getDestroySpeed(ItemStack stack, IBlockState state)
	{
		Material material = state.getMaterial();
		return material != Material.IRON && material != Material.ANVIL && material != Material.ROCK ? super.getDestroySpeed(stack, state) : this.efficiency;
	}

	@Override
	public void registerModels()
	{
		Main.proxy.registerItemRenderer(this, 0, "inventory");
	}
}