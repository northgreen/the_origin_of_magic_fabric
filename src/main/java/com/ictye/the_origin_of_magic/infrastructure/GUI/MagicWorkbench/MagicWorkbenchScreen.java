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

import java.util.Objects;

public class MagicWorkbenchScreen extends HandledScreen<ScreenHandler> {

    private static Text staffName = Text.empty();
    private  static Text staffSize = Text.empty();
    private static final Identifier TEXTURE = new Identifier("the_origin_of_magic","textures/gui/magic_workbench.png");

    public MagicWorkbenchScreen(ScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
        backgroundHeight = 204;
        backgroundWidth = 176;
        this.playerInventoryTitleY = this.backgroundHeight - 94;
    }

    public static void setStaffName(Text staffName) {
        MagicWorkbenchScreen.staffName = staffName;
    }

    public static void setStaffSize(Text staffSize) {
        MagicWorkbenchScreen.staffSize = staffSize;
    }

    @Override
    protected void drawBackground(MatrixStack matrices, float delta, int mouseX, int mouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F,1.0F,1.0F,1.0F);
        RenderSystem.setShaderTexture(0,TEXTURE);
        int x = (width - backgroundWidth) / 2;
        int y = (height - backgroundHeight) / 2;
        drawTexture(matrices,this.x, this.y,0,0,backgroundWidth,backgroundHeight,200,230);
        for (int k = 0; k < this.handler.slots.size(); ++k){
            if (!this.handler.slots.get(k).isEnabled()){
                Slot slot = this.handler.slots.get(k);
                int slotX = this.x + slot.x - 1;
                int slotY = this.y + slot.y - 1;
                drawTexture(matrices,slotX,slotY,0,204,18,18,200,230);
            }
        }

    }

    @Override
    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        renderBackground(matrices);
        super.render(matrices, mouseX, mouseY, delta);
        drawMouseoverTooltip(matrices, mouseX, mouseY);
        textRenderer. draw(matrices, staffName,x + 51,y + 18, 0x3F3F3F);
        if (!Objects.equals(staffSize, Text.empty())){
            textRenderer. draw(matrices, Text.translatable("text.the_origin_of_magic.staff_size").append(staffSize),x + 51,y + 18 + 9, 0x3F3F3F);
        }
    }

    @Override
    protected void init() {
        super.init();
        // 将标题居中
        titleX = (backgroundWidth - textRenderer.getWidth(title)) / 2;

    }
}
