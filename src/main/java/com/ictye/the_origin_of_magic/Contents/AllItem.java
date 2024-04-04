package com.ictye.the_origin_of_magic.Contents;

import com.ictye.the_origin_of_magic.Items.Magic.TestMagicEntityItem;
import com.ictye.the_origin_of_magic.Items.Staff.Test_Staff;
import com.ictye.the_origin_of_magic.the_origin_of_magic;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;


public class All_Item {
    public static final Item TEST_STAFF = new Test_Staff(new FabricItemSettings().maxCount(1));
    public static final Item TEST_MAGIC = new TestMagicEntityItem(new FabricItemSettings().maxCount(1),All_Entity.TEST_MAGIC_ENTITY_TYPE);

    private static void registerItem(String name, Item item){
        Registry.register(Registry.ITEM, new Identifier(the_origin_of_magic.Mod_Id, name), item);
    }
    public static void registerItems(){
        for (String name : All_Block.BlockItems.keySet()) {
            registerItem(name, All_Block.BlockItems.get(name));
        }
        registerItem("test_staff",TEST_STAFF);
        registerItem("test_magic",TEST_MAGIC);
    }
}
