package com.ictye.the_origin_of_magic.utils;

import com.ictye.the_origin_of_magic.foundation.Items.Magic.StdMagicItem;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.collection.DefaultedList;

/**
 * 存放魔法的物品欄，用於法術施放什麽的
 * 支持隊列操作，可以循環
 */
public class MagicInventory extends SimpleInventory {

    int count = 0;

    public MagicInventory(int size) {
        super(size);
    }

    public MagicInventory(DefaultedList<ItemStack> magicStack) {
        super(magicStack.size());
        for(ItemStack i : magicStack){
            if (i.getItem() instanceof StdMagicItem){
                this.setStack(magicStack.indexOf(i), i);
            }
        }
    }

    public MagicInventory setStackFromInventory(SimpleInventory inventory) {
        for (int i = 0; i < inventory.size(); i++) {
            this.setStack(i, inventory.getStack(i));
        }
        return this;
    }

    @Override
    public boolean canInsert(ItemStack stack) {
        return super.canInsert(stack) && stack.getItem() instanceof StdMagicItem;
    }

    private void updateCastCount(){
        if (size() != 1){
            if (count + 1 > size()){
                count = -1;
                updateCastCount();
            }else {
                count = count + 1;
            }
        }
    }

    public ItemStack next(){
        int c = count;
        updateCastCount();
        return getStack(c);
    }

    public void resetCount(){
        count = 0;
    }
}
