package com.ictye.the_origin_of_magic.Items.Staff;

import com.ictye.the_origin_of_magic.utils.MagicInventory;

public class TestStaff extends StdStaff {

    private int size = 9;

    private final MagicInventory inventory = new MagicInventory(getSize());

    public TestStaff(Settings settings) {
        super(settings);
    }

    @Override
    public int getSize() {
        return size;
    }
}
