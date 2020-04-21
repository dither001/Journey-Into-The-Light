package net.journey.entity.projectile;

import net.journey.init.JourneySounds;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class EntityNethicPiercer extends EntityThrowable {

    public float damage;
    public EntityLivingBase thrower;
    protected int bounces, maxBounces;

    public EntityNethicPiercer(World var1) {
        super(var1);
    }

    public EntityNethicPiercer(World var1, EntityLivingBase var3, float dam, int max) {
        super(var1, var3);
        this.damage = dam;
        this.thrower = var3;
        this.maxBounces = max;
    }

    @Override
    protected void onImpact(RayTraceResult par1) {
        if (par1.entityHit != null && par1.entityHit != this.thrower) {
            this.playSound(JourneySounds.KNIFE, 1.0F, 1.2F / (this.rand.nextFloat() * 0.2F + 0.9F));
            par1.entityHit.setFire(5);
            par1.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, this.thrower), this.damage);
            if (!this.world.isRemote)
                this.setDead();
            return;
        }
    }

    @Override
    protected float getGravityVelocity() {
        return 0.032F;
    }
}