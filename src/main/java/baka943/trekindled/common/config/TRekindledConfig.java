package baka943.trekindled.common.config;

import baka943.trekindled.common.lib.LibMisc;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Config(modid = LibMisc.MOD_ID, category = "", name = LibMisc.MOD_ID)
@Mod.EventBusSubscriber(modid = LibMisc.MOD_ID)
public class TRekindledConfig {

	private TRekindledConfig() {
		throw new UnsupportedOperationException("No instance fot you");
	}

	@SubscribeEvent
	public static void onConfigReload(ConfigChangedEvent.OnConfigChangedEvent event) {
		if(event.getModID().equals(LibMisc.MOD_ID)) {
			ConfigManager.sync(LibMisc.MOD_ID, Config.Type.INSTANCE);
		}
	}

	@Config.Ignore
	private static final String LANG_PREFIX = "config." + LibMisc.MOD_ID + ".";

	@Config.Comment("Compat")
	@Config.LangKey(LANG_PREFIX + "compat")
	@Config.Name("compat")
	public static final Compat COMPAT = new Compat();

	public static final class Compat {

		@Config.Comment("Do you know, Iguana Tweaks? If true, Iguana will wake up")
		@Config.LangKey(LANG_PREFIX + "iguana_model")
		@Config.Name("iguana_model")
		@Config.RequiresMcRestart
		public boolean iguanaModel = false;

		@Config.Comment("If true, it will disable all TinkerSmeltery Alloy recipes, so that you must find another way.")
		@Config.LangKey(LANG_PREFIX + "disable_alloys")
		@Config.Name("disable_alloys")
		@Config.RequiresMcRestart
		public boolean disableTinkerAlloys = false;

		@Config.Comment("If true, it will disable all TinkerSmeltery Melting recipes, so that you must find another way.")
		@Config.LangKey(LANG_PREFIX + "disable_melting")
		@Config.Name("disable_melting")
		@Config.RequiresMcRestart
		public boolean disableTinkerMelting = false;

	}

}
