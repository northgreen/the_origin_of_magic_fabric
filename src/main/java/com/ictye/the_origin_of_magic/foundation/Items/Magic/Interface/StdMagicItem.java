package com.ictye.the_origin_of_magic.foundation.Items.Magic.Interface;

import com.ictye.the_origin_of_magic.foundation.Entitys.Magics.MagicInterfaces.StdMagic.StdThrownMagic;
import com.ictye.the_origin_of_magic.foundation.Entitys.Magics.MagicInterfaces.StdMagicInterface;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

/**
 * 魔法物品，用於創建魔法物品
 */
public abstract class StdMagicItem extends Item implements StdMagicInterface{

    @SuppressWarnings("FieldCanBeLocal")
    private final EntityType<StdThrownMagic> magicEntityType;

    public StdMagicItem(Settings settings, EntityType<StdThrownMagic> entityType) {
        super(settings);
        this.magicEntityType = entityType;
    }

    /**
     * 獲取魔法
     * @param user 使用者
     * @param world 世界
     * @return 魔法（包含限制器或者效果器）
     */
    abstract public StdMagicInterface getMagic(PlayerEntity user, World world, ItemStack stack);
}
