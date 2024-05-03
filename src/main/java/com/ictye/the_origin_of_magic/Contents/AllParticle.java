package com.ictye.the_origin_of_magic.Contents;

import com.ictye.the_origin_of_magic.the_origin_of_magic;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.client.particle.FlameParticle;
import net.minecraft.particle.DefaultParticleType;

public class AllParticle {
    public static final DefaultParticleType MAGIC_BULLET_PARTICLE = (DefaultParticleType) the_origin_of_magic.MOD_REGISTRATE.particleBuilder(
            "magic_bullet_particle",
            FabricParticleTypes.simple());

    public static final DefaultParticleType MAGIC_BUBBLE_PARTICLE = (DefaultParticleType) the_origin_of_magic.MOD_REGISTRATE.particleBuilder(
            "magic_bubble_particle",
            FabricParticleTypes.simple());

    @Environment(value= EnvType.CLIENT)
    public static void register() {
        ParticleFactoryRegistry.getInstance().register(AllParticle.MAGIC_BUBBLE_PARTICLE, FlameParticle.Factory::new);
        ParticleFactoryRegistry.getInstance().register(AllParticle.MAGIC_BULLET_PARTICLE, FlameParticle.Factory::new);
    }
}
