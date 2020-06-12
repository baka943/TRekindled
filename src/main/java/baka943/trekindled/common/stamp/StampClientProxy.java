package baka943.trekindled.common.stamp;

import baka943.trekindled.client.core.proxy.ClientProxy;
import baka943.trekindled.client.texture.StampTextureCreator;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import slimeknights.tconstruct.common.ClientProxy.PatternMeshDefinition;

public class StampClientProxy extends ClientProxy {

	@Override
	public void preInit(FMLPreInitializationEvent event) {
		super.preInit(event);

		MinecraftForge.EVENT_BUS.register(new StampClientEvents());
	}

	public void registerModels() {
		final ResourceLocation stampLoc = StampClientEvents.locBlankStamp;
		final ResourceLocation rawStampLoc = StampClientEvents.locRawStamp;

		StampTextureCreator.stampModelLocation = new ResourceLocation(stampLoc.getResourceDomain(), "item/" + stampLoc.getResourcePath());
		ModelLoader.setCustomMeshDefinition(TinkerStamp.stamp, new PatternMeshDefinition(stampLoc));

		StampTextureCreator.rawStampModelLocation = new ResourceLocation(rawStampLoc.getResourceDomain(), "item/" + rawStampLoc.getResourcePath());
		ModelLoader.setCustomMeshDefinition(TinkerStamp.raw_stamp, new PatternMeshDefinition(rawStampLoc));
	}

}
