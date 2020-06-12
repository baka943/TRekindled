package baka943.trekindled.common.compat.jei.wrapper;

import com.google.common.collect.Lists;
import mezz.jei.api.ingredients.IIngredients;
import net.minecraft.item.ItemStack;
import teamroots.embers.compat.jei.wrapper.BaseRecipeWrapper;
import teamroots.embers.recipe.HeatCoilRecipe;

import java.util.List;

public class HeatCoilRecipeWrapper extends BaseRecipeWrapper<HeatCoilRecipe> {

	HeatCoilRecipe recipe;

	public HeatCoilRecipeWrapper(HeatCoilRecipe recipe) {
		this.recipe = recipe;
	}

	@SuppressWarnings("deprecation")
	@Override
	public void getIngredients(IIngredients ingredients) {
		List<ItemStack> inputStack = recipe.getInputs();
		List<ItemStack> outputStack = recipe.getOutputs();

		ingredients.setInputLists(ItemStack.class, Lists.<List<ItemStack>>newArrayList(inputStack));
		ingredients.setOutputLists(ItemStack.class, Lists.<List<ItemStack>>newArrayList(outputStack));
	}

}
