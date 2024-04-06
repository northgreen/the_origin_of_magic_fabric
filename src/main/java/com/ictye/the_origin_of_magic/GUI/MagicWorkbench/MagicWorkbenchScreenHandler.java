package com.ictye.the_origin_of_magic.GUI.MagicWorkbench;

import com.ictye.the_origin_of_magic.Items.Staff.StdStaff;
import com.ictye.the_origin_of_magic.the_origin_of_magic_client;
import com.ictye.the_origin_of_magic.utils.MagicInventory;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.InventoryChangedListener;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerContext;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.screen.slot.Slot;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import org.jetbrains.annotations.Nullable;
import java.util.ArrayList;
import java.util.List;

public class MagicWorkbenchScreenHandler extends ScreenHandler {

    private  final MagicInventory magicSlotInventory = new MagicInventory(9); // 魔法物品欄
    private final SimpleInventory staffInventory = new SimpleInventory(1); // 魔杖格子
    private final Slot staffSlot; // 魔杖格子
    private final List<magicSlot> magicSlots = new ArrayList<>();
    private final ScreenHandlerContext context;
    private PlayerInventory playerInvnetory;
    public MagicWorkbenchScreenHandler(@Nullable ScreenHandlerType<?> type, int syncId, PlayerInventory inventory) {
        this(syncId,inventory,ScreenHandlerContext.EMPTY);
    }

    public MagicWorkbenchScreenHandler(@Nullable ScreenHandlerType<?> type, int syncId, PlayerInventory inventory, final ScreenHandlerContext context) {
        this(syncId,inventory,context);
    }

    // 構造函數
    public MagicWorkbenchScreenHandler(int syncId, PlayerInventory playerInventory, final ScreenHandlerContext context){
        super(the_origin_of_magic_client.MAGIC_WORKSTATION_SCREEN_HANDLER_SCREEN_HANDLER_TYPE,syncId);
        this.context = context;
        this.playerInvnetory = playerInventory;
        // 魔杖格子
        Slot StaffSlot = new Slot(staffInventory, 0, 19, 21){
            @Override
            public boolean canInsert(ItemStack stack) {
                return stack.getItem() instanceof StdStaff;
            }

            @Override
            public void setStack(ItemStack stack) {
                // 同步魔法格子和魔杖格子的背包
                if(!(stack.getItem() == null) && stack.getItem() instanceof StdStaff staff){
                    // TODO(ictye):此處可能有伺服端和客戶端不同步的情況導致崩潰
                    // 同步名字
                    MagicWorkbenchScreen.setSTAFFNAME(stack.getName());

                    // 同步標簽和類本身的物品欄
                    staff.setItemFromNBT(stack.getNbt());
                    convInventoryToSlotWithDrop(staff.getInventory());
                } else {
                    MagicWorkbenchScreen.setSTAFFNAME(Text.empty());
                }
                super.setStack(stack);
            }

            @Override
            public void onTakeItem(PlayerEntity player, ItemStack stack) {
                if((stack.getItem() instanceof StdStaff staff) && player instanceof ServerPlayerEntity){
                    MagicInventory inventory1 = staff.getInventory();
                    for(int i = 0; i < magicSlotInventory.size(); i++){
                        if (i < staff.getSize()){
                            inventory1.addStack(magicSlotInventory.getStack(i));
                        }else {
                            int finalI = i;
                            context.run((world, pos) -> dropInventory(player, new SimpleInventory(magicSlotInventory.getStack(finalI))));
                            // dropInventory(player, new SimpleInventory(magicSlotInventory.getStack(i)));
                        }
                    }
                    setStaffNBT(new MagicInventory(staff.getInventory().size()).setStackFromInventory(inventory1));
                }
                super.onTakeItem(player, stack);
            }
        };

        staffInventory.onOpen(playerInventory.player);
        // staffInventory.addListener(new StaffChangeLisener());
        staffSlot = addSlot(StaffSlot);

        // 創建魔法格子
        int slotsCount = 9;
        magicSlotInventory.onOpen(playerInventory.player);
        int slotIndex = 0;
        for (int m = 0; m < slotsCount / 9 + 1; m++) {
            for (int n = 0; n < 9; n++) {
                if (slotIndex < slotsCount) {
                    magicSlots.add((magicSlot) addSlot(new magicSlot(magicSlotInventory, slotIndex, 8 + n * 18, 54 + m * 18)));
                    slotIndex++;
                } else {
                    break;
                }
            }
        }

        // 主物品栏的格子
        for (int m = 0; m < 3; ++m) {
            for (int n = 0; n < 9; ++n) {
                this.addSlot(new Slot(playerInventory, n + m * 9 + 9, 8 + n * 18, 85 + m * 18));
            }
        }

        // 快捷栏的格子
        for (int m = 0; m < 9; ++m) {
            this.addSlot(new Slot(playerInventory, m, 8 + m * 18, 143));
        }
    }
    public void setMagicSlotInventory(MagicInventory inventory) {
        magicSlotInventory.setStackFromInventory(inventory);
    }

