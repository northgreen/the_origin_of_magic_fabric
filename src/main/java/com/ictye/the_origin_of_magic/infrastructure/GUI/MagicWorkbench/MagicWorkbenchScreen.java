package com.ictye.the_origin_of_magic.infrastructure.GUI.MagicWorkbench;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.Element;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.util.Optional;

public class MagicWorkbenchScreen extends HandledScreen<ScreenHandler> {

    private static Text STAFFNAME = Text.empty();
    private static final Identifier TEXTURE = new Identifier("the_origin_of_magic","textures/gui/magic_workbench.png");

    public MagicWorkbenchScreen(ScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
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
        drawTexture(matrices,x,y - 2 ,0,0,backgroundWidth,backgroundHeight);
    }

    @Override
    public Optional<Element> hoveredElement(double mouseX, double mouseY) {
        return super.hoveredElement(mouseX, mouseY);
    }

    @Override
    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        renderBackground(matrices);
        super.render(matrices, mouseX, mouseY, delta);
        drawMouseoverTooltip(matrices, mouseX, mouseY);
        textRenderer.draw(matrices,Text.translatable("text.the_origin_of_magic.magic_work_station.staff_state"),x + 45,y - 2 + 20, 0x3F3F3F);
        textRenderer. draw(matrices,STAFFNAME,x + 45,y - 2 + 29, 0x3F3F3F);
    }

    @Override
    protected void handledScreenTick() {
        super.handledScreenTick();
    }

    @Override
    protected void init() {
        super.init();
        // 将标题居中
        titleX = (backgroundWidth - textRenderer.getWidth(title)) / 2;
    }
}
