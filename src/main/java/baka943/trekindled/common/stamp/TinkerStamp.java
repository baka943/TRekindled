package baka943.trekindled.common.stamp;

import baka943.trekindled.common.core.proxy.ServerProxy;
import baka943.trekindled.common.item.ItemStamp;
import baka943.trekindled.common.lib.LibMisc;
import baka943.trekindled.common.lib.LibRegistry;
import baka943.trekindled.common.recipe.StampRecipeRegistry;
import com.google.common.eventbus.Subscribe;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;
import slimeknights.mantle.pulsar.pulse.Pulse;
import slimeknights.tconstruct.common.TinkerPulse;
import slimeknights.tconstruct.library.TinkerRegistry;
import slimeknights.tconstruct.library.tools.IToolPart;
import slimeknights.tconstruct.library.tools.ToolPart;
import slimeknights.tconstruct.tools.TinkerTools;

@Pulse(id = TinkerStamp.PulseID, description = "All the Stamps in here")
public class TinkerStamp extends TinkerPulse {

	public static final String PulseID = "TinkerStamp";

	@SidedProxy(clientSide = "baka943.trekindled.common.stamp.StampClientProxy", serverSide = LibMisc.SERVER_PROXY)
	public static ServerProxy proxy;

	public static ItemStamp stamp;
	public static ItemStamp raw_stamp;

	@SubscribeEvent
	public void registerItems(RegistryEvent.Register<Item> event) {
		IForgeRegistry<Item> registry = event.getRegistry();

		stamp = registerItem(registry, new ItemStamp(), "stamp");
		raw_stamp = registerItem(registry, new ItemStamp(), "raw_stamp");

		for(IToolPart toolPart : TinkerRegistry.getToolParts()) {
			if(toolPart != TinkerTools.boltCore) {
				LibRegistry.addStampForItem((ToolPart)toolPart);
			}
		}
	}

	@SubscribeEvent
	public void registerModles(ModelRegistryEvent event) {
		proxy.registerModels();
	}

	@Subscribe
	public void preInit(FMLPreInitializationEvent event) {
		proxy.preInit(event);
	}

	@Subscribe
	public void init(FMLInitializationEvent event) {
		if(TinkerPulse.isToolsLoaded()) {
			LibRegistry.tabStamp.setDisplayIcon(ItemStamp.setTagForPart(new ItemStack(TinkerStamp.stamp), TinkerTools.pickHead));
		}

		proxy.init(event);
	}

	@Subscribe
	public void postInit(FMLPostInitializationEvent event) {
		if(TinkerPulse.isToolsLoaded()) {
			StampRecipeRegistry.init();
		}

		proxy.postInit(event);
	}

}
