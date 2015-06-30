package modmuss50.appiledExchange.client;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import modmuss50.appiledExchange.block.TileExchange;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import techreborn.client.SlotOutput;
import techreborn.client.container.TechRebornContainer;
import techreborn.tiles.TileAlloyFurnace;


public class ContainerExchange extends TechRebornContainer {
	EntityPlayer player;

	TileExchange tile;

	int emc;

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
		this.addSlotToContainer(new Slot(tileExchange.inventory, 0, 80, 36));


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

	@Override
	public void detectAndSendChanges() {
		super.detectAndSendChanges();
		for (int i = 0; i < this.crafters.size(); i++) {
			ICrafting icrafting = (ICrafting)this.crafters.get(i);
			if(this.emc != tile.emc){
				icrafting.sendProgressBarUpdate(this, 0, (int) tile.emc);
			}
		}
	}

	@Override
	public void addCraftingToCrafters(ICrafting crafting) {
		super.addCraftingToCrafters(crafting);
		crafting.sendProgressBarUpdate(this, 0, (int) tile.emc);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void updateProgressBar(int id, int value) {
		if(id == 0){
			this.emc = value;
		}
		this.tile.emc = emc;
	}

}
