package net.journey.entity.mob.overworld;

import net.journey.entity.MobStats;
import net.journey.entity.base.EntityAttributesHelper;
import net.journey.entity.base.JEntityMob;
import net.journey.init.JourneyLootTables;
import net.journey.init.JourneySounds;
import net.minecraft.init.Blocks;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class EntitySpyclops extends JEntityMob {

    public EntitySpyclops(World par1World) {
        super(par1World);
        addMeleeAttackingAI();
        setSize(1.0F, 2.0F);
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();

        EntityAttributesHelper.setMaxHealth(this, MobStats.SPYCLOPS_HEALTH);
        EntityAttributesHelper.setAttackDamage(this, MobStats.SPYCLOPS_DAMAGE);
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return JourneySounds.SPYCLOPS;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource d) {
        return JourneySounds.SPYCLOPS_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return JourneySounds.SPYCLOPS_HURT;
    }

    @Override
    public boolean getCanSpawnHere() {
        return
                this.world.getBlockState(new BlockPos(this.posX, this.posY - 1, this.posZ)).getBlock() == Blocks.GRASS ||
                        this.world.getBlockState(new BlockPos(this.posX, this.posY - 1, this.posZ)).getBlock() == Blocks.LEAVES ||
                        this.world.getBlockState(new BlockPos(this.posX, this.posY - 1, this.posZ)).getBlock() == Blocks.SAND ||
                        this.world.getBlockState(new BlockPos(this.posX, this.posY - 1, this.posZ)).getBlock() == Blocks.DIRT && this.dimension == 0;
    }

    @Override
    public ResourceLocation getLootTable() {
        return JourneyLootTables.SPYCLOPSE;
    }
}