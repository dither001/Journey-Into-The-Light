package net.journey.client.render.mob;

import net.journey.client.render.Textures;
import net.journey.client.render.base.RenderModMob;
import net.journey.entity.mob.senterian.mob.EntitySentryBlock;
import net.minecraft.client.model.ModelBase;
import net.minecraft.util.ResourceLocation;

public class RenderSentryBlock extends RenderModMob<EntitySentryBlock> {

    public RenderSentryBlock(ModelBase model, ResourceLocation tex) {
        super(model, tex);
    }

    @Override
    protected ResourceLocation getEntityTexture(EntitySentryBlock entity) {
        return entity.getPeekTick() < 0 ? Textures.sentryBlockAwake : Textures.sentryBlock;
    }
}