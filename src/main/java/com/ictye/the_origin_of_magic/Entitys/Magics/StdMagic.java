package com.ictye.the_origin_of_magic.Entitys.Magics;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.FlyingItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.thrown.ThrownEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.world.World;
import java.util.List;

public abstract class Std_Magic extends ThrownEntity implements FlyingItemEntity {

    /**
     * 附加魔法計數
     */
    private final int additionalTrigger = 0;

    /**
     * 附加魔法列表
     */
    private List<Std_Magic> magic;

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
    public void setAdditionTrigger(List<Std_Magic> magics){
        this.magic = magics;
    }

    protected Std_Magic(EntityType<? extends ThrownEntity> entityType, World world) {
        super(entityType, world);
    }

    protected Std_Magic(EntityType<? extends ThrownEntity> type, double x, double y, double z, World world) {
        super(type, x, y, z, world);
    }

    protected Std_Magic(EntityType<? extends ThrownEntity> type, LivingEntity owner, World world) {
        super(type, owner, world);
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
