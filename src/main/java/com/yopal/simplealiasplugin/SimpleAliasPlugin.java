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



        // alias commands
        registerCommands();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public void registerCommands() {
        HashMap<String, List<String>> aliases = ConfigManager.getAliases(this);
        HashMap<String, String> permissions = ConfigManager.getPermissions();

        // unregister all existing commands first
        ProxyServer.getInstance().getPluginManager().unregisterCommands(this);

        // register commands
        for (String mainCommand : ConfigManager.getCommandsToRun().keySet()) {
            ProxyServer.getInstance().getPluginManager().registerCommand(this, new AliasCommand(mainCommand, permissions.get(mainCommand), aliases.get(mainCommand)));
        }

        // config reload command
        ProxyServer.getInstance().getPluginManager().registerCommand(this, new ReloadCommand("sapReload", this));

    }
}
