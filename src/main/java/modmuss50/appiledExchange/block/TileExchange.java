package modmuss50.appiledExchange.block;

import com.pahimar.ee3.api.exchange.EnergyValueRegistryProxy;
import com.pahimar.ee3.api.knowledge.TransmutationKnowledgeRegistryProxy;
import com.pahimar.ee3.item.ItemAlchemicalTome;
import com.pahimar.ee3.tileentity.TileEntityAlchemicalChest;
import com.pahimar.ee3.util.ItemHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;
import techreborn.util.Inventory;

import java.util.ArrayList;
import java.util.Set;

public class TileExchange extends TileEntity implements IInventory {

	public Inventory inventory;

	public float emc;

	public TileExchange() {
		inventory = new Inventory(1, "TileExchange", 64);
	}

	@Override
	public void updateEntity() {
		super.updateEntity();
		if(!worldObj.isRemote){
			for(ForgeDirection direction : ForgeDirection.values()){
				TileEntity tile = worldObj.getTileEntity(xCoord + direction.offsetX, yCoord + direction.offsetY, zCoord + direction.offsetZ);
				if(tile != null && tile instanceof TileEntityAlchemicalChest){
					TileEntityAlchemicalChest chest = (TileEntityAlchemicalChest) tile;
					for (int i = 0; i < chest.getSizeInventory(); i++) {
						ItemStack stack = chest.getStackInSlot(i);
						if(stack != null && EnergyValueRegistryProxy.hasEnergyValue(stack) && EnergyValueRegistryProxy.getEnergyValue(stack) != null){
							for (int s = 0; s < stack.stackSize; s++) {
								this.emc += EnergyValueRegistryProxy.getEnergyValue(stack).getValue();
								chest.decrStackSize(i, 1);
								this.markDirty();
							}
						}
					}
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
					if(EnergyValueRegistryProxy.hasEnergyValue(itemStackArrayList.get(i)) && this.emc >= EnergyValueRegistryProxy.getEnergyValue(itemStackArrayList.get(i)).getValue()){
						return itemStackArrayList.get(i);
					}
					return null;
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
					if(EnergyValueRegistryProxy.hasEnergyValue(itemStackArrayList.get(slot)) && this.emc >= EnergyValueRegistryProxy.getEnergyValue(itemStackArrayList.get(slot)).getValue()){
						this.emc -= EnergyValueRegistryProxy.getEnergyValue(itemStackArrayList.get(slot)).getValue();
						ItemStack newstack = itemStackArrayList.get(slot).copy();
						this.markDirty();
						return newstack;
					}
					return null;
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
	public void setInventorySlotContents(int slot, ItemStack stack) {
		ItemStack itemStack = inventory.getStackInSlot(slot);
		if(EnergyValueRegistryProxy.hasEnergyValue(itemStack)){
			System.out.println(this.emc);
			this.emc += -EnergyValueRegistryProxy.getEnergyValue(itemStack).getValue();
			System.out.println(this.emc);
			this.markDirty();
		}
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

	@Override
	public void readFromNBT(NBTTagCompound tagCompound) {
		inventory.readFromNBT(tagCompound);
		tagCompound.getFloat("emc");
	}

	@Override
	public void writeToNBT(NBTTagCompound tagCompound) {
		inventory.writeToNBT(tagCompound);
		tagCompound.setFloat("emc", emc);
	}

	public Packet getDescriptionPacket() {
		NBTTagCompound nbtTag = new NBTTagCompound();
		writeToNBT(nbtTag);
		return new S35PacketUpdateTileEntity(this.xCoord, this.yCoord,
				this.zCoord, 1, nbtTag);
	}

	@Override
	public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity packet) {
		worldObj.markBlockRangeForRenderUpdate(xCoord, yCoord, zCoord, xCoord,
				yCoord, zCoord);
		readFromNBT(packet.func_148857_g());
	}
}
