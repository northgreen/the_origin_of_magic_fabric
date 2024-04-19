package com.ictye.the_origin_of_magic.infrastructure.Datagens;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

import java.io.IOException;

public class ModDataGenerator implements DataGeneratorEntrypoint {
    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {

        fabricDataGenerator.addProvider(DefaultLangGens::new);
        fabricDataGenerator.addProvider(BlockLootTableGens::new);

        try {
            fabricDataGenerator.run();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
