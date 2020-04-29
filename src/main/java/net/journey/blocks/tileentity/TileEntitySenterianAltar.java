package net.journey.blocks.tileentity;

import java.util.Random;

import net.journey.JITL;
import net.journey.entity.mob.senterian.mob.EntitySentryLord;
import net.journey.enums.EnumParticlesClasses;
import net.journey.init.items.JourneyItems;
import net.journey.util.handler.Helper;
import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;

public class TileEntitySenterianAltar extends TileEntity implements ITickable {

	public Item orb;
	public boolean isFull;
	public int spawnTimer;
	public int spawnCount;
	
    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
        super.writeToNBT(nbt);
        nbt.setInteger("orb", Item.getIdFromItem(orb));
        nbt.setBoolean("isFull", false);
        nbt.setInteger("spawnTimer", 0);
        nbt.setInteger("spawnCount", 0);
        return nbt;
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt) {
        super.readFromNBT(nbt);
        orb = Item.getItemById(nbt.getInteger("orb"));
        isFull = nbt.getBoolean("isFull");
        spawnTimer = nbt.getInteger("spawnTimer");
        spawnCount = nbt.getInteger("spawnCount");
    }
    
    @Override
    public SPacketUpdateTileEntity getUpdatePacket() {
        NBTTagCompound var1 = new NBTTagCompound();
        this.writeToNBT(var1);
        return new SPacketUpdateTileEntity(pos, 1, var1);
    }

    @Override
    public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt) {
        readFromNBT(pkt.getNbtCompound());
    }
	
	public void putInOrb(Item orb) {
		this.orb = orb;
	}
	
	public Item getOrbItem() {
		return orb;
	}
	
	public boolean getHasOrb() {
		return isFull;
	}

	@Override
	public void update() {
		isFull = getOrbItem() == JourneyItems.sapphire ? true : false;
		
		if(isFull && spawnTimer == 0) {
			spawnTimer = 50;
		}
		
		if(spawnTimer >= 0) 
			spawnTimer--;
		
		if(spawnTimer <= 0)
			spawnTimer = 0;
		
		if(isFull && spawnTimer == 0) {
			spawnMob();
			spawnCount++;
		}
		
		if(spawnCount == 5) {
			putInOrb(null);
			spawnCount = 0;
		}
	}
	
	public void spawnMob() {
		Random r = new Random();
		int x = this.pos.getX(), y = this.pos.getY(), z = this.pos.getZ();
		EntitySentryLord mob = new EntitySentryLord(world);
		mob.setLocationAndAngles(x + 0.5, y + 1, z + 0.5, 0.0F, 0.0F);
		if(!world.isRemote)
			world.spawnEntity(mob);
		for(int i = 0; i < 50; i++)
            JITL.proxy.spawnParticle(EnumParticlesClasses.DOOM, this.world, x - 0.5, y, z - 0.5, r.nextFloat(), r.nextFloat(), r.nextFloat());
	}
}
