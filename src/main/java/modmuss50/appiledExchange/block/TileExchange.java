package modmuss50.appiledExchange.block;


import appeng.me.storage.MEInventoryHandler;
import appeng.tile.grid.AENetworkTile;
import com.pahimar.ee3.api.knowledge.TransmutationKnowledgeRegistryProxy;
import com.pahimar.ee3.item.ItemAlchemicalTome;
import com.pahimar.ee3.util.ItemHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import techreborn.util.Inventory;

import java.util.ArrayList;
import java.util.Set;

public class TileExchange extends TileEntity implements IInventory {

	MEInventoryHandler handler = null;

	public Inventory inventory;

	public TileExchange() {
		inventory = new Inventory(1, "TileExchange", 64);
		inventory.setInventorySlotContents(0, new ItemStack(Items.diamond, 5));
	}

	@Override
	public void updateEntity() {
		super.updateEntity();
		ItemStack book = inventory.getStackInSlot(0);
		if(book != null){
			if(book.getItem() instanceof ItemAlchemicalTome){
				if(ItemHelper.hasOwner(book)){
					String owner = ItemHelper.getOwnerName(book);
					Set<ItemStack> stacks = TransmutationKnowledgeRegistryProxy.getPlayerKnownTransmutations(ItemHelper.getOwnerUUID(book));

				}
			}
		}
	}

	@Override
	public int getSizeInventory() {
		ItemStack book = inventory.getStackInSlot(0);
		if(book != null){
			if(book.getItem() instanceof ItemAlchemicalTome){
				if(ItemHelper.hasOwner(book)){
					return TransmutationKnowledgeRegistryProxy.getPlayerKnownTransmutations(ItemHelper.getOwnerUUID(book)).size();
				}
			}
		}
		return 0;
	}

	@Override
	public ItemStack getStackInSlot(int i) {
		ItemStack book = inventory.getStackInSlot(0);
		if(book != null){
			if(book.getItem() instanceof ItemAlchemicalTome){
				if(ItemHelper.hasOwner(book)){
					Set<ItemStack> stacks = TransmutationKnowledgeRegistryProxy.getPlayerKnownTransmutations(ItemHelper.getOwnerUUID(book));
					ArrayList<ItemStack> itemStackArrayList = new ArrayList<ItemStack>(stacks);
					return itemStackArrayList.get(i);
				}
			}
		}
		return null;
	}

	@Override
	public ItemStack decrStackSize(int slot, int amount) {
		ItemStack book = inventory.getStackInSlot(0);
		if(book != null){
			if(book.getItem() instanceof ItemAlchemicalTome){
				if(ItemHelper.hasOwner(book)){
					Set<ItemStack> stacks = TransmutationKnowledgeRegistryProxy.getPlayerKnownTransmutations(ItemHelper.getOwnerUUID(book));
					ArrayList<ItemStack> itemStackArrayList = new ArrayList<ItemStack>(stacks);
					//TODO use EMC
					return itemStackArrayList.get(slot);
				}
			}
		}
		return null;
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int slot) {
		return getStackInSlot(slot);
	}

	@Override
	public void setInventorySlotContents(int p_70299_1_, ItemStack p_70299_2_) {

	}

	@Override
	public String getInventoryName() {
		return "TileExchange";
	}

	@Override
	public boolean hasCustomInventoryName() {
		return false;
	}

	@Override
	public int getInventoryStackLimit() {
		return 64;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer p_70300_1_) {
		return true;
	}

	@Override
	public void openInventory() {

	}

	@Override
	public void closeInventory() {

	}

	@Override
	public boolean isItemValidForSlot(int p_94041_1_, ItemStack p_94041_2_) {
		return false;
	}
}
