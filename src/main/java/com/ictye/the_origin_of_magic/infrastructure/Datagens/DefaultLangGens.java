package com.ictye.the_origin_of_magic.infrastructure.Datagens;

import com.ictye.the_origin_of_magic.infrastructure.ModRegistrate;
import com.ictye.the_origin_of_magic.the_origin_of_magic;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.potion.Potion;
import net.minecraft.util.registry.Registry;

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
        for (Potion potion : Registry.POTION) {
            if (potion.getEffects().isEmpty()) continue;
            String str = potion.finishTranslationKey("");
            str = str.replace("_", " ").substring(0, 1).toUpperCase() + str.replace("_", " ").substring(1);;
            the_origin_of_magic.LOGGER.info("\"item."+the_origin_of_magic.Mod_Id+".ray_magic.effect." + potion.finishTranslationKey("")+"\":"+"\""+str+"\""+",");
            try{
                translationBuilder.add("item."+the_origin_of_magic.Mod_Id+".ray_magic.effect." + potion.finishTranslationKey(""), str +" Ray Magic");
            }catch (RuntimeException e){
                the_origin_of_magic.LOGGER.warn("Failed to add translation for item or potion!", e);
            }
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
