package modmuss50.appiledExchange.client;

import cpw.mods.fml.common.network.IGuiHandler;
import modmuss50.appiledExchange.block.TileExchange;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

/**
 * Created by mark on 30/06/15.
 */
public class GuiHander implements IGuiHandler {

	public static final int exchangeID = 0;

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		if(ID == exchangeID){
			return new ContainerExchange((TileExchange) world.getTileEntity(x, y, z), player);
		}
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		if(ID == exchangeID){
			return new GuiExchange(new ContainerExchange((TileExchange)world.getTileEntity(x, y, z), player));
		}
		return null;
	}
}
