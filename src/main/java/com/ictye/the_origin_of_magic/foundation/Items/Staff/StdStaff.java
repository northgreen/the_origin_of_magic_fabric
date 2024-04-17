package com.ictye.the_origin_of_magic.foundation.Items.Staff;

import com.ictye.the_origin_of_magic.foundation.Entitys.Magics.Limiters.StdMagicLimiter;
import com.ictye.the_origin_of_magic.foundation.Entitys.Magics.StdThrownMagic;
import com.ictye.the_origin_of_magic.foundation.Items.Magic.StdMagicItem;
import com.ictye.the_origin_of_magic.utils.MagicInventory;
import net.minecraft.block.BlockState;
import net.minecraft.client.item.TooltipContext;
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
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
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
    private MagicInventory inventory = new MagicInventory(getSize());

    /**
     * 釋放速度
     */
    int attackSpeed = 0;

    /**
     * 修正釋放速度
     */
    int appendAttackSpeed = 1;

    /**
     * 釋放數量
     */
    int castingNum = 2;

    /**
     * 附加的釋放數量
     */
    int appendCastingNum = 0;

    /**
     * 基礎倍率
     */
    int rate = 1;

    /**
     * 爆炸修正倍率
     */
    int exolisionRate = 1;

    /**
     * 傷害修正倍率
     */
    int hartRate = 1;

    /**
     * 散射
     */
    float scattering = 5f;

    /**
     * 法術飛行速度
     */
    float speed = 1.5F;

    /**
     * 修正倍率
     */
    int speedRate = 1;

    /**
     * 法杖是否冷卻
     */
    boolean coolDown = true;

    /**
     * 法杖冷卻時間
     */
    int coolingTime = 5;

    /**
     * 法杖冷卻時間倍率
     */
    int coolingTimeRate = 1;

    int size;

    /**
     * 法術釋放指針，指示當前的位置，通過<code>updateCastCount()</code>更新
     */
    int castCount = 0;

    int enchantability = 3;

    public StdStaff(Settings settings) {
        super(settings);
        this.size = 9;
        this.castCount = 0;
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
        this.inventory = new MagicInventory(this.size);
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
    private int getCoolingtime(){
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

    /**
     * 指示是否有冷卻時間，允許不冷卻的魔杖存在
     * @return 是否冷卻
     */
    private boolean hasCoolDown(){
        return coolDown;
    }

    /**
     * 獲取魔杖的物品欄，每個魔杖都必須擁有以存儲法術
     * @return 魔法物品欄
     */
    public MagicInventory getInventory(){
        return this.inventory;
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
    }

    /**
     * 更新釋放計數，用於循環槽位的指針{@link #castCount}
     */
    private void updateCastCount(){
        if (getSize() != 1){
            if (castCount + 1 > getSize()){
                castCount = -1;
                updateCastCount();
            }else {
                castCount = castCount + 1;
            }
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
        if(hasCoolDown()){
            user.getItemCooldownManager().set(this,getCoolingtime());
        }

        // 設置魔法堆棧
        ItemStack staffItemStack = user.getStackInHand(hand);
        StdStaff stdStaffItem = (StdStaff) staffItemStack.getItem();
        stdStaffItem.setItemFromNBT(staffItemStack.getNbt());
        MagicInventory Magics = this.getInventory();

        if (Magics.isEmpty()){
            if (!world.isClient) {
                // 空法杖
                user.sendMessage(Text.translatable("text.the_origin_of_magic.empty_staff"));
                return TypedActionResult.fail(user.getStackInHand(hand));
            }
        }else {
            if (!world.isClient) {
                // 施放解析邏輯
                int count = getCastingNum(); // 可釋放的數量
                List<StdThrownMagic> magicList = summonMagic(Magics,user,world,count);
                // 生成法術實體
                for(StdThrownMagic MagicEntity:magicList){
                    float finalSpeed = getSpeed(); // 計算最終速度
                    float finalScattering = getScattering(); // 計算最終散射
                    MagicEntity.setVelocity(user, user.getPitch(), user.getYaw(), 0.0F, finalSpeed, finalScattering); // 設置參數
                    if(world.spawnEntity(MagicEntity)){
                        // 生成法術實體并且破壞物品
                        staffItemStack.damage(2, user, e -> e.sendEquipmentBreakStatus(EquipmentSlot.MAINHAND));
                    }
                }
            }
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
    private List<StdThrownMagic> summonMagic(MagicInventory inventory, PlayerEntity user , World world, int count){
        /*
        FIXME:
            在解析附加魔法的時候存在邏輯錯誤，可能導致死循環
         */

        List<StdThrownMagic> magicItemList = new ArrayList<>();
        int startSlot = castCount; // 起始格子

        do {
            ItemStack magic = inventory.getStack(castCount); // 獲取魔法物品堆棧
            Item magicItem =  magic.getItem(); // 魔法物品
            //檢查是否為空
            if (magicItem == Items.AIR || magic == ItemStack.EMPTY){
                updateCastCount();
                continue;
            }
            if(((StdMagicItem)magicItem).getMagic(user,world,exolisionRate,hartRate) instanceof StdThrownMagic MagicEntity){
                int addition =  MagicEntity.getAdditionalTrigger();
                // 處理有附加的法術
                if(addition > 0){
                    MagicEntity.setAdditionTrigger(summonMagic(inventory,user,world,addition));
                }
                count --;
                updateCastCount();
                magicItemList.add(MagicEntity);
            }else if(((StdMagicItem)magicItem).getMagic(user,world,exolisionRate,hartRate) instanceof StdMagicLimiter limiter){
                StdThrownMagic lastmagic = magicItemList.get(magicItemList.size()-1);
                lastmagic.addLimiter(limiter);
                count --;
                updateCastCount();
            }
        }while (count > 0 && startSlot != castCount );
        return magicItemList;
    }

    /**
     * 獲取法杖容量
     * @return 法杖容量
     */
    public int getSize(){
        return this.size;
    }

    // 保證人物不能拿著法杖挖掘，那樣子真的是太奇怪了，誰的法杖能挖掘東西呢？？？
    @Override
    public boolean canMine(BlockState state, World world, BlockPos pos, PlayerEntity miner) {
        return !miner.isCreative();
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

    /**
     * 設置魔杖本身的物品欄
     */
    void setInventory(MagicInventory inventory) {
        this.inventory.setStackFromInventory(inventory) ;
    }

    /**
     * 從標簽設置各種魔杖的屬性
     * @param nbt NBT
     */
    public void setItemFromNBT(NbtCompound nbt){
        setInventory(getInventoryFromNbt(nbt));
    }
}
