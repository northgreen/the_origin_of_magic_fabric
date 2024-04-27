package com.ictye.the_origin_of_magic.foundation.Entitys.Magics;

import com.ictye.the_origin_of_magic.foundation.Entitys.Magics.EffectMagic.StdEffectMagic;
import com.ictye.the_origin_of_magic.foundation.Entitys.Magics.Limiters.StdMagicLimiter;
import com.ictye.the_origin_of_magic.foundation.Entitys.Magics.MagicInterfaces.StdCastInterface;
import com.ictye.the_origin_of_magic.utils.InterFaces.PlayerEntityMixinInterfaces;
import com.ictye.the_origin_of_magic.utils.Math.PRDRandom;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.EndGatewayBlockEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.FlyingItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.entity.projectile.ProjectileUtil;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

public abstract class StdThrownMagic extends ProjectileEntity implements FlyingItemEntity,StdCastInterface {

    protected PRDRandom prdRandom;

    /**
     * 附加魔法計數
     */
    protected int additionalTrigger = 0;

    /**
     * 附加魔法列表
     */
    private List<StdCastInterface> additionMagicList = new ArrayList<>();

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
    private List<StdMagicLimiter> limiters = new ArrayList<>();

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
    protected float explosionRate;

    public float getExplosionRate() {
        return explosionRate;
    }

    protected int lit = 15;

    protected int age = 0;

    private int ageRate = 1;

    public int getLit() {
        return lit;
    }
    /**
     * 是否反彈
     */
    protected boolean isReflect = false;

    protected int reflectCount = 100;
    public void setReflect(int count) {
        isReflect = true;
        reflectCount = count;
    }

    protected boolean hitCast = false;

    protected boolean ageCast = false;

    /**
     * 魔力扣除倍率
     */

    protected float magicRate = 4;

    public float getMagicRate() {
        return magicRate;
    }

