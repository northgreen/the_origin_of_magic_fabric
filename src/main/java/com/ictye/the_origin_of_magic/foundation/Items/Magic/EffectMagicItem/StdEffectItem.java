package com.ictye.the_origin_of_magic.foundation.Items.Magic.EffectMagicItem;

import com.ictye.the_origin_of_magic.foundation.Entitys.Magics.StdThrownMagic;
import com.ictye.the_origin_of_magic.foundation.Items.Magic.StdMagicItem;
import net.minecraft.entity.EntityType;

/**
 * 效果魔法對應的標準魔法物品
 */
public abstract class StdEffectItem extends StdMagicItem {
    public StdEffectItem(Settings settings, EntityType<StdThrownMagic> entityType) {
        super(settings, entityType);
    }
}
