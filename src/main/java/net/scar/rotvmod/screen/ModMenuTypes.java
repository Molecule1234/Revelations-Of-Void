package net.scar.rotvmod.screen;

import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.network.IContainerFactory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.scar.rotvmod.RotvMod;

public class ModMenuTypes {
    public static final DeferredRegister<MenuType<?>> MENUS =
            DeferredRegister.create(ForgeRegistries.MENU_TYPES, RotvMod.MOD_ID);

    public static final RegistryObject<MenuType<AlchemyFurnaceMenu>> ALCHEMY_FURNACE_MENU =
            registerMenuType("alchemy_furnace_menu", AlchemyFurnaceMenu::new);
    public static final RegistryObject<MenuType<VoidExtractorMenu>> VOID_EXTRACTOR_MENU =
            registerMenuType("void_extractor_menu", VoidExtractorMenu::new);

    private static <T extends AbstractContainerMenu>RegistryObject<MenuType<T>> registerMenuType(String name, IContainerFactory<T> factory) {
        return MENUS.register(name, () -> IForgeMenuType.create(factory));
    }

    public static void register(IEventBus eventBus) {
        MENUS.register(eventBus);
    }
}