    public int getAge(){
        return 200;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setAgeRate(int ageRate) {
        this.ageRate = ageRate;
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
    public void setAdditionTrigger(List<StdCastInterface> magics){
        this.additionMagicList = magics;
    }

    public StdThrownMagic(EntityType<? extends StdThrownMagic> entityType, World world) {
        super(entityType, world);
    }

    public StdThrownMagic(EntityType<? extends StdThrownMagic> type, double x, double y, double z, World world) {
        this(type, world);
        this.setPosition(x, y, z);
    }

    public StdThrownMagic(EntityType<? extends StdThrownMagic> type, LivingEntity owner, World world) {
        this(type, owner.getX(), owner.getEyeY() - (double)0.1f, owner.getZ(), world);
        this.setOwner(owner);
    }

    /**
     * {@inheritDoc}
     * 重寫的撞擊結果反饋
     * @param hitResult 撞擊結果
     */
    @Override
    protected void onCollision(HitResult hitResult) {
        // 計算反彈
        if(isReflect && reflectCount > 0){
            if (hitResult instanceof BlockHitResult blockHitResult) {
                Vec3d v = this.getVelocity();
                Direction face = blockHitResult.getSide();
                if(face == Direction.UP || face == Direction.DOWN){
                    setVelocity(new Vec3d(v.x,-v.y,v.z));
                }else if(face == Direction.NORTH || face == Direction.SOUTH){
                    setVelocity(new Vec3d(v.x,v.y,-v.z));
                }else if(face == Direction.EAST || face == Direction.WEST){
                    setVelocity(new Vec3d(-v.x,v.y,v.z));
                }
                reflectCount --;
                return;
            }
        }

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

        if(!world.isClient){
            if(hitCast){
                castAddiMagic(hitResult);
            }
        }

        collision(hitResult);
        super.onCollision(hitResult);
    }

    protected void castAddiMagic(HitResult hitResult){
        // 施放附加魔法
        if(getAdditionalTrigger() > 0){
            for (StdCastInterface additionMagic : additionMagicList) {
                Entity owner = this.getOwner();
                if(owner instanceof PlayerEntityMixinInterfaces player){
                    if(additionMagic instanceof StdThrownMagic magic){
                        magic.setPosition(this.getPos());
                        Vec3d v = this.getVelocity();
                        if(hitResult instanceof BlockHitResult blockHitResult){
                            // 撞到方塊后反射
                            Direction face = blockHitResult.getSide();
                            if(face == Direction.UP || face == Direction.DOWN){
                                magic.setVelocity(new Vec3d(v.x,-v.y,v.z));
                            }else if(face == Direction.NORTH || face == Direction.SOUTH){
                                magic.setVelocity(new Vec3d(v.x,v.y,-v.z));
                            }else if(face == Direction.EAST || face == Direction.WEST){
                                magic.setVelocity(new Vec3d(-v.x,v.y,v.z));
                            }
                        } else {
                            magic.setVelocity(v);
                        }
                        player.the_origin_of_magic$getMagicAbilitiesManager().cast((PlayerEntity)player,magic, this.world,1);
                    } else if (additionMagic instanceof StdDriestEffectMagic magic) {
                        player.the_origin_of_magic$getMagicAbilitiesManager().cast((PlayerEntity)player,magic, this.world,1);
                    }
                }
            }
            this.remove(RemovalReason.DISCARDED);
        }
    }

    protected void castAddiMagic(){
        // 施放附加魔法
        if(getAdditionalTrigger() > 0){
            for (StdCastInterface additionMagic : additionMagicList) {
                Entity owner = this.getOwner();
                if(owner instanceof PlayerEntityMixinInterfaces player){
                    if(additionMagic instanceof StdThrownMagic magic){
                        magic.setPosition(this.getPos());
                        Vec3d v = this.getVelocity();
                        magic.setVelocity(v);
                        player.the_origin_of_magic$getMagicAbilitiesManager().cast((PlayerEntity)player,magic, this.world,1);
                    } else if (additionMagic instanceof StdDriestEffectMagic magic) {
                        player.the_origin_of_magic$getMagicAbilitiesManager().cast((PlayerEntity)player,magic, this.world,1);
                    }
                }
            }
        }
    }

    /**
     * 對onCollision的包裝,任何撞擊都會調用此方法
     * @param hitResult 撞擊結果
     */
    protected void collision(HitResult hitResult){
    }

    protected boolean isWaterSlowDown(){
        return false;
    }

    @Override
    public void tick() {
        float h;
        super.tick();
        HitResult hitResult = ProjectileUtil.getCollision(this, this::canHit);
        boolean bl = false;
        if (hitResult.getType() == HitResult.Type.BLOCK) {
            BlockPos blockPos = ((BlockHitResult)hitResult).getBlockPos();
            BlockState blockState = this.world.getBlockState(blockPos);
            if (blockState.isOf(Blocks.NETHER_PORTAL)) {
                this.setInNetherPortal(blockPos);
                bl = true;
            } else if (blockState.isOf(Blocks.END_GATEWAY)) {
                BlockEntity blockEntity = this.world.getBlockEntity(blockPos);
                if (blockEntity instanceof EndGatewayBlockEntity && EndGatewayBlockEntity.canTeleport(this)) {
                    EndGatewayBlockEntity.tryTeleportingEntity(this.world, blockPos, blockState, this, (EndGatewayBlockEntity)blockEntity);
                }
                bl = true;
            }
        }
        if (hitResult.getType() != HitResult.Type.MISS && !bl) {
            this.onCollision(hitResult);
        }
        this.checkBlockCollision();
        Vec3d vec3d = this.getVelocity();
        double d = this.getX() + vec3d.x;
        double e = this.getY() + vec3d.y;
        double f = this.getZ() + vec3d.z;
        this.updateRotation();
        if (this.isTouchingWater()) {
            for (int i = 0; i < 4; ++i) {
                float g = 0.25f;
                this.world.addParticle(ParticleTypes.BUBBLE, d - vec3d.x * 0.25, e - vec3d.y * 0.25, f - vec3d.z * 0.25, vec3d.x, vec3d.y, vec3d.z);
            }
            h = 0.8f;
        } else {
            h = 0.99f;
        }
        if(isWaterSlowDown()){
            this.setVelocity(vec3d.multiply(h));
        }
        if (!this.hasNoGravity()) {
            Vec3d vec3d2 = this.getVelocity();
            this.setVelocity(vec3d2.x, vec3d2.y - (double)this.getGravity(), vec3d2.z);
        }
        this.setPosition(d, e, f);

        age++;
        if (age>=getAge() * this.ageRate){
            this.remove(RemovalReason.CHANGED_DIMENSION);
        }
        for(StdEffectMagic effectMagic : effectMagicList){
            effectMagic.tick(this.world);
        }
    }

    @Override
    public void remove(RemovalReason reason) {
        if(reason!=RemovalReason.KILLED){
            if(ageCast){
                if(!world.isClient){
                    castAddiMagic();
                }
            }
        }
        super.remove(reason);
    }

    protected float getGravity() {
        return 0.03f;
    }

    @Override
    public boolean shouldRender(double distance) {
        double d = this.getBoundingBox().getAverageSideLength() * 4.0;
        if (Double.isNaN(d)) {
            d = 4.0;
        }
        return distance < (d *= 64.0) * d;
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

    public MagicSetting Setting(){
        return new MagicSetting();
    }

    @SuppressWarnings("UnusedReturnValue")
    public class MagicSetting{
        public MagicSetting age(int a){
            age = a;
            return this;
        }

        public MagicSetting ageRate(int a){
            ageRate = a;
            return this;
        }

        public MagicSetting explosionRate (float a){
            explosionRate = a;
            return this;
        }

        public MagicSetting random(PRDRandom random){
            prdRandom = random;
            return this;
        }
    }
}
