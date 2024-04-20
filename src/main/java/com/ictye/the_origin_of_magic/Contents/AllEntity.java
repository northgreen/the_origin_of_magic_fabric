package com.ictye.the_origin_of_magic.Contents;

import com.ictye.the_origin_of_magic.foundation.Entitys.Magics.ExplosionThrownMagic;
import com.ictye.the_origin_of_magic.foundation.Entitys.Magics.StdThrownMagic;
import com.ictye.the_origin_of_magic.the_origin_of_magic;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.client.render.entity.FlyingItemEntityRenderer;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class AllEntity {

    public static final EntityType<StdThrownMagic> TEST_MAGIC_ENTITY_TYPE= FabricEntityTypeBuilder.<StdThrownMagic>create(SpawnGroup.MISC, ExplosionThrownMagic::new)
                        .dimensions(EntityDimensions.fixed(0.25F, 0.25F))
                        .trackRangeBlocks(4).trackedUpdateRate(10)
                        .build();

    public static void regEntity(){
        /*
        * 注冊實體
        * */
        Registry.register(Registry.ENTITY_TYPE,
                new Identifier(the_origin_of_magic.Mod_Id,"test_magic"),
                TEST_MAGIC_ENTITY_TYPE
                );
    }

    @Environment(EnvType.CLIENT)
    public static void regEntityRunder(){
        /*
        * 注冊實體渲染器
        * */
        EntityRendererRegistry.register(TEST_MAGIC_ENTITY_TYPE, FlyingItemEntityRenderer::new);

    }
}
