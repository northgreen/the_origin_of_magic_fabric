package com.ictye.the_origin_of_magic.Entitys.Magics;

import com.ictye.the_origin_of_magic.Contents.AllItem;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.thrown.ThrownEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion;

public class TestMagic extends StdMagic {

    private float exolisionRate;

    @Override
    protected float getGravity() {
        return 0.0F;
    }

    public TestMagic(EntityType<? extends ThrownEntity> entityType, World world) {
        super(entityType, world);
        this.exolisionRate = 1.0F;
    }

    public TestMagic(EntityType<? extends ThrownEntity> type, LivingEntity owner, World world) {
        super(type, owner, world);
        this.exolisionRate = 1.0F;
    }

    public TestMagic(EntityType<? extends ThrownEntity> type, LivingEntity owner, World world, float exolisionRate) {
        this(type, owner, world);
        this.exolisionRate = exolisionRate;
    }


    @Override
    protected void onCollision(HitResult hitResult) {
        /*
        * 撞擊后爆炸~
        * */
        BlockPos pos = new BlockPos(hitResult.getPos());
        this.world.createExplosion(this, pos.getX(), pos.getY(), pos.getZ(), 5.0F * exolisionRate, Explosion.DestructionType.BREAK);
        this.remove(RemovalReason.CHANGED_DIMENSION);
    }

    @Override
    protected void initDataTracker() {
        super.initDataTracker();
    }

    @Override
    public ItemStack getStack() {
        return new ItemStack(AllItem.TEST_MAGIC,1);
    }

    @Override
    public void tick() {
        super.tick();
        this.world.addParticle(ParticleTypes.DRAGON_BREATH, this.getX(), this.getY(), this.getZ(), 0.0D, 0.0D, 0.0D);
    }
}
