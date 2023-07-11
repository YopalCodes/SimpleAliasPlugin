package com.yopal.simplealiasplugin.commands;

import com.yopal.simplealiasplugin.SimpleAliasPlugin;
import com.yopal.simplealiasplugin.YML.ConfigManager;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;

public class AliasCommand extends Command {

    private String commandToRun;
    private List<String> aliases;
    private SimpleAliasPlugin sap;

    public AliasCommand(String commandToRun, List<String> aliases, SimpleAliasPlugin sap) {
        super(aliases.get(0), null, aliases.toArray(new String[0]));

        this.commandToRun = commandToRun;
        this.aliases = aliases;

        this.sap = sap;
    }


    @Override
    public void execute(CommandSender commandSender, String[] strings) {

        HashMap<String, List<String>> commands = ConfigManager.getCommands(sap);

        String command = null;
        for (String string : commands.keySet()) {
            if (string.equalsIgnoreCase(commandToRun)) {
                command = string;
                break;
            }
        }

        sap.getProxy().getLogger().log(Level.INFO, command);

        if (command != null) {
            ProxyServer.getInstance().getPluginManager().dispatchCommand(commandSender, command);
        }
    }
}
