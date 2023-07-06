package com.yopal.simplealiasplugin.listeners;

import com.yopal.simplealiasplugin.SimpleAliasPlugin;
import com.yopal.simplealiasplugin.YML.CommandsManager;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.AsyncEvent;
import net.md_5.bungee.api.event.ChatEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

import java.util.HashMap;

public class CommandListener implements Listener {

    private SimpleAliasPlugin sap;

    public CommandListener(SimpleAliasPlugin sap) {
        this.sap = sap;
    }

    @EventHandler
    public void onChat(ChatEvent e) {
        if (!e.isCommand()) {
            return;
        }

        if (!(e.getSender() instanceof ProxiedPlayer)) {
            return;
        }

        ProxiedPlayer player = (ProxiedPlayer) e.getSender();

        HashMap<String, String> commands = CommandsManager.getCommands();
        if (!commands.containsKey(e.getMessage())) {
            return;
        }

        sap.getLogger().info(e.getMessage());
        ProxyServer.getInstance().getPluginManager().dispatchCommand(player, e.getMessage());

     }
}
