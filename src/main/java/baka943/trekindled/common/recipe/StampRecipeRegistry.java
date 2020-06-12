package baka943.trekindled.common.recipe;

import baka943.trekindled.common.item.ItemStamp;
import baka943.trekindled.common.stamp.TinkerStamp;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraftforge.common.crafting.IngredientNBT;
import net.minecraftforge.fluids.FluidRegistry;
import slimeknights.tconstruct.library.TinkerRegistry;
import slimeknights.tconstruct.library.materials.Material;
import slimeknights.tconstruct.library.tinkering.MaterialItem;
import slimeknights.tconstruct.library.tools.IToolPart;
import slimeknights.tconstruct.library.tools.ToolPart;
import slimeknights.tconstruct.tools.TinkerMaterials;
import slimeknights.tconstruct.tools.TinkerTools;
import teamroots.embers.RegistryManager;
import teamroots.embers.recipe.HeatCoilRecipe;
import teamroots.embers.recipe.ItemStampingRecipe;
import teamroots.embers.recipe.RecipeRegistry;

public class StampRecipeRegistry {

	public static void init() {
		//Stamps Recipes
		registerStampsRecipes();

		//Toolparts Recipes
		for(Material material : TinkerRegistry.getAllMaterials()) {
			if(material != TinkerMaterials.blueslime) {
				registerToolpartRecipes(material);
			}
		}
	}

	private static void registerStampsRecipes() {
		for(IToolPart toolPart : TinkerRegistry.getToolParts()) {
			if(toolPart == TinkerTools.boltCore) {
				continue;
			}

			RecipeRegistry.stampingRecipes.add(new ItemStampingRecipe(Ingredient.fromItem(RegistryManager.blend_caminite), null, Ingredient.fromItem((ToolPart)toolPart), ItemStamp.setTagForPart(new ItemStack(TinkerStamp.raw_stamp), (ToolPart)toolPart)));

			RecipeRegistry.heatCoilRecipes.add(new HeatCoilRecipe(ItemStamp.setTagForPart(new ItemStack(TinkerStamp.stamp), (ToolPart)toolPart), new IngredientNBT(ItemStamp.setTagForPart(new ItemStack(TinkerStamp.raw_stamp), (ToolPart)toolPart)){}));
		}
	}

	private static void registerToolpartRecipes(Material material) {
		for(IToolPart toolPart : TinkerRegistry.getToolParts()) {
			if(!toolPart.canBeCasted() || !toolPart.canUseMaterial(material)) {
				continue;
			}

			if(toolPart instanceof MaterialItem) {
				ItemStack result = toolPart.getItemstackWithMaterial(material);
				ItemStack stamp = ItemStamp.setTagForPart(new ItemStack(TinkerStamp.stamp), (ToolPart)toolPart);

				if(FluidRegistry.isFluidRegistered(material.identifier)) {
					int cost = toolPart.getCost();

					if(material == TinkerMaterials.stone) {
						cost = cost /2;
					}

					RecipeRegistry.stampingRecipes.add(new ItemStampingRecipe(Ingredient.EMPTY, FluidRegistry.getFluidStack(material.identifier, cost), Ingredient.fromStacks(stamp), result));
				}
			}
		}
	}

}
