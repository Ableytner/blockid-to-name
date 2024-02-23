package com.ableytner.bidtoname;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.BlockAir;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONObject;

import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

@Mod(
    modid = BlockIdtoName.MODID,
    version = Tags.VERSION,
    name = "BlockIdtoName",
    acceptedMinecraftVersions = "[1.7.10]")
public class BlockIdtoName {

    public static final String MODID = "mymodid";
    public static final Logger LOG = LogManager.getLogger(MODID);

    @SidedProxy(clientSide = "com.ableytner.bidtoname.ClientProxy", serverSide = "com.ableytner.bidtoname.CommonProxy")
    public static CommonProxy proxy;

    @Mod.EventHandler
    // preInit "Run before anything else. Read your config, create blocks, items, etc, and register them with the
    // GameRegistry." (Remove if not needed)
    public void preInit(FMLPreInitializationEvent event) {
        proxy.preInit(event);
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        proxy.init(event);
    }

    @SuppressWarnings("unchecked")
    public static void generateList() {
        JSONObject blocks = new JSONObject();
        int maxBlockId = 4096;
        if (Loader.isModLoaded("neid")) {
            maxBlockId = 65535;
        }

        for (int blockId = 0; blockId < maxBlockId; blockId++) {
            Block b = Block.getBlockById(blockId);
            if (b != null && !(b instanceof BlockAir)) {
                List<ItemStack> subBlocks = new ArrayList<ItemStack>();
                b.getSubBlocks(Item.getItemFromBlock(b), CreativeTabs.tabAllSearch, subBlocks);
                for (int subBlockId = 0; subBlockId < subBlocks.size(); subBlockId++) {
                    ItemStack i = subBlocks.get(subBlockId);
                    Item item = i.getItem();
                    if (item != null) {
                        String name = Integer.toString(blockId) + ":" + Integer.toString(subBlockId);
                        blocks.put(name, item.getItemStackDisplayName(i));
                        if (item.getItemStackDisplayName(i)
                            .contains("Bitt")) {
                            LOG.info(item.getItemStackDisplayName(i));
                        }
                    }
                }
            }
        }

        try {
            Writer fw = new OutputStreamWriter(new FileOutputStream("blockid_to_name.json"), StandardCharsets.UTF_16); // forName("Windows-31J")
            fw.write((blocks.toJSONString()));
            fw.close();
            LOG.info("!!! saving finished !!!");
        } catch (IOException e) {
            LOG.info("!!! saving failed !!!");
        }
    }
}
