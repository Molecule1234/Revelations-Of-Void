package net.scar.rotvmod.registry;

import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.scar.rotvmod.RotvMod;

public class ModWoodTypes {
    public static final WoodType VOID = WoodType.register(new WoodType(RotvMod.MOD_ID + ":void", BlockSetType.OAK));
}