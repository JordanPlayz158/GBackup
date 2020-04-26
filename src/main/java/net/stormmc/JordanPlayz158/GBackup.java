// The Directory in which the class file resides
package net.stormmc.JordanPlayz158;

// Required for implements Listener
import org.bukkit.event.Listener;
// Required for extends JavaPlugin
import org.bukkit.plugin.java.JavaPlugin;

// Defines the class and what it extends or implements
public class GBackup extends JavaPlugin implements Listener {
    // When the plugin loads/enables
    public void onEnable() {
        // If config.yml doesn't exist, save config.yml to plugins/PluginName/config.yml
        saveDefaultConfig();
        // Registers the commands and references which classes to go to when each command is executed
        getCommand("gbackup").setExecutor(new GBackupCommand());
        getCommand("gcleanup").setExecutor(new GCleanupCommand());
        // Prints out a message containing the plugin enabled (name), version, and author
        System.out.println("You have successfully enabled " + getDescription().getName() + " v" + getDescription().getVersion() + " by JordanPlayz158");
    }
    // When the plugin unloads/disables
    public void onDisable() {
        // Prints out a message containing the plugin disabled (name), version, and author
        System.out.println("You have successfully disabled " + getDescription().getName() + " v" + getDescription().getVersion() + " by JordanPlayz158");
    }
}
