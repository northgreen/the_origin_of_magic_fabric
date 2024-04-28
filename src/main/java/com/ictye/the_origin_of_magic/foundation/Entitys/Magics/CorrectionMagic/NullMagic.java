package com.ictye.the_origin_of_magic.foundation.Entitys.Magics.CorrectionMagic;

import com.ictye.the_origin_of_magic.foundation.Entitys.Magics.MagicInterfaces.StdCastInterface;

public class NullMagic extends StdAttrMagic{
    @Override
    public void onUse(StdCastInterface magic) {
        magic.setAge(0);
    }
}
