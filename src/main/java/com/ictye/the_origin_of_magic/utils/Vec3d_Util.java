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

    public static Vec3d RodriguesRotation(Vec3d vec, double angle, Vec3d axis){

        double x = vec.x;
        double y = vec.y;
        double z = vec.z;

        double sinAngle = Math.sin(angle);
        double cosAngle = Math.cos(angle);

        double rx = (axis.x * (1 - cosAngle)) + (axis.x * axis.x * (1 - cosAngle)) + (axis.z * sinAngle);
        double ry = (axis.y * (1 - cosAngle)) + (axis.y * axis.y * (1 - cosAngle)) + (axis.z * sinAngle);
        double rz = (axis.z * (1 - cosAngle)) + (axis.x * axis.y * sinAngle) + (axis.z * axis.z * (1 - cosAngle));

        return new Vec3d(x * rx + y * ry + z * rz, x * ry + y * rz + z * rx, x * rz + y * rx + z * ry);
    }
}

