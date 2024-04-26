package com.ictye.the_origin_of_magic.utils.Math;

import com.ictye.the_origin_of_magic.the_origin_of_magic;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * PRD偽隨機事件發生器
 */
public class PRDRandom {
    private int count = 1;
    private float SP = 0;
    private static final Map<Float, Float> PRD_CMap = new HashMap<>();
    private Function<PRDRandom, Float> CallBack = (P) -> null;

    private float p;

    /**
     * 構造PRD僞隨機事件發生器
     * @param P 期望概率
     */
    public PRDRandom(float P) {
        setSP(P);
        this.p=P;
    }

    public PRDRandom(NbtCompound nbt){
        if(nbt.contains("PRDRandom", NbtElement.COMPOUND_TYPE)){
            NbtCompound PRD_Nbt = nbt.getCompound("PRDRandom");
            setSP(PRD_Nbt.getFloat("SP"));
            this.p = PRD_Nbt.getFloat("SP");
            setCount(PRD_Nbt.getInt("count"));
        }else{
            if (nbt.contains("SP", NbtElement.FLOAT_TYPE)){
                setSP(nbt.getFloat("SP"));
                this.p = nbt.getFloat("SP");
            }
            if (nbt.contains("count", NbtElement.INT_TYPE)){
                setCount(nbt.getInt("count"));
            }
        }
    }

    public NbtCompound writeNbt(NbtCompound nbt){
        nbt.put("PRDRandom",toNBT());
        return nbt;
    }

    public NbtCompound toNBT(){
        NbtCompound nbt = new NbtCompound();
        nbt.putFloat("SP",p);
        nbt.putInt("count",count);
        return nbt;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = Math.max(1,count);
    }

    public void resetCount(){
        count = 1;
    }

    /**
     * 設置生成的概率
     * @param P 概率
     */
    public void setSP(float P) {
        float fP = Math.min(1,P);
        fP = Math.max(0,fP);
        fP = (float) (Math.ceil(fP * 100) / 100);
        this.SP = fP;
        this.p = fP;
    }

    public float getP() {
        return Math.min(1, p);
    }

    public boolean getBool(){
        float random = (float)Math.random();
        count = Math.max(0,count);
        float c = PRD_CMap.get(getP());
        if (random < count * c){
            the_origin_of_magic.LOGGER.debug("PRD return true");
            resetCount();
            CallBack.apply(this);
            return true;
        }else{
            the_origin_of_magic.LOGGER.debug("PRD return flase");
            count++;
            CallBack.apply(this);
            return false;
        }
    }

    public void setCallBack(Function<PRDRandom, Float> callBack) {
        CallBack = callBack;
    }

