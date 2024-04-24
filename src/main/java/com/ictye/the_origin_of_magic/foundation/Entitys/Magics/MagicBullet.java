package com.ictye.the_origin_of_magic.foundation.Entitys.Magics;

import com.ictye.the_origin_of_magic.Contents.AllParticle;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.world.World;

public class MagicBullet extends StdThrownMagic{
    public MagicBullet(EntityType<? extends StdThrownMagic> entityType, World world) {
        super(entityType, world);
    }

    protected MagicBullet(EntityType<? extends StdThrownMagic> type, double x, double y, double z, World world) {
        super(type, x, y, z, world);
    }

    public MagicBullet(EntityType<? extends StdThrownMagic> type, LivingEntity owner, World world) {
        super(type, owner, world);
    }

    @Override
    public float getMagicRate() {
        return 2;
    }

    @Override
    public boolean hasNoGravity() {
        return true;
    }

    @Override
    public void tick() {
        super.tick();
        world.addParticle(AllParticle.MAGIC_BULLET_PARTICLE, getX(), getY(), getZ(), 0, 0, 0);
        world.addParticle(AllParticle.MAGIC_BULLET_PARTICLE, getX()*1.2, getY()*1.2, getZ()*1.2, 0, 0, 0);
        world.addParticle(AllParticle.MAGIC_BULLET_PARTICLE, getX()*0.7, getY()*0.7, getZ()*0.7, 0, 0, 0);
        world.addParticle(AllParticle.MAGIC_BULLET_PARTICLE, this.getParticleX(0.5) * 1.3, this.getRandomBodyY()* 1.3, this.getParticleZ(0.5)* 1.3, 0, 0, 0);
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
