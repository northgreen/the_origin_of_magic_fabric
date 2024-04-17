package com.ictye.the_origin_of_magic.infrastructure.GUI.MagicWorkbench;

import com.ictye.the_origin_of_magic.the_origin_of_magic_client;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerContext;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public  class MagicWorkbenchScreenFactory implements ExtendedScreenHandlerFactory {
    World world;
    BlockPos pos;

    public MagicWorkbenchScreenFactory(World world, BlockPos pos){
        this.world = world;
        this.pos = pos;
    }
    @Override
    public Text getDisplayName() {
        return Text.translatable("block.the_origin_of_magic.magic_workbench");
    }

    @Nullable
    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory inv, PlayerEntity player) {
        return new MagicWorkbenchScreenHandler(the_origin_of_magic_client.MAGIC_WORKSTATION_SCREEN_HANDLER_SCREEN_HANDLER_TYPE,syncId,player.getInventory(), ScreenHandlerContext.create(world, pos));
    }

    @Override
    public void writeScreenOpeningData(ServerPlayerEntity player, PacketByteBuf buf) {

    }
}
