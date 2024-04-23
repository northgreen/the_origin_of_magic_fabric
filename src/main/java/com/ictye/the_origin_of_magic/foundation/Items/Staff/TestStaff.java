package com.ictye.the_origin_of_magic.foundation.Items.Staff;

import com.ictye.the_origin_of_magic.utils.MagicInventory;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class TestStaff extends StdStaff {

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        super.appendTooltip(stack, world, tooltip, context);

        tooltip.add(Text.translatable("item.the_origin_of_magic.test_staff.tooltip"));
    }

    public TestStaff(Settings settings) {
        super(settings);
        size = 9;
        this.staffAgeRate = 5;
        this.inventory = new MagicInventory(this.size);
    }
}
