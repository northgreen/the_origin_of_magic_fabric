package com.ictye.the_origin_of_magic.Items.Staff;

import com.ictye.the_origin_of_magic.Entitys.Magics.Std_Magic;
import com.ictye.the_origin_of_magic.Items.Magic.Std_Magic_Item;
import com.ictye.the_origin_of_magic.utils.MagicInventory;
import net.minecraft.block.BlockState;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtList;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

/**
 * 標準的法杖類，用於創建一個新的法杖
 */
public abstract class Std_Staff extends Item  {

    /**
     * 釋放速度
     */
    private int attackSpeed = 0;

    /**
     * 修正釋放速度
     */
    private int appendAttackSpeed = 1;

    /**
     * 釋放數量
     */
    private int castingNum = 2;

    /**
     * 附加的釋放數量
     */
    private int appendCastingNum = 0;

    private int rate = 1; // 基礎倍率

    private int damageRadiusRate = 1; // 爆炸修正倍率

    private int hartRate = 1; // 傷害修正倍率

    /**
     * 散射
     */
    private float scattering = 5f;

    /**
     * 法術飛行速度
     */
    private float speed = 1.5F;

    /**
     * 修正倍率
     */
    private int speedRate = 1; // 速度修正倍率

    /**
     * 法杖是否冷卻
     */
    private boolean coolDown = true;

    /**
     * 法杖冷卻時間
     */
    private int coolingTime = 5;

    /**
     * 法杖冷卻時間倍率
     */
    private int coolingTimeRate = 1;

    private int size;

    private int castCount = 0;

    public Std_Staff(Settings settings) {
        super(settings);
    }

    private final MagicInventory inventory = new MagicInventory(getSize());

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        super.appendTooltip(stack, world, tooltip, context);
    }

    abstract TypedActionResult<ItemStack> onUse(World world, PlayerEntity user, Hand hand);
    public abstract MagicInventory getInventory();
    public int castingNum() {
        return castingNum + appendCastingNum;
    }

    private int getCoolingtime(){
        return coolingTime * coolingTimeRate;
    }

    private float getSpeed(){
        return speed * speedRate;
    }

    private float getScattering() {
        return scattering;
    }

    private boolean hasCoolDown(){
        return coolDown;
    }

    /**
     * 更新釋放計數，用於循環槽位的指針
     */
    private void updateCastCount(){
        if (castCount != getSize()){
            if (castCount + 1 > getSize()){
                castCount = 0;
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
        Std_Staff stdStaffItem = (Std_Staff) staffItemStack.getItem();
        stdStaffItem.setItemFromNBT(staffItemStack.getNbt());

        // 施放解析邏輯
        MagicInventory Magics = this.getInventory();
        List<Std_Magic> magicList = summonMagic(Magics,user,world);
        // 生成法術實體
        for(Std_Magic MagicEntity:magicList){
            if (!world.isClient) {
                float finalSpeed = getSpeed(); // 計算最終速度
                float finalScattering = getScattering(); // 計算最終散射
                MagicEntity.setVelocity(user, user.getPitch(), user.getYaw(), 0.0F, finalSpeed, finalScattering); // 設置參數
                world.spawnEntity(MagicEntity); // 生成法術實體
            }
        }
        return onUse(world, user, hand);
    }

    /**
     * 解析法術并且返回法術實體用於施放
     * @param inventory 魔杖物品欄
     * @param user 使用者
     * @param world 世界
     * @return 法術列表
     */
    private List<Std_Magic> summonMagic(MagicInventory inventory,PlayerEntity user ,World world){

        List<Std_Magic> magicItemList = new ArrayList<>();
        int count = castingNum();
        int startSlot = castCount;

        do {
            ItemStack magic = inventory.getStack(castCount);
            Item magicItem =  magic.getItem();
            //檢查是否為空
            if (magicItem == Items.AIR || magic == ItemStack.EMPTY){
                updateCastCount();
                continue;
            }
            Std_Magic MagicEntity = ((Std_Magic_Item)magicItem).getMagic(user,world);
            int addition =  MagicEntity.getAdditionalTrigger();
            if(addition > 0){
                MagicEntity.setAdditionTrigger(summonMagic(inventory,user,world));
            }
            count --;
            updateCastCount();
            magicItemList.add((Std_Magic) MagicEntity);
        }while ((count > 0 || startSlot == castCount )&& castCount != getSize());

        return magicItemList;
    }

    /**
     * 獲取法杖容量
     * @return 法杖容量
     */
    public int getSize(){
        return size;
    }

    // 保證人物不能拿著法杖挖掘，那樣子真的是太奇怪了，誰的法杖能挖掘東西呢？？？
    @Override
    public boolean canMine(BlockState state, World world, BlockPos pos, PlayerEntity miner) {
        return false;
    }

    /**
     * 將物品存儲到NBT標簽裏
     * @param nbt 標簽
     * @param inventory 物品（或者說魔法）
     */
    public void convNbt(NbtCompound nbt, MagicInventory inventory){
        NbtList inventoryNbtList= inventory.toNbtList();
        nbt.put("items",inventoryNbtList);
    }

    /**
     * 從nbt獲取魔法
     * @param nbt 物品的nbt標簽
     * @return 魔法物品欄
     */
    public MagicInventory getInventoryFromNbt(NbtCompound nbt){
        NbtList nbtList = nbt.getList("items",size);
        MagicInventory inventory1 = new MagicInventory(size);
        inventory1.readNbtList(nbtList);
        return inventory1;
    }

    abstract void setInventory(Inventory inventory);

    /**
     * 從標簽設置各種魔杖的屬性
     * @param nbt
     */
    public void setItemFromNBT(NbtCompound nbt){
        setInventory(getInventoryFromNbt(nbt));
    }
}
