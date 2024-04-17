package com.ictye.the_origin_of_magic.foundation.Items.Magic;

import com.ictye.the_origin_of_magic.Contents.AllEntity;
import com.ictye.the_origin_of_magic.foundation.Entitys.Magics.StdThrownMagic;
import com.ictye.the_origin_of_magic.foundation.Entitys.Magics.TestThrownMagic;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;

public class TestMagicEntityItem extends StdMagicItem {

    private final EntityType<StdThrownMagic> magicEntityType = AllEntity.TEST_MAGIC_ENTITY_TYPE;
    public TestMagicEntityItem(Settings settings, EntityType<StdThrownMagic> entityType) {
        super(settings, entityType);
    }

    @Override
    public StdThrownMagic getMagic(PlayerEntity user, World world, float exolisionRate, int hartRate) {
        return new TestThrownMagic(magicEntityType,user,world,exolisionRate);
    }
}
