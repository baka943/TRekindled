package baka943.trekindled.common;

import baka943.trekindled.client.core.proxy.ClientProxy;
import baka943.trekindled.common.core.proxy.IProxy;
import baka943.trekindled.common.lib.LibMisc;
import baka943.trekindled.common.stamp.TinkerStamp;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import slimeknights.mantle.pulsar.control.PulseManager;

@Mod(modid = LibMisc.MOD_ID, name = LibMisc.MOD_NAME, version = LibMisc.VERSION, dependencies = LibMisc.DEPENDENCIES)
public class TRekindledMain {

    @Mod.Instance
    public TRekindledMain instance;

    @SidedProxy(serverSide = LibMisc.SERVER_PROXY, clientSide = LibMisc.CLIENT_PROXY)
    private static IProxy proxy;

	public TRekindledMain() {}

    public static PulseManager pulseManager = new PulseManager(LibMisc.pulseEnable);

    static {
    	pulseManager.registerPulse(new TinkerStamp());
    }

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
    	proxy.preInit(event);

	    if(event.getSide().isClient()) {
		    ClientProxy.initClient();
	    }
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
	    proxy.init(event);
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
	    proxy.postInit(event);
    }

}
