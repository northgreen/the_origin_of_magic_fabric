package com.ictye.the_origin_of_magic.utils;

import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.Vec3d;

public class Vec3d_Util {
    public static Vec3d getLookVec(LivingEntity player, double length) {
        float pitch = (float) Math.toRadians(player.getPitch());
        float yaw = (float) Math.toRadians(player.getYaw());

        double x = -Math.sin(yaw) * Math.cos(pitch);
        double y = -Math.sin(pitch);
        double z = Math.cos(yaw) * Math.cos(pitch);

        Vec3d vec = new Vec3d(x, y, z);

        return vec.normalize().multiply(length);
    }
}

