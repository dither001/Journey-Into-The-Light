package net.journey.entity.mob.terrania.mob;

import net.journey.entity.MobStats;
import net.journey.entity.base.EntityAttributesHelper;
import net.journey.entity.base.JEntityMob;
import net.journey.init.JourneyLootTables;
import net.journey.init.JourneySounds;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;

public class EntityTerraScatterer extends JEntityMob {

    public EntityTerraScatterer(World w) {
        super(w);
        setSize(1.0F, 1.5F);
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();

        EntityAttributesHelper.setMaxHealth(this, MobStats.TERRA_SCATTERRER_HEALTH);
        EntityAttributesHelper.setAttackDamage(this, MobStats.TERRA_SCATTERRER_DAMAGE);
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return JourneySounds.WRAITH;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource d) {
        return JourneySounds.WRAITH_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return JourneySounds.WRAITH_DEATH;
    }

    @Override
    public ResourceLocation getLootTable() {
        return JourneyLootTables.TERRA_SCATTERER;
    }
}