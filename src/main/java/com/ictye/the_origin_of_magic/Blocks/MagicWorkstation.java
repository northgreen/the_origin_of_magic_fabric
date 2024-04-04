package com.ictye.the_origin_of_magic.Blocks;

import com.ictye.the_origin_of_magic.GUI.MagicWorkbench.MagicWorkbenchScreenFactory;
import com.ictye.the_origin_of_magic.Items.Staff.StdStaff;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
/*
* 魔法工作臺
* */

public class MagicWorkstation extends BlockWithEntity {
    public MagicWorkstation(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if(!world.isClient){
            ItemStack itemStack = player.getStackInHand(hand);
            if(itemStack.getItem() instanceof StdStaff){
                // 打開屏幕
                NamedScreenHandlerFactory screenHandlerFactory = new MagicWorkbenchScreenFactory(world,pos);
                player.openHandledScreen(screenHandlerFactory);
            }else {
                player.sendMessage(Text.translatable("text.the_origin_of_magic.not_staff"));
            }
        }
        return ActionResult.SUCCESS;
    }


    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new MagicWorkstationBlockEntity(pos,state);
    }

    @Override
    public BlockRenderType getRenderType(BlockState state){
        return BlockRenderType.MODEL;
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        return super.getTicker(world, state, type);
    }


}
