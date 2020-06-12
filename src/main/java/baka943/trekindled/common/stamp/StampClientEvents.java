package baka943.trekindled.common.stamp;

import baka943.trekindled.client.texture.StampTextureCreator;
import baka943.trekindled.common.lib.LibRegistry;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelBakeEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import slimeknights.tconstruct.library.Util;
import slimeknights.tconstruct.tools.ToolClientEvents;

public class StampClientEvents {

	private static final ResourceLocation MODEL_BlankStamp = Util.getResource("item/stamp");
	public static final ResourceLocation locBlankStamp = Util.getResource("stamp");
	private static final ResourceLocation MODEL_RawStamp = Util.getResource("item/raw_stamp");
	public static final ResourceLocation locRawStamp = Util.getResource("raw_stamp");

	@SubscribeEvent
	public void onModelBake(ModelBakeEvent event) {
		ToolClientEvents.replacePatternModel(locBlankStamp, MODEL_BlankStamp, event, StampTextureCreator.stampLocString, LibRegistry.getStampItems());

		ToolClientEvents.replacePatternModel(locRawStamp, MODEL_RawStamp, event, StampTextureCreator.rawStampLocString, LibRegistry.getStampItems());
	}

}
