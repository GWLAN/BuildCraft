package buildcraft.core;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import buildcraft.core.lib.engines.BlockEngineBase;
import buildcraft.core.lib.engines.TileEngineBase;

public class BlockEngine extends BlockEngineBase {
	private final ArrayList<Class<? extends TileEngineBase>> engineTiles;
	private final ArrayList<String> names;
	private final ArrayList<String> texturePaths;

	public BlockEngine() {
		super();
		setBlockName("engineBlock");

		engineTiles = new ArrayList<Class<? extends TileEngineBase>>(16);
		names = new ArrayList<String>(16);
		texturePaths = new ArrayList<String>(16);
	}

	@Override
	public String getTexturePrefix(int meta, boolean addPrefix) {
		if (meta < texturePaths.size()) {
			if (addPrefix) {
				return texturePaths.get(meta).replaceAll(":", ":textures/blocks/");
			} else {
				return texturePaths.get(meta);
			}
		} else {
			return null;
		}
	}

	@Override
	public String getUnlocalizedName(int metadata) {
		return names.get(metadata % names.size());
	}

	public void registerTile(Class<? extends TileEngineBase> engineTile, String name, String texturePath) {
		engineTiles.add(engineTile);
		names.add(name);
		texturePaths.add(texturePath);
	}

	@Override
	public TileEntity createTileEntity(World world, int metadata) {
		try {
			return engineTiles.get(metadata % engineTiles.size()).newInstance();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@SuppressWarnings({"unchecked", "rawtypes"})
	@Override
	public void getSubBlocks(Item item, CreativeTabs par2CreativeTabs, List itemList) {
		for (int i = 0; i < engineTiles.size(); i++) {
			itemList.add(new ItemStack(this, 1, i));
		}
	}

	public int getEngineCount() {
		return engineTiles.size();
	}
}
