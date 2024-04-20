package com.ictye.the_origin_of_magic.foundation.Items.Magic.LimiterItem;

import com.ictye.the_origin_of_magic.foundation.Entitys.Magics.Limiters.HostileEntityLimiter;
import com.ictye.the_origin_of_magic.foundation.Entitys.Magics.StdMagicInterface;
import com.ictye.the_origin_of_magic.foundation.Entitys.Magics.StdThrownMagic;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;

public class UndeadEntityLimiterItem extends StdLimiterItem{
    public UndeadEntityLimiterItem(Settings settings, EntityType<StdThrownMagic> entityType) {
        super(settings, entityType);
    }


    @Override
    public StdMagicInterface getMagic(PlayerEntity user, World world, float excisionRate, int hartRate) {
        return new HostileEntityLimiter();
    }
}
