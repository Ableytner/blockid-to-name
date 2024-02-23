package com.ableytner.bidtoname;

import java.io.FileWriter;
import java.io.IOException;

import net.minecraft.block.Block;
import net.minecraft.block.BlockAir;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONObject;

import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartedEvent;

@Mod(
    modid = BlockIdtoName.MODID,
    version = Tags.VERSION,
    name = "BlockIdtoName",
    acceptedMinecraftVersions = "[1.7.10]")
public class BlockIdtoName {

    public static final String MODID = "mymodid";
    public static final Logger LOG = LogManager.getLogger(MODID);

    @Mod.EventHandler
    // preInit "Run before anything else. Read your config, create blocks, items, etc, and register them with the
    // GameRegistry." (Remove if not needed)
    public void preInit(FMLPreInitializationEvent event) {
        LOG.info("I am BlockIdtoName at version " + Tags.VERSION);
    }

    @Mod.EventHandler
    public void worldStart(FMLServerStartedEvent event) {
        LOG.info("!!! server started !!!");

        JSONObject blocks = new JSONObject();
        int maxBlockId = 4096;
        if (Loader.isModLoaded("neid")) {
            maxBlockId = 65535;
        }

        for (int id = 0; id < maxBlockId; id++) {
            Block b = Block.getBlockById(id);
            if (b != null && !(b instanceof BlockAir)) {
                blocks.put(id, b.getLocalizedName());
            }
        }

        try {
            FileWriter fw = new FileWriter("blockid_to_name.json");
            fw.write((blocks.toJSONString()));
            fw.close();
            LOG.info("!!! saving finished !!!");
        } catch (IOException e) {
            LOG.info("!!! saving failed !!!");
        }
    }
}
