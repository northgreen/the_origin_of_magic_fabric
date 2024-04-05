package com.ictye.the_origin_of_magic.GUI.MagicWorkbench;

import com.ictye.the_origin_of_magic.Items.Magic.StdMagicItem;
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
import net.minecraft.text.Text;
import org.jetbrains.annotations.Nullable;
import java.util.ArrayList;
import java.util.List;

public class MagicWorkbenchScreenHandler extends ScreenHandler {

    private  final MagicInventory magicInventory = new MagicInventory(9); // 魔法物品欄
    private final SimpleInventory staffInventory = new SimpleInventory(1); // 魔杖格子
    private final Slot staffSlot; // 魔杖格子
    private final List<magicSlot> magicSlots = new ArrayList<>();
    private final ScreenHandlerContext context;
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
        // 魔杖格子
        Slot StaffSlot = new Slot(staffInventory, 0, 19, 21){
            @Override
            public boolean canInsert(ItemStack stack) {
                return (stack.getItem() instanceof StdStaff);
            }

            @Override
            public void setStack(ItemStack stack) {
                // 同步魔法格子和魔杖格子的背包
                if(!(stack.getItem() == null) && stack.getItem() instanceof StdStaff staff){
                    // 同步名字
                    MagicWorkbenchScreen.setSTAFFNAME(stack.getName());
                    // 同步標簽和類本身的物品欄
                    staff.setItemFromNBT(stack.getNbt());
                    magicInventory.setStackFromList(staff.getInventory());
                } else {
                    MagicWorkbenchScreen.setSTAFFNAME(Text.empty());
                }

                for(magicSlot slot : magicSlots){
                    slot.setEnable(false);
                }

                for(int q = 0;q<staffInventory.size();q++){
                    magicSlots.get(q).setEnable(true);
                }
                super.setStack(stack);
            }

            @Override
            public ItemStack getStack() {
                ItemStack stack = super.getStack();
                if(stack.getItem() instanceof StdStaff staff){
                    setStaffNBT();
                }
                return stack;
            }

            @Override
            public void onTakeItem(PlayerEntity player, ItemStack stack) {
                if(stack.getItem() instanceof StdStaff){
                    setStaffNBT();
                }
                super.onTakeItem(player, stack);
            }
        };

        staffInventory.onOpen(playerInventory.player);
        staffInventory.addListener(new StaffChangeLisener());
        staffSlot = addSlot(StaffSlot);

        // 創建魔法格子
        int slotsCount = magicInventory.size();
        magicInventory.onOpen(playerInventory.player);
        int slotIndex = 0;
        for (int m = 0; m < slotsCount / 9 + 1; m++) {
            for (int n = 0; n < 9; n++) {
                if (slotIndex < slotsCount) {
                    magicSlots.add((magicSlot) addSlot(new magicSlot(magicInventory, slotIndex, 8 + n * 18, 54 + m * 18)));
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
    public void setMagicInventory(MagicInventory inventory) {
        magicInventory.setStackFromList(inventory);
    }

    @Override
    public boolean canUse(PlayerEntity player) {
        return true;
    }

    @Override
    public void close(PlayerEntity player){
        if(staffInventory.getStack(0).getItem() instanceof StdStaff){
            setStaffNBT();
        }
        magicInventory.clear();
        this.context.run((world, pos) -> this.dropInventory(player, this.staffInventory));
        super.close(player);
    }

    private void setStaffNBT(){
        this.staffInventory.getStack(0).setSubNbt("items", magicInventory.toNbtList());
    }

    public ItemStack transferSlot(PlayerEntity player, int invSlot) {
        ItemStack newStack = ItemStack.EMPTY;
        Slot slot = this.slots.get(invSlot);
        if (slot.hasStack()) {
            ItemStack originalStack = slot.getStack();
            newStack = originalStack.copy();
            if (invSlot < this.magicInventory.size()) {
                if (!this.insertItem(originalStack, this.magicInventory.size(), this.slots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.insertItem(originalStack, 0, this.magicInventory.size(), false)) {
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
            if(sender.getStack(0).getItem() instanceof StdStaff stdStaff){
                MagicInventory staffInventory = stdStaff.getInventory();
                setMagicInventory(staffInventory);
            } else {
                setMagicInventory(new MagicInventory(0));
            }
            if(staffInventory.isEmpty()){
                setMagicInventory(new MagicInventory(0));
            }

        }
    }

    class magicSlot extends Slot {
        boolean enable = false;

        public magicSlot(Inventory inventory, int index, int x, int y) {
            super(inventory, index, x, y);
        }

        public boolean canInsert(ItemStack stack) {
            return (stack.getItem() instanceof StdMagicItem) && !staffInventory.isEmpty() && enable;
        }
        public void setEnable(boolean enable){
            this.enable = enable;
        }

    }
}
