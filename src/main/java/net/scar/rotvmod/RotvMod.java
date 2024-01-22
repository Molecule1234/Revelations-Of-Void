package net.scar.rotvmod;

import com.mojang.logging.LogUtils;
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
import net.scar.rotvmod.block.ModBlocks;
import net.scar.rotvmod.item.ModCreativeTabs;
import net.scar.rotvmod.item.ModItems;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(RotvMod.MOD_ID)
public class RotvMod {
    public static final String MOD_ID = "rotv";
    public static final Logger LOGGER = LogUtils.getLogger();

    public RotvMod() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModCreativeTabs.register(modEventBus);

        ModItems.register(modEventBus);
        ModBlocks.register(modEventBus);

        modEventBus.addListener(this::commonSetup);

        MinecraftForge.EVENT_BUS.register(this);
        modEventBus.addListener(this::addCreative);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {

    }

    // Add the example block item to the building blocks tab
    private void addCreative(BuildCreativeModeTabContentsEvent event) {
        if(event.getTabKey() == ModCreativeTabs.MAIN_TAB.getKey()) {
            event.accept(ModItems.ANCIENT_TOME);

            // Ingots
            event.accept(ModItems.INGOT_ALTURIUM);
            event.accept(ModItems.INGOT_DEORIUM);
            event.accept(ModItems.INGOT_OVERFLOWN_DEORIUM);

            // Nuggets
            event.accept(ModItems.NUGGET_DEORIUM);
            event.accept(ModItems.NUGGET_ALTURIUM);
            event.accept(ModItems.NUGGET_OVERFLOWN_DEORIUM);

            // Raws
            event.accept(ModItems.RAW_ALTURIUM);
            event.accept(ModItems.RAW_DEORIUM);
            event.accept(ModItems.RAW_OVERFLOWN_DEORIUM);

            event.accept(ModItems.CLEAR_RUNE);
            event.accept(ModItems.IMBUED_BRICK);

            // Wood
            event.accept(ModBlocks.VOID_LOG);
            event.accept(ModBlocks.STRIPPED_VOID_LOG);
            event.accept(ModBlocks.VOID_WOOD);
            event.accept(ModBlocks.STRIPPED_VOID_WOOD);
            event.accept(ModBlocks.VOID_PLANKS);
            event.accept(ModBlocks.VOID_LEAVES);
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

        }
    }
}
