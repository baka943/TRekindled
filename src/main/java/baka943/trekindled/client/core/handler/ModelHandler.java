package baka943.trekindled.client.core.handler;

import baka943.trekindled.client.render.IModelRegister;
import baka943.trekindled.common.lib.LibMisc;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.obj.OBJLoader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;

import java.util.Locale;

@Mod.EventBusSubscriber(value = Side.CLIENT, modid = LibMisc.MOD_ID)
public final class ModelHandler {

	private ModelHandler() {}

	@SubscribeEvent
	public static void registerModels(ModelRegistryEvent event) {
		OBJLoader.INSTANCE.addDomain(LibMisc.MOD_ID.toLowerCase(Locale.ROOT));

		for(Item item : Item.REGISTRY) {
			if(item instanceof IModelRegister) {
				((IModelRegister) item).registerModels();
			}
		}
	}

}
