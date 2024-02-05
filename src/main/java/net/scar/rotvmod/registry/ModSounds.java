package net.scar.rotvmod.registry;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.common.util.ForgeSoundType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.scar.rotvmod.RotvMod;

public class ModSounds {
    public static final DeferredRegister<SoundEvent> SOUND_EVENTS =
            DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, RotvMod.MOD_ID);

    public static final RegistryObject<SoundEvent> AURORA = registerSoundEvents("aurora");
    public static final RegistryObject<SoundEvent> CALM = registerSoundEvents("calm");
    public static final RegistryObject<SoundEvent> DEORIUM = registerSoundEvents("deorium");
    public static final RegistryObject<SoundEvent> ELEVEN = registerSoundEvents("elven");
    public static final RegistryObject<SoundEvent> PURPLE = registerSoundEvents("purple");
    public static final RegistryObject<SoundEvent> REVELATIONS = registerSoundEvents("revelations");
    public static final RegistryObject<SoundEvent> VOID = registerSoundEvents("void");

    private static RegistryObject<SoundEvent> registerSoundEvents(String name) {
        return SOUND_EVENTS.register(name, () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(RotvMod.MOD_ID, name)));
    }

    public static void register(IEventBus eventBus) {
        SOUND_EVENTS.register(eventBus);
    }
}