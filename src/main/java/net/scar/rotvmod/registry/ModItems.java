package net.scar.rotvmod.registry;

import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.scar.rotvmod.RotvMod;
import net.minecraft.world.item.*;
import net.scar.rotvmod.entity.ModBoatEntity;
import net.scar.rotvmod.item.*;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, RotvMod.MOD_ID);

    public static final RegistryObject<Item> ALCHEMIST_ALMANAC = ITEMS.register("alchemist_almanac",
            () -> new AlchemistAlmanac(new Item.Properties().rarity(Rarity.EPIC)));
    public static final RegistryObject<Item> CLEAR_RUNE = ITEMS.register("clear_rune",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> IMBUED_BRICK = ITEMS.register("imbued_brick",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> VOID_TREE_BARK = ITEMS.register("void_tree_bark",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> VOID_FLOWER_PETAL = ITEMS.register("void_flower_petal",
            () -> new Item(new Item.Properties()));


    public static final RegistryObject<Item> VOID_GEM = ITEMS.register("void_gem",
            () -> new VoidGem(new Item.Properties().stacksTo(1).rarity(Rarity.EPIC).durability(ChargedVoidItem.MAX_VOID_FLUID_COUNT).defaultDurability(0)));

    public static final RegistryObject<Item> SCARLET_FLUX = ITEMS.register("scarlet_flux",
            () -> new ScarletFlux(new Item.Properties()));
    public static final RegistryObject<Item> INFUSED_LEATHER = ITEMS.register("infused_leather",
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

    public static final RegistryObject<Item> VOID_SIGN = ITEMS.register("void_sign",
            () -> new SignItem(new Item.Properties().stacksTo(16), ModBlocks.VOID_SIGN.get(), ModBlocks.VOID_WALL_SIGN.get()));
    public static final RegistryObject<Item> VOID_HANGING_SIGN = ITEMS.register("void_hanging_sign",
            () -> new HangingSignItem(ModBlocks.VOID_HANGING_SIGN.get(), ModBlocks.VOID_WALL_HANGING_SIGN.get(), new Item.Properties().stacksTo(16)));

    public static final RegistryObject<Item> VOID_BOAT = ITEMS.register("void_boat",
            () -> new ModBoatItem(false, ModBoatEntity.Type.VOID, new Item.Properties()));
    public static final RegistryObject<Item> VOID_CHEST_BOAT = ITEMS.register("void_chest_boat",
            () -> new ModBoatItem(true, ModBoatEntity.Type.VOID, new Item.Properties()));

    public static final RegistryObject<Item> AURORA_MUSIC_DISC = ITEMS.register("aurora_music_disc",
            () -> new RecordItem(6, ModSounds.AURORA, new Item.Properties().stacksTo(1), 2440));
    public static final RegistryObject<Item> CALM_MUSIC_DISC = ITEMS.register("calm_music_disc",
            () -> new RecordItem(6, ModSounds.CALM, new Item.Properties().stacksTo(1), 2440));
    public static final RegistryObject<Item> DEORIUM_MUSIC_DISC = ITEMS.register("deorium_music_disc",
            () -> new RecordItem(6, ModSounds.DEORIUM, new Item.Properties().stacksTo(1), 2440));
    public static final RegistryObject<Item> ELEVEN_MUSIC_DISC = ITEMS.register("eleven_music_disc",
            () -> new RecordItem(6, ModSounds.ELEVEN, new Item.Properties().stacksTo(1), 2440));
    public static final RegistryObject<Item> PURPLE_MUSIC_DISC = ITEMS.register("purple_music_disc",
            () -> new RecordItem(6, ModSounds.PURPLE, new Item.Properties().stacksTo(1), 2440));
    public static final RegistryObject<Item> REVELATIONS_MUSIC_DISC = ITEMS.register("revelations_music_disc",
            () -> new RecordItem(6, ModSounds.REVELATIONS, new Item.Properties().stacksTo(1), 2440));
    public static final RegistryObject<Item> VOID_MUSIC_DISC = ITEMS.register("void_music_disc",
            () -> new RecordItem(6, ModSounds.VOID, new Item.Properties().stacksTo(1), 2440));


    public static void register(IEventBus eventBus) { ITEMS.register(eventBus); }
}