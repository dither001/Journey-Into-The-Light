package net.jitl.capabilities;

import net.jitl.JITL;
import net.jitl.common.helper.JArmorMaterial;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = JITL.MODID)
public class ArmorManager implements IArmorManager {
    private JArmorMaterial material;

    public void setArmor(JArmorMaterial setMaterial) {
        material = setMaterial;
    }

    public JArmorMaterial getArmor() {
        return material;
    }

    @SubscribeEvent()
    public static void registerCapabilities(AttachCapabilitiesEvent<Entity> event) {
        if (event.getObject() instanceof LivingEntity) {
            event.addCapability(JITL.rl("current_armor"), new ArmorProvider());
        }
    }
}
