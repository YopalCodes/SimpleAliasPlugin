package com.yopal.simplealiasplugin.commands;

import com.google.common.collect.ImmutableSet;
import com.yopal.simplealiasplugin.SimpleAliasPlugin;
import com.yopal.simplealiasplugin.YML.ConfigManager;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;
import net.md_5.bungee.api.plugin.TabExecutor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;

public class AliasCommand extends Command implements TabExecutor {

    private String command;

    public AliasCommand(String command, String permission, List<String> aliases) {
        super(command, permission, aliases.toArray(new String[0]));

        this.command = command;
    }

    @Override
    public void execute(CommandSender commandSender, String[] strings) {

        HashMap<String, String> commands = ConfigManager.getCommandsToRun();

        for (String key : commands.keySet()) {

            if (key.equalsIgnoreCase(command)) {

                String commandToRun = commands.get(command);
                for (String arg : strings) {
                    commandToRun += " " + arg;
                }

                ProxyServer.getInstance().getPluginManager().dispatchCommand(commandSender, commandToRun);
            }

        }

    }

    @Override
    public Iterable<String> onTabComplete(CommandSender commandSender, String[] strings) {

        HashMap<String, List<String>> arguments = ConfigManager.getArguments(command);

        List<String> matches = new ArrayList<>();

        for (String argPos : arguments.keySet()) {
            if (arguments.get(argPos).contains("<@player>")) {

                String arg = strings[Integer.parseInt(argPos.replace("args", "")) - 1];

                for (ProxiedPlayer player : ProxyServer.getInstance().getPlayers()) {
                    if (player.getName().contains(arg)) {
                        matches.add(player.getName());
                    }
                }

                return matches;

            } else if (argPos.contains(strings.length + "")) {
                return arguments.get(argPos);
            }
        }

        return ImmutableSet.of();
    }
}
