package net.stormmc.JordanPlayz158.config;

import net.stormmc.JordanPlayz158.GBackup;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class GReloadCommand implements CommandExecutor {
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        // Run Reload Method
        Reload();
        // Need to return true or false when dealing with booleans
        return false;
    }

    public static void Reload() {
        // Reloads config.yml
        GBackup.plugin.reloadConfig();
    }
}