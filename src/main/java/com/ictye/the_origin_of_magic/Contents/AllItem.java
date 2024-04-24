package com.ictye.the_origin_of_magic.Contents;

import com.ictye.the_origin_of_magic.foundation.Items.Magic.CorrectionMagic.MagicLIfeTimeDownItem;
import com.ictye.the_origin_of_magic.foundation.Items.Magic.CorrectionMagic.MagicLifeTimeUpItem;
import com.ictye.the_origin_of_magic.foundation.Items.Magic.ExplosionMagicEntityItem;
import com.ictye.the_origin_of_magic.foundation.Items.Magic.LimiterItem.UndeadEntityLimiterItem;
import com.ictye.the_origin_of_magic.foundation.Items.Magic.MagicBulletItem;
import com.ictye.the_origin_of_magic.foundation.Items.Magic.PoisonMagicEntityItem;
import com.ictye.the_origin_of_magic.foundation.Items.Magic.RayMagicItem;
import com.ictye.the_origin_of_magic.foundation.Items.Staff.DeadwoodStaff;
import com.ictye.the_origin_of_magic.foundation.Items.Staff.TestStaff;
import com.ictye.the_origin_of_magic.infrastructure.ModRegistrate;
import com.ictye.the_origin_of_magic.the_origin_of_magic;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.data.client.Models;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import net.minecraft.util.registry.Registry;

/**
 * 所有物品和物品組都應該寫在這個類裏方便管理和調用
 */
@SuppressWarnings("unused")
public class AllItem {

    //////////////////////////////////////////////////
    // 所有物品組
    /**
     * 主物品組
     */
    public static final ItemGroup TheOriginOfMagicItemGroup = FabricItemGroupBuilder.build(new Identifier(the_origin_of_magic.Mod_Id,"normal"),
            () ->new ItemStack(ModRegistrate.BlockItems.get("magic_workbench")));
    /////////////////////////////////////////////////
    // 所有法杖
    public static final Item DEADWOOD_STAFF = the_origin_of_magic.MOD_REGISTRATE.itemBuilder(
            DeadwoodStaff::new,
            "deadwood_staff",
            "Deadwood Staff",
            null,
            new FabricItemSettings()
                    .maxCount(1)
                    .group(TheOriginOfMagicItemGroup)
                    .maxDamage(100)
                    .rarity(Rarity.COMMON)
    );

    public static final Item TEST_STAFF = the_origin_of_magic.MOD_REGISTRATE.itemBuilder(
            TestStaff::new,
            "test_staff",
            "Test Staff",
            null,
            new FabricItemSettings()
                    .maxCount(1)
                    .group(TheOriginOfMagicItemGroup)
                    .maxDamage(300)
                    .rarity(Rarity.RARE));
    //////////////////////////////////////////////
    //所有魔法
    public static final Item EXPLOSION_MAGIC = the_origin_of_magic.MOD_REGISTRATE.itemBuilder(
            ExplosionMagicEntityItem::new,
            "explosion_magic",
            "Explosion Magic",
            Models.GENERATED,
            new FabricItemSettings()
                    .maxCount(1)
                    .group(TheOriginOfMagicItemGroup),
            AllEntity.EXPOLOSION_MAGIC_ENTITY_TYPE
    );

    public static final Item POISON_MAGIC = the_origin_of_magic.MOD_REGISTRATE.itemBuilder(
            PoisonMagicEntityItem::new,
            "poison_magic",
            "Poison Magic",
            Models.GENERATED,
            new FabricItemSettings()
                    .maxCount(1)
                    .group(TheOriginOfMagicItemGroup),
            AllEntity.POISON_MAGIC_ENTITY_TYPE
    );

    public static final Item HOSTILE_ENTITY_LIMITER = the_origin_of_magic.MOD_REGISTRATE.itemBuilder(
            UndeadEntityLimiterItem::new,
            "hostile_entity_limiter",
            "Hostile Entity Limiter",
            Models.GENERATED,
            new FabricItemSettings()
                    .maxCount(1)
                    .group(TheOriginOfMagicItemGroup)
                    .maxDamage(100)
                    .rarity(Rarity.COMMON),
            null
    );


    public static final Item RAY_MAGIC_ITEM = the_origin_of_magic.MOD_REGISTRATE.itemBuilder(
            RayMagicItem::new,
            "ray_magic",
            "Ray Magic",
            Models.GENERATED,
            new FabricItemSettings()
                .maxCount(1)
                .group(TheOriginOfMagicItemGroup)
                .maxDamage(100)
                .rarity(Rarity.COMMON),
            AllEntity.POISON_RAY_MAGIC_ENTITY_TYPE
    );

    public static final Item MAGIC_BULLET_ITEM = the_origin_of_magic.MOD_REGISTRATE.itemBuilder(
            MagicBulletItem::new,
            "magic_bullet",
            "Magic Bullet",
            Models.GENERATED,
            new FabricItemSettings()
                .maxCount(1)
                .group(TheOriginOfMagicItemGroup)
                .maxDamage(100)
                .rarity(Rarity.COMMON),
            AllEntity.MAGIC_BULLET_ENTITY_TYPE
    );

