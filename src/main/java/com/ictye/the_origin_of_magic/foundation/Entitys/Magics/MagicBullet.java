package com.ictye.the_origin_of_magic.foundation.Entitys.Magics;

import com.ictye.the_origin_of_magic.Contents.AllParticle;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.projectile.thrown.ThrownEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.world.World;

public class MagicBullet extends StdThrownMagic{
    public MagicBullet(EntityType<? extends ThrownEntity> entityType, World world) {
        super(entityType, world);
    }

    protected MagicBullet(EntityType<? extends ThrownEntity> type, double x, double y, double z, World world) {
        super(type, x, y, z, world);
    }

    public MagicBullet(EntityType<? extends ThrownEntity> type, LivingEntity owner, World world) {
        super(type, owner, world);
    }

    @Override
    public float getMagicRate() {
        return 2;
    }

    @Override
    public int getLit() {
        return super.getLit();
    }

    @Override
    protected float getGravity() {
        return 0;
    }

    @Override
    public void tick() {
        super.tick();
        world.addParticle(AllParticle.MAGIC_BULLET_PARTICLE, getX(), getY(), getZ(), 0, 0, 0);
    }

    @Override
    protected void onEntityHit(EntityHitResult entityHitResult) {
        entityHitResult.getEntity().damage(DamageSource.thrownProjectile(this, this.getOwner()), 4);
        super.onEntityHit(entityHitResult);
    }

    @Override
    public ItemStack getStack() {
        return new ItemStack(Items.AIR);
    }
}
