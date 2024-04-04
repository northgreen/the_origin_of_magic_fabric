package com.ictye.the_origin_of_magic.Items.Staff;

import com.ictye.the_origin_of_magic.utils.MagicInventory;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class TestStaff extends StdStaff {

    private int size = 9;

    private final MagicInventory inventory = new MagicInventory(getSize());

    public TestStaff(Settings settings) {
        super(settings);
    }

    @Override
    TypedActionResult<ItemStack> onUse(World world, PlayerEntity user, Hand hand) {
        return TypedActionResult.success(user.getStackInHand(hand), world.isClient());
    }

    @Override
    public MagicInventory getInventory() {
        return this.inventory;
    }

    @Override
    void setInventory(MagicInventory inventory) {
        // this.inventory = inventory;
        this.inventory.setStackFromList(inventory) ;
    }

    @Override
    public int getSize() {
        return size;
    }
}
