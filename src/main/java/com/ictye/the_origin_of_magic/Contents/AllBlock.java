package com.ictye.the_origin_of_magic.Contents;

import com.ictye.the_origin_of_magic.foundation.Blocks.MagicWorkstation;
import com.ictye.the_origin_of_magic.the_origin_of_magic;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import java.util.HashMap;

public class AllBlock {

    public static final HashMap<String,Item> BlockItems = new HashMap<>();

    public static Block MAGIC_WORKSTATION = new MagicWorkstation(FabricBlockSettings.of(Material.STONE).strength(50.0f, 1200.0f).luminance(state -> 10));
    private static void registerBlock(String name, Block block){
        Registry.register(Registry.BLOCK,new Identifier(the_origin_of_magic.Mod_Id, name),block);
        BlockItems.put(name,new BlockItem(block,new FabricItemSettings().group(AllItem.TheOriginOfMagicItemGroup)));
    }

    public static void register(){
        registerBlock("magic_workbench",MAGIC_WORKSTATION);
    }
}
