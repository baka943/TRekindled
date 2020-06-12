package baka943.trekindled.common.compat.jei.category;

import baka943.trekindled.common.compat.jei.wrapper.HeatCoilRecipeWrapper;
import baka943.trekindled.common.lib.LibMisc;
import mezz.jei.api.IGuiHelper;
import mezz.jei.api.gui.*;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.IRecipeCategory;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nonnull;

public class HeatCoilRecipeCategory implements IRecipeCategory<HeatCoilRecipeWrapper> {

	private final IDrawable background;
	private final String name;
	public static final String UID = "trekindled.heatcoil";

	public static ResourceLocation texture = new ResourceLocation("tconstruct:textures/gui/jei_heatcoil.png");

	private static final int INPUT_SLOT = 0;
	private static final int OUTPUT_SLOT = 1;

	protected final IDrawableAnimated animatedArrow;
	protected final IDrawableAnimated animatedFlame;

	public HeatCoilRecipeCategory(IGuiHelper helper) {
		IDrawableStatic staticArrow = helper.createDrawable(texture, 108, 0, 24, 17);
		this.animatedArrow = helper.createAnimatedDrawable(staticArrow, 200, IDrawableAnimated.StartDirection.LEFT, false);
		IDrawableStatic staticFlame = helper.createDrawable(texture, 108, 17, 14, 14);
		this.animatedFlame = helper.createAnimatedDrawable(staticFlame, 300, IDrawableAnimated.StartDirection.TOP, true);

		this.background = helper.createDrawable(texture, 0, 0, 108, 83);
		this.name = I18n.format("trekindled.jei.recipe.heatcoil");
	}

	@Override
	public void setRecipe(@Nonnull IRecipeLayout recipeLayout, @Nonnull HeatCoilRecipeWrapper heatCoilRecipeWrapper, @Nonnull IIngredients ingredients) {
		IGuiItemStackGroup stack = recipeLayout.getItemStacks();

		stack.init(INPUT_SLOT, true, 17, 18);
		stack.init(OUTPUT_SLOT, false, 73, 18);
		stack.set(ingredients);
	}

	@Override
	public void drawExtras(@Nonnull Minecraft minecraft) {
		animatedArrow.draw(minecraft, 42, 18);
		animatedFlame.draw(minecraft, 46, 40);
	}

	@Nonnull
	@Override
	public String getUid() {
		return UID;
	}

	@Nonnull
	@Override
	public String getTitle() {
		return name;
	}

	@Nonnull
	@Override
	public String getModName() {
		return LibMisc.MOD_ID;
	}

	@Nonnull
	@Override
	public IDrawable getBackground() {
		return background;
	}

}
