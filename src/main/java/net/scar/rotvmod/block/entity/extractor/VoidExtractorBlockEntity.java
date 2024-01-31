package net.scar.rotvmod.block.entity.extractor;

import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.RegistryAccess;
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
import net.minecraft.world.item.crafting.BlastingRecipe;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.SmeltingRecipe;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.AbstractFurnaceBlock;
import net.minecraft.world.level.block.BlastFurnaceBlock;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FurnaceBlock;
import net.minecraft.world.level.block.entity.BaseContainerBlockEntity;
import net.minecraft.world.level.block.entity.BlastFurnaceBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.FurnaceBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import net.scar.rotvmod.RotvMod;
import net.scar.rotvmod.block.custom.extractor.VoidExtractorBlock;
import net.scar.rotvmod.entity.ModBlockEntities;
import net.scar.rotvmod.item.ModItems;
import net.scar.rotvmod.screen.VoidExtractorMenu;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class VoidExtractorBlockEntity extends BlockEntity implements MenuProvider {
    private final ItemStackHandler itemHandler = new ItemStackHandler(3);

    private static final int INPUT_SLOT = 0;
    private static final int FUEL_SLOT = 1;
    private static final int OUTPUT_SLOT = 2;

    private LazyOptional<IItemHandler> lazyItemHandler = LazyOptional.empty();

    protected final ContainerData data;
    private int progress = 0;
    private int maxProgress = 78;
    private int fluidVoid = 0;
    private int maxFluidVoid = 2000;
    public int litTime = 0;
    public int litDuration = 0;
    public int cookingProgress = 0;
    public int cookingTotalTime = 0;

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
                    case 6 -> VoidExtractorBlockEntity.this.cookingProgress;
                    case 7 -> VoidExtractorBlockEntity.this.cookingTotalTime;
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
                    case 6 -> VoidExtractorBlockEntity.this.cookingProgress = pValue;
                    case 7 -> VoidExtractorBlockEntity.this.cookingTotalTime = pValue;
                }
            }

            @Override
            public int getCount() {
                return 8;
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
        pTag.putInt("cookingProgress", cookingProgress);
        pTag.putInt("cookingTotalTime", cookingTotalTime);
        super.saveAdditional(pTag);
    }

    @Override
    public void load(CompoundTag pTag) {
        super.load(pTag);
        itemHandler.deserializeNBT(pTag.getCompound("inventory"));
        progress = pTag.getInt("void_extractor.progress");
        fluidVoid = pTag.getInt("void_extractor.fluid_void");
        litTime = pTag.getInt("BurnTime");
        cookingProgress = pTag.getInt("CookTime");
        cookingTotalTime = pTag.getInt("CookTimeTotal");
        litDuration = this.getBurnDuration(itemHandler.getStackInSlot(1));
    }

    public void tick(Level pLevel, BlockPos pPos, BlockState pState) {
        if (this.isLit()) {
            --this.litTime;
        }

        if(hasRecipe()) {

            if (!this.isLit()) {
                this.litTime = this.getBurnDuration(itemHandler.getStackInSlot(1));
                this.litDuration = this.litTime;

                if (this.litTime > 0) {
                    itemHandler.getStackInSlot(1).shrink(1);
                    pState = pState.setValue(VoidExtractorBlock.LIT, !this.isLit());
                    pLevel.setBlock(pPos, pState, 3);
                    setChanged(pLevel, pPos, pState);
                }

            } else {
                if (this.litTime > 0) {
                    increaseCraftingProgress();
                    setChanged(pLevel, pPos, pState);

                    if (hasProgressFinished()) {
                        craftItem();
                        resetProgress();
                        setChanged(pLevel, pPos, pState);
                    }
                }
            }
        } else {
            resetProgress();

            pState = pState.setValue(VoidExtractorBlock.LIT, this.isLit());
            pLevel.setBlock(pPos, pState, 3);

            setChanged(pLevel, pPos, pState);
        }
    }

    private void resetProgress() {
        progress = 0;
    }


    private void addVoid(int count) {
        int availableSpace = maxFluidVoid - fluidVoid;
        fluidVoid += Math.min(count, availableSpace);
    }

    private void craftItem() {
        ItemStack result = new ItemStack(ModItems.INGOT_ALTURIUM.get(), 1);
        this.itemHandler.extractItem(INPUT_SLOT, 1, false);

        this.itemHandler.setStackInSlot(OUTPUT_SLOT, new ItemStack(result.getItem(),
                this.itemHandler.getStackInSlot(OUTPUT_SLOT).getCount() + result.getCount()));
        this.addVoid(300);
    }

    private boolean hasRecipe() {
        boolean hasCraftingItem = this.itemHandler.getStackInSlot(INPUT_SLOT).getItem() == ModItems.RAW_ALTURIUM.get();
        ItemStack result = new ItemStack(ModItems.INGOT_ALTURIUM.get());

        return hasCraftingItem && canInsertAmountIntoOutputSlot(result.getCount()) && canInsertItemIntoOutputSlot(result.getItem());
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

    public boolean canBurn(RegistryAccess pRegistryAccess, SmeltingRecipe pRecipe, int pMaxStackSize) {
        if (!itemHandler.getStackInSlot(0).isEmpty() && pRecipe != null) {
            SimpleContainer inv = new SimpleContainer(1);
            inv.setItem(0, itemHandler.getStackInSlot(0));

            ItemStack itemstack = pRecipe.assemble(inv, pRegistryAccess);
            if (itemstack.isEmpty()) {
                return false;
            } else {
                ItemStack itemstack1 = itemHandler.getStackInSlot(2);
                if (itemstack1.isEmpty()) {
                    return true;
                } else if (!ItemStack.isSameItem(itemstack1, itemstack)) {
                    return false;
                } else if (itemstack1.getCount() + itemstack.getCount() <= pMaxStackSize && itemstack1.getCount() + itemstack.getCount() <= itemstack1.getMaxStackSize()) {
                    return true;
                } else {
                    return itemstack1.getCount() + itemstack.getCount() <= itemstack.getMaxStackSize();
                }
            }
        } else {
            return false;
        }
    }

    public boolean burn(RegistryAccess pRegistryAccess, SmeltingRecipe pRecipe, int pMaxStackSize) {
        if (pRecipe != null && this.canBurn(pRegistryAccess, pRecipe, pMaxStackSize)) {
            SimpleContainer inv = new SimpleContainer(1);
            inv.setItem(0, itemHandler.getStackInSlot(0));

            ItemStack itemstack = itemHandler.getStackInSlot(0);
            ItemStack itemstack1 = pRecipe.assemble(inv, pRegistryAccess);
            ItemStack itemstack2 = itemHandler.getStackInSlot(2);
            if (itemstack2.isEmpty()) {
                itemHandler.setStackInSlot(2, itemstack1.copy());
            } else if (itemstack2.is(itemstack1.getItem())) {
                itemstack2.grow(itemstack1.getCount());
            }

            if (itemstack.is(Blocks.WET_SPONGE.asItem()) && !itemHandler.getStackInSlot(1).isEmpty() && itemHandler.getStackInSlot(1).is(Items.BUCKET)) {
                itemHandler.setStackInSlot(1, new ItemStack(Items.WATER_BUCKET));
            }

            itemstack.shrink(1);
            return true;
        } else {
            return false;
        }
    }

    public boolean isLit() {
        return this.litTime > 0;
    }
}