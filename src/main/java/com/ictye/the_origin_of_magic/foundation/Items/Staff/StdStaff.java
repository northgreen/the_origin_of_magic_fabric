package com.ictye.the_origin_of_magic.foundation.Items.Staff;

import com.ictye.the_origin_of_magic.foundation.Entitys.Magics.CorrectionMagic.StdAttrMagic;
import com.ictye.the_origin_of_magic.foundation.Entitys.Magics.EffectMagic.StdEffectMagic;
import com.ictye.the_origin_of_magic.foundation.Entitys.Magics.Limiters.StdMagicLimiter;
import com.ictye.the_origin_of_magic.foundation.Entitys.Magics.MagicInterfaces.StdCastInterface;
import com.ictye.the_origin_of_magic.foundation.Entitys.Magics.MagicInterfaces.StdMagic.StdDriestEffectMagic;
import com.ictye.the_origin_of_magic.foundation.Entitys.Magics.MagicInterfaces.StdMagic.StdThrownMagic;
import com.ictye.the_origin_of_magic.foundation.Entitys.Magics.MagicInterfaces.StdMagicInterface;
import com.ictye.the_origin_of_magic.foundation.Items.Magic.Interface.StdMagicItem;
import com.ictye.the_origin_of_magic.foundation.PlayerAbilities.MagicAbilitiesManager;
import com.ictye.the_origin_of_magic.the_origin_of_magic;
import com.ictye.the_origin_of_magic.utils.InterFaces.PlayerEntityMixinInterfaces;
import com.ictye.the_origin_of_magic.utils.MagicInventory;
import com.ictye.the_origin_of_magic.utils.Math.PRDRandom;
import net.minecraft.block.BlockState;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.NbtList;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 標準魔杖接口，旨在定義魔杖的標準屬性
 * <pre>
 * 釋放速度：魔杖的法術釋放速度
 * 修正釋放速度：魔杖的法術釋放速度修正倍率
 * 釋放數量：魔杖的法術釋放數量
 * 附加的釋放數量：魔杖的法術釋放數量修正值
 * 基礎倍率：魔杖的基礎的法術倍率
 * 爆炸修正倍率：魔杖的法術爆炸修正倍率
 * 傷害修正倍率：魔杖的法術傷害修正倍率
 * 散射：魔杖的法術散射
 * 法術飛行速度：法術飛行的速度
 * </pre>
 * <h2>計算：</h2>
 * <pre>
 * 釋放速度 = 基礎倍率 * 修正釋放速度 * 釋放數量 + 附加的釋放數量
 * 法術飛行速度 = 基礎倍率 * 修正飛行速度 * 法術飛行速度倍率
 * 法術飛行速度倍率 = 基礎倍率 * 修正飛行速度倍率
 * </pre>
 * */
public abstract class StdStaff extends Item  {
    /**
     * 釋放速度
     */
    int attackSpeed;

    /**
     * 修正釋放速度
     */
    int appendAttackSpeed;

    /**
     * 釋放數量
     */
    int castingNum;

    /**
     * 附加的釋放數量
     */
    int appendCastingNum;

    /**
     * 基礎倍率
     */
    int rate;

    /**
     * 爆炸修正倍率
     */
    int exolisionRate;

    /**
     * 傷害修正倍率
     */
    int hartRate;

    /**
     * 散射
     */
    float scattering;

    /**
     * 法術飛行速度
     */
    float speed;

    /**
     * 修正倍率
     */
    int speedRate;

    /**
     * 法杖是否冷卻
     */
    boolean coolDown;

    /**
     * 法杖冷卻時間
     */
    int coolingTime;

    /**
     * 法杖冷卻時間倍率
     */
    int coolingTimeRate;

    /**
     * 法杖容量
     */
    int size;

    /**
     * 附魔能力
     */
    int enchantability;

    /**
     * 法杖法術持續時間修正
     */
    int staffAgeRate;

    float staffMagicCastRate;

    float staffMagicLevel;

    public float getStaffMagicLevel() {
        return staffMagicLevel;
    }

    public void setStaffMagicLevel(float staffMagicLevel) {
        this.staffMagicLevel = staffMagicLevel;
    }

    /**
     * 法杖暴擊率
     */
    float Crate;

    /**
     * 隨即發生器
     */
    protected PRDRandom random;

    /**
     * 魔杖是否隨機施放
     */
    boolean randomCast = false;

    public StdStaff(Settings settings) {
        super(settings);
        // 初始化各個參數
        this.size = 9;
        this.attackSpeed = 0;
        this.appendAttackSpeed = 1;
        this.castingNum = 2;
        this.appendCastingNum = 0;
        this.rate = 1;
        this.exolisionRate = 1;
        this.hartRate = 1;
        this.scattering = 5f;
        this.speed = 1.5F;
        this.speedRate = 1;
        this.coolDown = true;
        this.coolingTime = 5;
        this.coolingTimeRate = 1;
        this.enchantability = 3;
        this.staffAgeRate = 1;
        this.Crate = 0.15f;
        this.random = new PRDRandom(getCrate());
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        super.inventoryTick(stack, world, entity, slot, selected);

    }

