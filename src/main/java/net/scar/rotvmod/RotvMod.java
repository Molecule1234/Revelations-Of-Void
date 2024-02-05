package net.scar.rotvmod;

import com.mojang.logging.LogUtils;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FlowerPotBlock;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.scar.rotvmod.registry.ModBlocks;
import net.scar.rotvmod.client.renders.ModBoatRenderer;
import net.scar.rotvmod.datagen.tree.ModFoliagePlacers;
import net.scar.rotvmod.datagen.tree.ModTrunkPlacerTypes;
import net.scar.rotvmod.registry.ModBlockEntities;
import net.scar.rotvmod.registry.ModEntities;
import net.scar.rotvmod.item.ModCreativeTabs;
import net.scar.rotvmod.registry.ModItems;
import net.scar.rotvmod.registry.ModRecipes;
import net.scar.rotvmod.inventory.screen.AlchemyMachineScreen;
import net.scar.rotvmod.inventory.screen.VoidExtractorScreen;
import net.scar.rotvmod.registry.ModMenuTypes;
import net.scar.rotvmod.registry.ModSounds;
import net.scar.rotvmod.registry.ModWoodTypes;
import org.slf4j.Logger;
import net.minecraft.client.renderer.entity.EntityRenderers;

@Mod(RotvMod.MOD_ID)
public class RotvMod {
    public static final String MOD_ID = "rotv";
    public static final Logger LOGGER = LogUtils.getLogger();

    public RotvMod() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModCreativeTabs.register(modEventBus);

        ModItems.register(modEventBus);

        ModBlocks.register(modEventBus);
        ModBlockEntities.register(modEventBus);
        ModMenuTypes.register(modEventBus);

        ModRecipes.register(modEventBus);

        ModEntities.register(modEventBus);

        ModTrunkPlacerTypes.register(modEventBus);
        ModFoliagePlacers.register(modEventBus);

        ModSounds.register(modEventBus);

        modEventBus.addListener(this::commonSetup);

