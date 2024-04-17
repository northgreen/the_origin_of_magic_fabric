package com.ictye.the_origin_of_magic.foundation.Items.Magic;

import com.ictye.the_origin_of_magic.foundation.Entitys.Magics.StdMagicInterface;
import com.ictye.the_origin_of_magic.foundation.Entitys.Magics.StdThrownMagic;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.world.World;

public abstract class StdMagicItem extends Item {

    private final EntityType<StdThrownMagic> magicEntityType;
    public StdMagicItem(Settings settings, EntityType<StdThrownMagic> entityType) {
        super(settings);
        this.magicEntityType = entityType;
    }

    abstract public StdMagicInterface getMagic(PlayerEntity user, World world, float exolisionRate, int hartRate);
}
