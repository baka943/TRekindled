package baka943.trekindled.client.core.proxy;

import baka943.trekindled.client.texture.StampTextureCreator;
import baka943.trekindled.common.core.proxy.ServerProxy;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ClientProxy extends ServerProxy {

	public static void initClient() {
		MinecraftForge.EVENT_BUS.register(StampTextureCreator.INSTANCE);
	}

	@Override
    public void preInit(FMLPreInitializationEvent event) {}

    @Override
    public void init(FMLInitializationEvent event) {}

    @Override
    public void postInit(FMLPostInitializationEvent event) {}

}
