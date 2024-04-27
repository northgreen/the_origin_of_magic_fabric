package com.ictye.the_origin_of_magic.foundation.Items.Magic;

import com.ictye.the_origin_of_magic.Contents.AllEntity;
import com.ictye.the_origin_of_magic.foundation.Entitys.Magics.MagicBulletWithTimeTrigger;
import com.ictye.the_origin_of_magic.foundation.Entitys.Magics.MagicInterfaces.StdMagicInterface;
import com.ictye.the_origin_of_magic.foundation.Entitys.Magics.StdThrownMagic;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class MagicBulletWithTimeTriggerItem extends StdMagicItem{
    public MagicBulletWithTimeTriggerItem(Settings settings, EntityType<StdThrownMagic> entityType) {
        super(settings, entityType);
    }

    @Override
    public StdMagicInterface getMagic(PlayerEntity user, World world, ItemStack stack) {
        return new MagicBulletWithTimeTrigger(AllEntity.MAGIC_BULLET_WITH_TIME_TRIGGER_ENTITY_TYPE,user, world);
    }
}
