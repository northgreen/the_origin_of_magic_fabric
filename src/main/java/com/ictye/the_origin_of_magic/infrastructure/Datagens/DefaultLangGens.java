package com.ictye.the_origin_of_magic.infrastructure.Datagens;

import com.ictye.the_origin_of_magic.infrastructure.ModRegistrate;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;

import java.nio.file.Path;

public class DefaultLangGens extends FabricLanguageProvider {
    protected DefaultLangGens(FabricDataGenerator dataGenerator) {
        super(dataGenerator, "en_us");
    }

    @Override
    public void generateTranslations(TranslationBuilder translationBuilder) {
        // 生成代碼内翻譯
        for(String name : ModRegistrate.ItemTransMap.keySet()){
            translationBuilder.add(ModRegistrate.ItemTransMap.get(name), name);
        }

        // 添加預製的語言文件
        try {
            Path existingFilePath = dataGenerator.getModContainer().findPath("assets/the_origin_of_magic/lang/en_us.existing.json").get();
            translationBuilder.add(existingFilePath);
        } catch (Exception e) {
            throw new RuntimeException("Failed to add existing language file!", e);
        }
    }
}
