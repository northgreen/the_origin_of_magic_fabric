package com.ictye.the_origin_of_magic.foundation.Items.Magic;

import com.ictye.the_origin_of_magic.foundation.Entitys.Magics.StdMagic;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.world.World;

public abstract class StdMagicItem extends Item {

    private final EntityType<StdMagic> magicEntityType;
    public StdMagicItem(Settings settings, EntityType<StdMagic> entityType) {
        super(settings);
        this.magicEntityType = entityType;
    }

    abstract public StdMagic getMagic(PlayerEntity user, World world,float exolisionRate,int hartRate);
}
