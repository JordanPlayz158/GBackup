package me.JordanPlayz158.GBackup;

import me.JordanPlayz158.GBackup.commands.GBackup;
import me.JordanPlayz158.GBackup.commands.GCleanup;
import me.JordanPlayz158.GBackup.commands.GReload;
import me.JordanPlayz158.GBackup.commands.GUpload;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.Objects;

public class Main extends JavaPlugin implements Listener {
    public static Main plugin;

    @Override
    public void onEnable() {
        plugin = this;
        // If config.yml doesn't exist, save config.yml to plugins/<PluginName>/config.yml
        saveDefaultConfig();
        // Checks if backups folder exists in root directory
        new File(Utilities.config("backupFolder")).mkdir();
        // Registers the commands and references which classes to go to when each command is executed
        Objects.requireNonNull(getCommand("gbackup")).setExecutor(new GBackup());
        Objects.requireNonNull(getCommand("gcleanup")).setExecutor(new GCleanup());
        Objects.requireNonNull(getCommand("greload")).setExecutor(new GReload());
        Objects.requireNonNull(getCommand("gupload")).setExecutor(new GUpload());
    }

    @Override
    public void onDisable() {

    }
}
