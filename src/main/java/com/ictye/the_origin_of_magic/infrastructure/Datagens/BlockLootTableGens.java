package com.ictye.the_origin_of_magic.infrastructure.Datagens;

import com.ictye.the_origin_of_magic.infrastructure.ModRegistrate;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.block.Block;

public class BlockLootTableGens extends FabricBlockLootTableProvider {

    public BlockLootTableGens(FabricDataGenerator dataGenerator) {
        super(dataGenerator);
    }

    @Override
    protected void generateBlockLootTables() {
        for (Block block : ModRegistrate.NormalBlockList) {
            addDrop(block);
        }
        for (Block block : ModRegistrate.OreItemMap.keySet()) {
            addDrop(block, ModRegistrate.OreItemMap.get(block));
        }
    }
}
