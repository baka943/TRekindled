package baka943.trekindled.client.texture;

import baka943.trekindled.common.lib.LibRegistry;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.client.resources.IResourceManagerReloadListener;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.client.model.IModel;
import net.minecraftforge.client.model.ModelLoaderRegistry;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.apache.logging.log4j.Logger;
import slimeknights.tconstruct.library.Util;
import slimeknights.tconstruct.library.tools.Pattern;

import javax.annotation.Nonnull;
import java.io.IOException;

@SuppressWarnings("deprecation")
public class StampTextureCreator implements IResourceManagerReloadListener {

	public static final StampTextureCreator INSTANCE = new StampTextureCreator();

	private static final Logger log = Util.getLogger("TextureGen");

	public static ResourceLocation stampModelLocation;
	public static String stampLocString;
	public static ResourceLocation rawStampModelLocation;
	public static String rawStampLocString;

	@SubscribeEvent(priority = EventPriority.LOW)
	public void createCustomTextures(TextureStitchEvent.Pre event) {
		createPatterntextures(event.getMap());
	}

	private void createPatterntextures(TextureMap map) {
		if(stampModelLocation != null) {
			stampLocString = createPatternTexturesFor(map, stampModelLocation, LibRegistry.getStampItems());
		}

		if(rawStampModelLocation != null) {
			rawStampLocString = createPatternTexturesFor(map, rawStampModelLocation, LibRegistry.getStampItems());
		}
	}

	public String createPatternTexturesFor(TextureMap map, ResourceLocation baseTextureLoc, Iterable<Item> items) {
		String baseTextureString;
		ResourceLocation patternLocation;

		try {
			IModel patternModel = ModelLoaderRegistry.getModel(baseTextureLoc);
			patternLocation = patternModel.getTextures().iterator().next();
			baseTextureString = patternLocation.toString();
		} catch(Exception e) {
			log.error(e);
			return null;
		}

		for(Item item : items) {
			try {
				String identifier = Pattern.getTextureIdentifier(item);
				String partPatternLocation = baseTextureString + identifier;
				TextureAtlasSprite partPatternTexture;

				if(exists(partPatternLocation)) {
					partPatternTexture = map.registerSprite(new ResourceLocation(partPatternLocation));
					map.setTextureEntry(partPatternTexture);
				}
			} catch(Exception e) {
				log.error(e);
			}
		}

		return baseTextureString;
	}

	public static boolean exists(String res) {
		try {
			ResourceLocation loc = new ResourceLocation(res);
			loc = new ResourceLocation(loc.getResourceDomain(), "textures/" + loc.getResourcePath() + ".png");
			Minecraft.getMinecraft().getResourceManager().getAllResources(loc);

			return true;
		} catch(IOException e) {
			return false;
		}
	}

	@Override
	public void onResourceManagerReload(@Nonnull IResourceManager resourceManager) {
	}

}
