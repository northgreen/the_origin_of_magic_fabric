package com.ictye.the_origin_of_magic.foundation.Entitys.Magics.CorrectionMagic;

import com.ictye.the_origin_of_magic.foundation.Entitys.Magics.MagicInterfaces.StdCastInterface;
import com.ictye.the_origin_of_magic.foundation.Entitys.Magics.MagicInterfaces.StdMagicInterface;

public abstract class StdAttrMagic implements StdMagicInterface {
    public abstract void onUse(StdCastInterface magic);
}
