package com.ictye.the_origin_of_magic.Blocks;

import com.ictye.the_origin_of_magic.Contents.AllBlockEntity;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;

public class MagicWorkstationBlockEntity extends BlockEntity  {
    public MagicWorkstationBlockEntity(BlockPos pos, BlockState state) {
        super(AllBlockEntity.MAGIC_WORK_STATION_BLOCK_ENTITIY, pos, state);
    }

}
