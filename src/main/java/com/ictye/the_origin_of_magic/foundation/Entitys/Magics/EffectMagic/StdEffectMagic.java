package com.ictye.the_origin_of_magic.foundation.Entitys.Magics.EffectMagic;

import com.ictye.the_origin_of_magic.foundation.Entitys.Magics.StdMagicInterface;
import net.minecraft.world.World;

public abstract class StdEffectMagic implements StdMagicInterface {
    /**
     * 生產魔法特效
     * @param world 世界
     */
    public abstract void tick(World world);
}
