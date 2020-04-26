package net.stormmc.JordanPlayz158;

import org.bukkit.plugin.java.JavaPlugin;

public class GBackup extends JavaPlugin {
    public void onEnable() {
        saveDefaultConfig();
        getCommand("gbackup").setExecutor(new GBackupCommand());
        getCommand("gcleanup").setExecutor(new GCleanupCommand());
        System.out.println("You have successfully enabled Backup v" + getDescription().getVersion() + " by JordanPlayz158");
    }
    public void onDisable() {
        System.out.println("You have successfully disabled Backup v" + getDescription().getVersion() + " by JordanPlayz158");
    }
}
