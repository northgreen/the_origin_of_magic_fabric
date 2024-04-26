package com.ictye.the_origin_of_magic.foundation.Entitys.Magics.CorrectionMagic;

import com.ictye.the_origin_of_magic.foundation.Entitys.Magics.MagicInterfaces.StdCastInterface;
import com.ictye.the_origin_of_magic.foundation.Entitys.Magics.MagicInterfaces.StdMagicInterface;

public class MagicLifeTimeDown extends StdAttrMagic implements StdMagicInterface {
    @Override
    public void onUse(StdCastInterface magic) {
        magic.setAge(magic.getAge() - 75);
    }
}
