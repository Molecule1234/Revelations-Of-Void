package net.scar.rotvmod.item;

import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.scar.rotvmod.RotvMod;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, RotvMod.MOD_ID);

    public static final RegistryObject<Item> ANCIENT_TOME = ITEMS.register("ancient_tome",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> CLEAR_RUNE = ITEMS.register("clear_rune",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> IMBUED_BRICK = ITEMS.register("imbued_brick",
            () -> new Item(new Item.Properties()));

    // Ingots
    public static final RegistryObject<Item> INGOT_ALTURIUM = ITEMS.register("ingot_alturium",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> INGOT_DEORIUM = ITEMS.register("ingot_deorium",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> INGOT_OVERFLOWN_DEORIUM = ITEMS.register("ingot_overflown_deorium",
            () -> new Item(new Item.Properties()));

    // Nuggets
    public static final RegistryObject<Item> NUGGET_ALTURIUM = ITEMS.register("nugget_alturium",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> NUGGET_DEORIUM = ITEMS.register("nugget_deorium",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> NUGGET_OVERFLOWN_DEORIUM = ITEMS.register("nugget_overflown_deorium",
            () -> new Item(new Item.Properties()));

    // Raws
    public static final RegistryObject<Item> RAW_ALTURIUM = ITEMS.register("raw_alturium",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> RAW_DEORIUM = ITEMS.register("raw_deorium",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> RAW_OVERFLOWN_DEORIUM = ITEMS.register("raw_overflown_deorium",
            () -> new Item(new Item.Properties()));

    public static void register(IEventBus eventBus) { ITEMS.register(eventBus); }
}