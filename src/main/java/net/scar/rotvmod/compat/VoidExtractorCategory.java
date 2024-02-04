package net.scar.rotvmod.compat;

import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.scar.rotvmod.RotvMod;
import net.scar.rotvmod.block.ModBlocks;
import net.scar.rotvmod.recipe.VoidExtractorRecipe;

public class VoidExtractorCategory implements IRecipeCategory<VoidExtractorRecipe> {
    public static final ResourceLocation UID = new ResourceLocation(RotvMod.MOD_ID, "void_extractor");
    public static final ResourceLocation TEXTURE = new ResourceLocation(RotvMod.MOD_ID,
            "textures/gui/void_extractor.png");

    public static final RecipeType<VoidExtractorRecipe> VOID_EXTRACTOR_TYPE =
            new RecipeType<>(UID, VoidExtractorRecipe.class);

    private final IDrawable background;
    private final IDrawable icon;

    public VoidExtractorCategory(IGuiHelper helper) {
        this.background = helper.createDrawable(TEXTURE, 0, 0, 172, 79);
        this.icon = helper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(ModBlocks.VOID_EXTRACTOR.get()));
    }

    @Override
    public RecipeType<VoidExtractorRecipe> getRecipeType() {
        return VOID_EXTRACTOR_TYPE;
    }

    @Override
    public Component getTitle() {
        return Component.translatable("block.rotv.void_extractor");
    }

    @Override
    public IDrawable getBackground() {
        return this.background;
    }

    @Override
    public IDrawable getIcon() {
        return this.icon;
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, VoidExtractorRecipe recipe, IFocusGroup focuses) {
        builder.addSlot(RecipeIngredientRole.INPUT, 56, 16).addIngredients(recipe.getIngredients().get(0));

        builder.addSlot(RecipeIngredientRole.OUTPUT, 104, 32).addItemStack(recipe.getResultItem(null));
    }
}