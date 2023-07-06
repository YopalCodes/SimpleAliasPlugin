package com.yopal.simplealiasplugin;

import com.yopal.simplealiasplugin.YML.ConfigManager;
import com.yopal.simplealiasplugin.commands.AliasCommand;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.plugin.Plugin;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public final class SimpleAliasPlugin extends Plugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        try {
            ConfigManager.makeConfig(this);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    private void registerCommands() {
        HashMap<String, List<String>> commands = ConfigManager.getCommands();

        for (String command : commands.keySet()) {
            ProxyServer.getInstance().getPluginManager().registerCommand(this, new AliasCommand(command, commands.get(command)));
        }


    }
}
