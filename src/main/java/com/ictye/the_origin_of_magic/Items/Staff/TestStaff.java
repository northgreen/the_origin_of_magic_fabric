package com.ictye.the_origin_of_magic.Items.Staff;

import com.ictye.the_origin_of_magic.utils.MagicInventory;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class Test_Staff extends StdStaff {

    private int size = 9;

    private MagicInventory inventory = new MagicInventory(size);

    public Test_Staff(Settings settings) {
        super(settings);
    }

    @Override
    TypedActionResult<ItemStack> onUse(World world, PlayerEntity user, Hand hand) {
        return TypedActionResult.success(user.getStackInHand(hand), world.isClient());
    }

    @Override
    public MagicInventory getInventory() {
        // this.inventory.addStack(new ItemStack(All_Item.TEST_MAGIC));
        return this.inventory;
    }

    @Override
    void setInventory(Inventory inventory) {
        this.inventory = (MagicInventory) inventory;
    }
}
