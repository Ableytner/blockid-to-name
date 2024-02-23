package com.ableytner.bidtoname;

import net.minecraftforge.client.ClientCommandHandler;

import com.ableytner.bidtoname.commands.SaveCommand;

import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

public class ClientProxy extends CommonProxy {

    public void preInit(FMLPreInitializationEvent event) {
        super.preInit(event);
    }

    public void init(FMLInitializationEvent event) {
        super.init(event);

        ClientCommandHandler.instance.registerCommand(new SaveCommand());
    }
}
