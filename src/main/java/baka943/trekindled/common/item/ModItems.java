package baka943.trekindled.common.item;

import baka943.trekindled.common.lib.LibMisc;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;

@Mod.EventBusSubscriber(modid = LibMisc.MOD_ID)
public class ModItems {

	public static final Item STAMP_GEM = new ItemMod("stamp_gem");
	public static final Item STAMP_GEM_RAW = new ItemMod("stamp_gem_raw");

	@SubscribeEvent
	public static void registerItems(RegistryEvent.Register<Item> event) {
		IForgeRegistry<Item> registry = event.getRegistry();

		registry.register(STAMP_GEM);
		registry.register(STAMP_GEM_RAW);
	}

}
