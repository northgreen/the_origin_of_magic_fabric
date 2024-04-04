package com.ictye.the_origin_of_magic.Items.Magic;

import com.ictye.the_origin_of_magic.Contents.AllEntity;
import com.ictye.the_origin_of_magic.Entitys.Magics.StdMagic;
import com.ictye.the_origin_of_magic.Entitys.Magics.TestMagic;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;

public class TestMagicEntityItem extends StdMagicItem {

    private final EntityType<StdMagic> magicEntityType= AllEntity.TEST_MAGIC_ENTITY_TYPE;
    public TestMagicEntityItem(Settings settings, EntityType<StdMagic> entityType) {
        super(settings, entityType);
    }


    @Override
    public StdMagic getMagic(PlayerEntity user, World world,float exolisionRate,int hartRate) {
        return new TestMagic(magicEntityType,user,world,exolisionRate);
    }

}
