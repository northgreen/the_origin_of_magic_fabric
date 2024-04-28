package com.ictye.the_origin_of_magic.foundation.Entitys.Magics.StdMagic;

import com.ictye.the_origin_of_magic.Contents.AllItem;
import com.ictye.the_origin_of_magic.foundation.Entitys.Magics.MagicInterfaces.StdMagic.StdThrownMagic;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.item.ItemStack;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.world.World;

public class MagicEnergyThrowBoll extends StdThrownMagic {
    public MagicEnergyThrowBoll(EntityType<? extends StdThrownMagic> entityType, World world) {
        super(entityType, world);
        isReflect = true;
    }

    public MagicEnergyThrowBoll(EntityType<? extends StdThrownMagic> type, double x, double y, double z, World world) {
        super(type, x, y, z, world);
        isReflect = true;
    }

    public MagicEnergyThrowBoll(EntityType<? extends StdThrownMagic> type, LivingEntity owner, World world) {
        super(type, owner, world);
        isReflect = true;
    }

    @Override
    protected float getGravity() {
        return super.getGravity() * 0.5f;
    }

    @Override
    protected void onEntityHit(EntityHitResult entityHitResult) {
        if(prdRandom!=null){
            prdRandom.setSP((float) (prdRandom.getP() + 0.35));
            if(prdRandom.getBool()){
                entityHitResult.getEntity().damage(DamageSource.thrownProjectile(this, this.getOwner()), 9);
            }else {
                entityHitResult.getEntity().damage(DamageSource.thrownProjectile(this, this.getOwner()), 5);
            }
        }else {
            entityHitResult.getEntity().damage(DamageSource.thrownProjectile(this, this.getOwner()), 5);
        }
        super.onEntityHit(entityHitResult);
    }

    @Override
    public ItemStack getStack() {
        return new ItemStack(AllItem.MAGIC_ENERGY_THROW_BOLL_ITEM);
    }
}
