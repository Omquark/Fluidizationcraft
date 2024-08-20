package com.omquark.fluidizationcraft.Screen.Dissolvinator;

import com.omquark.fluidizationcraft.Blocks.Entity.DissolvinatorBlockEntity;
import com.omquark.fluidizationcraft.Blocks.FluidizationBlocks;
import com.omquark.fluidizationcraft.FluidizationCraft;
import com.omquark.fluidizationcraft.Screen.ModMenuTypes;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.items.SlotItemHandler;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Objects;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class DissolvinatorMenu extends AbstractContainerMenu {

    public final DissolvinatorBlockEntity blockEntity;
    private final Level level;
    private final ContainerData data;

    Slot inputSlot, outputSlot, inFuelSlot, outFuelSlot;


    public DissolvinatorMenu(int containerId, Inventory inv, FriendlyByteBuf extraData){
        this(containerId, inv, Objects.requireNonNull(inv.player.level().getBlockEntity(extraData.readBlockPos())), new SimpleContainerData(4));
    }

    public DissolvinatorMenu(int containerId, Inventory inv, BlockEntity entity, ContainerData data){
        super(ModMenuTypes.DISSOLVINATOR_MENU.get(), containerId);
        checkContainerSize(inv, 4);
        blockEntity = ((DissolvinatorBlockEntity) entity);
        this.level = inv.player.level();
        this.data = data;

        addPlayerInventory(inv);
        addPlayerHotBar(inv);

        this.blockEntity.getCapability(ForgeCapabilities.ITEM_HANDLER).ifPresent(iItemHandler -> {
            inputSlot = new SlotItemHandler(iItemHandler, 0, 55, 24);
            outputSlot = new SlotItemHandler(iItemHandler, 1, 114, 24);
            inFuelSlot = new SlotItemHandler(iItemHandler, 2, 63, 57);
            outFuelSlot = new SlotItemHandler(iItemHandler, 3, 99, 57);
            this.addSlot(inputSlot);
            this.addSlot(outputSlot);
            this.addSlot(inFuelSlot);
            this.addSlot(outFuelSlot);
        });

        addDataSlots(data);
    }

    public boolean isCrafting(){
        return data.get(0) > 0;
    }

    public int getScaledProgress() {
        int progress = this.data.get(0);
        int maxProgress = this.data.get(1);
        int progressArrowSize = 26;

        return maxProgress != 9 && progress != 0 ? progress * progressArrowSize / maxProgress : 0;
    }

    // CREDIT GOES TO: diesieben07 | https://github.com/diesieben07/SevenCommons
    // must assign a slot number to each of the slots used by the GUI.
    // For this container, we can see both the tile inventory's slots as well as the player inventory slots and the hotbar.
    // Each time we add a Slot to the container, it automatically increases the slotIndex, which means
    //  0 - 8 = hotbar slots (which will map to the InventoryPlayer slot numbers 0 - 8)
    //  9 - 35 = player inventory slots (which map to the InventoryPlayer slot numbers 9 - 35)
    //  36 - 44 = TileInventory slots, which map to our TileEntity slot numbers 0 - 8)
    private static final int HOTBAR_SLOT_COUNT = 9;
    private static final int PLAYER_INVENTORY_ROW_COUNT = 3;
    private static final int PLAYER_INVENTORY_COLUMN_COUNT = 9;
    private static final int PLAYER_INVENTORY_SLOT_COUNT = PLAYER_INVENTORY_COLUMN_COUNT * PLAYER_INVENTORY_ROW_COUNT;
    private static final int VANILLA_SLOT_COUNT = HOTBAR_SLOT_COUNT + PLAYER_INVENTORY_SLOT_COUNT;
    private static final int VANILLA_FIRST_SLOT_INDEX = 0;
    private static final int TE_INVENTORY_FIRST_SLOT_INDEX = VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT;

    // THIS YOU HAVE TO DEFINE!
    private static final int TE_INVENTORY_SLOT_COUNT = 4;  // must be the number of slots you have!
    @Override
    public ItemStack quickMoveStack(Player playerIn, int pIndex) {
        Slot sourceSlot = slots.get(pIndex);
        if (!sourceSlot.hasItem()) return ItemStack.EMPTY;  //EMPTY_ITEM
        ItemStack sourceStack = sourceSlot.getItem();
        ItemStack copyOfSourceStack = sourceStack.copy();

        // Check if the slot clicked is one of the vanilla container slots
        if (pIndex < VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT) {
            // This is a vanilla container slot so merge the stack into the tile inventory
            if (!moveItemStackTo(sourceStack, TE_INVENTORY_FIRST_SLOT_INDEX, TE_INVENTORY_FIRST_SLOT_INDEX
                    + TE_INVENTORY_SLOT_COUNT, false)) {
                return ItemStack.EMPTY;  // EMPTY_ITEM
            }
        } else if (pIndex < TE_INVENTORY_FIRST_SLOT_INDEX + TE_INVENTORY_SLOT_COUNT) {
            // This is a TE slot so merge the stack into the players inventory
            if (!moveItemStackTo(sourceStack, VANILLA_FIRST_SLOT_INDEX, VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT, false)) {
                return ItemStack.EMPTY;
            }
        } else {
//            System.out.println("Invalid slotIndex:" + pIndex);
            return ItemStack.EMPTY;
        }
        // If stack size == 0 (the entire stack was moved) set slot contents to null
        if (sourceStack.getCount() == 0) {
            sourceSlot.set(ItemStack.EMPTY);
        } else {
            sourceSlot.setChanged();
        }
        sourceSlot.onTake(playerIn, sourceStack);
        return copyOfSourceStack;
    }

    @Override
    public boolean stillValid(Player player) {
        return stillValid(ContainerLevelAccess.create(level, blockEntity.getBlockPos()),
                player, FluidizationBlocks.DISSOLVINATOR_BLOCK.get());
    }

    private void addPlayerInventory(Inventory playerInventory){
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 9; j++){
                FluidizationCraft.LOGGER.debug("Adding player inventory");
                this.addSlot(new Slot(playerInventory, j + i * 9 + 9, 9 + j * 18, 85 + i * 18));
//                this.addSlot(new Slot(playerInventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }
    }

    private void addPlayerHotBar(Inventory playerInventory){
        for(int i = 0; i < 9; i++){
                this.addSlot(new Slot(playerInventory, i, 9 + i * 18, 142));
//                this.addSlot(new Slot(playerInventory, i, 8 + i * 18, 142));
        }
    }
}
