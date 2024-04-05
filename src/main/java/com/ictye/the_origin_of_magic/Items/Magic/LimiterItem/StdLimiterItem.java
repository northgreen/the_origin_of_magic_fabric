package com.ictye.the_origin_of_magic.Items.Magic.LimiterItem;

import com.ictye.the_origin_of_magic.Entitys.Magics.StdMagic;
import com.ictye.the_origin_of_magic.Items.Magic.StdMagicItem;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;

public class StdLimiterItem extends StdMagicItem {
    public StdLimiterItem(Settings settings, EntityType<StdMagic> entityType) {
        super(settings, entityType);
    }

    @Override
    public StdMagic getMagic(PlayerEntity user, World world, float exolisionRate, int hartRate) {
        return null;
    }
}
