package com.omquark.fluidizationcraft.Fluids;

import com.omquark.fluidizationcraft.Fluids.ReduxBaseFluid.FluidDamageValues;

import net.minecraft.block.material.Material;
import net.minecraftforge.fluids.Fluid;

public class PyroniteFluidBlock extends ReduxBaseFluid{

	public PyroniteFluidBlock(Fluid fluid, Material material) {
		super(fluid, material);

		damageValue = FluidDamageValues.PYRONITE_DAMAGE_VALUE.ordinal();
	}

}
