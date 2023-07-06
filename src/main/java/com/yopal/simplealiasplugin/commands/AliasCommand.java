package com.yopal.simplealiasplugin.commands;

import com.yopal.simplealiasplugin.YML.ConfigManager;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.plugin.Command;

import java.util.HashMap;
import java.util.List;

public class AliasCommand extends Command {

    private String commandToRun;
    private List<String> aliases;
    public AliasCommand(String commandToRun, List<String> aliases) {
        super(aliases.get(0), null, aliases.toArray(new String[0]));

        this.commandToRun = commandToRun;
        this.aliases = aliases;
    }


    @Override
    public void execute(CommandSender commandSender, String[] strings) {
        HashMap<String, List<String>> commands = ConfigManager.getCommands();

        String command = null;
        for (String string : commands.keySet()) {
            if (string.equalsIgnoreCase(commandToRun)) {
                command = string;
                break;
            }
        }

        if (command != null) {
            ProxyServer.getInstance().getPluginManager().dispatchCommand(commandSender, command);
        }
    }
}
