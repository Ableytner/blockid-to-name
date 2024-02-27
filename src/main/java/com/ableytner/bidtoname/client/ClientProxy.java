package com.ableytner.bidtoname.client;

import net.minecraftforge.client.ClientCommandHandler;

import com.ableytner.bidtoname.CommonProxy;
import com.ableytner.bidtoname.commands.SaveCommand;
import com.ableytner.bidtoname.util.ListGenerator;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartedEvent;

public class ClientProxy extends CommonProxy {

    @Override
    public void preInit(FMLPreInitializationEvent event) {}

    @Override
    public void init(FMLInitializationEvent event) {
        ClientCommandHandler.instance.registerCommand(new SaveCommand());

        FMLCommonHandler.instance()
            .bus()
            .register(new ClientEvents());

        ListGenerator.setup();
    }

    @Override
    public void worldStart(FMLServerStartedEvent event) {}
}
