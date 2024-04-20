package com.ictye.the_origin_of_magic.foundation.Items.Magic;

import com.ictye.the_origin_of_magic.Contents.AllEntity;
import com.ictye.the_origin_of_magic.foundation.Entitys.Magics.ExplosionThrownMagic;
import com.ictye.the_origin_of_magic.foundation.Entitys.Magics.StdThrownMagic;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;

public class ExplosionMagicEntityItem extends StdMagicItem {

    private final EntityType<StdThrownMagic> magicEntityType = AllEntity.EXPOLOSION_MAGIC_ENTITY_TYPE;
    public ExplosionMagicEntityItem(Settings settings, EntityType<StdThrownMagic> entityType) {
        super(settings, entityType);
    }

    @Override
    public StdThrownMagic getMagic(PlayerEntity user, World world, float explosionRate, int hartRate) {
        return new ExplosionThrownMagic(magicEntityType,user,world,explosionRate);
    }
}
