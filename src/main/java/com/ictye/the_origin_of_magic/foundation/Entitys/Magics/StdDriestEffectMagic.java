package com.ictye.the_origin_of_magic.foundation.Entitys.Magics;

import com.ictye.the_origin_of_magic.foundation.Entitys.Magics.MagicInterfaces.StdCastInterface;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

public abstract class StdDriestEffectMagic implements StdCastInterface {
    public abstract boolean onCast(PlayerEntity player, World world);

    public List<StdCastInterface> additionMagic = new ArrayList<>();

    /**
     * 附加魔法計數
     */
    protected final int additionalTrigger = 0;

    public int getAdditionalTrigger() {
        return additionalTrigger;
    }

    public void setAdditionTrigger(List<StdCastInterface> additionMagic){
        this.additionMagic = additionMagic;
    }

    public void addAdditionMagic(StdCastInterface magic){
        additionMagic.add(magic);
    }


    public abstract float getMagicRate();

    public abstract class Setting{

    };
}
