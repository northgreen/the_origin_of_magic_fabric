package com.ictye.the_origin_of_magic.foundation.player;

import com.ictye.the_origin_of_magic.foundation.Entitys.Magics.StdThrownMagic;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;

public class MagicAbilitiesManager {
    private float magicLevel = 20;

    private int magicRate = 1;

    private int magicTickTimmer = 0;

    public float getMagicLevel() {
        return magicLevel;
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
            return world.spawnEntity(magic);
        }else {
            return false;
        }
    }

    public void update(PlayerEntity player){
        magicTickTimmer++;
        if (!player.getHungerManager().isNotFull() && player.world.getGameRules().getBoolean(GameRules.NATURAL_REGENERATION) && magicLevel < 20&& magicTickTimmer > 10){
            magicLevel += 0.5;
            magicTickTimmer = 0;
        }
    }

    public void writeNbt(NbtCompound nbt){
        nbt.putInt("magicRate", magicRate);
        nbt.putInt("magicTickTimmer", magicTickTimmer);
        nbt.putFloat("magicLevel", magicLevel);
    }

    public void readNbt(NbtCompound nbt){
        if(nbt.contains("magicLevel", NbtElement.FLOAT_TYPE)){
            magicRate = nbt.getInt("magicRate");
            magicTickTimmer = nbt.getInt("magicTickTimmer");
            magicLevel = nbt.getFloat("magicLevel");
        }
    }
}
