package com.ableytner.bidtoname;

import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartedEvent;

public abstract class CommonProxy {

    @SidedProxy(
        clientSide = "com.ableytner.bidtoname.client.ClientProxy",
        serverSide = "com.ableytner.bidtoname.server.ServerProxy")
    public static CommonProxy proxy;

    public abstract void preInit(FMLPreInitializationEvent event);

    public abstract void init(FMLInitializationEvent event);

    public abstract void worldStart(FMLServerStartedEvent event);
}
