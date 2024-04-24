package com.ictye.the_origin_of_magic.foundation.Items.Magic;

import com.ictye.the_origin_of_magic.foundation.Entitys.Magics.PoisonThrownMagic;
import com.ictye.the_origin_of_magic.foundation.Entitys.Magics.StdMagicInterface;
import com.ictye.the_origin_of_magic.foundation.Entitys.Magics.StdThrownMagic;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import static com.ictye.the_origin_of_magic.Contents.AllEntity.POISON_MAGIC_ENTITY_TYPE;

/**
 * 中毒法術
 */
public class PoisonMagicEntityItem extends StdMagicItem{

    public PoisonMagicEntityItem(Settings settings, EntityType<StdThrownMagic> entityType) {
        super(settings, entityType);
    }

    @Override
    public StdMagicInterface getMagic(PlayerEntity user, World world, ItemStack itemStack) {
        return  new PoisonThrownMagic(POISON_MAGIC_ENTITY_TYPE,user,world);
    }
}
