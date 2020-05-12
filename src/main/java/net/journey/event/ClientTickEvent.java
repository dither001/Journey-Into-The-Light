package net.journey.event;

import net.journey.JITL;
import net.journey.client.render.gui.JourneyMainMenu;
import net.journey.client.server.EssenceProvider;
import net.journey.client.server.IEssence;
import net.journey.init.items.JourneyArmory;
import net.journey.init.items.JourneyWeapons;
import net.journey.items.bows.ItemModBow;
import net.journey.items.interactive.ItemAddEssence;
import net.journey.items.interactive.ItemEternalNight;
import net.journey.items.interactive.ItemTeleport;
import net.journey.items.ranged.ItemGun;
import net.journey.items.ranged.ItemHammer;
import net.journey.items.ranged.ItemStaff;
import net.journey.util.Config;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiIngame;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.Phase;
import net.minecraftforge.fml.common.gameevent.TickEvent.PlayerTickEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.RenderTickEvent;
import org.lwjgl.opengl.GL11;

import java.util.Random;

public class ClientTickEvent {

	private Item boots = null, body = null, legs = null, helmet = null;

	@SubscribeEvent(priority = EventPriority.LOWEST)
	public void openGui(GuiOpenEvent event) {
		if (Config.changeMainMenu == true) {
			if (event.getGui() instanceof GuiMainMenu) {
				JourneyMainMenu customMainMenu = new JourneyMainMenu();
				if (customMainMenu != null) {
					event.setGui(customMainMenu);
				}
			}
		}
	}

	@SubscribeEvent
	public void clientTickEvent(PlayerTickEvent event) {
		ItemStack stackBoots = event.player.inventory.armorItemInSlot(0);
		ItemStack stackLegs = event.player.inventory.armorItemInSlot(1);
		ItemStack stackBody = event.player.inventory.armorItemInSlot(2);
		ItemStack stackHelmet = event.player.inventory.armorItemInSlot(3);
		if (stackBoots != null) boots = stackBoots.getItem();
		else boots = null;
		if (stackBody != null) body = stackBody.getItem();
		else body = null;
		if (stackLegs != null) legs = stackLegs.getItem();
		else legs = null;
		if (stackHelmet != null) helmet = stackHelmet.getItem();
		else helmet = null;
		Random rand = new Random();
		if (event.phase == Phase.END) {
			for (int i = 0; i < 2; i++) {
				if (helmet == JourneyArmory.flameHelmet && body == JourneyArmory.flameChest && legs == JourneyArmory.flameLegs && boots == JourneyArmory.flameBoots) {
					event.player.world.spawnParticle(EnumParticleTypes.FLAME, event.player.posX + rand.nextFloat() - 0.5D, event.player.posY + 0.1D, event.player.posZ + rand.nextFloat() - 0.5D, -event.player.motionX, +event.player.motionY + 0.2D, -event.player.motionZ);
					event.player.world.spawnParticle(EnumParticleTypes.FLAME, event.player.posX + rand.nextFloat() - 0.5D, event.player.posY + 0.1D, event.player.posZ + rand.nextFloat() - 0.5D, 0, 0, 0);
				}
			}
		}
	}
}