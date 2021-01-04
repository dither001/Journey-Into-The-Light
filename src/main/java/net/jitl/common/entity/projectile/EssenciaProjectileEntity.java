package net.jitl.common.entity.projectile;

import net.jitl.common.entity.EssenciaBoltEntity;
import net.jitl.common.entity.projectile.base.DamagingProjectileEntity;
import net.jitl.init.JEntityTypes;
import net.jitl.init.JParticleManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.network.IPacket;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.network.NetworkHooks;

public class EssenciaProjectileEntity extends DamagingProjectileEntity {

    public EssenciaProjectileEntity(EntityType<EssenciaProjectileEntity> type, World world) {
        super(type, world);
    }

    public EssenciaProjectileEntity(EntityType<EssenciaProjectileEntity> type, World world, LivingEntity thrower, float damage) {
        super(type, world, thrower, damage);
    }

    public EssenciaProjectileEntity(World world, LivingEntity thrower, float damage) {
        super(JEntityTypes.ESSENCIA_PROJECTILE_TYPE, world, thrower, damage);
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void onClientTick() {
        super.onClientTick();
        int count = 2;
        Vector3d vector3d = this.getDeltaMovement();
        double d0 = this.getX() + vector3d.x;
        double d1 = this.getY() + vector3d.y;
        double d2 = this.getZ() + vector3d.z;
        for (int i = 0; i < count; ++i) {
            this.level.addParticle(JParticleManager.RED_FLAME.get(),
                    d0 - vector3d.x * 0.25D + this.random.nextDouble() * 0.6D - 0.3D,
                    d1 - vector3d.y + 0.25F,
                    d2 - vector3d.z * 0.25D + this.random.nextDouble() * 0.6D - 0.3D,
                    vector3d.x,
                    vector3d.y,
                    vector3d.z);
        }
    }

    @Override
    protected void onEntityImpact(RayTraceResult result, Entity target) {
        EssenciaBoltEntity essenciaBoltEntity = new EssenciaBoltEntity(JEntityTypes.ESSENCIA_BOLT_TYPE, level);
        essenciaBoltEntity.setPos(result.getLocation().x(), result.getLocation().y(), result.getLocation().z());
        level.addFreshEntity(essenciaBoltEntity);
    }

    @Override
    protected float getGravity() {
        return 0.003F;
    }

    @Override
    public IPacket<?> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }
}
