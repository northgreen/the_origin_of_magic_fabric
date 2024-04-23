package com.ictye.the_origin_of_magic.Contents;

import com.ictye.the_origin_of_magic.the_origin_of_magic;
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.particle.DefaultParticleType;

public class AllParticle {
    public static final DefaultParticleType MAGIC_BULLET_PARTICLE = (DefaultParticleType) the_origin_of_magic.MOD_REGISTRATE.particleBuilder(
            "magic_bullet_particle",
            FabricParticleTypes.simple());
}
