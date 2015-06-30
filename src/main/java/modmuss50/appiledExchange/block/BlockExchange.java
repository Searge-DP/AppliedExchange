package modmuss50.appiledExchange.block;

import modmuss50.appiledExchange.AppliedExchange;
import modmuss50.appiledExchange.client.GuiHander;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;


public class BlockExchange extends BlockContainer {
	public BlockExchange() {
		super(Material.iron);
		setBlockName("BlockExchange");
	}

	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
		return new TileExchange();
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
		player.openGui(AppliedExchange.INSTANCE, GuiHander.exchangeID, world, x, y, z);
		return true;
	}
}
