package com.ictye.the_origin_of_magic.utils;

import com.ictye.the_origin_of_magic.foundation.Items.Magic.StdMagicItem;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;

/**
 * 存放魔法的物品欄，用於法術施放什麽的
 */
public class MagicInventory extends SimpleInventory {

    public MagicInventory(int size) {
        super(size);
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

}
