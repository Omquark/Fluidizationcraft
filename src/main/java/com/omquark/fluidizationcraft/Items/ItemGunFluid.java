package com.omquark.fluidizationcraft.Items;

import com.omquark.fluidizationcraft.data.items.FluidShooter;
import com.omquark.fluidizationcraft.entity.AcidShotProjectile;
import com.omquark.fluidizationcraft.inventory.FluidShooterInventory;
import com.omquark.fluidizationcraft.screen.FluidShooter.FluidShooterMenu;
import com.omquark.fluidizationcraft.util.EverythingNonNullByDefault;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.*;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.items.ItemStackHandler;
import org.jetbrains.annotations.Nullable;


@EverythingNonNullByDefault
public class ItemGunFluid extends Item {

    protected final ContainerData data;
    private final static int SLOT_COUNT = 2;
    private final static int INPUT_SLOT = 0;
    private final static int OUTPUT_SLOT = 1;
    private int fuelMb = 0;
    private int fuelMaxMb = 16000;
    ItemStackHandler itemStackHandler = new ItemStackHandler(SLOT_COUNT);

    public final FluidShooter inventory = new FluidShooter(ItemStack.EMPTY, ItemStack.EMPTY, 0);

//    public static final ItemCapability<FluidShooter, Void> ITEM_HANDLER_ITEM = ItemCapability.createVoid(
//            ResourceLocation.parse("fluid_shooter_item_handler"), FluidShooter.class);

    public ItemGunFluid(Properties properties) {
        super(properties);
        this.data = new ContainerData() {
            @Override
            public int get(int index) {
                return switch (index) {
                    case (0) -> ItemGunFluid.this.fuelMb;
                    case (1) -> ItemGunFluid.this.fuelMaxMb;
                    default -> 0;
                };
            }

            @Override
            public void set(int index, int value) {
                switch (index) {
                    case (0) -> ItemGunFluid.this.fuelMb = value;
                    case (1) -> ItemGunFluid.this.fuelMaxMb = value;
                }
            }

            @Override
            public int getCount() {
                return 2;
            }
        };
    }

    public ItemStackHandler getItemStackHandler() {
        return itemStackHandler;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        if (player.isShiftKeyDown()) {
            if (!level.isClientSide() && player instanceof ServerPlayer serverPlayer) {
//                serverPlayer.openMenu(new SimpleMenuProvider((id, inv, p) -> createMenu(id, player.getInventory(), player), getDisplayName()));
//                serverPlayer.openMenu(new FluidShooterInventory());
                FluidShooterInventory.openGUI(serverPlayer, player.getInventory().getSelected());

            }
            return InteractionResultHolder.sidedSuccess(player.getItemInHand(hand), level.isClientSide());
        }

        if (!level.isClientSide) {
            if (fuelMb >= 1000) {
                this.fuelMb -= 1000;
                //TODO: Adjust the acid projectile to spawn different fluid depending on what is in the gun
                AcidShotProjectile acidShot = new AcidShotProjectile(level, player, player.getItemInHand(hand), new ItemStack(this));
                //shootFromRotation(player, xRot, yRot, gravity effect?, power <- setting this high will glitch, inaccuracy
                acidShot.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0f, 3.0f, 0.0f);
                level.addFreshEntity(acidShot);
            }
            return InteractionResultHolder.success(player.getItemInHand(hand));
        }

        return InteractionResultHolder.fail(player.getItemInHand(hand));
    }



//    @Override
//    public Component getDisplayName() {
//        return Component.translatable("item.fluidizationcraft.gun_acid");
//    }
//
//    @Nullable
//    @Override
//    public AbstractContainerMenu createMenu(int containerId, Inventory playerInventory, Player player) {
//        return new FluidShooterMenu(containerId, playerInventory, this.data, this.itemStackHandler);
//    }

    @Override
    public void inventoryTick(ItemStack pStack, Level pLevel, Entity pEntity, int pSlotId, boolean pIsSelected) {
        if (!pLevel.isClientSide) return;
        addFuel();
    }

    private void addFuel() {
        ItemStack fuel = itemStackHandler.getStackInSlot(INPUT_SLOT).copy();
        ItemStack outFuel = itemStackHandler.getStackInSlot(OUTPUT_SLOT).copy();

        if (!fuel.is(FluidizationItems.VIAL_ACID.get()) || //Do not add if not acid
                (!outFuel.is(FluidizationItems.VIAL_EMPTY.get()) && !outFuel.isEmpty()) || //Do not add if output is NOT an empty vial
                (!outFuel.isEmpty() && outFuel.getCount() == outFuel.getMaxStackSize()) || //Do not add if output slot is full
                fuelMb + 1000 > fuelMaxMb) { //Do not add if it will go beyond max fuel
            return;
        }

        fuel.shrink(1);
        if (outFuel.isEmpty()) outFuel = new ItemStack(FluidizationItems.VIAL_EMPTY.get(), 1);
        else outFuel.grow(1);
        itemStackHandler.setStackInSlot(INPUT_SLOT, fuel);
        itemStackHandler.setStackInSlot(OUTPUT_SLOT, outFuel);
        this.fuelMb += 1000;
    }

    private ItemStackHandler createInventory(int size){
        return new ItemStackHandler(size){

        };
    }
}
