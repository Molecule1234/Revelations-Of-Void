package net.scar.rotvmod.block.entity.extractor;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.Containers;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import net.scar.rotvmod.block.custom.extractor.VoidExtractorBlock;
import net.scar.rotvmod.inventory.screen.VoidExtractorMenu;
import net.scar.rotvmod.item.ChargedVoidItem;
import net.scar.rotvmod.registry.ModBlockEntities;
import net.scar.rotvmod.recipe.VoidExtractorRecipe;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class VoidExtractorBlockEntity extends BlockEntity implements MenuProvider {
    private final ItemStackHandler itemHandler = new ItemStackHandler(4);

    private static final int INPUT_SLOT = 0;
    private static final int FUEL_SLOT = 1;
    private static final int OUTPUT_SLOT = 2;
    private static final int CHARGE_SLOT = 3;

    private LazyOptional<IItemHandler> lazyItemHandler = LazyOptional.empty();

    protected final ContainerData data;
    private int progress = 0;
    private int maxProgress = 78;
    private int fluidVoid = 0;
    private int maxFluidVoid = 2000;
    public int litTime = 0;
    public int litDuration = 0;

    public VoidExtractorBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(ModBlockEntities.VOID_EXTRACTOR.get(), pPos, pBlockState);
        this.data = new ContainerData() {
            @Override
            public int get(int pIndex) {
                return switch (pIndex) {
                    case 0 -> VoidExtractorBlockEntity.this.progress;
                    case 1 -> VoidExtractorBlockEntity.this.maxProgress;
                    case 2 -> VoidExtractorBlockEntity.this.fluidVoid;
                    case 3 -> VoidExtractorBlockEntity.this.maxFluidVoid;
                    case 4 -> VoidExtractorBlockEntity.this.litTime;
                    case 5 -> VoidExtractorBlockEntity.this.litDuration;
                    default -> 0;
                };
            }

            @Override
            public void set(int pIndex, int pValue) {
                switch (pIndex) {
                    case 0 -> VoidExtractorBlockEntity.this.progress = pValue;
                    case 1 -> VoidExtractorBlockEntity.this.maxProgress = pValue;
                    case 2 -> VoidExtractorBlockEntity.this.fluidVoid = pValue;
                    case 3 -> VoidExtractorBlockEntity.this.maxFluidVoid = pValue;
                    case 4 -> VoidExtractorBlockEntity.this.litTime = pValue;
                    case 5 -> VoidExtractorBlockEntity.this.litDuration = pValue;
                }
            }

            @Override
            public int getCount() {
                return 6;
            }
        };
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if(cap == ForgeCapabilities.ITEM_HANDLER) {
            return lazyItemHandler.cast();
        }

        return super.getCapability(cap, side);
    }

    @Override
    public void onLoad() {
        super.onLoad();
        lazyItemHandler = LazyOptional.of(() -> itemHandler);
    }

    @Override
    public void invalidateCaps() {
        super.invalidateCaps();
        lazyItemHandler.invalidate();
    }

    public void drops() {
        SimpleContainer inventory = new SimpleContainer(itemHandler.getSlots());
        for(int i = 0; i < itemHandler.getSlots(); i++) {
            inventory.setItem(i, itemHandler.getStackInSlot(i));
        }
        Containers.dropContents(this.level, this.worldPosition, inventory);
    }

    @Override
    public Component getDisplayName() {
        return Component.translatable("block.rotv.void_extractor");
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int pContainerId, Inventory pPlayerInventory, Player pPlayer) {
        return new VoidExtractorMenu(pContainerId, pPlayerInventory, this, this.data);
    }

    @Override
    protected void saveAdditional(CompoundTag pTag) {
        pTag.put("inventory", itemHandler.serializeNBT());
        pTag.putInt("void_extractor.progress", progress);
        pTag.putInt("void_extractor.fluid_void", fluidVoid);
        pTag.putInt("litTime", litTime);
        pTag.putInt("litDuration", litDuration);
        super.saveAdditional(pTag);
    }

    @Override
    public void load(CompoundTag pTag) {
        super.load(pTag);
        itemHandler.deserializeNBT(pTag.getCompound("inventory"));
        progress = pTag.getInt("void_extractor.progress");
        fluidVoid = pTag.getInt("void_extractor.fluid_void");
        litTime = pTag.getInt("litTime");
        litDuration = this.getBurnDuration(itemHandler.getStackInSlot(FUEL_SLOT));
    }

    public void tick(Level pLevel, BlockPos pPos, BlockState pState) {
        if (this.isLit()) {
            --litTime;
        } else if (progress > 0) {
            --progress;
        }

        ItemStack chargeStack = this.itemHandler.getStackInSlot(CHARGE_SLOT);

        if (chargeStack.getItem() != Items.AIR) {
            int voidCount = ChargedVoidItem.getFluidCount(chargeStack);
            if (voidCount > 0 && this.fluidVoid < this.maxFluidVoid) {
                ++fluidVoid;
                voidCount -= 1;
                chargeStack.setDamageValue(ChargedVoidItem.MAX_VOID_FLUID_COUNT - voidCount);
                itemHandler.setStackInSlot(CHARGE_SLOT, ChargedVoidItem.setFluidCount(chargeStack, voidCount));
            }
        }

        if(hasRecipe()) {

            if (!this.isLit()) {
                litTime = this.getBurnDuration(itemHandler.getStackInSlot(FUEL_SLOT));
                litDuration = this.litTime;

                if (this.litTime > 0) {
                    itemHandler.getStackInSlot(FUEL_SLOT).shrink(1);
                }

            } else {
                if (this.litTime > 0) {
                    increaseCraftingProgress();

                    if (hasProgressFinished()) {
                        craftItem();
                        resetProgress();
                        setChanged(pLevel, pPos, pState);
                    }
                }
            }
        } else {
            resetProgress();
            setChanged(pLevel, pPos, pState);
        }

        pState = pState.setValue(VoidExtractorBlock.LIT, this.isLit());
        pLevel.setBlock(pPos, pState, 3);
    }

    private void resetProgress() {
        progress = 0;
    }


    private void addVoid(int count) {
        int availableSpace = maxFluidVoid - fluidVoid;
        fluidVoid += Math.min(count, availableSpace);
    }

    private void craftItem() {
        Optional<VoidExtractorRecipe> recipe = getCurrentRecipe();
        ItemStack result = recipe.get().getResultItem(null);

        this.itemHandler.extractItem(INPUT_SLOT, 1, false);

        this.itemHandler.setStackInSlot(OUTPUT_SLOT, new ItemStack(result.getItem(),
                this.itemHandler.getStackInSlot(OUTPUT_SLOT).getCount() + result.getCount()));
        this.addVoid(recipe.get().getVoidFluid());
    }

    private boolean hasRecipe() {
        Optional<VoidExtractorRecipe> recipe = getCurrentRecipe();

        if(recipe.isEmpty()) {
            return false;
        }
        ItemStack result = recipe.get().getResultItem(getLevel().registryAccess());

        return canInsertAmountIntoOutputSlot(result.getCount()) && canInsertItemIntoOutputSlot(result.getItem());
    }

    private Optional<VoidExtractorRecipe> getCurrentRecipe() {
        SimpleContainer inventory = new SimpleContainer(this.itemHandler.getSlots());
        for(int i = 0; i < itemHandler.getSlots(); i++) {
            inventory.setItem(i, this.itemHandler.getStackInSlot(i));
        }

        return this.level.getRecipeManager().getRecipeFor(VoidExtractorRecipe.Type.INSTANCE, inventory, level);
    }

    private boolean canInsertItemIntoOutputSlot(Item item) {
        return this.itemHandler.getStackInSlot(OUTPUT_SLOT).isEmpty() || this.itemHandler.getStackInSlot(OUTPUT_SLOT).is(item);
    }

    private boolean canInsertAmountIntoOutputSlot(int count) {
        return this.itemHandler.getStackInSlot(OUTPUT_SLOT).getCount() + count <= this.itemHandler.getStackInSlot(OUTPUT_SLOT).getMaxStackSize();
    }

    private boolean hasProgressFinished() {
        return progress >= maxProgress;
    }

    private void increaseCraftingProgress() {
        progress++;
    }

    public boolean isFuel(ItemStack pStack) {
        return net.minecraftforge.common.ForgeHooks.getBurnTime(pStack, RecipeType.SMELTING) > 0;
    }

    public int getBurnDuration(ItemStack pFuel) {
        if (pFuel.isEmpty()) {
            return 0;
        } else {
            Item item = pFuel.getItem();
            return net.minecraftforge.common.ForgeHooks.getBurnTime(pFuel, RecipeType.SMELTING);
        }
    }

    public boolean isLit() {
        return this.litTime > 0;
    }
}