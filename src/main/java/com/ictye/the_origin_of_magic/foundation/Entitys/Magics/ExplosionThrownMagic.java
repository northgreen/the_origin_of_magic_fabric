package com.ictye.the_origin_of_magic.foundation.Entitys.Magics;

import com.ictye.the_origin_of_magic.Contents.AllItem;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion;

/**
 * 爆炸法術
 */
public class ExplosionThrownMagic extends StdThrownMagic {

    private float explosionRate;

    @Override
    protected boolean isWaterSlowDown() {
        return true;
    }

    //////////////////////
    //構造器（們？）
    public ExplosionThrownMagic(EntityType<? extends StdThrownMagic> entityType, World world) {
        super(entityType, world);
        this.explosionRate = 1.0F;
    }

    public ExplosionThrownMagic(EntityType<? extends StdThrownMagic> type, LivingEntity owner, World world) {
        super(type, owner, world);
        this.explosionRate = 1.0F;
    }
    /////////////////////

    @Override
    protected void collision(HitResult hitResult) {
        /*
        * 撞擊后爆炸~
        * */
        if (this.world.isClient){
            return;
        }
        this.remove(RemovalReason.CHANGED_DIMENSION);
    }

    @Override
    public void remove(RemovalReason reason) {
        Vec3d pos = this.getPos();
        if(reason != RemovalReason.KILLED){
            if(prdRandom!=null && prdRandom.getBool()){
                this.world.createExplosion(this, pos.getX(), pos.getY(), pos.getZ(), 5.0F * explosionRate, Explosion.DestructionType.BREAK);
            }else {
                this.world.createExplosion(this, pos.getX(), pos.getY(), pos.getZ(), 3.0F * explosionRate, Explosion.DestructionType.BREAK);
            }
        }
        super.remove(reason);
    }

    @Override
    public ItemStack getStack() {
        return new ItemStack(AllItem.EXPLOSION_MAGIC,1);
    }

    @Override
    public void tick() {
        super.tick();
        this.world.addParticle(ParticleTypes.FIREWORK, this.getX(), this.getY(), this.getZ(), 0.0D, 0.0D, 0.0D);
        Vec3d v = this.getVelocity();
        for(int i = 8;i > 0;i--){
            double x = random.nextDouble() * 2 - 1;
            double y = random.nextDouble() * 2 - 1;
            double z = random.nextDouble() * 2 - 1;
            this.world.addParticle(ParticleTypes.FLAME, this.getX(), this.getY(), this.getZ(), x/2, y/2, z/2);
        }
    }

    @Override
    public void setAge(int age) {
        this.age = age;
    }
}
