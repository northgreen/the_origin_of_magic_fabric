package com.ictye.the_origin_of_magic.foundation.Items.Magic;

import com.ictye.the_origin_of_magic.Contents.AllEntity;
import com.ictye.the_origin_of_magic.foundation.Entitys.Magics.InstantDamageItemMagic;
import com.ictye.the_origin_of_magic.foundation.Entitys.Magics.StdMagicInterface;
import com.ictye.the_origin_of_magic.foundation.Entitys.Magics.StdThrownMagic;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class InstantDamageMagicItem extends StdMagicItem {
    public InstantDamageMagicItem(Settings settings, EntityType<StdThrownMagic> entityType) {
        super(settings, entityType);
    }

    @Override
    public StdMagicInterface getMagic(PlayerEntity user, World world, float excisionRate, int hartRate, ItemStack itemStack) {
        return new InstantDamageItemMagic(AllEntity.INSTANT_DAMAGE_ENTITY_TYPE,user, world);
    }
}
