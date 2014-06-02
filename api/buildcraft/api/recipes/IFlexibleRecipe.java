/**
 * Copyright (c) 2011-2014, SpaceToad and the BuildCraft Team
 * http://www.mod-buildcraft.com
 *
 * BuildCraft is distributed under the terms of the Minecraft Mod Public
 * License 1.0, or MMPL. Please check the contents of the license located in
 * http://www.mod-buildcraft.com/MMPL-1.0.txt
 */
package buildcraft.api.recipes;

import net.minecraft.inventory.IInventory;

import net.minecraftforge.fluids.IFluidHandler;

public interface IFlexibleRecipe {

	boolean canBeCrafted(IInventory items, IFluidHandler fluids);

	CraftingResult craftPreview(IInventory items, IFluidHandler fluids);

	CraftingResult craft(IInventory items, IFluidHandler fluids);

	String getId();

}