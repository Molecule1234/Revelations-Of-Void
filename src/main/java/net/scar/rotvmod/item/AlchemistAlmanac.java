package net.scar.rotvmod.item;

import net.minecraft.client.Minecraft;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.scar.rotvmod.inventory.screen.AlmanacScreen;
import org.jetbrains.annotations.NotNull;

public class AlchemistAlmanac extends Item {

    public AlchemistAlmanac(Properties pProperties) {
        super(pProperties);
    }

    @NotNull
    @Override
    public InteractionResultHolder<ItemStack> use(Level worldIn, Player playerIn, InteractionHand handIn) {
        ItemStack stack = playerIn.getItemInHand(handIn);
        playerIn.awardStat(Stats.ITEM_USED.get(this));

        if (worldIn.isClientSide) {
            openGui();
        }

        return InteractionResultHolder.sidedSuccess(stack, worldIn.isClientSide());
    }

    @OnlyIn(Dist.CLIENT)
    public void openGui() {
        Minecraft.getInstance().player.playNotifySound(SoundEvents.BOOK_PAGE_TURN, SoundSource.NEUTRAL, 1.0f, 1.0f);
        Minecraft.getInstance().setScreen(new AlmanacScreen());
    }
}
