package com.ictye.the_origin_of_magic.infrastructure.netWork;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;

public class NetWorkReg {
    /**
     * Register all packets to be sent from the client to the server.
     */
    public static void registerC2SPackets() {
    }

    /**
     * Register all packets to be sent from the server to the client.
     */
    public static void registerS2CPackets() {
        ClientPlayNetworking.registerGlobalReceiver(NetworkIDFinder.SYNC_MAGIC_HUD_ID, SyncHUDS2CPacket::receive);
    }
}
