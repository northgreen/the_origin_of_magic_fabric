package com.ictye.the_origin_of_magic.foundation.Items.Magic.CorrectionMagic;

import com.ictye.the_origin_of_magic.foundation.Entitys.Magics.CorrectionMagic.NullMagic;
import com.ictye.the_origin_of_magic.foundation.Entitys.Magics.MagicInterfaces.StdMagic.StdThrownMagic;
import com.ictye.the_origin_of_magic.foundation.Entitys.Magics.MagicInterfaces.StdMagicInterface;
import com.ictye.the_origin_of_magic.foundation.Items.Magic.Interface.StdMagicItem;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class NullMagicItem extends StdMagicItem {
    public NullMagicItem(Settings settings, EntityType<StdThrownMagic> entityType) {
        super(settings, entityType);
    }

    @Override
    public StdMagicInterface getMagic(PlayerEntity user, World world, ItemStack stack) {
        return new NullMagic();
    }
}
