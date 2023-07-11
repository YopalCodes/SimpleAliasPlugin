package com.yopal.simplealiasplugin;

import com.yopal.simplealiasplugin.YML.ConfigManager;
import com.yopal.simplealiasplugin.commands.AliasCommand;
import com.yopal.simplealiasplugin.commands.ReloadCommand;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.plugin.Plugin;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;

public final class SimpleAliasPlugin extends Plugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        try {
            ConfigManager.makeConfig(this);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // ProxyServer.getInstance().getPluginManager().registerCommand(this, new ReloadCommand("sapReload"));

        registerCommands();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    private void registerCommands() {
        HashMap<String, List<String>> commands = ConfigManager.getCommands(this);

        for (String command : commands.keySet()) {
            getLogger().log(Level.INFO, command);
            ProxyServer.getInstance().getPluginManager().registerCommand(this, new AliasCommand(command, commands.get(command), this));
        }

    }
}
