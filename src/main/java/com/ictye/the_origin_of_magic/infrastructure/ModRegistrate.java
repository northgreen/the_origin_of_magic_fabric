package com.ictye.the_origin_of_magic.infrastructure;

import com.ictye.the_origin_of_magic.the_origin_of_magic;
import net.minecraft.block.Block;
import net.minecraft.data.client.Model;
import net.minecraft.entity.EntityType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;

public class ModRegistrate {
    public ModRegistrate(){

    }
    /**
     * 物品ID列表
     */
    public static final Map<String, Item> ItemMap = new HashMap<>();

    /**
     * 物品英文翻譯列表
     */
    public static final Map<String, Item> ItemTransMap = new HashMap<>();

    /**
     * 物品模型列表
     */
    public static final Map< Item, Model> ItemModelMap = new HashMap<>();

    /**
     * 方塊ID和方塊的映射
     */
    public static final Map<String,Item> BlockItems = new HashMap<>();

    /**
     * 注册物品
     * @param item 物品
     * @param id 物品ID
     * @param name 物品英文翻译
     * @param model 物品模型
     * @param settings 物品设置
     * @return 物品對象
     */
    public Item itemBuilder(Function<Item.Settings,Item> item ,String id,String name,Model model,Item.Settings settings){
        Item item1 = item.apply(settings);
        ItemMap.put(id,item1);
        ItemTransMap.put(name,item1);
        if(model != null)  ItemModelMap.put(item1,model);
        Registry.register(Registry.ITEM, new Identifier(the_origin_of_magic.Mod_Id, id), item1);
        return item1;
    }

    /**
     * 注册物品,不過這個是注冊魔法物品的
     * @param item 物品
     * @param id 物品ID
     * @param name 物品英文翻译
     * @param model 物品模型
     * @param settings 物品设置
     * @return 物品對象
     */
    public Item itemBuilder(BiFunction<Item.Settings, EntityType ,Item> item , String id, String name, Model model, Item.Settings settings , EntityType entityType){
        Item item1 = item.apply(settings,entityType);
        ItemMap.put(id,item1);
        ItemTransMap.put(name,item1);
        if(model != null)  ItemModelMap.put(item1,model);
        Registry.register(Registry.ITEM, new Identifier(the_origin_of_magic.Mod_Id, id), item1);
        return item1;
    }


    /**
     * 注册方塊(帶有方塊物品)
     * @param block 方塊
     * @param id ID
     * @param name 名字
     * @param settings 方塊設置
     * @param itemSettings 物品設置
     * @return 方塊對象
     */
    public Block blockBuilder(Function<Block.Settings,Block> block ,String id,String name,Block.Settings settings,Item.Settings itemSettings){
        Block block1 = block.apply(settings);
        Item blockItem = new BlockItem(block1,itemSettings);
        BlockItems.put(id,blockItem);
        ItemMap.put(id,blockItem);
        ItemTransMap.put(name,blockItem);
        Registry.register(Registry.ITEM, new Identifier(the_origin_of_magic.Mod_Id, id), blockItem);
        Registry.register(Registry.BLOCK, new Identifier(the_origin_of_magic.Mod_Id, id), block1);
        return block1;
    }

    /**
     * 注册方塊(進注冊方塊)
     * @param block 方塊
     * @param id ID
     * @param settings 方塊設置
     * @return 方塊對象
     */
    public Block blockBuilder(Function<Block.Settings,Block> block ,String id,Block.Settings settings){
        Block block1 = block.apply(settings);
        Registry.register(Registry.BLOCK, new Identifier(the_origin_of_magic.Mod_Id, id), block1);
        return block1;
    }

    public EntityType entityBuilder(EntityType entityType,String id){
        Registry.register(Registry.ENTITY_TYPE,
                new Identifier(the_origin_of_magic.Mod_Id, id),
                entityType);
        return entityType;
    }
}
