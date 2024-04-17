package com.ictye.the_origin_of_magic.infrastructure.Datagen;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

import java.io.IOException;

public class ModDataGenerator implements DataGeneratorEntrypoint {
    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {

        fabricDataGenerator.addProvider(DefaultLang::new);
        fabricDataGenerator.addProvider(BlockLootTable::new);

        try {
            fabricDataGenerator.run();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
