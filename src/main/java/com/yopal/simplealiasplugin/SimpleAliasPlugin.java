package com.yopal.simplealiasplugin;

import com.yopal.simplealiasplugin.commands.CreativeCommand;
import com.yopal.simplealiasplugin.commands.LTSCommand;
import com.yopal.simplealiasplugin.commands.LobbyCommand;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.plugin.Plugin;

public final class SimpleAliasPlugin extends Plugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        ProxyServer.getInstance().getPluginManager().registerCommand(this, new LTSCommand());
        ProxyServer.getInstance().getPluginManager().registerCommand(this, new CreativeCommand());
        ProxyServer.getInstance().getPluginManager().registerCommand(this, new LobbyCommand());

        /*
        IT NO WORKY RN D:

        ProxyServer.getInstance().getPluginManager().registerCommand(this, new ReloadCommand(this));
        ProxyServer.getInstance().getPluginManager().registerListener(this, new CommandListener(this));

        try {
            CommandsManager.makeConfig(this);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
         */

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }


}