    @Override
    public boolean isEnchantable(ItemStack stack) {
        return true;
    }

    /**
     * 獲取攻擊速率
     * @return 攻擊速率
     */
    public int getAttackSpeed() {
        return (int) (attackSpeed * appendAttackSpeed * (rate * 0.25 > 1 ? rate * 0.25 : 1));
    }

    /**
     * 獲取冷卻時間
     * @return 冷卻時間
     */
    private int getCoolingTime(){
        return (int) (coolingTime * coolingTimeRate * (rate * 0.25 > 1 ? rate * 0.25 : 1));
    }

    /**
     * 獲取射擊速度（對於實體而言）
     * @return 射擊速度
     */
    private float getSpeed(){
        return (float) (speed * speedRate * (rate * 0.25 > 1 ? rate * 0.25 : 1));
    }

    /**
     * 獲取實體散射程度
     * @return 散射
     */
    private float getScattering() {
        return (float) (scattering * (rate * 0.25 > 1 ? rate * 0.25 : 1));
    }

    /**
     * 附魔親和力
     * @return 附魔親和力
     */
    @Override
    public int getEnchantability() {
        return (int) (enchantability * rate * (rate * 0.25 > 1 ? rate * 0.25 : 1));
    }

    public float getCrate() {
        return Crate;
    }

    /**
     * 指示是否有冷卻時間，允許不冷卻的魔杖存在
     * @return 是否冷卻
     */
    private boolean hasCoolDown(){
        return coolDown;
    }

    /**
     * 返回每次釋放的法術數量
     * @return 釋放數量
     */
    public int getCastingNum() {
        return castingNum + appendCastingNum;
    }

