package com.ictye.the_origin_of_magic;

import com.ictye.the_origin_of_magic.Contents.AllBlock;
import com.ictye.the_origin_of_magic.Contents.AllBlockEntity;
import com.ictye.the_origin_of_magic.Contents.AllEntity;
import com.ictye.the_origin_of_magic.Contents.AllItem;
import com.ictye.the_origin_of_magic.infrastructure.netWork.NetWorkReg;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class the_origin_of_magic implements ModInitializer {
    public static final String Mod_Id = "the_origin_of_magic";

    public static final Logger LOGGER = LoggerFactory.getLogger(Mod_Id);

    @Override
    public void onInitialize() {
        AllBlock.register();
        AllItem.registerItems();
        AllBlockEntity.register();
        AllEntity.regEntity();

        NetWorkReg.registerC2SPackets();
    }
}
