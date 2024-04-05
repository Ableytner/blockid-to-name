package com.ableytner.bidtoname.server;

import com.ableytner.bidtoname.BlockIdtoName;
import com.ableytner.bidtoname.CommonProxy;

import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartedEvent;

public class ServerProxy extends CommonProxy {

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        BlockIdtoName.LOG.fatal("!!! BlockIdtoName is not supported on server side !!!");
    }

    @Override
    public void init(FMLInitializationEvent event) {}

    @Override
    public void worldStart(FMLServerStartedEvent event) {}
}
