package com.ictye.the_origin_of_magic.Contents;

import com.ictye.the_origin_of_magic.Blocks.MagicWorkstationBlockEntity;
import com.ictye.the_origin_of_magic.the_origin_of_magic;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class AllBlockEntity {
    public static final BlockEntityType<MagicWorkstationBlockEntity> MAGIC_WORK_STATION_BLOCK_ENTITIY = BlockEntityType.Builder.create(MagicWorkstationBlockEntity::new, AllBlock.MAGIC_WORKSTATION).build(null);

    public static void register(){
        Registry.register(Registry.BLOCK_ENTITY_TYPE, new Identifier(the_origin_of_magic.Mod_Id,"magic_work_station"),MAGIC_WORK_STATION_BLOCK_ENTITIY);
    }
}
