package com.ictye.the_origin_of_magic.utils;

import com.ictye.the_origin_of_magic.Items.Magic.Std_Magic_Item;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.RecipeInputProvider;

public class MagicItemStack extends SimpleInventory implements Inventory, RecipeInputProvider {

    public MagicItemStack(int size) {
        super(size);
    }

    @Override
    public boolean canInsert(ItemStack stack) {
        if(stack.getItem() instanceof Std_Magic_Item){
            return super.canInsert(stack);
        }else {
            return false;
        }
    }

    @Override
    public boolean canPlayerUse(PlayerEntity player) {
        return false;
    }
}
