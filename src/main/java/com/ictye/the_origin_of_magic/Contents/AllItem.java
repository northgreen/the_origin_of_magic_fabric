package com.ictye.the_origin_of_magic.Contents;

import com.ictye.the_origin_of_magic.Items.Magic.TestMagicEntityItem;
import com.ictye.the_origin_of_magic.Items.Staff.DeadwoodStaff;
import com.ictye.the_origin_of_magic.Items.Staff.TestStaff;
import com.ictye.the_origin_of_magic.the_origin_of_magic;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import net.minecraft.util.registry.Registry;

/**
 * 所有物品和物品組都應該寫在這個類裏方便管理和調用
 */
public class AllItem {

    /*
    所有物品組
      */

    /**
     * 主物品組
     */
    public static final ItemGroup TheOriginOfMagicItemGroup = FabricItemGroupBuilder.build(new Identifier(the_origin_of_magic.Mod_Id,"normal"),
                                                                                                    () ->new ItemStack(AllBlock.BlockItems.get("magic_workbench")));
    /**
     * 所有法杖
     */
    public static final Item DEADWOOD_STAFF = new DeadwoodStaff(new FabricItemSettings().maxCount(1).group(TheOriginOfMagicItemGroup).maxDamage(100).rarity(Rarity.COMMON));
    public static final Item TEST_STAFF = new TestStaff(new FabricItemSettings().maxCount(1).group(TheOriginOfMagicItemGroup).maxDamage(300).rarity(Rarity.RARE)); // 測試法杖
    /**
     * 所有魔法
     */
    public static final Item TEST_MAGIC = new TestMagicEntityItem(new FabricItemSettings().maxCount(1).group(TheOriginOfMagicItemGroup), AllEntity.TEST_MAGIC_ENTITY_TYPE); // 測試魔法

    private static void registerItem(String name, Item item){
        Registry.register(Registry.ITEM, new Identifier(the_origin_of_magic.Mod_Id, name), item);
    }
    public static void registerItems(){
        // 注冊方塊物品
        for (String name : AllBlock.BlockItems.keySet()) {
            registerItem(name, AllBlock.BlockItems.get(name));
        }

        // 注冊其他物品
        registerItem("test_staff",TEST_STAFF);
        registerItem("test_magic",TEST_MAGIC);
        registerItem("deadwood_staff",DEADWOOD_STAFF);
    }
}
