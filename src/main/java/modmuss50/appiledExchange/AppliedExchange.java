package modmuss50.appiledExchange;

import com.pahimar.ee3.init.ModItems;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import modmuss50.appiledExchange.block.BlockExchange;
import modmuss50.appiledExchange.block.TileExchange;
import modmuss50.appiledExchange.client.GuiHander;
import modmuss50.appiledExchange.lib.ModInfo;
import net.minecraft.block.Block;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

@Mod(name = ModInfo.MOD_NAME, modid = ModInfo.MOD_ID, version = ModInfo.MOD_VERSION)
public class AppliedExchange {


	public static Block exchangeBlock;

	@Mod.Instance
	public static AppliedExchange INSTANCE;

	@Mod.EventHandler
	public static void preinit(FMLPreInitializationEvent event) {

	}

	@Mod.EventHandler
	public static void init(FMLInitializationEvent event) {
		exchangeBlock = new BlockExchange();
		GameRegistry.registerBlock(exchangeBlock, "ExchangeBlock");
		GameRegistry.registerTileEntity(TileExchange.class, "tileExchange");
		NetworkRegistry.INSTANCE.registerGuiHandler(INSTANCE, new GuiHander());
		GameRegistry.addShapelessRecipe(new ItemStack(exchangeBlock), new ItemStack(ModItems.alchemicalTome), new ItemStack(Items.nether_star));
	}

	@Mod.EventHandler
	public static void postinit(FMLPostInitializationEvent event) {

	}

}
