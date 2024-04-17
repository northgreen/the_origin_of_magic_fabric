package com.ictye.the_origin_of_magic.infrastructure.Datagen;

import com.ictye.the_origin_of_magic.Contents.AllItem;
import com.ictye.the_origin_of_magic.the_origin_of_magic;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import java.nio.file.Path;

public class DefaultLang extends FabricLanguageProvider {
    protected DefaultLang(FabricDataGenerator dataGenerator) {
        super(dataGenerator, "en_us");
    }

    @Override
    public void generateTranslations(TranslationBuilder translationBuilder) {
        // 生成代碼内翻譯
        for(String name : AllItem.ItemTrans.keySet()){
            translationBuilder.add(AllItem.ItemTrans.get(name), name);
        }

        the_origin_of_magic.LOGGER.info("Adding existing language file!");

        // 添加預製的語言文件
        try {
            Path existingFilePath = dataGenerator.getModContainer().findPath("assets/the_origin_of_magic/lang/en_us.existing.json").get();
            translationBuilder.add(existingFilePath);
        } catch (Exception e) {
            throw new RuntimeException("Failed to add existing language file!", e);
        }
    }
}
