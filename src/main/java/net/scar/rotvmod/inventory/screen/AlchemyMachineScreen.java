package net.scar.rotvmod.inventory.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.ItemStack;
import net.scar.rotvmod.RotvMod;

public class AlchemyMachineScreen extends AbstractContainerScreen<AlchemyMachineMenu> {
    private static final ResourceLocation TEXTURE =
            new ResourceLocation(RotvMod.MOD_ID, "textures/gui/alchemy_machine.png");

    public AlchemyMachineScreen(AlchemyMachineMenu pMenu, Inventory pPlayerInventory, Component pTitle) {
        super(pMenu, pPlayerInventory, pTitle);
    }

    @Override
    protected void init() {
        super.init();
        this.inventoryLabelX = 10000;
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float delta) {
        renderBackground(guiGraphics);
        super.render(guiGraphics, mouseX, mouseY, delta);
        renderTooltip(guiGraphics, mouseX, mouseY);
    }

    @Override
    protected void renderBg(GuiGraphics guiGraphics, float pPartialTick, int pMouseX, int pMouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, TEXTURE);
        int x = (width - imageWidth) / 2;
        int y = (height - imageHeight) / 2;

        guiGraphics.blit(TEXTURE, x, y, 0, 0, imageWidth, imageHeight);

        renderProgressArrow(guiGraphics, x, y);
        renderProgressFuel(guiGraphics, x, y);
        renderVoid(guiGraphics, x, y);
    }

    @Override
    protected void renderTooltip(GuiGraphics pGuiGraphics, int pX, int pY) {
        if (this.menu.getCarried().isEmpty() && this.hoveredSlot != null && this.hoveredSlot.hasItem()) {
            ItemStack itemstack = this.hoveredSlot.getItem();
            pGuiGraphics.renderTooltip(this.font, this.getTooltipFromContainerItem(itemstack), itemstack.getTooltipImage(), itemstack, pX, pY);
        }

        int i = this.leftPos;
        int j = this.topPos;

        if (pX >= (i + 39) && pX <= (i + 48) && pY >= (j + 17) && pY <= (j + 74)) {
            pGuiGraphics.renderTooltip(this.font, this.guiTextVoidFluid(), pX, pY);
        }

    }

    private MutableComponent guiTextVoidFluid() {
        String voidFluid = String.valueOf(menu.getVoidFluid());
        String voidMaxFluid = String.valueOf(menu.getMaxVoidFluid());
        return Component.translatable("gui.alchemy_machine.void_fluid", voidFluid, voidMaxFluid);
    }

    private void renderProgressArrow(GuiGraphics guiGraphics, int x, int y) {
        guiGraphics.blit(TEXTURE, x + 109, y + 31, 176, 0, menu.getScaledProgress(), 10);
    }

    private void renderProgressFuel(GuiGraphics guiGraphics, int x, int y) {
        int k = this.menu.getLitProgress();
        int j = 0;

        if (k > 0) { j = 1; }

        guiGraphics.blit(TEXTURE, x + 70, y + 55 + 13 - k, 192, 13 - k, 14, k + j);
    }

    private void renderVoid(GuiGraphics guiGraphics, int x, int y) {
        int height = Math.round((float) menu.getVoidFluid() / 36);
        if (height == 50) { height++; }
        guiGraphics.blit(TEXTURE, x + 40, y + 74 - height, 176, 15, 8, height);
    }

}