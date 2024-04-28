package com.ictye.the_origin_of_magic.foundation.Items.Magic.StdMagic;

import com.ictye.the_origin_of_magic.Contents.AllEntity;
import com.ictye.the_origin_of_magic.foundation.Entitys.Magics.MagicInterfaces.StdMagic.StdThrownMagic;
import com.ictye.the_origin_of_magic.foundation.Entitys.Magics.StdMagic.ExplosionThrownMagic;
import com.ictye.the_origin_of_magic.foundation.Items.Magic.Interface.StdMagicItem;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ExplosionMagicEntityItem extends StdMagicItem {

    private final EntityType<StdThrownMagic> magicEntityType = AllEntity.EXPOLOSION_MAGIC_ENTITY_TYPE;
    public ExplosionMagicEntityItem(Settings settings, EntityType<StdThrownMagic> entityType) {
        super(settings, entityType);
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        super.appendTooltip(stack, world, tooltip, context);
    }

    @Override
    public StdThrownMagic getMagic(PlayerEntity user, World world,ItemStack itemStack) {
        return new ExplosionThrownMagic(magicEntityType,user,world);
    }
}
