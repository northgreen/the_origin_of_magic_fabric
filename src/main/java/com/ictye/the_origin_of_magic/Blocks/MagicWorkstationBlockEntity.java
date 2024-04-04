package com.ictye.the_origin_of_magic.Blocks;

import com.ictye.the_origin_of_magic.Contents.All_BlockEntity;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;

public class Magic_Workstation_Block_Entity extends BlockEntity  {
    public Magic_Workstation_Block_Entity(BlockPos pos, BlockState state) {
        super(All_BlockEntity.MAGIC_WORK_STATION_BLOCK_ENTITIY, pos, state);
    }

}
