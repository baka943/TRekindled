package baka943.trekindled.common.item;

import baka943.trekindled.client.render.IModelRegister;
import baka943.trekindled.common.lib.LibMisc;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import teamroots.embers.Embers;

public class ItemMod extends Item implements IModelRegister {

	public ItemMod(String name) {
		super();
		this.setRegistryName(new ResourceLocation(Embers.MODID, name));
		this.setUnlocalizedName(LibMisc.MOD_ID + "." + name);
		this.setCreativeTab(Embers.tab);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void registerModels() {
		ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation(getRegistryName(), "inventory"));
	}

}
