package com.ictye.the_origin_of_magic.Entitys.Magics;

import com.ictye.the_origin_of_magic.Contents.AllItem;
import com.ictye.the_origin_of_magic.utils.Vec3d_Util;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.thrown.ThrownEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion;

public class TestMagic extends StdMagic {

    Vec3d V_speed = Vec3d.ZERO;

    double speed = 5;

    public TestMagic(EntityType<? extends ThrownEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    protected float getGravity() {
        return 0.0F;
    }

    protected TestMagic(EntityType<? extends ThrownEntity> type, double x, double y, double z, World world) {
        super(type, x, y, z, world);
    }

    public TestMagic(EntityType<? extends ThrownEntity> type, LivingEntity owner, World world) {
        super(type, owner, world);
        V_speed = Vec3d_Util.getLookVec(owner,speed);
    }

    @Override
    protected void onCollision(HitResult hitResult) {
        /*
        * 撞擊后爆炸~
        * */
        BlockPos pos = new BlockPos(hitResult.getPos());
        this.world.createExplosion(this, pos.getX(), pos.getY(), pos.getZ(), 5.0F, Explosion.DestructionType.BREAK);
        this.remove(RemovalReason.CHANGED_DIMENSION);
    }

    @Override
    protected void initDataTracker() {

    }

    @Override
    public ItemStack getStack() {
        return new ItemStack(AllItem.TEST_MAGIC,1);
    }


}
