package me.JordanPlayz158.GBackup.commands;

import me.JordanPlayz158.GBackup.Main;
import me.JordanPlayz158.GBackup.Utilities;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class GReload implements CommandExecutor {
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        // Reloads config.yml
        try {
            Main.plugin.reloadConfig();
            System.out.println(Utilities.pluginPrefix() + " config.yml has been loaded successfully!");
            // Need to return true or false when dealing with booleans
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            // Need to return true or false when dealing with booleans
            return false;
        }
    }
}