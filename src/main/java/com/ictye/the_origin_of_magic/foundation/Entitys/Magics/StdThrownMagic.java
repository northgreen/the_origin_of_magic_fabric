package com.ictye.the_origin_of_magic.foundation.Entitys.Magics;

import com.ictye.the_origin_of_magic.foundation.Entitys.Magics.EffectMagic.StdEffectMagic;
import com.ictye.the_origin_of_magic.foundation.Entitys.Magics.Limiters.StdMagicLimiter;
import com.ictye.the_origin_of_magic.utils.InterFaces.PlayerEntityMixinInterfaces;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.FlyingItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.thrown.ThrownEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

public abstract class StdThrownMagic extends ThrownEntity implements FlyingItemEntity,StdMagicInterface {

    /**
     * 附加魔法計數
     */
    private final int additionalTrigger = 0;

    /**
     * 附加魔法列表
     */
    private List<StdThrownMagic> additionMagicList;

    private List<StdEffectMagic> effectMagicList = new ArrayList<>();

    /**
     * 添加法術效果
     * @param effectMagic 效果法術
     */
    public void addEffect(StdEffectMagic effectMagic) {
        effectMagicList.add(effectMagic);
    }

    /**
     * 限制器列表
     */
    private final List<StdMagicLimiter> limiters = new ArrayList<>();

    /**
     * 添加監聽
     * @param limiter 監聽器
     */
    public void addLimiter(StdMagicLimiter  limiter){
        limiters.add(limiter);
    }

    /**
     * 爆炸傷害倍率
     */
    private float explosionRate;

    public float getExplosionRate() {
        return explosionRate;
    }

    /**
     * 魔力扣除倍率
     */

    float magicRate = 5;

    public float getMagicRate() {
        return magicRate;
    }

    /**
     *  獲取附加魔法計數
     * */
    public int getAdditionalTrigger() {
        return additionalTrigger;
    }

    /**
     * 設置附加魔法
     * @param magics 魔法列表
     */
    public void setAdditionTrigger(List<StdThrownMagic> magics){
        this.additionMagicList = magics;
    }

    protected StdThrownMagic(EntityType<? extends ThrownEntity> entityType, World world) {
        super(entityType, world);
    }

    protected StdThrownMagic(EntityType<? extends ThrownEntity> type, double x, double y, double z, World world) {
        super(type, x, y, z, world);
    }

    protected StdThrownMagic(EntityType<? extends ThrownEntity> type, LivingEntity owner, World world) {
        super(type, owner, world);
    }

    public StdThrownMagic(EntityType<? extends ThrownEntity> type, LivingEntity owner, World world, float explosionRate) {
        this(type, owner, world);
        this.explosionRate = explosionRate;
    }

    /**
     * {@inheritDoc}
     * 重寫的撞擊結果反饋
     * @param hitResult 撞擊結果
     */
    @Override
    protected void onCollision(HitResult hitResult) {
        // 檢查是否能生效
        for (StdMagicLimiter limiter : limiters) {
            HitResult.Type type = hitResult.getType();
            if (type == HitResult.Type.ENTITY) {
                if (hitResult instanceof EntityHitResult && !(limiter.canEffect((EntityHitResult) hitResult, hitResult, null))) {
                    this.remove(RemovalReason.CHANGED_DIMENSION);
                    return;
                }
            } else if (type == HitResult.Type.BLOCK) {
                if (hitResult instanceof BlockHitResult && !(limiter.canEffect(null, hitResult, (BlockHitResult) hitResult))) {
                    this.remove(RemovalReason.CHANGED_DIMENSION);
                    return;
                }
            }
        }

        // 施放附加魔法
        //noinspection ConstantValue
        if(additionalTrigger > 0){
            for (StdThrownMagic additionMagic : additionMagicList) {
                Entity owner = this.getOwner();
                if(owner instanceof PlayerEntityMixinInterfaces player){

                    Vec3d v = this.getVelocity();
                    if(hitResult instanceof BlockHitResult blockHitResult){
                        Direction face = blockHitResult.getSide();
                        if(face == Direction.UP || face == Direction.DOWN){
                            additionMagic.setVelocity(new Vec3d(v.x,v.y,-v.z));
                        }else if(face == Direction.NORTH || face == Direction.SOUTH){
                            additionMagic.setVelocity(new Vec3d(v.x,-v.z,v.y));
                        }else if(face == Direction.EAST || face == Direction.WEST){
                            additionMagic.setVelocity(new Vec3d(-v.z,v.y,v.x));
                        }
                    } else {
                        additionMagic.setVelocity(v);
                    }
                    player.the_origin_of_magic$getMagicAbilitiesManager().cast((PlayerEntity)player,additionMagic, this.world);
                }
            }
        }
        collision(hitResult);
        super.onCollision(hitResult);
    }

    /**
     * 對onCollision的包裝
     * @param hitResult 撞擊結果
     *
     * @see #onCollision(HitResult)
     */
    void collision(HitResult hitResult){}

    @Override
    public void tick() {
        super.tick();
        for(StdEffectMagic effectMagic : effectMagicList){
            effectMagic.tick(this.world);
        }
    }

    @Override
    protected void readCustomDataFromNbt(NbtCompound nbt) {
        super.readCustomDataFromNbt(nbt);
    }

    @Override
    protected void writeCustomDataToNbt(NbtCompound nbt) {
        super.writeCustomDataToNbt(nbt);
    }

    @Override
    protected void initDataTracker() {
    }
}
