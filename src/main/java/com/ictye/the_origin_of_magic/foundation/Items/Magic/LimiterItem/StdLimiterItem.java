package com.ictye.the_origin_of_magic.foundation.Items.Magic.LimiterItem;

import com.ictye.the_origin_of_magic.foundation.Entitys.Magics.MagicInterfaces.StdMagic.StdThrownMagic;
import com.ictye.the_origin_of_magic.foundation.Items.Magic.Interface.StdMagicItem;
import net.minecraft.entity.EntityType;

/**
 * 限制器魔法，用於對包含該魔法的物品進行限制
 */
public abstract class StdLimiterItem extends StdMagicItem {
    public StdLimiterItem(Settings settings, EntityType<StdThrownMagic> entityType) {
        super(settings, entityType);
    }
}
