package john.mod;

import john.mod.proxy.CommonProxy;
import john.mod.tabs.BioTab;
import john.mod.tabs.NTutTab;
import john.mod.util.Reference;
import john.mod.util.handlers.DefaultBioPlayerDataHandler;
import john.mod.util.handlers.RegistryHandler;
import john.mod.util.interfaces.IBioPlayerDataHandler;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
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

	@CapabilityInject(IBioPlayerDataHandler.class)
	public static final Capability<IBioPlayerDataHandler> CAPABILITY_BIO_PLAYER_DATA = null;

	//Used to get the the element capabilities of a player
	public static BioElements getElement(Entity entity)
	{
		if (entity.hasCapability(CAPABILITY_BIO_PLAYER_DATA, null))
			return entity.getCapability(CAPABILITY_BIO_PLAYER_DATA, null).getElement();
		return null;
	}

	public static DefaultBioPlayerDataHandler getBioData(Entity entity)
	{
		if (entity.hasCapability(CAPABILITY_BIO_PLAYER_DATA, null))
			return (DefaultBioPlayerDataHandler)entity.getCapability(CAPABILITY_BIO_PLAYER_DATA, null);
		return null;
	}
}
