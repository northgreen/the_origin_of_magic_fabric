package com.ictye.the_origin_of_magic.GameTest;

import com.ictye.the_origin_of_magic.Contents.AllBlock;
import com.ictye.the_origin_of_magic.infrastructure.GUI.MagicWorkbench.MagicWorkbenchScreenHandler;
import com.ictye.the_origin_of_magic.the_origin_of_magic_client;
import net.fabricmc.fabric.api.gametest.v1.FabricGameTest;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.test.GameTest;
import net.minecraft.test.TestContext;
import net.minecraft.util.math.BlockPos;


public class GameTestMain {
    @GameTest(templateName = FabricGameTest.EMPTY_STRUCTURE)
    public void test(TestContext testContext){
        BlockPos magicWorkbenchPos = new BlockPos(0, 0, 0);
        testContext.setBlockState(magicWorkbenchPos, AllBlock.MAGIC_WORKSTATION.getDefaultState());
        testContext.expectBlock(AllBlock.MAGIC_WORKSTATION,magicWorkbenchPos);
        testContext.useBlock(magicWorkbenchPos);

        testContext.complete();
    }
}
