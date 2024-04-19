package com.ictye.the_origin_of_magic.infrastructure.netWork;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;

public class NetWorkReg {
    public static void registerC2SPackets() {
    }

    public static void registerS2CPackets() {
        ClientPlayNetworking.registerGlobalReceiver(NetworkIDFinder.SYNC_HUD_ID, SyncHUDS2CPacket::receive);
    }
}