    public static final Item MAGIC_LIFE_TIME_UP = the_origin_of_magic.MOD_REGISTRATE.itemBuilder(
            MagicLifeTimeUpItem::new,
            "magic_life_time_up",
            "Magic Life Time Up",
            Models.GENERATED,
            new FabricItemSettings()
                .maxCount(1)
                .group(TheOriginOfMagicItemGroup)
                .maxDamage(100)
                .rarity(Rarity.COMMON),
            null
    );

    public static final Item MAGIC_LIFE_TIME_DOWN = the_origin_of_magic.MOD_REGISTRATE.itemBuilder(
            MagicLIfeTimeDownItem::new,
            "magic_life_time_down",
            "Magic Life Time Down",
            Models.GENERATED,
            new FabricItemSettings()
                    .maxCount(1)
                    .group(TheOriginOfMagicItemGroup)
                    .maxDamage(100)
                    .rarity(Rarity.COMMON),
            null
    );


    ////////////////////////////////////////////////
    //所有材料
    /**
     * 血色寶石
     */
    public static final Item BLOOD_ESSENCE = the_origin_of_magic.MOD_REGISTRATE.itemBuilder(
            Item::new,
            "blood_essence",
            "Blood Essence",
            Models.GENERATED,
            new FabricItemSettings()
                    .group(TheOriginOfMagicItemGroup)
                    .rarity(Rarity.COMMON));

    /**
     * 水元素魔法
     */
    public static final Item WATER_MAGIC_ELEMENT = the_origin_of_magic.MOD_REGISTRATE.itemBuilder(
            Item::new,
            "water_magic_element",
            "Water Magic Element",
            Models.GENERATED,
            new FabricItemSettings()
                    .group(TheOriginOfMagicItemGroup)
                    .rarity(Rarity.COMMON));

    /**
     * 土元素魔法
     */
    public static final Item EARTH_MAGIC_ELEMENT = the_origin_of_magic.MOD_REGISTRATE.itemBuilder(
            Item::new,
            "earth_magic_element",
            "Earth Magic Element",
            Models.GENERATED,
            new FabricItemSettings()
                    .group(TheOriginOfMagicItemGroup)
                    .rarity(Rarity.COMMON));

    /**
     * 火元素魔法
     */
    public static final Item FIRE_MAGIC_ELEMENT = the_origin_of_magic.MOD_REGISTRATE.itemBuilder(
            Item::new,
            "fire_magic_element",
            "Fire Magic Element",
            Models.GENERATED,
            new FabricItemSettings()
                .group(TheOriginOfMagicItemGroup)
                .rarity(Rarity.COMMON));

    /**
     * 金元素魔法
     */
    public static final Item GOLD_MAGIC_ELEMENT = the_origin_of_magic.MOD_REGISTRATE.itemBuilder(
            Item::new,
            "gold_magic_element",
            "Gold Magic Element",
            Models.GENERATED,
            new FabricItemSettings()
                .group(TheOriginOfMagicItemGroup)
                .rarity(Rarity.COMMON));


    /**
     * 木元素魔法
     */
    public static final Item NATURE_MAGIC_ELEMENT = the_origin_of_magic.MOD_REGISTRATE.itemBuilder(
            Item::new,
            "nature_magic_element",
            "Nature Magic Element",
            Models.GENERATED,
            new FabricItemSettings()
                .group(TheOriginOfMagicItemGroup)
                .rarity(Rarity.COMMON));

    /**
     * 空元素魔法
     */
    public static final Item EMPTY_MAGIC_ELEMENT = the_origin_of_magic.MOD_REGISTRATE.itemBuilder(
            Item::new,
            "empty_magic_element",
            "Empty Magic Element",
            Models.GENERATED,
            new FabricItemSettings()
                    .group(TheOriginOfMagicItemGroup)
                    .rarity(Rarity.COMMON));



    /**
     * 邪惡魔法元素
     */
    public static final Item EVIL_MAGIC_ELEMENT = the_origin_of_magic.MOD_REGISTRATE.itemBuilder(
            Item::new,
            "evil_magic_element",
            "Evil Magic Element",
            Models.GENERATED,
            new FabricItemSettings()
                    .group(TheOriginOfMagicItemGroup)
                    .rarity(Rarity.COMMON));

    /**
     * 善良魔法元素
     */
    public static final Item KIND_MAGIC_ELEMENT = the_origin_of_magic.MOD_REGISTRATE.itemBuilder(
            Item::new,
            "kind_magic_element",
            "Kind Magic Element",
            Models.GENERATED,
            new FabricItemSettings()
                    .group(TheOriginOfMagicItemGroup)
                    .rarity(Rarity.COMMON));


    /**
     * 魔法核心
     */
    public static final Item MAGIC_CORE = the_origin_of_magic.MOD_REGISTRATE.itemBuilder(
            Item::new,
            "magic_core",
            "Magic Core",
            Models.GENERATED,
            new FabricItemSettings()
            .group(TheOriginOfMagicItemGroup)
            .rarity(Rarity.COMMON));
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
    }
}
