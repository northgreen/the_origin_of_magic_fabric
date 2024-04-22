package com.ictye.the_origin_of_magic.infrastructure.GUI.MagicWorkbench;

import com.ictye.the_origin_of_magic.foundation.Items.Staff.StdStaff;
import com.ictye.the_origin_of_magic.the_origin_of_magic_client;
import com.ictye.the_origin_of_magic.utils.MagicInventory;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerContext;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.screen.slot.Slot;
import net.minecraft.text.Text;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class MagicWorkbenchScreenHandler extends ScreenHandler {

    private  final MagicInventory magicSlotInventory = new MagicInventory(27); // 魔法物品欄
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
        Slot StaffSlot = new Slot(staffInventory, 0, 19, 23){
            ////////////////////////////////////////////////
            //                魔杖格子                     //
            ///////////////////////////////////////////////
            @Override
            public boolean canInsert(ItemStack stack) {
                return stack.getItem() instanceof StdStaff;
            }

            @Override
            public void setStack(ItemStack stack) {
                // 同步魔法格子和魔杖格子的背包
                if(!(stack.getItem() == null) && stack.getItem() instanceof StdStaff staff){
                    // 檢查是否為空上下文是爲了判斷是否為服務端，在服務端這麽做很大概率會炸掉貓貓的電腦的
                    if (context == ScreenHandlerContext.EMPTY){
                        // 同步名字
                        MagicWorkbenchScreen.setSTAFFNAME(stack.getName());
                    }

                    // 同步標簽和類本身的物品欄
                    staff.setItemFromNBT(stack.getNbt());
                    convInventoryToSlotWithDrop(staff.getInventory());
                } else {
                    if (context == ScreenHandlerContext.EMPTY){
                        // 清空名稱
                        MagicWorkbenchScreen.setSTAFFNAME(Text.empty());
                    }
                }
                super.setStack(stack);
            }

            @Override
            public void onTakeItem(PlayerEntity player, ItemStack stack) {
                // 取出魔杖
                if(stack.getItem() instanceof StdStaff staff){
                    // 同步NBT
                    MagicInventory inventory1 = staff.getInventory();
                    staff.getInventory().clear();
                    for(int i = 0; i < magicSlotInventory.size(); i++){
                        if (i < staff.getSize()){
                            inventory1.setStack(i,magicSlotInventory.getStack(i));
                        }
                    }
                    stack.setSubNbt("items",new MagicInventory(inventory1.size()).setStackFromInventory(inventory1).toNbtList());
                }
                magicSlotInventory.clear();
                super.onTakeItem(player, stack);
            }
        };

        staffInventory.onOpen(playerInventory.player);
        staffSlot = addSlot(StaffSlot);

        // 創建魔法格子
        int slotsCount = 27;
        magicSlotInventory.onOpen(playerInventory.player);
        int slotIndex = 0;
        for (int m = 0; m < slotsCount / 9 + 1; m++) {
            for (int n = 0; n < 9; n++) {
                if (slotIndex < slotsCount) {
                    magicSlots.add((magicSlot) addSlot(new magicSlot(magicSlotInventory, slotIndex, 8 + n * 18, 55 + m * 18)));
                    slotIndex++;
                } else {
                    break;
                }
            }
        }

        // 主物品栏的格子
        for (int m = 0; m < 3; ++m) {
            for (int n = 0; n < 9; ++n) {
                this.addSlot(new Slot(playerInventory, n + m * 9 + 9, 8 + n * 18, 122 + m * 18));
            }
        }

        // 快捷栏的格子
        for (int m = 0; m < 9; ++m) {
            this.addSlot(new Slot(playerInventory, m, 8 + m * 18, 180));
        }
    }

    @Override
    public boolean canUse(PlayerEntity player) {
        return this.staffInventory.canPlayerUse(player);
    }

    @Override
    public void close(PlayerEntity player){
        if(staffInventory.getStack(0).getItem() instanceof StdStaff staff){
            // 同步NBT
            MagicInventory inventory1 = staff.getInventory();
            staff.getInventory().clear();
            for(int i = 0; i < magicSlotInventory.size(); i++){
                if (i < staff.getSize()){
                    inventory1.setStack(i,magicSlotInventory.getStack(i));
                }
            }
            staffInventory.getStack(0).setSubNbt("items",new MagicInventory(inventory1.size()).setStackFromInventory(inventory1).toNbtList());
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

    public void convInventoryToSlotWithDrop(MagicInventory inventory){
        for(int i = 0; i < magicSlotInventory.size(); i++){
            if (i < inventory.size()){
                magicSlotInventory.setStack(i, inventory.getStack(i));
            }else {
                int finalI = i;
                context.run((world, pos) -> dropInventory(playerInvnetory.player, new SimpleInventory(inventory.getStack(finalI))));
            }
        }
    }

    /**
     * 魔法格子，用於存儲魔法的喵
     */
    class magicSlot extends Slot {
        public magicSlot(Inventory inventory, int index, int x, int y) {
            super(inventory, index, x, y);
        }

        public boolean canInsert(ItemStack stack) {
            // 防止錯誤地放入格子裏面
            if(staffInventory.getStack(0).getItem() instanceof StdStaff staff){
                return this.getIndex() < staff.getSize();
            }
            return false;
        }

        @Override
        public boolean isEnabled() {
            if (getSlot(0).getStack().getItem() instanceof StdStaff staff){
                return this.getIndex() < staff.getSize();
            }
            return false;
        }
    }
}
