package com.ableytner.bidtoname.client;

import com.ableytner.bidtoname.util.ListGenerator;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;

public class ClientEvents {

    @SubscribeEvent
    public void onPlayerTick(TickEvent.PlayerTickEvent event) {
        ListGenerator.generateList();
    }
}
