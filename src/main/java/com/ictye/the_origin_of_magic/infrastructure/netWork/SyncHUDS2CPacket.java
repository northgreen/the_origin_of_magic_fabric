package com.ictye.the_origin_of_magic.infrastructure.netWork;

import com.ictye.the_origin_of_magic.foundation.PlayerAbilities.MagicAbilitiesManager;
import com.ictye.the_origin_of_magic.utils.PlayerEntityMixinInterfaces;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.PacketByteBuf;

public class SyncHUDS2CPacket {
    @SuppressWarnings("unused")
    public static void receive(MinecraftClient client, ClientPlayNetworkHandler handler, PacketByteBuf buffer, PacketSender responseSender) {
        PlayerEntity playerEntity = client.player;
        MagicAbilitiesManager magicAbilitiesManager = null;
        if (playerEntity != null) {
            magicAbilitiesManager = ((PlayerEntityMixinInterfaces) playerEntity).the_origin_of_magic$getMagicAbilitiesManager();
            magicAbilitiesManager.setMagicLevel(buffer.readFloat());
        }
    }
}
