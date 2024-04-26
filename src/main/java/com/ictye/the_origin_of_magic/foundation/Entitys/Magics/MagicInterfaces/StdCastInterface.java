package com.ictye.the_origin_of_magic.foundation.Entitys.Magics.MagicInterfaces;


import com.ictye.the_origin_of_magic.foundation.Entitys.Magics.EffectMagic.StdEffectMagic;
import com.ictye.the_origin_of_magic.foundation.Entitys.Magics.Limiters.StdMagicLimiter;

import java.util.List;

public interface StdCastInterface extends StdMagicInterface {
    public void addLimiter(StdMagicLimiter limiter);

    public void addEffect(StdEffectMagic effectMagic);

    public void setAge(int age);

    public int getAge();


    public int getAdditionalTrigger();

    public void setAdditionTrigger(List<StdCastInterface> stdCastInterfaces);
}
