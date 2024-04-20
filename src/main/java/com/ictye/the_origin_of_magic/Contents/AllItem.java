package com.ictye.the_origin_of_magic.Contents;

import com.ictye.the_origin_of_magic.foundation.Items.Magic.ExplosionMagicEntityItem;
import com.ictye.the_origin_of_magic.foundation.Items.Magic.LimiterItem.UndeadEntityLimiterItem;
import com.ictye.the_origin_of_magic.foundation.Items.Magic.PoisonMagicEntityItem;
import com.ictye.the_origin_of_magic.foundation.Items.Staff.DeadwoodStaff;
import com.ictye.the_origin_of_magic.foundation.Items.Staff.TestStaff;
import com.ictye.the_origin_of_magic.the_origin_of_magic;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.data.client.Model;
import net.minecraft.data.client.Models;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import net.minecraft.util.registry.Registry;

import java.util.Map;

/**
 * 所有物品和物品組都應該寫在這個類裏方便管理和調用
 */
public class AllItem {

    //////////////////////////////////////////////////
    // 所有物品組
    /**
     * 主物品組
     */
    public static final ItemGroup TheOriginOfMagicItemGroup = FabricItemGroupBuilder.build(new Identifier(the_origin_of_magic.Mod_Id,"normal"),
            () ->new ItemStack(AllBlock.BlockItems.get("magic_workbench")));
    /////////////////////////////////////////////////
    // 所有法杖
    public static final Item DEADWOOD_STAFF = new DeadwoodStaff(new FabricItemSettings()
            .maxCount(1)
            .group(TheOriginOfMagicItemGroup)
            .maxDamage(100)
            .rarity(Rarity.COMMON));
    public static final Item TEST_STAFF = new TestStaff(new FabricItemSettings()
            .maxCount(1)
            .group(TheOriginOfMagicItemGroup)
            .maxDamage(300)
            .rarity(Rarity.RARE)); // 測試法杖
    //////////////////////////////////////////////
    //所有魔法
    public static final Item EXPLOSION_MAGIC = new ExplosionMagicEntityItem(new FabricItemSettings()
            .maxCount(1)
            .group(TheOriginOfMagicItemGroup), AllEntity.EXPOLOSION_MAGIC_ENTITY_TYPE); // 測試魔法

    public static final Item POISON_MAGIC = new PoisonMagicEntityItem(new FabricItemSettings()
            .maxCount(1)
            .group(TheOriginOfMagicItemGroup), AllEntity.POISON_MAGIC_ENTITY_TYPE);

    public static final Item HOSTILE_ENTITY_LIMITER = new UndeadEntityLimiterItem(new FabricItemSettings()
            .maxCount(1)
            .group(TheOriginOfMagicItemGroup)
            .maxDamage(100)
            .rarity(Rarity.COMMON),null);
    ////////////////////////////////////////////////
    //所有材料
    /**
     * 血色寶石
     */
    public static final Item BLOOD_ESSENCE = new Item(new FabricItemSettings()
            .group(TheOriginOfMagicItemGroup)
            .rarity(Rarity.COMMON));

    /**
     * 水元素魔法
     */
    public static final Item WATER_MAGIC_ELEMENT = new Item(new FabricItemSettings()
            .group(TheOriginOfMagicItemGroup)
            .rarity(Rarity.COMMON));

    //////////////////////////////////////////////
    // 注冊列表
    /**
     * 物品ID列表
     */
    public static final Map<String, Item> ItemMap = Map.of(
            "deadwood_staff",DEADWOOD_STAFF,
            "test_staff",TEST_STAFF,
            "explosion_magic", EXPLOSION_MAGIC,
            "hostile_entity_limiter", HOSTILE_ENTITY_LIMITER,
            "blood_essence",BLOOD_ESSENCE,
            "water_magic_element",WATER_MAGIC_ELEMENT,
            "poison_magic",POISON_MAGIC
    );

    /**
     * 物品英文翻譯列表
     */
    public static final Map<String, Item> ItemTransMap = Map.of(
            "Deadwood Staff",DEADWOOD_STAFF,
            "Test Staff",TEST_STAFF,
            "Explosion Magic", EXPLOSION_MAGIC,
            "Hostile Entity Limiter", HOSTILE_ENTITY_LIMITER,
            "Blood Essence", BLOOD_ESSENCE,
            "Water Magic Element",WATER_MAGIC_ELEMENT,
            "Poison Magic",POISON_MAGIC
    );

    /**
     * 物品模型列表
     */
    public static final Map< Item,Model> ItemModelMap = Map.of(
            BLOOD_ESSENCE,Models.GENERATED,
            WATER_MAGIC_ELEMENT,Models.GENERATED,
            EXPLOSION_MAGIC,Models.GENERATED,
            HOSTILE_ENTITY_LIMITER,Models.GENERATED,
            POISON_MAGIC,Models.GENERATED
    );
    ///////////////////////////////////////

    /**
     * 注冊物品
     * @param name 名稱
     * @param item 物品
     */
    private static void registerItem(String name, Item item){
        Registry.register(Registry.ITEM, new Identifier(the_origin_of_magic.Mod_Id, name), item);
    }

    /**
     * 開始注冊物品
     */
    public static void registerItems(){
        // 注冊方塊物品
        for (String name : AllBlock.BlockItems.keySet()) {
            registerItem(name, AllBlock.BlockItems.get(name));
        }

        // 注冊物品
        for (String name : ItemMap.keySet()) {
            registerItem(name, ItemMap.get(name));
        }
    }
}
