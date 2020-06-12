package baka943.trekindled.common.compat.jei;

import baka943.trekindled.common.compat.jei.category.HeatCoilRecipeCategory;
import baka943.trekindled.common.compat.jei.wrapper.HeatCoilRecipeWrapper;
import com.google.common.collect.Lists;
import mezz.jei.api.*;
import mezz.jei.api.recipe.IRecipeCategoryRegistration;
import mezz.jei.api.recipe.VanillaRecipeCategoryUid;
import net.minecraft.item.ItemStack;
import teamroots.embers.RegistryManager;
import teamroots.embers.recipe.HeatCoilRecipe;
import teamroots.embers.recipe.IWrappableRecipe;
import teamroots.embers.recipe.RecipeRegistry;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@JEIPlugin
public class TRekindledJEIPlugin implements IModPlugin {

	public static IJeiHelpers HELPER;

	@Override
	public void registerCategories(IRecipeCategoryRegistration registry) {
		IGuiHelper guiHelper = registry.getJeiHelpers().getGuiHelper();

		registry.addRecipeCategories(new HeatCoilRecipeCategory(guiHelper));
	}

	@Override
	public void register(IModRegistry registry) {
		HELPER = registry.getJeiHelpers();

		registry.handleRecipes(HeatCoilRecipe.class, HeatCoilRecipeWrapper::new, HeatCoilRecipeCategory.UID);
		registry.addRecipes(expandRecipes(RecipeRegistry.heatCoilRecipes), HeatCoilRecipeCategory.UID);
		registry.addRecipeCatalyst(new ItemStack(RegistryManager.heat_coil), HeatCoilRecipeCategory.UID);
		registry.addRecipeCatalyst(new ItemStack(RegistryManager.heat_coil), VanillaRecipeCategoryUid.SMELTING);
	}

	public static List<Object> expandRecipes(List<?> recipes) {
		return recipes.stream().map(TRekindledJEIPlugin::expandRecipe).flatMap(Collection::stream).collect(Collectors.toList());
	}

	public static List<?> expandRecipe(Object recipe) {
		if(recipe instanceof IWrappableRecipe) {
			return ((IWrappableRecipe) recipe).getWrappers();
		} else {
			return Lists.newArrayList(recipe);
		}
	}

}
