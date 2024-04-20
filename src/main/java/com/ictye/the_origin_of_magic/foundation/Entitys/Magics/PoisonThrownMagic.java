package com.ictye.the_origin_of_magic.foundation.Entitys.Magics;

import com.ictye.the_origin_of_magic.Contents.AllItem;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.projectile.thrown.ThrownEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.world.World;

public class PoisonThrownMagic extends StdThrownMagic{
    public PoisonThrownMagic(EntityType<? extends ThrownEntity> entityType, World world) {
        super(entityType, world);
        magicRate = 3;
    }

    public PoisonThrownMagic(EntityType<? extends ThrownEntity> type, LivingEntity owner, World world) {
        super(type, owner, world);
        magicRate = 3;
    }

    @Override
    public ItemStack getStack() {
        return new ItemStack(AllItem.POISON_MAGIC);
    }

    @Override
    protected void onEntityHit(EntityHitResult entityHitResult) {
        StatusEffectInstance poison = new StatusEffectInstance(StatusEffects.POISON, 200);
        ((LivingEntity)entityHitResult.getEntity()).addStatusEffect(poison);
        super.onEntityHit(entityHitResult);
        this.remove(RemovalReason.CHANGED_DIMENSION);
    }
}
