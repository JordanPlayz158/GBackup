// The Directory in which the class file resides
package net.stormmc.JordanPlayz158;

// Required for implements Listener
import net.stormmc.JordanPlayz158.commands.GBackupCommand;
import net.stormmc.JordanPlayz158.commands.GCleanupCommand;
import net.stormmc.JordanPlayz158.config.GReloadCommand;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

// Required for extends JavaPlugin

// Defines the class and what it extends or implements
public class GBackup extends JavaPlugin implements Listener {
    public static GBackup plugin;
    // When the plugin loads/enables
    @Override
    public void onEnable() {
        // Checks if backups folder exists in root directory
        new File("backups").mkdirs();
        // Defines what "plugin." goes to
        plugin = this;
        // If config.yml doesn't exist, save config.yml to plugins/PluginName/config.yml
        saveDefaultConfig();
        // Registers the commands and references which classes to go to when each command is executed
        getCommand("gbackup").setExecutor(new GBackupCommand());
        getCommand("gcleanup").setExecutor(new GCleanupCommand());
        getCommand("greload").setExecutor(new GReloadCommand());
        // Prints out a message containing the plugin enabled (name), version, and author
        System.out.println("You have successfully enabled " + getDescription().getName() + " v" + getDescription().getVersion() + " by JordanPlayz158");
    }
    // When the plugin unloads/disables
    @Override
    public void onDisable() {
        // Prints out a message containing the plugin disabled (name), version, and author
        System.out.println("You have successfully disabled " + getDescription().getName() + " v" + getDescription().getVersion() + " by JordanPlayz158");
    }
}
