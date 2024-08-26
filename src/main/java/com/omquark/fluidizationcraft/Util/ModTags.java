package com.omquark.fluidizationcraft.util;

import com.omquark.fluidizationcraft.FluidizationCraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

public class ModTags {
    public static class Blocks{
        public static final TagKey<Block> ALUMINUM_ORE = tag("aluminum_ore");
        private static TagKey<Block> tag(String name){
            return BlockTags.create(new ResourceLocation(FluidizationCraft.MODID, name));
        }
    }

    public static class Items{

    }


}
