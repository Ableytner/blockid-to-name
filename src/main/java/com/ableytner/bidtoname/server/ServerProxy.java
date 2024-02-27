package com.ableytner.bidtoname.server;

import com.ableytner.bidtoname.CommonProxy;
import com.ableytner.bidtoname.util.ListGenerator;

import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartedEvent;

public class ServerProxy extends CommonProxy {

    @Override
    public void preInit(FMLPreInitializationEvent event) {}

    @Override
    public void init(FMLInitializationEvent event) {}

    @Override
    public void worldStart(FMLServerStartedEvent event) {
        ListGenerator.generateList();
    }
}
