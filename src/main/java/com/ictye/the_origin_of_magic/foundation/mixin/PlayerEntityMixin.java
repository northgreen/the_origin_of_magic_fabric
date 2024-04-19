package com.ictye.the_origin_of_magic.foundation.mixin;


import com.ictye.the_origin_of_magic.foundation.player.MagicAbilitiesManager;
import com.ictye.the_origin_of_magic.infrastructure.netWork.NetworkIDFinder;
import com.ictye.the_origin_of_magic.utils.PlayerEntityMixinInterfaces;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin extends LivingEntity implements PlayerEntityMixinInterfaces {

    protected PlayerEntityMixin(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }

    private final MagicAbilitiesManager magicAbilitiesManager = new MagicAbilitiesManager();

    @Override
    public MagicAbilitiesManager the_origin_of_magic$getMagicAbilitiesManager() {
        return this.magicAbilitiesManager;
    }

    @Inject(method = "tick()V", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/player/HungerManager;update(Lnet/minecraft/entity/player/PlayerEntity;)V", shift = At.Shift.AFTER))
    @SuppressWarnings("ConstantValue")
    public void tick(CallbackInfo ci){
        if((Object)this instanceof ServerPlayerEntity player){
            PacketByteBuf buffer = PacketByteBufs.create();
            buffer.writeFloat(this.magicAbilitiesManager.getMagicLevel());
            ServerPlayNetworking.send(player, NetworkIDFinder.SYNC_HUD_ID, buffer);

            this.magicAbilitiesManager.update(player);
        }
    }

    @Inject(method = "writeCustomDataToNbt",at = @At("HEAD"))
    public void writeCustomDataToNbt(NbtCompound nbt, CallbackInfo ci){
        magicAbilitiesManager.writeNbt(nbt);
    }

    @Inject(method = "readCustomDataFromNbt",at = @At("HEAD"))
    public void readCustomDataFromNbt(NbtCompound nbt, CallbackInfo ci){
        magicAbilitiesManager.readNbt(nbt);
    }
}
