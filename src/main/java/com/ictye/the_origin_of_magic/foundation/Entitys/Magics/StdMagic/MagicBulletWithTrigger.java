package com.ictye.the_origin_of_magic.foundation.Entitys.Magics.StdMagic;

import com.ictye.the_origin_of_magic.Contents.AllParticle;
import com.ictye.the_origin_of_magic.foundation.Entitys.Magics.MagicInterfaces.StdMagic.StdThrownMagic;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.world.World;

public class MagicBulletWithTrigger extends StdThrownMagic {
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
        if(prdRandom!=null){
            prdRandom.setSP((float) (prdRandom.getP() + 0.5));
            if(prdRandom.getBool()){
                entityHitResult.getEntity().damage(DamageSource.thrownProjectile(this, this.getOwner()), 7);
            }else {
                entityHitResult.getEntity().damage(DamageSource.thrownProjectile(this, this.getOwner()), 4);
            }
        }else {
            entityHitResult.getEntity().damage(DamageSource.thrownProjectile(this, this.getOwner()), 4);
        }

        super.onEntityHit(entityHitResult);
    }

    @Override
    public ItemStack getStack() {
        return new ItemStack(Items.AIR);
    }
    public MagicBulletWithTrigger(EntityType<? extends StdThrownMagic> entityType, World world) {
        super(entityType, world);
        this.hitCast = true;
        this.additionalTrigger = 1;
    }

    public MagicBulletWithTrigger(EntityType<? extends StdThrownMagic> type, LivingEntity owner, World world) {
        super(type, owner, world);
        this.hitCast = true;
        this.additionalTrigger = 1;
    }

}