        MinecraftForge.EVENT_BUS.register(this);
        modEventBus.addListener(this::addCreative);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(ModBlocks.VOID_FLOWER.getId(), ModBlocks.POTTED_VOID_FLOWER);
            ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(ModBlocks.SLIME_FLOWER.getId(), ModBlocks.POTTED_SLIME_FLOWER);
        });
    }

    // Add the example block item to the building blocks tab
    private void addCreative(BuildCreativeModeTabContentsEvent event) {
        if(event.getTabKey() == ModCreativeTabs.MAIN_TAB.getKey()) {
            event.accept(ModItems.ALCHEMIST_ALMANAC);


            event.accept(ModBlocks.VOID_LOG);
            event.accept(ModBlocks.VOID_WOOD);
            event.accept(ModBlocks.STRIPPED_VOID_LOG);
            event.accept(ModBlocks.STRIPPED_VOID_WOOD);
            event.accept(ModBlocks.VOID_PLANKS);
            event.accept(ModBlocks.VOID_STAIRS);
            event.accept(ModBlocks.VOID_SLAB);
            event.accept(ModBlocks.VOID_WALL);
            event.accept(ModBlocks.VOID_FENCE);
            event.accept(ModBlocks.VOID_FENCE_GATE);
            event.accept(ModBlocks.VOID_DOOR);
            event.accept(ModBlocks.VOID_TRAPDOOR);
            event.accept(ModBlocks.VOID_PRESSURE_PLATE);
            event.accept(ModBlocks.VOID_BUTTON);
            event.accept(ModBlocks.VOID_SIGN);
            event.accept(ModBlocks.VOID_HANGING_SIGN);
            event.accept(ModItems.VOID_BOAT);
            event.accept(ModItems.VOID_CHEST_BOAT);

            // Ingots
            event.accept(ModItems.INGOT_ALTURIUM);
            event.accept(ModItems.INGOT_DEORIUM);
            event.accept(ModItems.INGOT_OVERFLOWN_DEORIUM);

            // Nuggets
            event.accept(ModItems.NUGGET_ALTURIUM);
            event.accept(ModItems.NUGGET_DEORIUM);
            event.accept(ModItems.NUGGET_OVERFLOWN_DEORIUM);

            // Raws
            event.accept(ModItems.RAW_ALTURIUM);
            event.accept(ModItems.RAW_DEORIUM);
            event.accept(ModItems.RAW_OVERFLOWN_DEORIUM);


            event.accept(ModItems.CLEAR_RUNE);
            event.accept(ModItems.VOID_TREE_BARK);


            event.accept(ModBlocks.VOID_FLOWER);
            event.accept(ModBlocks.SLIME_FLOWER);
            event.accept(ModBlocks.VOID_SAPLING);


            event.accept(ModBlocks.VOID_LEAVES);
            event.accept(ModBlocks.VOID_LEAVES_MONOCHROME);
            event.accept(ModBlocks.FLOWERING_VOID_LEAVES);


            event.accept(ModItems.VOID_GEM);
            event.accept(ModBlocks.ALCHEMY_MACHINE);
            event.accept(ModBlocks.VOID_EXTRACTOR);


            event.accept(ModItems.IMBUED_BRICK);
            event.accept(ModBlocks.IMBUED_STONE);
            event.accept(ModBlocks.IMBUED_STONE_BRICKS);
            event.accept(ModBlocks.CRACKED_IMBUED_STONE_BRICKS);
            event.accept(ModBlocks.IMBUED_STONE_TILE);
            event.accept(ModBlocks.CARVED_IMBUED_STONE);
            event.accept(ModBlocks.POLISHED_IMBUED_STONE);


            event.accept(ModBlocks.IMBUED_STONE_STAIRS);
            event.accept(ModBlocks.IMBUED_STONE_BRICKS_STAIRS);
            event.accept(ModBlocks.POLISHED_IMBUED_STONE_STAIRS);

            event.accept(ModBlocks.IMBUED_STONE_SLAB);
            event.accept(ModBlocks.IMBUED_STONE_BRICKS_SLAB);
            event.accept(ModBlocks.POLISHED_IMBUED_STONE_SLAB);


            event.accept(ModBlocks.IMBUED_STONE_WALL);
            event.accept(ModBlocks.IMBUED_STONE_BRICKS_WALL);
            event.accept(ModBlocks.POLISHED_IMBUED_STONE_WALL);


            event.accept(ModBlocks.IMBUED_STONE_BUTTON);
            event.accept(ModBlocks.IMBUED_STONE_BRICKS_BUTTON);
            event.accept(ModBlocks.POLISHED_IMBUED_STONE_BUTTON);


            event.accept(ModItems.AURORA_MUSIC_DISC);
            event.accept(ModItems.CALM_MUSIC_DISC);
            event.accept(ModItems.DEORIUM_MUSIC_DISC);
            event.accept(ModItems.ELEVEN_MUSIC_DISC);
            event.accept(ModItems.PURPLE_MUSIC_DISC);
            event.accept(ModItems.REVELATIONS_MUSIC_DISC);
            event.accept(ModItems.VOID_MUSIC_DISC);
        }
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {

    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            Sheets.addWoodType(ModWoodTypes.VOID);

            EntityRenderers.register(ModEntities.MOD_BOAT.get(), pContext -> new ModBoatRenderer(pContext, false));
            EntityRenderers.register(ModEntities.MOD_CHEST_BOAT.get(), pContext -> new ModBoatRenderer(pContext, true));

            MenuScreens.register(ModMenuTypes.VOID_EXTRACTOR_MENU.get(), VoidExtractorScreen::new);
            MenuScreens.register(ModMenuTypes.ALCHEMY_FURNACE_MENU.get(), AlchemyMachineScreen::new);
        }
    }
}
