package com.ictye.the_origin_of_magic.Contents;

import com.ictye.the_origin_of_magic.Blocks.Magic_Workstation_Block_Entity;
import com.ictye.the_origin_of_magic.the_origin_of_magic;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class All_BlockEntity {
    public static final BlockEntityType<Magic_Workstation_Block_Entity> MAGIC_WORK_STATION_BLOCK_ENTITIY = BlockEntityType.Builder.create(Magic_Workstation_Block_Entity::new, All_Block.MAGIC_WORKSTATION).build(null);

    public static void register(){
        Registry.register(Registry.BLOCK_ENTITY_TYPE, new Identifier(the_origin_of_magic.Mod_Id,"magic_work_station"),MAGIC_WORK_STATION_BLOCK_ENTITIY);
    }
}
