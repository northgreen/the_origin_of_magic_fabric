package com.ictye.the_origin_of_magic.infrastructure.GUI;

import com.ictye.the_origin_of_magic.foundation.PlayerAbilities.MagicAbilitiesManager;
import com.ictye.the_origin_of_magic.utils.InterFaces.PlayerEntityMixinInterfaces;
import com.mojang.blaze3d.systems.RenderSystem;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Identifier;

@Environment(value = EnvType.CLIENT)
public class MagicLevelHud {

    private static final Identifier magicTexture = new Identifier("the_origin_of_magic", "textures/gui/magic_power_image.png");
    public static void renderThirstHud(MatrixStack matrixStack, MinecraftClient client, PlayerEntity playerEntity) {
        if (playerEntity != null && !playerEntity.isCreative() && !playerEntity.isSpectator()) {
            int width = client.getWindow().getScaledWidth() / 2;
            int height = client.getWindow().getScaledHeight();
            int bounceFactor = 0;

            // Defining the texture
            RenderSystem.setShader(GameRenderer::getPositionTexShader);
            RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f);
            RenderSystem.setShaderTexture(0, magicTexture);

            MagicAbilitiesManager magicAbilitiesManager = ((PlayerEntityMixinInterfaces)playerEntity).the_origin_of_magic$getMagicAbilitiesManager();

            float magicLevel = magicAbilitiesManager.getMagicLevel();

            // 繪製空圖標
            for(int i = 0; i<10; i++){
                DrawableHelper.drawTexture(matrixStack
                        , width + 82 - (i * 9) + i, (height - 49 + bounceFactor) - 1
                        , 9, 0
                        , 9, 9
                        , 27, 9);
            }

            // 繪製半個魔力

            for (int i = 0; i <= 20; i++) {
                if(magicLevel!=0){
                    if (((((int)magicLevel) + 1) / 2) > i){
                        DrawableHelper.drawTexture(
                                matrixStack
                                ,width + 82 - (i * 9) + i , (height - 49) - 1,
                                18, 0,
                                9, 9,
                                27, 9);
                    }
                }
            }

            for (int i = 0; i < 20; i++) {
                if(magicLevel!=0){
                    if ((((int)magicLevel)  / 2) > i){
                        DrawableHelper.drawTexture(
                                matrixStack,
                                width + 82 - (i * 9) + i , (height - 49) - 1,
                                0, 0,
                                9, 9,
                                27, 9);
                    }
                }
            }

            RenderSystem.setShaderTexture(0, DrawableHelper.GUI_ICONS_TEXTURE);
        }
    }
}
