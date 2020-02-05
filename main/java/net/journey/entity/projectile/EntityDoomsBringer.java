package net.journey.entity.projectile;

import java.util.Random;

import net.journey.JITL;
import net.journey.client.render.particles.EntityDoomFX;
import net.journey.enums.EnumParticlesClasses;
import net.minecraft.client.particle.Particle;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.world.World;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityDoomsBringer extends EntityBasicProjectile {

	public EntityDoomsBringer(World var1) {
		super(var1);
	}
	
	public EntityDoomsBringer(World var1, EntityLivingBase var3, float dam) {
		super(var1, var3, dam);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void onUpdate() {
		Random rand = new Random();
		super.onUpdate();
		for(int i = 0; i < 6; ++i) {
			JITL.proxy.spawnParticle(EnumParticlesClasses.DOOM, this.world, this.posX, this.posY - 1.0F, this.posZ, -this.motionX, -this.motionY + 0.2D, -this.motionZ);
		}
	}
}