package com.ictye.the_origin_of_magic.foundation.mixin.HUD;

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
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.Slice;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(InGameHud.class)
public abstract class PlayerHudMixin extends DrawableHelper{



    @Shadow @Final private MinecraftClient client;

    @Shadow protected abstract PlayerEntity getCameraPlayer();

    @Shadow private int ticks;

    // 渲染魔法等級條
    @Inject(method = "renderStatusBars", at = @At(value = "HEAD"))
    private void renderStatusBars(MatrixStack matrices, CallbackInfo ci){
        MagicLevelHud.renderThirstHud(matrices, client, this.getCameraPlayer());
    }

    // 提高氣泡的位置
    @ModifyArg(method = "renderStatusBars",
            at = @At(value = "INVOKE",target = "Lnet/minecraft/client/gui/hud/InGameHud;drawTexture(Lnet/minecraft/client/util/math/MatrixStack;IIIIII)V"),
            index = 2,
            slice = @Slice(
                    from = @At(value = "INVOKE",target = "Lnet/minecraft/entity/player/PlayerEntity;getMaxAir()I"),
                    to = @At(value = "INVOKE",target = "Lnet/minecraft/util/profiler/Profiler;pop()V")
            ))
    private int modifyAirBarPosition(int par2) {
        return par2 - 12;
    }
}
