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

public class CommandsManager {

    private static Configuration configuration;

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

        configuration = ConfigurationProvider.getProvider(YamlConfiguration.class).load(new File(sap.getDataFolder(), "config.yml"));
    }

    public static HashMap<String, String> getCommands() {
        /*
        HashMap is set up like this:
        key = what the command alias is
        value = what the command alias will run
         */
        HashMap<String, String> commands = new HashMap<>();

        for (String alias : configuration.getSection("commands.").getKeys()) {
            commands.put(alias, configuration.getString("commands." + alias));
        }

        return commands;
    }

    public static void saveConfig(SimpleAliasPlugin sap) {
        try {
            ConfigurationProvider.getProvider(YamlConfiguration.class).save(configuration, new File(sap.getDataFolder(), "config.yml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }



}
