package com.ictye.the_origin_of_magic.infrastructure.Renders.Colors;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtElement;
import net.minecraft.potion.PotionUtil;

public class MagicRay {
    public static int getColor (ItemStack stack, int tintIndex){
        if (stack.getNbt().contains("Potion", NbtElement.STRING_TYPE)) {
            return PotionUtil.getColor(PotionUtil.getPotion(stack.getNbt()));
        }
        return 0xFFFFFF; // white
    }
}
