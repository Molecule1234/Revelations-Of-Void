package net.scar.rotvmod.inventory.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.scar.rotvmod.RotvMod;
import net.scar.rotvmod.registry.Almanac;
import net.scar.rotvmod.utils.CategoryAlmanac;
import net.scar.rotvmod.utils.ChapterAlmanac;

import java.util.List;

public class AlmanacScreen extends Screen {
    private static final ResourceLocation INDEX_PAGE =
            new ResourceLocation(RotvMod.MOD_ID, "textures/gui/almanac/index_page.png");
    private static final ResourceLocation VOID_FLOWER_PAGE =
            new ResourceLocation(RotvMod.MOD_ID, "textures/gui/almanac/void_flower_page.png");
    protected int imageWidth = 304;
    protected int imageHeight = 182;

    protected int pageLeftWidth = 20 - 7;
    protected int pageRightWidth = 160 - 7;

    private String location = "index";
    private final CategoryAlmanac openCategory = Almanac.CATEGORY_1;

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
        RenderSystem.setShaderTexture(0, INDEX_PAGE);

        int x = (width - imageWidth) / 2;
        int y = (height - imageHeight) / 2;

        // добавление первого слоя, в виде обложки страницы
        guiGraphics.blit(INDEX_PAGE, x, y, 0, 176, imageWidth, imageHeight, 512, 512);

        // добавление закладок
        renderCategories(guiGraphics, x, y);

        // добавление второго слоя, в виде фона страниц
        guiGraphics.blit(INDEX_PAGE, x + 16, y - 2, 16, 0, 279, 172, 512, 512);

        // renderVoidFlower(guiGraphics, x + pageRightWidth + 12, y + 8);

        if (location.equals("index")) {
            renderIndex(guiGraphics, x, y);
        } else if (location.equals("chapter")) {
        }
    }

    public void renderTooltip(GuiGraphics guiGraphics, int pX, int pY) {
        if (pX >= (pX - 20) && pX <= (pX + 6) && pY >= (pY + 2) && pY <= (pY + 14)) {
            guiGraphics.renderTooltip(this.font, Component.literal("test"), pX, pY);
        }
    }

    public void renderChapters(GuiGraphics guiGraphics, int pX, int pY) {
        int addedCount = 0;
        int pageWidth = pageLeftWidth;
        List<ChapterAlmanac> chapters = this.openCategory.getChapters();

        for (ChapterAlmanac chapter : chapters) {

            if ((addedCount + 1) > 5) {
                pageWidth = pageRightWidth;
                addedCount = -1;
            }

            guiGraphics.blit(INDEX_PAGE, pX + pageWidth + 16, pY + 40 + (22 * addedCount), 369, 16, 20, 20, 512, 512);
            guiGraphics.renderFakeItem(chapter.getIcon(), pX + pageWidth + 18, pY + 43 + (22 * addedCount));
            guiGraphics.renderItemDecorations(this.font, chapter.getIcon(), pX + pageWidth + 18, pY + 43 + (22 * addedCount), null);

            guiGraphics.drawString(this.font, Component.literal(chapter.getName()), pX + pageWidth + 38, pY + 48 + (22 * addedCount), 0x000000, false);
            guiGraphics.blit(INDEX_PAGE, pX + pageWidth + 36, pY + 56 + (22 * addedCount), 7, 359, 98, 4, 512, 512);

            ++addedCount;
        }
    }

    public void renderCategories(GuiGraphics guiGraphics, int pX, int pY) {
        for (int i = 0; i < Almanac.CATEGORIES.size(); i++) {
            CategoryAlmanac category = Almanac.CATEGORIES.get(i);
            guiGraphics.blit(INDEX_PAGE, pX - 21, pY + 12 + (i * 21), 306, i * 19, 60, 19, 512, 512);
            guiGraphics.renderFakeItem(category.getIcon(), pX - 9, pY + 12 + (i * 21));
            guiGraphics.renderItemDecorations(this.font, category.getIcon(), pX - 9, pY + 12 + (i * 21), null);
        }
    }

    public void renderIndex(GuiGraphics guiGraphics, int pX, int pY) {
        guiGraphics.drawString(this.font, Component.literal(this.openCategory.getName()), pX + pageLeftWidth + 40, pY + 16, 0x000000, false);
        //guiGraphics.drawWordWrap(this.font, Component.literal("Vestibulum non sapien in mi facilisis pharetra. In id hendrerit magna, sed fringilla tellus. Sed nibh nibh, aliquet sit amet nisl sed, ornare placerat eros. Praesent elementum erat at placerat varius. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia curae; Praesent hendrerit mattis tempus."), pX + pageLeftWidth + 12, pY + 26, 128, 0x000000);
        guiGraphics.blit(INDEX_PAGE, pX + pageLeftWidth + 14, pY + 26, 7, 364, 111, 6, 512, 512);
        renderChapters(guiGraphics, pX, pY);
    }

    public void renderVoidFlower(GuiGraphics guiGraphics, int pX, int pY) {
        guiGraphics.blit(VOID_FLOWER_PAGE, pX, pY, 70, 50, 119, imageHeight, 256, 256);
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }

    private void home() {
        this.location = "index";
    }

    private void chapter() {
        this.location = "chapter";
    }

}