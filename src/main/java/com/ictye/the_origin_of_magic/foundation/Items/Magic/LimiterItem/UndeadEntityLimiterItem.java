package com.ictye.the_origin_of_magic.foundation.Items.Magic.LimiterItem;

import com.ictye.the_origin_of_magic.foundation.Entitys.Magics.Limiters.HoglinEntityLimiter;
import com.ictye.the_origin_of_magic.foundation.Entitys.Magics.Limiters.StdMagicLimiter;
import com.ictye.the_origin_of_magic.foundation.Entitys.Magics.StdThrownMagic;
import net.minecraft.entity.EntityType;

public class UndeadEntityLimiterItem extends StdLimiterItem{
    public UndeadEntityLimiterItem(Settings settings, EntityType<StdThrownMagic> entityType) {
        super(settings, entityType);
    }

    @Override
    public StdMagicLimiter getMagic() {
        return new HoglinEntityLimiter();
    }
}
