package net.scar.rotvmod.registry;

import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.scar.rotvmod.RotvMod;
import net.scar.rotvmod.block.entity.ModHangingSignBlockEntity;
import net.scar.rotvmod.block.entity.ModSignBlockEntity;
import net.scar.rotvmod.block.entity.alchemy.AlchemyMachineBlockEntity;
import net.scar.rotvmod.block.entity.extractor.VoidExtractorBlockEntity;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, RotvMod.MOD_ID);


    public static final RegistryObject<BlockEntityType<AlchemyMachineBlockEntity>> ALCHEMY_FURNACE =
            BLOCK_ENTITIES.register("alchemy_furnace", () ->
                    BlockEntityType.Builder.of(AlchemyMachineBlockEntity::new,
                            ModBlocks.ALCHEMY_MACHINE.get()).build(null));
    public static final RegistryObject<BlockEntityType<VoidExtractorBlockEntity>> VOID_EXTRACTOR =
            BLOCK_ENTITIES.register("void_extractor", () ->
                    BlockEntityType.Builder.of(VoidExtractorBlockEntity::new,
                            ModBlocks.VOID_EXTRACTOR.get()).build(null));

    public static final RegistryObject<BlockEntityType<ModSignBlockEntity>> VOID_SIGN =
            BLOCK_ENTITIES.register("void_sign", () ->
                    BlockEntityType.Builder.of(ModSignBlockEntity::new,
                            ModBlocks.VOID_SIGN.get(), ModBlocks.VOID_WALL_SIGN.get()).build(null));

    public static final RegistryObject<BlockEntityType<ModHangingSignBlockEntity>> VOID_HANGING_SIGN =
            BLOCK_ENTITIES.register("void_hanging_sign", () ->
                    BlockEntityType.Builder.of(ModHangingSignBlockEntity::new,
                            ModBlocks.VOID_HANGING_SIGN.get(), ModBlocks.VOID_WALL_HANGING_SIGN.get()).build(null));

    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }
}