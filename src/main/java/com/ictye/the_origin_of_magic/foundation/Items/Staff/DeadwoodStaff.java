package com.ictye.the_origin_of_magic.foundation.Items.Staff;

public class DeadwoodStaff extends StdStaff {
    public DeadwoodStaff(Settings settings) {
        super(settings);
        this.size = 1;
        this.attackSpeed = 0;
        this.appendAttackSpeed = 1;
        this.castingNum = 1;
        this.appendCastingNum = 0;
        this.rate = 1;
        this.exolisionRate = 1;
        this.hartRate = 1;
        this.scattering = 10f;
        this.speed = 1.0F;
        this.speedRate = 1;
        this.coolDown = true;
        this.coolingTime = 5;
        this.coolingTimeRate = 1;
        this.enchantability = 2;
    }


}
