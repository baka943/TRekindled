package baka943.trekindled.common.recipe;

import baka943.trekindled.common.item.ModItems;
import baka943.trekindled.common.lib.LibMisc;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.Ingredient;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import slimeknights.tconstruct.TConstruct;
import slimeknights.tconstruct.common.config.Config;
import slimeknights.tconstruct.shared.TinkerCommons;
import slimeknights.tconstruct.shared.TinkerFluids;
import slimeknights.tconstruct.smeltery.TinkerSmeltery;
import teamroots.embers.RegistryManager;
import teamroots.embers.recipe.FluidMixingRecipe;
import teamroots.embers.recipe.ItemStampingRecipe;
import teamroots.embers.recipe.RecipeRegistry;

@Mod.EventBusSubscriber(modid = LibMisc.MOD_ID)
public final class RecipeRegister {

	@SubscribeEvent
	public static void registerRecipes(RegistryEvent.Register<IRecipe> event) {
		//Mixing Recipes
		registerAlloyingRecipes();

		//Casting Table Recipes
		if(TConstruct.pulseManager.isPulseLoaded(TinkerSmeltery.PulseId)) {
			RecipeRegistry.stampingRecipes.add(new ItemStampingRecipe(Ingredient.EMPTY, new FluidStack(TinkerFluids.searedStone, 72), Ingredient.fromItem(RegistryManager.stamp_bar), TinkerCommons.searedBrick));

			RecipeRegistry.stampingRecipes.add(new ItemStampingRecipe(Ingredient.EMPTY, new FluidStack(TinkerFluids.dirt, 144), Ingredient.fromItem(RegistryManager.stamp_bar), TinkerCommons.mudBrick));

			RecipeRegistry.stampingRecipes.add(new ItemStampingRecipe(Ingredient.EMPTY, new FluidStack(TinkerFluids.clay, 144), Ingredient.fromItem(RegistryManager.stamp_bar), new ItemStack(Items.BRICK)));
		}

		//Gem Stamp
		RecipeRegistry.stampingRecipes.add(new ItemStampingRecipe(Ingredient.fromItem(RegistryManager.blend_caminite), null, Ingredient.fromItem(Items.EMERALD), new ItemStack(ModItems.STAMP_GEM_RAW)));

		GameRegistry.addSmelting(new ItemStack(ModItems.STAMP_GEM_RAW), new ItemStack(ModItems.STAMP_GEM), 0.35F);
	}

	private static void registerAlloyingRecipes() {
		//125lava + 125water = 36obsidian
		if(Config.obsidianAlloy && FluidRegistry.isFluidRegistered("obsidian")) {
			RecipeRegistry.mixingRecipes.add(new FluidMixingRecipe(new FluidStack[]{new FluidStack(FluidRegistry.WATER,125), new FluidStack(FluidRegistry.LAVA,125)}, new FluidStack(TinkerFluids.obsidian,36)));
		}
		//250water + 72seared ingot + 144dirt = 144clay
		if(FluidRegistry.isFluidRegistered("stone") && FluidRegistry.isFluidRegistered("dirt") && FluidRegistry.isFluidRegistered("clay")) {
			RecipeRegistry.mixingRecipes.add(new FluidMixingRecipe(new FluidStack[]{new FluidStack(FluidRegistry.WATER, 250), new FluidStack(TinkerFluids.searedStone, 72), new FluidStack(TinkerFluids.dirt, 144)}, new FluidStack(TinkerFluids.clay, 144)));
		}
		//72iron + 125purple slime ball + 144seared ingot = 72knightslime
		if(FluidRegistry.isFluidRegistered("iron") && FluidRegistry.isFluidRegistered("purpleslime") && FluidRegistry.isFluidRegistered("stone") && FluidRegistry.isFluidRegistered("knightslime")) {
			RecipeRegistry.mixingRecipes.add(new FluidMixingRecipe(new FluidStack[]{new FluidStack(TinkerFluids.iron, 72), new FluidStack(TinkerFluids.purpleSlime, 125), new FluidStack(TinkerFluids.searedStone, 144)}, new FluidStack(TinkerFluids.knightslime, 72)));
		}
		//144iron + 40blood +72clay = 144pigiron
		if(FluidRegistry.isFluidRegistered("iron") && FluidRegistry.isFluidRegistered("blood") && FluidRegistry.isFluidRegistered("clay") && FluidRegistry.isFluidRegistered("pigiron")) {
			RecipeRegistry.mixingRecipes.add(new FluidMixingRecipe(new FluidStack[]{new FluidStack(TinkerFluids.iron, 144), new FluidStack(TinkerFluids.blood, 40), new FluidStack(TinkerFluids.clay, 72)}, new FluidStack(TinkerFluids.pigIron, 144)));
		}
		//2cobalt + 2ardite = 2manyullyn
		if(FluidRegistry.isFluidRegistered("cobalt") && FluidRegistry.isFluidRegistered("ardite") && FluidRegistry.isFluidRegistered("manyullyn")) {
			RecipeRegistry.mixingRecipes.add(new FluidMixingRecipe(new FluidStack[]{new FluidStack(TinkerFluids.cobalt,2), new FluidStack(TinkerFluids.ardite,2)}, new FluidStack(TinkerFluids.manyullyn,2)));
		}
		//1copper + 3aluminium = 4alubrass
		if(FluidRegistry.isFluidRegistered("copper") && FluidRegistry.isFluidRegistered("aluminum") && FluidRegistry.isFluidRegistered("alubrass")) {
			RecipeRegistry.mixingRecipes.add(new FluidMixingRecipe(new FluidStack[]{new FluidStack(TinkerFluids.copper, 1), new FluidStack(TinkerFluids.aluminum, 3)}, new FluidStack(TinkerFluids.alubrass, 4)));
		}
		//2copper + 1zinc = 3brass
		if(FluidRegistry.isFluidRegistered("copper") && FluidRegistry.isFluidRegistered("zinc") && FluidRegistry.isFluidRegistered("brass")) {
			RecipeRegistry.mixingRecipes.add(new FluidMixingRecipe(new FluidStack[]{new FluidStack(TinkerFluids.copper, 2), new FluidStack(TinkerFluids.zinc, 1)}, new FluidStack(TinkerFluids.brass, 3)));
		}
	}

}
