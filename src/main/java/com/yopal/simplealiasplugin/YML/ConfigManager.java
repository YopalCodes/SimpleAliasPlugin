package com.yopal.simplealiasplugin.YML;

import com.yopal.simplealiasplugin.SimpleAliasPlugin;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

public class ConfigManager {

    private static Configuration config;

    public static void loadConfig(SimpleAliasPlugin sap) throws IOException {
        config = ConfigurationProvider.getProvider(YamlConfiguration.class).load(new File(sap.getDataFolder(), "config.yml"));
    }

    public static HashMap<String, String> getCommandsToRun() {
        /*
        key = actual command to run (/server Lobby)
        value = aliases
         */

        HashMap<String, String> commands = new HashMap<>();

        for (String key : config.getSection("commands").getKeys()) {
            commands.put(key, config.getString("commands." + key + ".commandToRun"));
        }

        return commands;
    }

    public static HashMap<String, List<String>> getAliases(SimpleAliasPlugin sap) {
        /*
        key = main command (/lts)
        value = aliases
         */

        HashMap<String, List<String>> commands = new HashMap<>();

        for (String key : config.getSection("commands").getKeys()) {
            commands.put(key, config.getStringList("commands." + key + ".aliases"));
        }

        return commands;
    }

    public static HashMap<String, List<String>> getArguments(String mainCommand) {
        /*
        key = args position
        value = args
         */
        HashMap<String, List<String>> arguments = new HashMap<>();

        for (String key : config.getSection("commands." + mainCommand + ".tab").getKeys()) {
            arguments.put(key, config.getStringList("commands." + mainCommand + ".tab." + key));
        }

        return arguments;
    }

    public static HashMap<String, String> getPermissions() {
        /*
        key = main command (/lts)
        value = permission
         */

        HashMap<String, String> permissions = new HashMap<>();

        for (String key : config.getSection("commands").getKeys()) {
            permissions.put(key, config.getString("commands." + key + ".permission"));
        }

        return permissions;
    }

    public static void makeConfig(SimpleAliasPlugin sap) throws IOException {
        // Create plugin config folder if it doesn't exist
        if (!sap.getDataFolder().exists()) {
            sap.getLogger().info("Created config folder: " + sap.getDataFolder().mkdir());
        }

        File configFile = new File(sap.getDataFolder(), "config.yml");

        // Copy default config if it doesn't exist
        if (!configFile.exists()) {
            FileOutputStream outputStream = new FileOutputStream(configFile); // Throws IOException
            InputStream in = sap.getResourceAsStream("config.yml"); // This file must exist in the jar resources folder
            in.transferTo(outputStream); // Throws IOException
        }

        loadConfig(sap);
    }
}
