package com.ictye.the_origin_of_magic.foundation.PlayerAbilities;

import com.ictye.the_origin_of_magic.foundation.Entitys.Magics.StdThrownMagic;
import com.ictye.the_origin_of_magic.infrastructure.netWork.NetworkIDFinder;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;

public class MagicAbilitiesManager {
    private float magicLevel = 20;

    private int magicRate = 1;

    private int magicTickTimer = 0;

    public float getMagicLevel() {
        return magicLevel;
    }

    public void setMagicLevel(float magicLevel) {
        this.magicLevel = magicLevel;
    }

    /**
     * 生成魔法實體
     * @param player 玩家
     * @param magic 魔法
     * @param world 世界
     * @return 是否成功
     */
    public boolean cast(PlayerEntity player, StdThrownMagic magic , World world){
        float neededMagic = magic.getMagicRate() * magicRate;
        if(player.isCreative()){
            return world.spawnEntity(magic);
        }
        if(magicLevel>neededMagic){
            magicLevel -= neededMagic;
            if(player instanceof ServerPlayerEntity serverPlayerEntity){
                PacketByteBuf buffer = PacketByteBufs.create();
                buffer.writeFloat(magicLevel);
                ServerPlayNetworking.send(serverPlayerEntity, NetworkIDFinder.SYNC_MAGIC_HUD_ID, buffer);
            }
            return world.spawnEntity(magic);
        }else {
            return false;
        }
    }

    /**
     * 自然更新魔力
     * @param player 玩家
     */
    public void update(PlayerEntity player){
        magicTickTimer++;
        if (!(player.getHungerManager().getFoodLevel() < 10)
                && player.world.getGameRules().getBoolean(GameRules.NATURAL_REGENERATION)
                && magicLevel < 20
                && magicTickTimer > 10){
            magicLevel += 0.5;
            magicTickTimer = 0;
        }
    }

    public void writeNbt(NbtCompound nbt){
        nbt.putInt("magicRate", magicRate);
        nbt.putInt("magicTickTimer", magicTickTimer);
        nbt.putFloat("magicLevel", magicLevel);
    }

    public void readNbt(NbtCompound nbt){
        if(nbt.contains("magicLevel", NbtElement.FLOAT_TYPE)){
            magicRate = nbt.getInt("magicRate");
            magicTickTimer = nbt.getInt("magicTickTimer");
            magicLevel = nbt.getFloat("magicLevel");
        }
    }
}
