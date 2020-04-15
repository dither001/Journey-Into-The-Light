package net.journey.client.server;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;

import javax.annotation.Nullable;

public class EssenceStorage implements IStorage<IEssence> {

    @Nullable
    @Override
    public NBTBase writeNBT(Capability<IEssence> capability, IEssence instance, EnumFacing side) {
        return instance.writeNBT(instance, new NBTTagCompound());
    }

    @Override
    public void readNBT(Capability<IEssence> capability, IEssence instance, EnumFacing side, NBTBase nbt) {
        instance.readNBT(nbt, instance, new NBTTagCompound());
    }
}