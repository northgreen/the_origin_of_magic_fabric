package com.ictye.the_origin_of_magic.Renders.MagicsRender;

import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.entity.Entity;
import net.minecraft.util.Identifier;

public class TestMagicRender extends EntityRenderer {
    protected TestMagicRender(EntityRendererFactory.Context ctx) {
        super(ctx);
    }

    @Override
    protected boolean hasLabel(Entity entity) {
        return false;
    }

    @Override
    public Identifier getTexture(Entity entity) {
        return null;
    }
}
