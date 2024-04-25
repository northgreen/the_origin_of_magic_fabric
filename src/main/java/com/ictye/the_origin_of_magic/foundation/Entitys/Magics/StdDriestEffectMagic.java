package com.ictye.the_origin_of_magic.foundation.Entitys.Magics;

import com.ictye.the_origin_of_magic.foundation.Entitys.Magics.MagicInterfaces.StdMagicInterface;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;

public abstract class StdDriestEffectMagic implements StdMagicInterface {
    public abstract void onCast(PlayerEntity player, World world);

    public abstract class Setting{

    };
}
