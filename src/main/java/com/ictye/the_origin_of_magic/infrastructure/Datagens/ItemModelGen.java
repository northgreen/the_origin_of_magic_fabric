package com.ictye.the_origin_of_magic.infrastructure.Datagens;

import com.ictye.the_origin_of_magic.Contents.AllItem;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.item.Item;

public class ItemModelGen extends FabricModelProvider {
    public ItemModelGen(FabricDataGenerator dataGenerator) {
        super(dataGenerator);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {

    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        // 生成物品模型
        for(Item item : AllItem.ItemModelMap.keySet()){
            itemModelGenerator.register(item, AllItem.ItemModelMap.get(item));
        }
    }
}
