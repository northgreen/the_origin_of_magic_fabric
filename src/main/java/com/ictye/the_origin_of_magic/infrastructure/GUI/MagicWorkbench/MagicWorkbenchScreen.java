package com.ictye.the_origin_of_magic.infrastructure.GUI.MagicWorkbench;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.Slot;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class MagicWorkbenchScreen extends HandledScreen<ScreenHandler> {

    private static Text STAFFNAME = Text.empty();
    private static final Identifier TEXTURE = new Identifier("the_origin_of_magic","textures/gui/magic_workbench.png");

    public MagicWorkbenchScreen(ScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
        backgroundHeight = 204;
        backgroundWidth = 176;
        this.playerInventoryTitleY = this.backgroundHeight - 94;
    }

    public static void setSTAFFNAME(Text STAFFNAME) {
        MagicWorkbenchScreen.STAFFNAME = STAFFNAME;
    }



    @Override
    protected void drawBackground(MatrixStack matrices, float delta, int mouseX, int mouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F,1.0F,1.0F,1.0F);
        RenderSystem.setShaderTexture(0,TEXTURE);
        int x = (width - backgroundWidth) / 2;
        int y = (height - backgroundHeight) / 2;
        drawTexture(matrices,this.x, this.y,354,0,backgroundWidth,backgroundHeight,550,550);
        for (int k = 0; k < this.handler.slots.size(); ++k){
            if (!this.handler.slots.get(k).isEnabled()){
                Slot slot = this.handler.slots.get(k);
                int slotX = this.x + slot.x - 1;
                int slotY = this.y + slot.y - 1;
                drawTexture(matrices,slotX,slotY,0,170,18,18,550,550);
            }
        }

    }

    @Override
    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        renderBackground(matrices);
        super.render(matrices, mouseX, mouseY, delta);
        drawMouseoverTooltip(matrices, mouseX, mouseY);
        textRenderer.draw(matrices,Text.translatable("text.the_origin_of_magic.magic_work_station.staff_state"),x + 51,y + 18, 0x3F3F3F);
        textRenderer. draw(matrices,STAFFNAME,x + 51 + 2,y + 18 + 9, 0x3F3F3F);
    }

    @Override
    protected void init() {
        super.init();
        // 将标题居中
        titleX = (backgroundWidth - textRenderer.getWidth(title)) / 2;

    }
}
