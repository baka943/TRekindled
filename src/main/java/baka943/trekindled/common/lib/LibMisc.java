package baka943.trekindled.common.lib;

import slimeknights.mantle.pulsar.config.ForgeCFG;

public class LibMisc {
    public static final String MOD_ID = "trekindled";
    public static final String MOD_NAME = "Tinkers' Rekindled";
    public static final String VERSION = "@version@";
    public static final String DEPENDENCIES = "required-after:tconstruct;"
		    + "required-after:embers;"
		    + "after:conarm;"
		    + "after:pewter;"
		    + "after:tinkerscompendium";

	public static ForgeCFG pulseEnable = new ForgeCFG("TinkerRekindledPulse", "Enable");

    public static final String SERVER_PROXY = "baka943.trekindled.common.core.proxy.ServerProxy";
    public static final String CLIENT_PROXY = "baka943.trekindled.client.core.proxy.ClientProxy";

}
