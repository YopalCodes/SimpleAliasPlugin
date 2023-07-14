package com.yopal.simplealiasplugin.commands;

import com.yopal.simplealiasplugin.SimpleAliasPlugin;
import com.yopal.simplealiasplugin.YML.ConfigManager;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

import java.io.IOException;

public class ReloadCommand extends Command {

    private SimpleAliasPlugin sap;

    public ReloadCommand(String name, SimpleAliasPlugin sap) {
        super(name, "sap.reload");

        this.sap = sap;
    }

    @Override
    public void execute(CommandSender commandSender, String[] strings) {
        if (!(commandSender instanceof ProxiedPlayer)) {
            return;
        }

        ProxiedPlayer player = (ProxiedPlayer) commandSender;

        try {
            ConfigManager.loadConfig(sap);
            sap.registerCommands();
            player.sendMessage(ChatColor.GRAY + "[" + ChatColor.GOLD + "SAP" + ChatColor.GRAY + "] " + ChatColor.GREEN + "config.yml and commands have been reloaded!");
        } catch (IOException e) {
            player.sendMessage(ChatColor.GRAY + "[" + ChatColor.GOLD + "SAP" + ChatColor.GRAY + "] " + ChatColor.RED + "Error reloading config.yml");
            throw new RuntimeException(e);
        }
    }
}
