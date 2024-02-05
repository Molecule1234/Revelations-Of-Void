package net.scar.rotvmod.compat;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.registration.IGuiHandlerRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeManager;
import net.scar.rotvmod.RotvMod;
import net.scar.rotvmod.registry.ModBlocks;
import net.scar.rotvmod.recipe.VoidExtractorRecipe;
import net.scar.rotvmod.inventory.screen.VoidExtractorScreen;

import java.util.List;

@JeiPlugin
public class JEIModPlugin implements IModPlugin {
    @Override
    public ResourceLocation getPluginUid() {
        return new ResourceLocation(RotvMod.MOD_ID, "jei_plugin");
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
        registration.addRecipeCategories(new VoidExtractorCategory(registration.getJeiHelpers().getGuiHelper()));
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        RecipeManager recipeManager = Minecraft.getInstance().level.getRecipeManager();

        List<VoidExtractorRecipe> polishingRecipes = recipeManager.getAllRecipesFor(VoidExtractorRecipe.Type.INSTANCE);
        registration.addRecipes(VoidExtractorCategory.VOID_EXTRACTOR_TYPE, polishingRecipes);
    }

    @Override
    public void registerGuiHandlers(IGuiHandlerRegistration registration) {
        registration.addRecipeClickArea(VoidExtractorScreen.class, 60, 30, 20, 30,
                VoidExtractorCategory.VOID_EXTRACTOR_TYPE);
    }

    @Override
    public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {
        registration.addRecipeCatalyst(new ItemStack(ModBlocks.VOID_EXTRACTOR.get()), VoidExtractorCategory.VOID_EXTRACTOR_TYPE);
    }
}