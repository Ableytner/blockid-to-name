package com.ableytner.bidtoname;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartedEvent;

@Mod(
    modid = BlockIdtoName.MODID,
    version = Tags.VERSION,
    name = "BlockIdtoName",
    acceptedMinecraftVersions = "[1.7.10]",
    acceptableRemoteVersions = "*")
public class BlockIdtoName {

    public static final String MODID = "bidtoname";
    public static final Logger LOG = LogManager.getLogger(MODID);

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        LOG.info("I am BlockIdtoName at version " + Tags.VERSION);

        CommonProxy.proxy.preInit(event);
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        CommonProxy.proxy.init(event);
    }

    @Mod.EventHandler
    public void worldStart(FMLServerStartedEvent event) {
        CommonProxy.proxy.worldStart(event);
    }
}
