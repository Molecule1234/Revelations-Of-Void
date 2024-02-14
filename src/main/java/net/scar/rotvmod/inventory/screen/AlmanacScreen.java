package net.scar.rotvmod.inventory.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.inventory.BookViewScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.scar.rotvmod.RotvMod;
import net.scar.rotvmod.registry.Almanac;
import net.scar.rotvmod.utils.*;

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
    private CategoryAlmanac openCategory = Almanac.CATEGORY_1;
    private ChapterAlmanac openChapter = null;
    private int pageIndex = 1;
    private final SpatialIndex spatialIndex = new SpatialIndex(10000, 10000);

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

        guiGraphics.fillGradient(0, 0, this.width, this.height, -1072689136, -804253680);
        net.minecraftforge.common.MinecraftForge.EVENT_BUS.post(new net.minecraftforge.client.event.ScreenEvent.BackgroundRendered(this, guiGraphics));

        int x = (width - imageWidth) / 2;
        int y = (height - imageHeight) / 2;

        // добавление первого слоя, в виде обложки страницы
        guiGraphics.blit(INDEX_PAGE, x, y, 0, 176, imageWidth, imageHeight, 512, 512);

        // добавление закладок
        renderCategories(guiGraphics, x, y);

        // добавление второго слоя, в виде фона страниц
        guiGraphics.blit(INDEX_PAGE, x + 16, y - 2, 16, 0, 279, 172, 512, 512);

        renderButtons(guiGraphics, x, y);

        // renderVoidFlower(guiGraphics, x + pageRightWidth + 12, y + 8);

        if (location.equals("index")) {
            if (this.pageIndex == 1) {
                guiGraphics.drawString(this.font, Component.literal(this.openCategory.getName()), x + pageLeftWidth + 40, y + 16, 0x000000, false);
                //guiGraphics.drawWordWrap(this.font, Component.literal("Vestibulum non sapien in mi facilisis pharetra. In id hendrerit magna, sed fringilla tellus. Sed nibh nibh, aliquet sit amet nisl sed, ornare placerat eros. Praesent elementum erat at placerat varius. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia curae; Praesent hendrerit mattis tempus."), pX + pageLeftWidth + 12, pY + 26, 128, 0x000000);
                guiGraphics.blit(INDEX_PAGE, x + pageLeftWidth + 14, y + 26, 7, 364, 111, 6, 512, 512);
            }
            renderChapters(guiGraphics, x, y);
        } else if (location.equals("chapter")) {
            renderDescriptionChapters(guiGraphics, x, y);
        }

        renderTooltip(guiGraphics, mouseX, mouseY);
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int pButton) {
        // Поиск элементов в определенной точке
        List<Object> foundItems = spatialIndex.search((int) mouseX, (int) mouseY);

        if (!foundItems.isEmpty()) {
            for (Object item : foundItems) {
                if (item instanceof CategoryAlmanac category) {
                    this.location = "index";
                    this.openCategory = category;
                    this.pageIndex = 1;

                    spatialIndex.clearChapterAlmanacs();

                    return true;

                } else if (item instanceof ChapterAlmanac chapter) {
                    if (chapter.getPages().size() > 1) {
                        this.location = "chapter";
                        this.pageIndex = 1;
                        this.openChapter = chapter;

                        spatialIndex.clearChapterAlmanacs();

                        return true;
                    }
                } else if (item instanceof ButtonAlmanac button) {
                    if (this.location.equals("index")) {
                        boolean hasNextEmptyPages = hasNextEmptyPages(pageIndex + 1, this.openCategory.getChapters());

                        if (button.getType().equals("prev") && this.pageIndex != 1) {
                            this.pageIndex -= 2;
                            spatialIndex.clearChapterAlmanacs();
                        } else if (button.getType().equals("next") && !hasNextEmptyPages) {
                            this.pageIndex += 2;
                            spatialIndex.clearChapterAlmanacs();
                        }
                    } else if (this.location.equals("chapter")) {
                        if (button.getType().equals("prev") && this.pageIndex != 1) {
                            this.pageIndex -= 2;
                        } else if (button.getType().equals("next") && (this.pageIndex + 2) <= this.openChapter.getPages().size()) {
                            this.pageIndex += 2;
                        }
                    }

                    return true;
                }
            }
        }
        return false;
    }

    public void renderTooltip(GuiGraphics guiGraphics, int pX, int pY) {
        // Поиск элементов в определенной точке
        List<Object> foundItems = spatialIndex.search( pX, pY);

        if (!foundItems.isEmpty()) {
            for (Object item : foundItems) {
                if (item instanceof CategoryAlmanac category) {
                    Component text = category != this.openCategory
                            ? Component.literal("Click to open category")
                            : Component.literal("This category open");
                    guiGraphics.renderTooltip(this.font, text, pX, pY);
                } else if (item instanceof ChapterAlmanac chapter) {
                    Component text = chapter.getPages().size() < 1
                            ? Component.literal("Chapter is empty")
                            : Component.literal("Click to open chapter");
                    guiGraphics.renderTooltip(this.font, text, pX, pY);
                } else if (item instanceof ButtonAlmanac button) {
                    if (button.getType().equals("prev") || button.getType().equals("next")) {
                        if (this.location.equals("index")) {
                            boolean hasNextEmptyPages = hasNextEmptyPages(pageIndex + 1, this.openCategory.getChapters());
                            Component text = !hasNextEmptyPages
                                    ? Component.literal("Click to go to " + button.getType() + " page.")
                                    : Component.literal("There are no more pages");
                            guiGraphics.renderTooltip(this.font, text, pX, pY);
                        } else if (this.location.equals("chapter")) {
                            int index = button.getType().equals("next") ? this.pageIndex + 2 : this.pageIndex - 2;

                            if (index <= 0 || index > this.openChapter.getPages().size()) {
                                // Индекс выходит за пределы диапазона страниц
                                guiGraphics.renderTooltip(this.font, Component.literal("There are no more pages"), pX, pY);
                            } else {
                                // Индекс находится в допустимом диапазоне
                                guiGraphics.renderTooltip(this.font, Component.literal("Click to go to " + button.getType() + " page."), pX, pY);
                            }
                        }
                    }
                }
            }
        }

    }

    public void renderChapters(GuiGraphics guiGraphics, int pX, int pY) {
        List<ChapterAlmanac> chaptersOnFirstPage = this.openCategory.getChaptersPage(this.pageIndex);
        List<ChapterAlmanac> chaptersOnSecondPage = this.openCategory.getChaptersPage(this.pageIndex + 1);

        int addedCount = this.pageIndex == 1 ? 0 : -1;

        for (ChapterAlmanac chapter : chaptersOnFirstPage) {
            renderChaptersButton(guiGraphics, pX, pY, pageLeftWidth, chapter, addedCount);
            ++addedCount;
        }

        addedCount = -1;
        for (ChapterAlmanac chapter : chaptersOnSecondPage) {
            renderChaptersButton(guiGraphics, pX, pY, pageRightWidth, chapter, addedCount);
            ++addedCount;
        }
    }

    public void renderDescriptionChapters(GuiGraphics guiGraphics, int pX, int pY) {
        if (this.pageIndex - 1 >= 0 && this.pageIndex - 1 < this.openChapter.getPages().size()) {
            PageAlmanac pageOnFirstPage = this.openChapter.getPages().get(this.pageIndex - 1);
            renderChapterPage(guiGraphics, pX, pY, pageLeftWidth, pageOnFirstPage);
        }

        if (this.pageIndex >= 0 && this.pageIndex < this.openChapter.getPages().size()) {
            PageAlmanac pageOnSecondPage = this.openChapter.getPages().get(this.pageIndex);
            renderChapterPage(guiGraphics, pX, pY, pageRightWidth, pageOnSecondPage);
        }


    }

    public void renderChaptersButton(GuiGraphics guiGraphics, int pX, int pY, int pageWidth, ChapterAlmanac chapter, int addedCount) {
        guiGraphics.blit(INDEX_PAGE, pX + pageWidth + 16, pY + 40 + (22 * addedCount), 369, 16, 20, 20, 512, 512);
        guiGraphics.renderFakeItem(chapter.getIcon(), pX + pageWidth + 18, pY + 43 + (22 * addedCount));
        guiGraphics.renderItemDecorations(this.font, chapter.getIcon(), pX + pageWidth + 18, pY + 43 + (22 * addedCount), null);

        guiGraphics.drawString(this.font, Component.literal(chapter.getName()), pX + pageWidth + 38, pY + 48 + (22 * addedCount), 0x000000, false);
        guiGraphics.blit(INDEX_PAGE, pX + pageWidth + 36, pY + 56 + (22 * addedCount), 7, 359, 98, 4, 512, 512);

        spatialIndex.add(chapter, pX + pageWidth + 38, pY + 48 + (22 * addedCount), this.font.width(chapter.getName()), this.font.lineHeight);
    }

    public void renderChapterPage(GuiGraphics guiGraphics, int pX, int pY, int pageWidth, PageAlmanac page) {
        if (page.type.equals("text")) {
            guiGraphics.drawString(this.font, Component.literal(page.title), pX + pageWidth + 40, pY + 16, 0x000000, false);
            guiGraphics.blit(INDEX_PAGE, pX + pageWidth + 14, pY + 26, 7, 364, 111, 6, 512, 512);
            guiGraphics.drawWordWrap(this.font, Component.literal(page.description), pX + pageWidth + 12, pY + 36, 128, 0x000000);
        }

    }

    public void renderButtons(GuiGraphics guiGraphics, int pX, int pY) {
        guiGraphics.blit(INDEX_PAGE, pX + 2, pY + 152, 369, 0, 24, 14, 512, 512);
        guiGraphics.blit(INDEX_PAGE, pX + 286, pY + 152, 395, 0, 24, 14, 512, 512);

        spatialIndex.add(Almanac.BUTTON_PREV, pX + 3, pY + 152, 23, 14);
        spatialIndex.add(Almanac.BUTTON_NEXT, pX + 287, pY + 152, 23, 14);
    }

    public void renderCategories(GuiGraphics guiGraphics, int pX, int pY) {
        for (int i = 0; i < Almanac.CATEGORIES.size(); i++) {
            CategoryAlmanac category = Almanac.CATEGORIES.get(i);
            if (category == this.openCategory) {
                guiGraphics.blit(INDEX_PAGE, pX - 21 - 10, pY + 12 + (i * 21), 306, i * 19, 60, 19, 512, 512);
                guiGraphics.renderFakeItem(category.getIcon(), pX - 9 - 10, pY + 12 + (i * 21));
                guiGraphics.renderItemDecorations(this.font, category.getIcon(), pX - 9 - 10, pY + 12 + (i * 21), null);

                spatialIndex.add(category, pX - 21 - 10, pY + 12 + (i * 21), 48, 18);
            } else {
                guiGraphics.blit(INDEX_PAGE, pX - 21, pY + 12 + (i * 21), 306, i * 19, 60, 19, 512, 512);
                guiGraphics.renderFakeItem(category.getIcon(), pX - 9, pY + 12 + (i * 21));
                guiGraphics.renderItemDecorations(this.font, category.getIcon(), pX - 9, pY + 12 + (i * 21), null);

                spatialIndex.add(category, pX - 21, pY + 12 + (i * 21), 37, 18);
            }
        }
    }

    public void renderVoidFlower(GuiGraphics guiGraphics, int pX, int pY) {
        guiGraphics.blit(VOID_FLOWER_PAGE, pX, pY, 70, 50, 119, imageHeight, 256, 256);
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }

    // Функция для проверки наличия следующих пустых страниц
    private boolean hasNextEmptyPages(int pageIndex, List<ChapterAlmanac> chapters) {
        int totalLeftChapters = chapters.size() - 5;
        int totalPages = (int) Math.ceil((double) totalLeftChapters / 6) + 1;
        return pageIndex >= totalPages;
    }

}