package com.ictye.the_origin_of_magic.Contents;

import com.ictye.the_origin_of_magic.foundation.Entitys.Magics.ExplosionThrownMagic;
import com.ictye.the_origin_of_magic.foundation.Entitys.Magics.PoisonThrownMagic;
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

    /**
     * 爆炸魔法實體
     */
    public static final EntityType<StdThrownMagic> EXPOLOSION_MAGIC_ENTITY_TYPE = FabricEntityTypeBuilder
                        .<StdThrownMagic>create(SpawnGroup.MISC, ExplosionThrownMagic::new)
                        .dimensions(EntityDimensions.fixed(0.25F, 0.25F))
                        .trackRangeBlocks(4).trackedUpdateRate(10)
                        .build();

    /**
     * 毒魔法實體
     */
    public static final EntityType<StdThrownMagic> POISON_MAGIC_ENTITY_TYPE = FabricEntityTypeBuilder
                        .<StdThrownMagic>create(SpawnGroup.MISC, PoisonThrownMagic::new)
                        .dimensions(EntityDimensions.fixed(0.25F, 0.25F))
                        .trackRangeBlocks(4).trackedUpdateRate(10)
                        .build();

    /**
     * 注冊魔法實體
     */
    public static void regEntity(){
        // 注冊實體
        Registry.register(Registry.ENTITY_TYPE,
                new Identifier(the_origin_of_magic.Mod_Id,"test_magic"),
                EXPOLOSION_MAGIC_ENTITY_TYPE
                );

        Registry.register(Registry.ENTITY_TYPE,
                new Identifier(the_origin_of_magic.Mod_Id,"poison_magic"),
                POISON_MAGIC_ENTITY_TYPE
        );
    }

    /**
     * 注冊魔法實體渲染器
     */
    @Environment(EnvType.CLIENT)
    public static void regEntityRenderer(){
        // 注冊實體渲染器
        EntityRendererRegistry.register(EXPOLOSION_MAGIC_ENTITY_TYPE, FlyingItemEntityRenderer::new);
        EntityRendererRegistry.register(POISON_MAGIC_ENTITY_TYPE, FlyingItemEntityRenderer::new);
    }
}