    /**
     * 添加物品提示
     * <pre>
     *     當在主手上：
     *     （法杖散射）
     *     （法杖容量）
     *     （法杖施放數量）
     * </pre>
     *
     * @param stack 物品
     * @param world 世界
     * @param tooltip the list of tooltips to show
     * @param context 上下文
     */
    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        tooltip.add(Text.empty());
        tooltip.add(Text.of(Text.translatable("item.modifiers.mainhand").getString())
                .copy().formatted(Formatting.DARK_GRAY));
        tooltip.add(Text.of(getScattering() +" " + Text.translatable("text.the_origin_of_magic.staff_scattering")
                .getString()).copy().formatted(Formatting.GREEN));
        tooltip.add(Text.of(getSize() +" " + Text.translatable("text.the_origin_of_magic.staff_capacity")
                .getString()).copy().formatted(Formatting.GREEN));
        tooltip.add(Text.of(getCastingNum() +" " + Text.translatable("text.the_origin_of_magic.staff_casting")
                .getString()).copy().formatted(Formatting.GREEN));
        if(randomCast){
            tooltip.add(Text.translatable("text.the_origin_of_magic.random_cast").formatted(Formatting.BLUE));
        }
    }

    /**
     * 法術施放主方法
     * @param world 世界
     * @param user 使用者
     * @param hand 手
     * @return 結果
     */
    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        // 物品冷卻
        if (hasCoolDown()) {
            user.getItemCooldownManager().set(this, getCoolingTime());
        }


        ItemStack staffItemStack = user.getStackInHand(hand);
        NbtCompound nbt = staffItemStack.getNbt();
        MagicInventory Magics = this.getInventoryFromNbt(nbt);

        float p;
        if (Magics.isEmpty()) {
            p = 0;
            if (!world.isClient) {
                // 空法杖
                user.sendMessage(Text.translatable("text.the_origin_of_magic.empty_staff"));
                return TypedActionResult.fail(user.getStackInHand(hand));
            }
        } else {
            p = random.getP();
            // 施放解析邏輯
            int count = getCastingNum(); // 可釋放的數量
            List<StdCastInterface> magicList;
            if(randomCast){
                DefaultedList<ItemStack> magicStack = Magics.stacks;
                Collections.shuffle(magicStack); // 打亂法術
                MagicInventory inventory = new MagicInventory(magicStack);
                magicList = summonMagic(inventory, user, world, count, random);
            }else {
                magicList = summonMagic(Magics, user, world, count, random);
            }


            // 生成法術實體
            for (StdCastInterface MagicEntity : magicList) {
                the_origin_of_magic.LOGGER.debug("MagicEntity: " + MagicEntity.getClass().getSimpleName());
                if (MagicEntity instanceof StdThrownMagic Magic) {
                    MagicAbilitiesManager magicAbilitiesManager = ((PlayerEntityMixinInterfaces) user).the_origin_of_magic$getMagicAbilitiesManager();
                    Magic.setAgeRate(this.staffAgeRate);
                    random.setSP(p);
                    // 生成法術實體并且破壞物品
                    if (magicAbilitiesManager.cast(user, Magic, world, staffMagicCastRate)) {
                        staffItemStack.damage(2, user, e -> e.sendEquipmentBreakStatus(EquipmentSlot.MAINHAND));
                    }
                } else if (MagicEntity instanceof StdDriestEffectMagic Magic) {
                    MagicAbilitiesManager magicAbilitiesManager = ((PlayerEntityMixinInterfaces) user).the_origin_of_magic$getMagicAbilitiesManager();
                    if (magicAbilitiesManager.cast(user, Magic, world, staffMagicCastRate)) {
                        // 生成法術實體并且破壞物品
                        random.setSP(p);
                        staffItemStack.damage(2, user, e -> e.sendEquipmentBreakStatus(EquipmentSlot.MAINHAND));
                    }
                }
            }
            random.setCallBack((t)->{random.setSP(p); return null;});
        }
        return TypedActionResult.success(user.getStackInHand(hand));
    }

    /**
     * 解析法術并且返回法術實體用於施放
     * @param inventory 魔杖物品欄
     * @param user 使用者
     * @param world 世界
     * @return 法術列表
     */
    private List<StdCastInterface> summonMagic(MagicInventory inventory, PlayerEntity user , World world, int count, PRDRandom random){

        List<StdCastInterface> magicItemList = new ArrayList<>();
        List<StdMagicLimiter> limiterList = new ArrayList<>();
        List<StdEffectMagic> effectList = new ArrayList<>();
        List<StdAttrMagic> attrMagicList = new ArrayList<>();
        float timeSet = 0;

        for(int i = inventory.size() + 1; i > 0 && count > 0 ;i --){
            ItemStack itemStack = inventory.next();
            Item magicItem =  itemStack.getItem(); // 魔法物品

            if (magicItem == Items.AIR){
                continue;
            }

            the_origin_of_magic.LOGGER.debug("Summon Magic: " + magicItem.getTranslationKey());

            StdMagicInterface magic = ((StdMagicItem)magicItem).getMagic(user,world,itemStack);
            // 分類討論（）
            if(magic instanceof StdCastInterface castInterface){
                // 處理一般魔法
                if(castInterface instanceof StdThrownMagic MagicEntity){
                    // 設置魔法
                    MagicEntity.Setting()
                            .ageRate(staffAgeRate)
                            .explosionRate(exolisionRate)
                            .random(random);
                    float finalSpeed = getSpeed(); // 計算最終速度
                    float finalScattering = getScattering(); // 計算最終散射
                    MagicEntity.setVelocity(user, user.getPitch(), user.getYaw(), 0.0F, finalSpeed, finalScattering); // 設置參數
                    magicItemList.add(MagicEntity);
                } else if (magic instanceof StdDriestEffectMagic driestEffectMagic) {
                    // 處理直接施放魔法
                    magicItemList.add(driestEffectMagic);
                }

                // 處理附加
                int addition = castInterface.getAdditionalTrigger();
                if (addition > 0) {
                    castInterface.setAdditionTrigger(summonMagic(inventory, user, world, addition, random));
                }
                count--;
            } else if(magic instanceof StdMagicLimiter limiter){
                // 處理限制器
                limiterList.add(limiter);
                count--;
            } else if (magic instanceof StdEffectMagic effect) {
                // 效果法術
                effectList.add(effect);
            }else if (magic instanceof StdAttrMagic attrMagic){
                attrMagicList.add(attrMagic);
            }
        }

        // 添加限制器
        for(StdMagicLimiter limiter :limiterList){
            for(StdCastInterface magic :magicItemList){
                magic.addLimiter(limiter);
            }
        }

        // 添加效果法術
        for (StdEffectMagic effect : effectList){
            for(StdCastInterface magic :magicItemList){
                magic.addEffect(effect);
            }
        }

        // 調節法術
        for (StdAttrMagic attrMagic : attrMagicList){
            for(StdCastInterface magic :magicItemList){
                attrMagic.onUse(magic);
            }
        }

        return magicItemList;
    }

    /**
     * 獲取法杖容量
     * @return 法杖容量
     */
    public int getSize(){
        return this.size;
    }

    @Override
    public boolean canMine(BlockState state, World world, BlockPos pos, PlayerEntity miner) {
        return !miner.isCreative();// 保證人物不能拿著法杖挖掘，那樣子真的是太奇怪了，誰的法杖能挖掘東西呢？？？
    }

    /**
     * 從nbt獲取魔法
     * @param nbt 物品的nbt標簽
     * @return 魔法物品欄
     */
    public MagicInventory getInventoryFromNbt(NbtCompound nbt){
        if(nbt != null) {
            NbtList nbtList =  nbt.getList("items", NbtElement.COMPOUND_TYPE);
            MagicInventory inventory1 = new MagicInventory(getSize());
            inventory1.readNbtList(nbtList);
            return inventory1;
        } else {
            return new MagicInventory(getSize());
        }
    }
}
