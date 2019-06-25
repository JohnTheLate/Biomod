package mod.jtl.biomod.init;

import mod.jtl.biomod.Biomod;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

import javax.annotation.Nonnull;
import java.util.function.Supplier;

public class ModItemGroups
{

	public static final ItemGroup BIOMOD_ITEM_GROUP = new ModItemGroup(Biomod.MODID, () -> new ItemStack(ModItems.BIOGEAR));

	public static final class ModItemGroup extends ItemGroup {

		@Nonnull
		private final Supplier<ItemStack> iconSupplier;

		public ModItemGroup(@Nonnull final String name, @Nonnull final Supplier<ItemStack> iconSupplier) {
			super(name);
			this.iconSupplier = iconSupplier;
		}

		@Override
		@Nonnull
		public ItemStack createIcon() {
			return iconSupplier.get();
		}

	}

}