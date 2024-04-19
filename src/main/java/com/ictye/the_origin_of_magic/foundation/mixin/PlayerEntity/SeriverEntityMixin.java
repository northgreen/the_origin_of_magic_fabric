package com.ictye.the_origin_of_magic.foundation.mixin.PlayerEntity;

import com.ictye.the_origin_of_magic.foundation.PlayerAbilities.MagicAbilitiesManager;
import com.ictye.the_origin_of_magic.infrastructure.netWork.NetworkIDFinder;
import com.ictye.the_origin_of_magic.utils.PlayerEntityMixinInterfaces;
import com.mojang.authlib.GameProfile;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.encryption.PlayerPublicKey;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerPlayerEntity.class)
public abstract class SeriverEntityMixin extends PlayerEntity {
    public SeriverEntityMixin(World world, BlockPos pos, float yaw, GameProfile gameProfile, @Nullable PlayerPublicKey publicKey) {
        super(world, pos, yaw, gameProfile, publicKey);
    }

    private final MagicAbilitiesManager magicAbilitiesManager = ((PlayerEntityMixinInterfaces)this).the_origin_of_magic$getMagicAbilitiesManager();

    @Inject(method = "playerTick", at = @At(value = "HEAD"))
    public void playerTick(CallbackInfo ci) {
        // ServerPlayerEntity
        ServerPlayerEntity serverPlayerEntity = (ServerPlayerEntity) (Object) this;
        PacketByteBuf buffer = PacketByteBufs.create();
        buffer.writeFloat(this.magicAbilitiesManager.getMagicLevel());
        ServerPlayNetworking.send(serverPlayerEntity, NetworkIDFinder.SYNC_MAGIC_HUD_ID, buffer);

    }

}
