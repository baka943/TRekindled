package baka943.trekindled.common.core.proxy;

import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.LoaderState;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import slimeknights.tconstruct.TConstruct;

public class ServerProxy implements IProxy {

    @Override
    public void preInit(FMLPreInitializationEvent event) {}

    @Override
    public void init(FMLInitializationEvent event) {}

    @Override
    public void postInit(FMLPostInitializationEvent event) {}

    public void registerModels() {
    	if(Loader.instance().hasReachedState(LoaderState.INITIALIZATION)) {
		    TConstruct.log.error("Proxy.registerModels has to be called during preInit. Otherwise the models wont be found on first load.");
	    }
    }

}
