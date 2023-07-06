package com.yopal.simplealiasplugin.commands;

import com.yopal.simplealiasplugin.SimpleAliasPlugin;
import com.yopal.simplealiasplugin.YML.CommandsManager;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class ReloadCommand extends Command {

    private SimpleAliasPlugin sap;

    public ReloadCommand(SimpleAliasPlugin sap) {
        super("sapReload", "sap.admin.reload");
        this.sap = sap;
    }

    @Override
    public void execute(CommandSender commandSender, String[] strings) {
        if (!(commandSender instanceof ProxiedPlayer)) {
            return;
        }

        if (!hasPermission(commandSender)) {
            return;
        }

        ProxiedPlayer player = (ProxiedPlayer) commandSender;

        player.sendMessage(new ComponentBuilder("Config reloaded!").color(ChatColor.GREEN).create());
        CommandsManager.saveConfig(sap);

    }
}
