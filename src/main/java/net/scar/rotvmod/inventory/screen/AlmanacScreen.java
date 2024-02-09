package net.scar.rotvmod.inventory.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.scar.rotvmod.RotvMod;
import net.scar.rotvmod.registry.AlmanacCategories;
import net.scar.rotvmod.utils.CategoryAlmanac;

import java.util.ArrayList;
import java.util.List;

public class AlmanacScreen extends Screen {
    private static final ResourceLocation TEXTURE =
            new ResourceLocation(RotvMod.MOD_ID, "textures/gui/almanac/index_page.png");
    protected int imageWidth = 208;
    protected int imageHeight = 104;

    public AlmanacScreen() {
        super(Component.translatable("gui.rotv.almanac.title"));
    }

    @Override
    protected void init() {
        super.init();
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float delta) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, TEXTURE);
        int x = (width - imageWidth) / 2;
        int y = (height - imageHeight) / 2;

        // добавление первого слоя, в виде обложки страницы
        guiGraphics.blit(TEXTURE, x, y, 0, 0, imageWidth, imageHeight);

        // добавление закладок
        for (int i = 0; i < AlmanacCategories.CATEGORYIES.size(); i++) {
            guiGraphics.blit(TEXTURE, x - 20, y + 4 + (i * 12), 210, i * 12, 44, 12);
        }

        // добавление второго слоя, в виде фона страниц
        guiGraphics.blit(TEXTURE, x - 53, y - 2, imageWidth - 10, imageHeight, imageWidth + 60, imageHeight - 6);

        renderTooltip(guiGraphics, mouseX, mouseY);
    }

    public void renderTooltip(GuiGraphics pGuiGraphics, int pX, int pY) {
        int i = (this.width - this.imageWidth) / 2;
        int j = (this.height - this.imageHeight) / 2;
        if (pX >= (i - 20) && pX <= (i + 6) && pY >= (j + 2) && pY <= (j + 14)) {
            pGuiGraphics.renderTooltip(this.font, Component.literal("test"), pX, pY);
        }
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }

}