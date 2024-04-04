package com.ictye.the_origin_of_magic.Items.Magic;

import com.ictye.the_origin_of_magic.Entitys.Magics.Std_Magic;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.world.World;

public abstract class Std_Magic_Item extends Item {

    private final EntityType<Std_Magic> magicEntityType;
    public Std_Magic_Item(Settings settings, EntityType<Std_Magic> entityType) {
        super(settings);
        this.magicEntityType = entityType;
    }

    abstract public Std_Magic getMagic(PlayerEntity user, World world);
}
