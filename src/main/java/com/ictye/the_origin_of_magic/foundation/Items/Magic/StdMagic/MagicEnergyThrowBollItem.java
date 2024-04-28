package com.ictye.the_origin_of_magic.foundation.Items.Magic.StdMagic;

import com.ictye.the_origin_of_magic.Contents.AllEntity;
import com.ictye.the_origin_of_magic.foundation.Entitys.Magics.MagicInterfaces.StdMagic.StdThrownMagic;
import com.ictye.the_origin_of_magic.foundation.Entitys.Magics.MagicInterfaces.StdMagicInterface;
import com.ictye.the_origin_of_magic.foundation.Entitys.Magics.StdMagic.MagicEnergyThrowBoll;
import com.ictye.the_origin_of_magic.foundation.Items.Magic.Interface.StdMagicItem;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class MagicEnergyThrowBollItem extends StdMagicItem {
    public MagicEnergyThrowBollItem(Settings settings, EntityType<StdThrownMagic> entityType) {
        super(settings, entityType);
    }

    @Override
    public StdMagicInterface getMagic(PlayerEntity user, World world, ItemStack stack) {
        return new MagicEnergyThrowBoll(AllEntity.MAGIC_ENERGY_THROW_BOLL_ENTITY_TYPE,user, world);
    }
}