    public static void init(){
        // 這段是自動生產的參數，實際在代碼裏面計算非常耗費性能！！！,就像你不應該動態生產PI常量一樣
        PRD_CMap.put(0.0f,0.0f);
        PRD_CMap.put(0.01f,0.000156042f);
        PRD_CMap.put(0.02f,0.000620088f);
        PRD_CMap.put(0.03f,0.00138618f);
        PRD_CMap.put(0.04f,0.00244856f);
        PRD_CMap.put(0.05f,0.00380166f);
        PRD_CMap.put(0.06f,0.00544011f);
        PRD_CMap.put(0.07f,0.00735871f);
        PRD_CMap.put(0.08f,0.00955242f);
        PRD_CMap.put(0.09f,0.0120164f);
        PRD_CMap.put(0.1f,0.0147458f);
        PRD_CMap.put(0.11f,0.0177363f);
        PRD_CMap.put(0.12f,0.0209832f);
        PRD_CMap.put(0.13f,0.0244824f);
        PRD_CMap.put(0.14f,0.0282297f);
        PRD_CMap.put(0.15f,0.0322209f);
        PRD_CMap.put(0.16f,0.0364523f);
        PRD_CMap.put(0.17f,0.0409199f);
        PRD_CMap.put(0.18f,0.0456201f);
        PRD_CMap.put(0.19f,0.0505493f);
        PRD_CMap.put(0.2f,0.055704f);
        PRD_CMap.put(0.21f,0.0610808f);
        PRD_CMap.put(0.22f,0.0666764f);
        PRD_CMap.put(0.23f,0.0724875f);
        PRD_CMap.put(0.24f,0.0785111f);
        PRD_CMap.put(0.25f,0.0847441f);
        PRD_CMap.put(0.26f,0.0911835f);
        PRD_CMap.put(0.27f,0.0978264f);
        PRD_CMap.put(0.28f,0.10467f);
        PRD_CMap.put(0.29f,0.111712f);
        PRD_CMap.put(0.3f,0.118949f);
        PRD_CMap.put(0.31f,0.126379f);
        PRD_CMap.put(0.32f,0.134001f);
        PRD_CMap.put(0.33f,0.141805f);
        PRD_CMap.put(0.34f,0.14981f);
        PRD_CMap.put(0.35f,0.157983f);
        PRD_CMap.put(0.36f,0.166329f);
        PRD_CMap.put(0.37f,0.174909f);
        PRD_CMap.put(0.38f,0.183625f);
        PRD_CMap.put(0.39f,0.192486f);
        PRD_CMap.put(0.4f,0.201547f);
        PRD_CMap.put(0.41f,0.21092f);
        PRD_CMap.put(0.42f,0.220365f);
        PRD_CMap.put(0.43f,0.229899f);
        PRD_CMap.put(0.44f,0.23954f);
        PRD_CMap.put(0.45f,0.249307f);
        PRD_CMap.put(0.46f,0.259872f);
        PRD_CMap.put(0.47f,0.270453f);
        PRD_CMap.put(0.48f,0.281008f);
        PRD_CMap.put(0.49f,0.291552f);
        PRD_CMap.put(0.5f,0.302103f);
        PRD_CMap.put(0.51f,0.312677f);
        PRD_CMap.put(0.52f,0.323291f);
        PRD_CMap.put(0.53f,0.33412f);
        PRD_CMap.put(0.54f,0.34737f);
        PRD_CMap.put(0.55f,0.360398f);
        PRD_CMap.put(0.56f,0.373217f);
        PRD_CMap.put(0.57f,0.38584f);
        PRD_CMap.put(0.58f,0.398278f);
        PRD_CMap.put(0.59f,0.410545f);
        PRD_CMap.put(0.6f,0.42265f);
        PRD_CMap.put(0.61f,0.434604f);
        PRD_CMap.put(0.62f,0.446419f);
        PRD_CMap.put(0.63f,0.458104f);
        PRD_CMap.put(0.64f,0.46967f);
        PRD_CMap.put(0.65f,0.481125f);
        PRD_CMap.put(0.66f,0.492481f);
        PRD_CMap.put(0.67f,0.507463f);
        PRD_CMap.put(0.68f,0.529412f);
        PRD_CMap.put(0.69f,0.550725f);
        PRD_CMap.put(0.7f,0.571429f);
        PRD_CMap.put(0.71f,0.591549f);
        PRD_CMap.put(0.72f,0.611111f);
        PRD_CMap.put(0.73f,0.630137f);
        PRD_CMap.put(0.74f,0.648649f);
        PRD_CMap.put(0.75f,0.666667f);
        PRD_CMap.put(0.76f,0.684211f);
        PRD_CMap.put(0.77f,0.701299f);
        PRD_CMap.put(0.78f,0.717949f);
        PRD_CMap.put(0.79f,0.734177f);
        PRD_CMap.put(0.8f,0.75f);
        PRD_CMap.put(0.81f,0.765432f);
        PRD_CMap.put(0.82f,0.780488f);
        PRD_CMap.put(0.83f,0.795181f);
        PRD_CMap.put(0.84f,0.809524f);
        PRD_CMap.put(0.85f,0.823529f);
        PRD_CMap.put(0.86f,0.837209f);
        PRD_CMap.put(0.87f,0.850575f);
        PRD_CMap.put(0.88f,0.863636f);
        PRD_CMap.put(0.89f,0.876404f);
        PRD_CMap.put(0.9f,0.888889f);
        PRD_CMap.put(0.91f,0.901099f);
        PRD_CMap.put(0.92f,0.913043f);
        PRD_CMap.put(0.93f,0.924731f);
        PRD_CMap.put(0.94f,0.93617f);
        PRD_CMap.put(0.95f,0.947368f);
        PRD_CMap.put(0.96f,0.958333f);
        PRD_CMap.put(0.97f,0.969072f);
        PRD_CMap.put(0.98f,0.979592f);
        PRD_CMap.put(0.99f,0.989899f);
        PRD_CMap.put(1f,1f);
    }
}
