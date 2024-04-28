package com.ictye.the_origin_of_magic.infrastructure.Renders.MagicsRender;

import com.ictye.the_origin_of_magic.foundation.Entitys.Magics.MagicInterfaces.StdMagic.StdThrownMagic;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.FlyingItemEntityRenderer;
import net.minecraft.util.math.BlockPos;

public class MagicRender extends FlyingItemEntityRenderer<StdThrownMagic> {

    public MagicRender(EntityRendererFactory.Context ctx, float scale, boolean lit) {
        super(ctx, scale, true);
    }

    public MagicRender(EntityRendererFactory.Context context) {
        super(context);
    }

    @Override
    protected int getBlockLight(StdThrownMagic entity, BlockPos pos) {
        return 15;
    }
}
