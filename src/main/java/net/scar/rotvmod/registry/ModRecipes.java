package net.scar.rotvmod.registry;

import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.scar.rotvmod.RotvMod;
import net.scar.rotvmod.recipe.AlchemyMachineRecipe;
import net.scar.rotvmod.recipe.VoidExtractorRecipe;

public class ModRecipes {
    public static final DeferredRegister<RecipeSerializer<?>> SERIALIZERS =
            DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, RotvMod.MOD_ID);

    public static final RegistryObject<RecipeSerializer<VoidExtractorRecipe>> VOID_EXTRACTOR_SERIALIZER =
            SERIALIZERS.register("void_extractor", () -> VoidExtractorRecipe.Serializer.INSTANCE);
    public static final RegistryObject<RecipeSerializer<AlchemyMachineRecipe>> ALCHEMY_MACHINE_SERIALIZER =
            SERIALIZERS.register("alchemy_machine", () -> AlchemyMachineRecipe.Serializer.INSTANCE);

    public static void register(IEventBus eventBus) {
        SERIALIZERS.register(eventBus);
    }
}