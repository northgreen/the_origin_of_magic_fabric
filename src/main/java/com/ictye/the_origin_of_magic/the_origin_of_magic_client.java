package com.ictye.the_origin_of_magic;

import com.ictye.the_origin_of_magic.Contents.AllEntity;
import com.ictye.the_origin_of_magic.Contents.AllItem;
import com.ictye.the_origin_of_magic.Contents.AllParticle;
import com.ictye.the_origin_of_magic.infrastructure.GUI.MagicWorkbench.MagicWorkbenchScreen;
import com.ictye.the_origin_of_magic.infrastructure.GUI.MagicWorkbench.MagicWorkbenchScreenHandler;
import com.ictye.the_origin_of_magic.infrastructure.NetWork.NetWorkReg;
import com.ictye.the_origin_of_magic.infrastructure.Renders.Colors.MagicRay;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.fabricmc.fabric.api.screenhandler.v1.ScreenHandlerRegistry;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;

public class the_origin_of_magic_client implements ClientModInitializer {

    public static final ScreenHandlerType<MagicWorkbenchScreenHandler> MAGIC_WORKSTATION_SCREEN_HANDLER_SCREEN_HANDLER_TYPE =
            ScreenHandlerRegistry.registerExtended(
                    new Identifier("the_origin_of_magic","magic_workstation_gui"),
                    ((syncId, inventory, buf) -> new MagicWorkbenchScreenHandler(null,syncId, inventory)));
    @Override
    @Environment(EnvType.CLIENT)
    public void onInitializeClient() {
        the_origin_of_magic.LOGGER.info("Client is loading");

        HandledScreens.register(MAGIC_WORKSTATION_SCREEN_HANDLER_SCREEN_HANDLER_TYPE, MagicWorkbenchScreen::new);

        ColorProviderRegistry.ITEM.register(MagicRay::getColor, AllItem.RAY_MAGIC_ITEM);

        AllEntity.regEntityRenderer();
        NetWorkReg.registerS2CPackets();
        AllParticle.register();
    }
}
