package john.mod;

import john.mod.proxy.CommonProxy;
import john.mod.tabs.BioTab;
import john.mod.tabs.NTutTab;
import john.mod.util.Reference;
import john.mod.util.handlers.RegistryHandler;
import john.mod.util.interfaces.IElementHandler;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = Reference.MODID, name = Reference.NAME, version = Reference.VERSION)
public class Main 
{
	public static final CreativeTabs NTUTTAB = new NTutTab("ntuttab");
	public static final CreativeTabs BIOTAB = new BioTab("biotab");
	
	@Instance
	public static Main instance;
	
	@SidedProxy(clientSide = Reference.CLIENT, serverSide = Reference.COMMON)
	public static CommonProxy proxy;

	@EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
		RegistryHandler.preInitRegistries();
	}
	@EventHandler
	public void init(FMLInitializationEvent event)
	{
		RegistryHandler.initRegistries();
	}
	@EventHandler
	public void postInit(FMLPostInitializationEvent event)
	{
		RegistryHandler.postInitRegistries();
	}

	@CapabilityInject(IElementHandler.class)
	public static final Capability<IElementHandler> CAPABILITY_ELEMENT = null;

	public static IElementHandler getHandler(Entity entity) {

		if (entity.hasCapability(CAPABILITY_ELEMENT, EnumFacing.DOWN))
			return entity.getCapability(CAPABILITY_ELEMENT, EnumFacing.DOWN);
		return null;
	}
}
