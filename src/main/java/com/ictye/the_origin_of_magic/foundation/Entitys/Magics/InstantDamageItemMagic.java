package com.ictye.the_origin_of_magic.foundation.Entitys.Magics;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.projectile.thrown.ThrownEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.world.World;

public class InstantDamageItemMagic extends StdThrownMagic{
    public InstantDamageItemMagic(EntityType<? extends ThrownEntity> entityType, World world) {
        super(entityType, world);
    }

    public InstantDamageItemMagic(EntityType<? extends StdThrownMagic> type, LivingEntity owner, World world) {
        super(type, owner, world);
    }

    @Override
    protected float getGravity() {
        return 0;
    }

    @Override
    protected void onEntityHit(EntityHitResult entityHitResult) {
        StatusEffectInstance poison = new StatusEffectInstance(StatusEffects.INSTANT_DAMAGE, 10);
        ((LivingEntity)entityHitResult.getEntity()).addStatusEffect(poison);
        super.onEntityHit(entityHitResult);
        this.remove(RemovalReason.CHANGED_DIMENSION);
    }

    @Override
    public void tick() {
        this.world.addParticle(ParticleTypes.DAMAGE_INDICATOR, this.getX(), this.getY(), this.getZ(), 0.0D, 0.0D, 0.0D);
        super.tick();
    }

    @Override
    public ItemStack getStack() {
        return new ItemStack(Items.AIR);
    }
}
