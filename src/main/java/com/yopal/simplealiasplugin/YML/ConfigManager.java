package com.yopal.simplealiasplugin.YML;

import com.yopal.simplealiasplugin.SimpleAliasPlugin;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;

public class ConfigManager {

    private static Configuration config;

    public static void loadConfig(SimpleAliasPlugin sap) throws IOException {
        config = ConfigurationProvider.getProvider(YamlConfiguration.class).load(new File(sap.getDataFolder(), "config.yml"));
    }

    public static void saveConfig(SimpleAliasPlugin sap) throws IOException {
        ConfigurationProvider.getProvider(YamlConfiguration.class).save(config, new File(sap.getDataFolder(), "config.yml"));
    }

    public static HashMap<String, List<String>> getCommands(SimpleAliasPlugin sap) {
        /*
        key = actual command
        value = aliases
         */

        HashMap<String, List<String>> commands = new HashMap<>();

        for (String key : config.getSection("commands").getKeys()) {
            sap.getLogger().info(key);
            commands.put(config.getString("commands." + key + "commandToRun"), config.getStringList("commands." + key + ".aliases"));
        }

        return commands;
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
