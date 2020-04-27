package net.stormmc.JordanPlayz158.config;


import net.stormmc.JordanPlayz158.GBackup;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class GReloadCommand implements CommandExecutor {
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        Reload();
        return false;
    }

    public static void Reload() {
        GBackup.plugin.reloadConfig();
    }
}