package com.ictye.the_origin_of_magic.foundation.Entitys.Magics;

import com.ictye.the_origin_of_magic.foundation.Entitys.Magics.Limiters.StdMagicLimiter;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.FlyingItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.thrown.ThrownEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
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
    private List<StdThrownMagic> magic;

    private final List<StdMagicLimiter> limiters = new ArrayList<>();

    /**
     * 爆炸傷害倍率
     */
    private float exolisionRate;

    /**
     * 魔力扣除倍率
     */

    private float magicRate = 5;

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
        this.magic = magics;
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

    public StdThrownMagic(EntityType<? extends ThrownEntity> type, LivingEntity owner, World world, float exolisionRate) {
        this(type, owner, world);
        this.exolisionRate = exolisionRate;
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
        collision(hitResult);
        super.onCollision(hitResult);
    }

    /**
     * 添加監聽
     * @param limiter 監聽器
     */
    public void addLimiter(StdMagicLimiter  limiter){
        limiters.add(limiter);
    }

    /**
     * 對onCollision的包裝
     * @param hitResult 撞擊結果
     *
     * @see #onCollision(HitResult)
     */
    void collision(HitResult hitResult){}

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
