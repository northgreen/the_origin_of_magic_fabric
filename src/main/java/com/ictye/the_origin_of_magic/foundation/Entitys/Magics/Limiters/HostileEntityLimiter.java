package com.ictye.the_origin_of_magic.foundation.Entitys.Magics.Limiters;

import net.minecraft.entity.mob.HoglinEntity;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import org.jetbrains.annotations.Nullable;

public class HostileEntityLimiter extends StdMagicLimiter {
    @Override
    public boolean canEffect(@Nullable EntityHitResult entityHitResult, @Nullable HitResult hitResult, @Nullable BlockHitResult blockHitResult) {
        // 判斷是否為亡靈生物
        if (entityHitResult != null) {
            return entityHitResult.getEntity() instanceof HoglinEntity;
        }
        return false;
    }
}
