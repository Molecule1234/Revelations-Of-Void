package net.scar.rotvmod.compat;

import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.scar.rotvmod.RotvMod;
import net.scar.rotvmod.registry.ModBlocks;
import net.scar.rotvmod.recipe.VoidExtractorRecipe;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class VoidExtractorCategory implements IRecipeCategory<VoidExtractorRecipe> {
    public static final ResourceLocation UID = new ResourceLocation(RotvMod.MOD_ID, "void_extractor");
    public static final ResourceLocation TEXTURE = new ResourceLocation(RotvMod.MOD_ID,
            "textures/gui/void_extractor.png");

    public static final RecipeType<VoidExtractorRecipe> VOID_EXTRACTOR_TYPE =
            new RecipeType<>(UID, VoidExtractorRecipe.class);

    private final IDrawable background;
    private final IDrawable icon;

    public VoidExtractorCategory(IGuiHelper helper) {
        this.background = helper.createDrawable(TEXTURE, 4, 4, 168, 72);
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
        builder.addSlot(RecipeIngredientRole.INPUT, 52, 12).addIngredients(recipe.getIngredients().get(0));

        builder.addSlot(RecipeIngredientRole.OUTPUT, 100, 29).addItemStack(recipe.getResultItem(null));
    }

    @Override
    public void draw(@NotNull VoidExtractorRecipe recipe, @NotNull IRecipeSlotsView view, @NotNull GuiGraphics gui, double mouseX, double mouseY) {
        int height = Math.round((float) recipe.getVoidFluid() / 40);
        if (height == 50) { height++; }
        gui.blit(TEXTURE, 124, 62 - height, 176, 18, 8, height);
    }

    @Override
    public List<Component> getTooltipStrings(VoidExtractorRecipe recipe, IRecipeSlotsView recipeSlotsView, double mouseX, double mouseY) {
        if (mouseX >= 124 && mouseX <= 131 && mouseY >= 10 && mouseY <= 61) {
            String voidFluid = String.valueOf(recipe.getVoidFluid());
            String voidMaxFluid = String.valueOf(2000);
            return List.of(Component.translatable("gui.void_extractor.void_fluid", voidFluid, voidMaxFluid));
        }

        return List.of();
    }
}