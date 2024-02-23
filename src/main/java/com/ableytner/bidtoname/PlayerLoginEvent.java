package com.ableytner.bidtoname;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent;

public class PlayerLoginEvent {

    @SubscribeEvent
    public void onPlayerLogin(PlayerLoggedInEvent event) {
        BlockIdtoName.LOG.info("!!! player logged in !!!");

        BlockIdtoName.generateList();
    }
}
