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

public class MagicBubble extends StdThrownMagic {
    public MagicBubble(EntityType<? extends StdThrownMagic> entityType, World world) {
        super(entityType, world);
    }

    public MagicBubble(EntityType<? extends StdThrownMagic> type, double x, double y, double z, World world) {
        super(type, x, y, z, world);
    }

    public MagicBubble(EntityType<? extends StdThrownMagic> type, LivingEntity owner, World world) {
        super(type, owner, world);
    }

    @Override
    public void tick() {
        world.addParticle(AllParticle.MAGIC_BUBBLE_PARTICLE, this.getX(), this.getY(), this.getZ(), 0, 0, 0);
        super.tick();
    }

    @Override
    protected float getGravity() {
        return 0.005f;
    }

    @Override
    public float getMagicRate() {
        return 3;
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
}
