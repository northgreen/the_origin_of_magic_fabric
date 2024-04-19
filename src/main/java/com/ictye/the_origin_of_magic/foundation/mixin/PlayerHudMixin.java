package com.ictye.the_origin_of_magic.foundation.mixin;

import com.ictye.the_origin_of_magic.infrastructure.GUI.MagicLevelHud;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(InGameHud.class)
public abstract class PlayerHudMixin extends DrawableHelper{



    @Shadow @Final private MinecraftClient client;

    @Shadow protected abstract PlayerEntity getCameraPlayer();

    @Shadow private int ticks;

    @Inject(method = "renderStatusBars", at = @At(value = "HEAD"))
    private void renderStatusBars(MatrixStack matrices, CallbackInfo ci){
        MagicLevelHud.renderThirstHud(matrices, client, this.getCameraPlayer(), ticks);
    }
}
