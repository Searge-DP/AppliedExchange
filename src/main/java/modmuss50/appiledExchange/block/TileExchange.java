package modmuss50.appiledExchange.block;


import appeng.me.storage.MEInventoryHandler;
import appeng.tile.grid.AENetworkTile;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import techreborn.util.Inventory;

public class TileExchange extends AENetworkTile {

	MEInventoryHandler handler = null;

	public Inventory inventory;

	public TileExchange() {
		inventory = new Inventory(1, "TileExchange", 64);
		inventory.setInventorySlotContents(0, new ItemStack(Items.diamond, 5));
	}
	

}
