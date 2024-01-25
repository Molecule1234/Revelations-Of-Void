package net.scar.rotvmod.item;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.scar.rotvmod.RotvMod;
import org.jetbrains.annotations.NotNull;
import vazkii.patchouli.api.PatchouliAPI;

public class AncientTome extends Item {

    public AncientTome(Properties pProperties) {
        super(pProperties);
    }

    @NotNull
    @Override
    public InteractionResultHolder<ItemStack> use(Level worldIn, Player playerIn, InteractionHand handIn) {
        ItemStack stack = playerIn.getItemInHand(handIn);

        if (playerIn instanceof ServerPlayer player) {
            RotvMod.LOGGER.info("open");
            PatchouliAPI.get().openBookGUI(player, BuiltInRegistries.ITEM.getKey(this));
        }

        return InteractionResultHolder.sidedSuccess(stack, worldIn.isClientSide());
    }
}
