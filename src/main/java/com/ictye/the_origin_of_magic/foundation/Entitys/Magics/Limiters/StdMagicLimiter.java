package com.ictye.the_origin_of_magic.foundation.Entitys.Magics.Limiters;

import com.ictye.the_origin_of_magic.foundation.Entitys.Magics.MagicInterfaces.StdMagicInterface;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import org.jetbrains.annotations.Nullable;

//限制魔法，給魔法添加效果，限制魔法結果
public class StdMagicLimiter implements StdMagicInterface {
    public void onHit( @Nullable EntityHitResult entityHitResult, @Nullable HitResult hitResult, @Nullable BlockHitResult blockHitResult){

    }

    public boolean canEffect(@Nullable EntityHitResult entityHitResult, @Nullable HitResult hitResult, @Nullable BlockHitResult blockHitResult){
        return true;
    }
}
