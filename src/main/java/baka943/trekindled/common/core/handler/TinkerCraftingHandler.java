package baka943.trekindled.common.core.handler;

import baka943.trekindled.common.config.TRekindledConfig;
import baka943.trekindled.common.item.ModItems;
import baka943.trekindled.common.lib.LibMisc;
import net.minecraft.client.resources.I18n;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.Event;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.oredict.OreDictionary;
import slimeknights.tconstruct.library.events.TinkerCraftingEvent;
import slimeknights.tconstruct.library.events.TinkerRegisterEvent;
import slimeknights.tconstruct.library.smeltery.CastingRecipe;
import slimeknights.tconstruct.library.smeltery.ICast;
import slimeknights.tconstruct.library.smeltery.ICastingRecipe;
import slimeknights.tconstruct.library.smeltery.MeltingRecipe;
import slimeknights.tconstruct.library.tinkering.MaterialItem;
import slimeknights.tconstruct.library.tools.IToolPart;
import slimeknights.tconstruct.smeltery.TinkerSmeltery;
import slimeknights.tconstruct.tools.TinkerTools;
import teamroots.embers.RegistryManager;
import teamroots.embers.recipe.ItemMeltingRecipe;
import teamroots.embers.recipe.ItemStampingRecipe;
import teamroots.embers.recipe.RecipeRegistry;

import java.util.List;

@Mod.EventBusSubscriber(modid = LibMisc.MOD_ID)
public final class TinkerCraftingHandler {

	@SubscribeEvent(priority = EventPriority.HIGHEST)
	public static void onTooltip(ItemTooltipEvent event) {
		if(event.getEntityPlayer() == null) return;

		if(TRekindledConfig.COMPAT.iguanaModel) {
			ItemStack stack = event.getItemStack();

			if(!(stack.getItem() instanceof IToolPart) || stack.getItem() == TinkerTools.bowString || stack.getItem() == TinkerTools.fletching || stack.getItem() == TinkerTools.arrowShaft) {
				return ;
			}

			IToolPart toolPart = (IToolPart)stack.getItem();

			if(toolPart.getMaterial(stack).isCraftable()) {
				event.getToolTip().add(1, "");
				event.getToolTip().add(2, I18n.format("tooltip." + LibMisc.MOD_ID + ".castonly1"));
				event.getToolTip().add(3, I18n.format("tooltip." + LibMisc.MOD_ID + ".castonly2"));

				event.setResult(Event.Result.DENY);
			}
		}
	}

	@SubscribeEvent(priority = EventPriority.HIGH)
	public static void onToolCraft(TinkerCraftingEvent.ToolCraftingEvent event) {
		if(event.getPlayer() == null) return;

		if(TRekindledConfig.COMPAT.iguanaModel) {
			for(ItemStack stack : event.getToolParts()) {
				if(!(stack.getItem() instanceof IToolPart) || stack.getItem() == TinkerTools.bowString || stack.getItem() == TinkerTools.fletching || stack.getItem() == TinkerTools.arrowShaft) {
					return ;
				}

				IToolPart toolPart = (IToolPart)stack.getItem();

				if(toolPart.getMaterial(stack).isCraftable()) {
					event.setCanceled( I18n.format("gui.error." + LibMisc.MOD_ID + ".tool.default"));
				}
			}
		}
	}

	@SubscribeEvent
	public static void alloyRecipe(TinkerRegisterEvent.AlloyRegisterEvent event) {
		event.setCanceled(TRekindledConfig.COMPAT.disableTinkerAlloys);
	}

	@SubscribeEvent
	public static void meltingRecipe(TinkerRegisterEvent.MeltingRegisterEvent event) {
		MeltingRecipe recipe = event.getRecipe();
		List<ItemStack> inputs = recipe.input.getInputs();
		FluidStack output = recipe.getResult();

		for(ItemStack stack : inputs) {
			RecipeRegistry.meltingRecipes.add(new ItemMeltingRecipe(Ingredient.fromStacks(stack), output));
		}

		event.setCanceled(TRekindledConfig.COMPAT.disableTinkerMelting);
	}

	@SubscribeEvent
	public static void tableCastingRecipe(TinkerRegisterEvent.TableCastingRegisterEvent event) {
		ICastingRecipe recipe = event.getRecipe();

		if(recipe instanceof CastingRecipe) {
			CastingRecipe castingRecipe = (CastingRecipe)recipe;
			ItemStack result = castingRecipe.getResult();
			FluidStack fluid = castingRecipe.getFluid();

			if(!result.isEmpty() && !(result.getItem() instanceof ICast) && !(result.getItem() instanceof MaterialItem)) {
				String ordName = getOrdName(result);

				if(ordName.contains("ingot")) {
					RecipeRegistry.stampingRecipes.add(new ItemStampingRecipe(Ingredient.EMPTY, fluid, Ingredient.fromItem(RegistryManager.stamp_bar), result));
				}

				if(ordName.contains("plate")) {
					RecipeRegistry.stampingRecipes.add(new ItemStampingRecipe(Ingredient.EMPTY, fluid, Ingredient.fromItem(RegistryManager.stamp_plate), result));
				}

				if(ordName.contains("gear")) {
					RecipeRegistry.stampingRecipes.add(new ItemStampingRecipe(Ingredient.EMPTY, new FluidStack(fluid.getFluid(), 288), Ingredient.fromItem(RegistryManager.stamp_gear), result));
				}

				if(ordName.contains("gem")) {
					RecipeRegistry.stampingRecipes.add(new ItemStampingRecipe(Ingredient.EMPTY, fluid, Ingredient.fromItem(ModItems.STAMP_GEM), result));
				}
			}
		}
	}

	@SubscribeEvent
	public static void basinCastingRecipe(TinkerRegisterEvent.BasinCastingRegisterEvent event) {
		ICastingRecipe recipe = event.getRecipe();

		if(recipe instanceof CastingRecipe) {
			CastingRecipe castingRecipe = (CastingRecipe)recipe;
			ItemStack result = castingRecipe.getResult();
			FluidStack fluid = castingRecipe.getFluid();

			if(!result.isEmpty() && !(result.getItem() instanceof ICast) && !(result.getItem() instanceof MaterialItem)) {
				String ordName = getOrdName(result);

				if(ordName.contains("block")) {
					if(result.equals(new ItemStack(TinkerSmeltery.searedStairsCobble))) {
						RecipeRegistry.stampingRecipes.add(new ItemStampingRecipe(Ingredient.fromStacks(new ItemStack(Blocks.COBBLESTONE)), fluid, Ingredient.fromItem(RegistryManager.stamp_flat), result));
					} else {
						RecipeRegistry.stampingRecipes.add(new ItemStampingRecipe(Ingredient.EMPTY, fluid, Ingredient.fromItem(RegistryManager.stamp_flat), result));
					}
				}
			}
		}
	}

	private static String getOrdName(ItemStack stack) {
		for(String key : OreDictionary.getOreNames()) {
			for(ItemStack item : OreDictionary.getOres(key)) {
				if(item.getItem() == stack.getItem() && (item.getItemDamage() == OreDictionary.WILDCARD_VALUE || item.getItemDamage() == stack.getItemDamage())) {
					return key;
				}
			}
		}

		return "null";
	}

}
