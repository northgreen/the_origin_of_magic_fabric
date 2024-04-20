package com.ictye.the_origin_of_magic.foundation.Blocks;

import com.ictye.the_origin_of_magic.Contents.AllBlockEntity;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;

/**
 *  魔法工作臺的方塊實體
 */
public class MagicWorkstationBlockEntity extends BlockEntity  {
    public MagicWorkstationBlockEntity(BlockPos pos, BlockState state) {
        super(AllBlockEntity.MAGIC_WORK_STATION_BLOCK_ENTITIY, pos, state);
    }
}
