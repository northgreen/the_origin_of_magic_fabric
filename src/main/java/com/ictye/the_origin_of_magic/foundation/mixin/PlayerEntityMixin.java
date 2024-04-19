package com.ictye.the_origin_of_magic.foundation.mixin;


import com.ictye.the_origin_of_magic.foundation.player.MagicAbilitiesManager;
import com.ictye.the_origin_of_magic.utils.PlayerEntityMixinInterfaces;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.network.ServerPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin implements PlayerEntityMixinInterfaces {

    @Unique
    protected MagicAbilitiesManager magicAbilitiesManager = new MagicAbilitiesManager();


    public MagicAbilitiesManager the_origin_of_magic$getMagicAbilitiesManager() {
        return this.magicAbilitiesManager;
    }

    @Inject(method = "tick",at = @At("HEAD"))
    @SuppressWarnings("ConstantValue")
    public void tick(CallbackInfo ci){
        if((Object)this instanceof ServerPlayerEntity player){
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
