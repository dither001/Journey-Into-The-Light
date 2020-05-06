package net.slayer.api.item;

import net.journey.init.JourneyTabs;
import net.journey.init.items.JourneyItems;
import net.journey.util.gen.lang.LangGeneratorFacade;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemSeeds;
import net.slayer.api.SlayerAPI;

public class ItemModSeeds extends ItemSeeds {

    public ItemModSeeds(String name, String f, Block block) {
        super(block, Blocks.FARMLAND);
        setTranslationKey(name);
        setCreativeTab(JourneyTabs.CROPS);
        JourneyItems.items.add(this);
        JourneyItems.itemNames.add(SlayerAPI.PREFIX + name);
        setRegistryName(SlayerAPI.MOD_ID, name);

        LangGeneratorFacade.addItemEntry(this, f);
    }
}