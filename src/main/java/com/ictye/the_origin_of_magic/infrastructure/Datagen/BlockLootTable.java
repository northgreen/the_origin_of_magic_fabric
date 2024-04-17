package com.ictye.the_origin_of_magic.infrastructure.Datagen;

import com.ictye.the_origin_of_magic.Contents.AllBlock;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;

public class BlockLootTable extends FabricBlockLootTableProvider {

    public BlockLootTable(FabricDataGenerator dataGenerator) {
        super(dataGenerator);
    }

    @Override
    protected void generateBlockLootTables() {
        addDrop(AllBlock.MAGIC_WORKSTATION);
    }
}
