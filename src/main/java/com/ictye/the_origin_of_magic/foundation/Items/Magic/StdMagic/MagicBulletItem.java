package com.ictye.the_origin_of_magic.foundation.Items.Magic.StdMagic;

import com.ictye.the_origin_of_magic.Contents.AllEntity;
import com.ictye.the_origin_of_magic.foundation.Entitys.Magics.MagicInterfaces.StdMagic.StdThrownMagic;
import com.ictye.the_origin_of_magic.foundation.Entitys.Magics.MagicInterfaces.StdMagicInterface;
import com.ictye.the_origin_of_magic.foundation.Entitys.Magics.StdMagic.MagicBullet;
import com.ictye.the_origin_of_magic.foundation.Items.Magic.Interface.StdMagicItem;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class MagicBulletItem extends StdMagicItem {
    public MagicBulletItem(Settings settings, EntityType<StdThrownMagic> entityType) {
        super(settings, entityType);
    }

    @Override
    public StdMagicInterface getMagic(PlayerEntity user, World world, ItemStack itemStack) {
        return new MagicBullet(AllEntity.MAGIC_BULLET_ENTITY_TYPE, user, world);
    }
}
