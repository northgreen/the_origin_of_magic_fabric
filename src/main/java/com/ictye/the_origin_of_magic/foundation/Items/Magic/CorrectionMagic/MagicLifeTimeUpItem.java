package com.ictye.the_origin_of_magic.foundation.Items.Magic.CorrectionMagic;

import com.ictye.the_origin_of_magic.foundation.Entitys.Magics.CorrectionMagic.MagicLifeTimeUp;
import com.ictye.the_origin_of_magic.foundation.Entitys.Magics.StdMagicInterface;
import com.ictye.the_origin_of_magic.foundation.Entitys.Magics.StdThrownMagic;
import com.ictye.the_origin_of_magic.foundation.Items.Magic.StdMagicItem;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class MagicLifeTimeUpItem extends StdMagicItem {
    public MagicLifeTimeUpItem(Settings settings, EntityType<StdThrownMagic> entityType) {
        super(settings, entityType);
    }

    @Override
    public StdMagicInterface getMagic(PlayerEntity user, World world, ItemStack itemStack) {
        return new MagicLifeTimeUp();
    }
}
