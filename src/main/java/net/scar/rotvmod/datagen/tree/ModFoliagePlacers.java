package net.scar.rotvmod.datagen.tree;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import net.scar.rotvmod.RotvMod;

public class ModFoliagePlacers {
    public static final DeferredRegister<FoliagePlacerType<?>> FOLIAGE_PLACERS =
            DeferredRegister.create(Registries.FOLIAGE_PLACER_TYPE, RotvMod.MOD_ID);

    public static final RegistryObject<FoliagePlacerType<VoidTreeFoliagePlacer>> VOID_TREE_FOLIAGE_PLACER =
            FOLIAGE_PLACERS.register("void_tree_foliage_placer", () -> new FoliagePlacerType<>(VoidTreeFoliagePlacer.CODEC));

    public static void register(IEventBus eventBus) {
        FOLIAGE_PLACERS.register(eventBus);
    }
}