    @Override
    public boolean canUse(PlayerEntity player) {
        return true;
    }

    @Override
    public void close(PlayerEntity player){
        if(staffInventory.getStack(0).getItem() instanceof StdStaff staff){
            setStaffNBT(new MagicInventory(staff.getInventory().size()).setStackFromInventory(magicSlotInventory));
        }
        magicSlotInventory.clear();
        this.context.run((world, pos) -> this.dropInventory(player, this.staffInventory));
        super.close(player);
    }

    /**
     * 設置魔杖的NBT
     * @param inventory 物品格子
     */
    private void setStaffNBT(MagicInventory inventory){
        this.staffInventory.getStack(0).setSubNbt("items", inventory.toNbtList());
    }

    public ItemStack transferSlot(PlayerEntity player, int invSlot) {
        ItemStack newStack = ItemStack.EMPTY;
        Slot slot = this.slots.get(invSlot);
        if (slot.hasStack()) {
            ItemStack originalStack = slot.getStack();
            newStack = originalStack.copy();
            if (invSlot < this.magicSlotInventory.size()) {
                if (!this.insertItem(originalStack, this.magicSlotInventory.size(), this.slots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.insertItem(originalStack, 0, this.magicSlotInventory.size(), false)) {
                return ItemStack.EMPTY;
            }

            if (originalStack.isEmpty()) {
                slot.setStack(ItemStack.EMPTY);
            } else {
                slot.markDirty();
            }
        }
        return newStack;
    }

    class StaffChangeLisener implements InventoryChangedListener {
        @Override
        public void onInventoryChanged(Inventory sender) {
            if(sender instanceof StdStaff staff){
                MagicInventory inventory1 = staff.getInventory();

                for(int i = 0; i < magicSlotInventory.size(); i++){
                    if (i < staff.getSize()){
                        inventory1.addStack(magicSlotInventory.getStack(i));
                    }else {
                        playerInvnetory.insertStack(magicSlotInventory.getStack(i));
                    }
                }
                setStaffNBT(new MagicInventory(staff.getInventory().size()).setStackFromInventory(inventory1));
            }

            if(sender.getStack(0).getItem() instanceof StdStaff stdStaff){
                MagicInventory staffInventory = stdStaff.getInventory();
                setMagicSlotInventory(staffInventory);
            } else {
                setMagicSlotInventory(new MagicInventory(9));
            }
            if(staffInventory.isEmpty()){
                setMagicSlotInventory(new MagicInventory(9));
            }
        }
    }

    public void convInventoryToSlotWithDrop(MagicInventory inventory){
        for(int i = 0; i < magicSlotInventory.size(); i++){
            if (i < inventory.size()){
                magicSlotInventory.setStack(i, inventory.getStack(i));
            }else {
                playerInvnetory.addPickBlock(inventory.getStack(i));
            }
        }
    }

    class magicSlot extends Slot {

        public magicSlot(Inventory inventory, int index, int x, int y) {
            super(inventory, index, x, y);
        }

        public boolean canInsert(ItemStack stack) {
            if(staffInventory.getStack(0).getItem() instanceof StdStaff staff){
                return this.getIndex() < staff.getSize();
            }
            return false;
        }
    }
}
