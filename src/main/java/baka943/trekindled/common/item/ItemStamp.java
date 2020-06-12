package baka943.trekindled.common.item;

import baka943.trekindled.common.lib.IStamp;
import baka943.trekindled.common.lib.LibRegistry;
import net.minecraft.item.Item;
import slimeknights.tconstruct.library.materials.Material;
import slimeknights.tconstruct.library.tools.Pattern;

import java.util.Collection;

public class ItemStamp extends Pattern implements IStamp {

	public ItemStamp() {
		this.setCreativeTab(LibRegistry.tabStamp);
	}

	@Override
	protected Collection<Item> getSubItemToolparts() {
		return LibRegistry.getStampItems();
	}

	@Override
	protected boolean isValidSubitemMaterial(Material material) {
		return material.isCastable();
	}

}
