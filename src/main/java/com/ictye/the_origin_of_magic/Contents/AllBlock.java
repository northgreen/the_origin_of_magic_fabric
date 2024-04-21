package com.ictye.the_origin_of_magic.Contents;

import com.ictye.the_origin_of_magic.foundation.Blocks.MagicWorkstation;
import com.ictye.the_origin_of_magic.the_origin_of_magic;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.item.Item;

import java.util.HashMap;

public class AllBlock {

    /**
     * 方塊ID和方塊的映射
     */
    public static HashMap<String,Item> BlockItems = new HashMap<>();

    //魔法工作臺
    public static Block MAGIC_WORKSTATION = the_origin_of_magic.MOD_REGISTRATE.blockBuilder(
            MagicWorkstation::new,
                "magic_workbench",
            "Magic Workbench",
            FabricBlockSettings.
                    of(Material.STONE)
                    .strength(50.0f, 1200.0f)
                    .luminance(state -> 10),
            new FabricItemSettings()
                    .group(AllItem.TheOriginOfMagicItemGroup)

    );
}
