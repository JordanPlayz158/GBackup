package net.stormmc.JordanPlayz158.commands;

import net.stormmc.JordanPlayz158.GBackup;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class GBackupCommand implements CommandExecutor {
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        // Run Backup Method with CompressionLevel and ServerName value from config.yml
        Backup(GBackup.plugin.getConfig().getInt("CompressionLevel"), GBackup.plugin.getConfig().getString("ServerName"));
        // Need to return true or false when dealing with booleans
        return false;
    }

    public static void Backup(int CompressionLevel, String ServerName) {
        // Variable for storing the strings/logs
        String s;
        // Variable for storing the process ID
        Process p;
        try {
            // Tells the system to run the command
            p = Runtime.getRuntime().exec("env GZIP=-" + CompressionLevel + " tar cvzf backups/" + ServerName + "-$( date '+%Y-%m-%d_%H-%M-%S' ).tar.gz world");
            BufferedReader br = new BufferedReader(
                    new InputStreamReader(p.getInputStream()));
            while ((s = br.readLine()) != null)
                System.out.println("line: " + s);
            p.waitFor();
            System.out.println ("exit: " + p.exitValue());
            p.destroy();
        } catch (Exception e) {}
    }
}
