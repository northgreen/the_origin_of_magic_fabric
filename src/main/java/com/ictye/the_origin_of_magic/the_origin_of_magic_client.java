package com.ictye.the_origin_of_magic;

import com.ictye.the_origin_of_magic.Contents.AllEntity;
import com.ictye.the_origin_of_magic.Contents.AllParticle;
import com.ictye.the_origin_of_magic.infrastructure.GUI.MagicWorkbench.MagicWorkbenchScreen;
import com.ictye.the_origin_of_magic.infrastructure.GUI.MagicWorkbench.MagicWorkbenchScreenHandler;
import com.ictye.the_origin_of_magic.infrastructure.netWork.NetWorkReg;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.fabricmc.fabric.api.event.client.ClientSpriteRegistryCallback;
import net.fabricmc.fabric.api.screenhandler.v1.ScreenHandlerRegistry;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.minecraft.client.particle.FlameParticle;
import net.minecraft.screen.PlayerScreenHandler;
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
        HandledScreens.register(MAGIC_WORKSTATION_SCREEN_HANDLER_SCREEN_HANDLER_TYPE, MagicWorkbenchScreen::new);
        AllEntity.regEntityRenderer();
        NetWorkReg.registerS2CPackets();

        ClientSpriteRegistryCallback.event(PlayerScreenHandler.BLOCK_ATLAS_TEXTURE).register(((atlasTexture, registry) -> {
            registry.register(new Identifier(the_origin_of_magic.Mod_Id,"particles/magic_bullet_particle"));
        }));
        ParticleFactoryRegistry.getInstance().register(AllParticle.MAGIC_BULLET_PARTICLE, FlameParticle.Factory::new);
    }
}
