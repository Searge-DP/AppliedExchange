package modmuss50.appiledExchange.client;

import modmuss50.appiledExchange.block.TileExchange;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import techreborn.client.SlotOutput;
import techreborn.tiles.TileAlloyFurnace;


public class ContainerExchange extends Container {
	EntityPlayer player;

	TileExchange tile;

	@Override
	public boolean canInteractWith(EntityPlayer player)
	{
		return true;
	}

	public int tickTime;

	public ContainerExchange(TileExchange tileExchange,
								 EntityPlayer player)
	{
		tile = tileExchange;
		this.player = player;

		//book
		this.addSlotToContainer(new Slot(tileExchange.inventory, 0, 47, 17));


		int i;

		for (i = 0; i < 3; ++i)
		{
			for (int j = 0; j < 9; ++j)
			{
				this.addSlotToContainer(new Slot(player.inventory, j + i * 9
						+ 9, 8 + j * 18, 84 + i * 18));
			}
		}

		for (i = 0; i < 9; ++i)
		{
			this.addSlotToContainer(new Slot(player.inventory, i, 8 + i * 18,
					142));
		}
	}

}