package com.ictye.the_origin_of_magic.foundation.Items.Magic.LimiterItem;

import com.ictye.the_origin_of_magic.foundation.Entitys.Magics.Limiters.HostileEntityLimiter;
import com.ictye.the_origin_of_magic.foundation.Entitys.Magics.StdMagicInterface;
import com.ictye.the_origin_of_magic.foundation.Entitys.Magics.StdThrownMagic;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class UndeadEntityLimiterItem extends StdLimiterItem{
    public UndeadEntityLimiterItem(Settings settings, EntityType<StdThrownMagic> entityType) {
        super(settings, entityType);
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        super.appendTooltip(stack, world, tooltip, context);
        tooltip.add(Text.translatable("text.the_origin_of_magic.undead_entity_limiter_item_tooltip1").formatted(Formatting.GRAY));
        tooltip.add(Text.translatable("text.the_origin_of_magic.undead_entity_limiter_item_tooltip2").formatted(Formatting.GRAY));
    }

    @Override
    public StdMagicInterface getMagic(PlayerEntity user, World world, float excisionRate, int hartRate,ItemStack itemStack) {
        return new HostileEntityLimiter();
    }
}
