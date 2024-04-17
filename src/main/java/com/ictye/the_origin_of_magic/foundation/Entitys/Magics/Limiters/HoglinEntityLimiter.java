package com.ictye.the_origin_of_magic.foundation.Entitys.Magics.Limiters;

import net.minecraft.entity.mob.HoglinEntity;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import org.jetbrains.annotations.Nullable;

public class HoglinEntityLimiter extends StdMagicLimiter {
    @Override
    public boolean canEffect(@Nullable EntityHitResult entityHitResult, @Nullable HitResult hitResult, @Nullable BlockHitResult blockHitResult) {
        // 判斷是否為亡靈生物
        return super.canEffect(entityHitResult, hitResult, blockHitResult) && entityHitResult.getEntity() != null && entityHitResult.getEntity() instanceof HoglinEntity;
    }
}
