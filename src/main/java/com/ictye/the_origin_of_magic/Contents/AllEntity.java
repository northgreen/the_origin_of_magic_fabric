package com.ictye.the_origin_of_magic.Contents;

import com.ictye.the_origin_of_magic.foundation.Entitys.Magics.*;
import com.ictye.the_origin_of_magic.infrastructure.Renders.MagicsRender.MagicRender;
import com.ictye.the_origin_of_magic.the_origin_of_magic;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.client.render.entity.FlyingItemEntityRenderer;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;

public class AllEntity {

    /**
     * 爆炸魔法實體
     */
    public static final EntityType<StdThrownMagic> EXPOLOSION_MAGIC_ENTITY_TYPE = the_origin_of_magic.MOD_REGISTRATE.entityBuilder(
            FabricEntityTypeBuilder
                    .<StdThrownMagic>create(SpawnGroup.MISC, ExplosionThrownMagic::new)
                    .dimensions(EntityDimensions.fixed(0.25F, 0.25F))
                    .trackRangeBlocks(4).trackedUpdateRate(10)
                    .build(),
            "test_magic"
    );

    /**
     * 毒魔法實體
     */
    public static final EntityType<StdThrownMagic> POISON_MAGIC_ENTITY_TYPE = the_origin_of_magic.MOD_REGISTRATE.entityBuilder(
            FabricEntityTypeBuilder
                    .<StdThrownMagic>create(SpawnGroup.MISC, PoisonThrownMagic::new)
                    .dimensions(EntityDimensions.fixed(0.25F, 0.25F))
                    .trackRangeBlocks(4).trackedUpdateRate(10)
                    .build(),
            "poison_magic"
    );

    public static final EntityType<StdThrownMagic> MAGIC_BULLET_ENTITY_TYPE = the_origin_of_magic.MOD_REGISTRATE.entityBuilder(
            FabricEntityTypeBuilder
                    .<StdThrownMagic>create(SpawnGroup.MISC, MagicBullet::new)
                    .dimensions(EntityDimensions.fixed(0.25F, 0.25F))
                    .trackRangeBlocks(4).trackedUpdateRate(10)
                    .build(),
            "magic_bullet"
    );

    public static final EntityType<StdThrownMagic> POISON_RAY_MAGIC_ENTITY_TYPE = the_origin_of_magic.MOD_REGISTRATE.entityBuilder(
            FabricEntityTypeBuilder
                    .<StdThrownMagic>create(SpawnGroup.MISC, RayMagic::new)
                    .dimensions(EntityDimensions.fixed(0.25F, 0.25F))
                    .trackRangeBlocks(4).trackedUpdateRate(10)
                    .build(),
            "poison_ray_magic"
    );

    public static final EntityType<StdThrownMagic> MAGIC_BULLET_WITH_TRIGGER_ENTITY_TYPE = the_origin_of_magic.MOD_REGISTRATE.entityBuilder(
            FabricEntityTypeBuilder
                    .<StdThrownMagic>create(SpawnGroup.MISC, MagicBulletWithTrigger::new)
                    .dimensions(EntityDimensions.fixed(0.25F, 0.25F))
                    .trackRangeBlocks(4).trackedUpdateRate(10)
                    .build(),
            "magic_bullet_with_trigger"
    );


    public static final EntityType<StdThrownMagic> MAGIC_BULLET_WITH_TIME_TRIGGER_ENTITY_TYPE = the_origin_of_magic.MOD_REGISTRATE.entityBuilder(
            FabricEntityTypeBuilder
                    .<StdThrownMagic>create(SpawnGroup.MISC, MagicBulletWithTimeTrigger::new)
                    .dimensions(EntityDimensions.fixed(0.25F, 0.25F))
                    .trackRangeBlocks(4).trackedUpdateRate(10)
                    .build(),
            "magic_bullet_with_time_trigger"
    );

    /**
     * 注冊魔法實體渲染器
     */
    @Environment(EnvType.CLIENT)
    public static void regEntityRenderer(){
        // 注冊實體渲染器
        EntityRendererRegistry.register(EXPOLOSION_MAGIC_ENTITY_TYPE, FlyingItemEntityRenderer::new);
        EntityRendererRegistry.register(POISON_MAGIC_ENTITY_TYPE, FlyingItemEntityRenderer::new);
        EntityRendererRegistry.register(MAGIC_BULLET_ENTITY_TYPE, MagicRender::new);
        EntityRendererRegistry.register(POISON_RAY_MAGIC_ENTITY_TYPE, MagicRender::new);
        EntityRendererRegistry.register(MAGIC_BULLET_WITH_TRIGGER_ENTITY_TYPE, MagicRender::new);
        EntityRendererRegistry.register(MAGIC_BULLET_WITH_TIME_TRIGGER_ENTITY_TYPE, MagicRender::new);
    }
}
