package net.scar.rotvmod.datagen.tree;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import net.scar.rotvmod.RotvMod;

public class ModTrunkPlacerTypes {
    public static final DeferredRegister<TrunkPlacerType<?>> TRUNK_PLACER =
            DeferredRegister.create(Registries.TRUNK_PLACER_TYPE, RotvMod.MOD_ID);

    public static final RegistryObject<TrunkPlacerType<VoidTreeTrunkPlacer>> VOID_TREE_TRUNK_PLACER =
            TRUNK_PLACER.register("void_tree_trunk_placer", () -> new TrunkPlacerType<>(VoidTreeTrunkPlacer.CODEC));

    public static void register(IEventBus eventBus) {
        TRUNK_PLACER.register(eventBus);
    }
}