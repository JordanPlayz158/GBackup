package net.stormmc.JordanPlayz158.commands;

import net.stormmc.JordanPlayz158.GBackup;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class GCleanupCommand implements CommandExecutor {
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        // Run Cleanup method with DaysBeforeDeletion value from config.yml
        Cleanup(GBackup.plugin.getConfig().getInt("DaysBeforeDeletion"));
        // Need to return true or false when dealing with booleans
        return false;
    }

    public static void Cleanup(int DaysBeforeDeletion) {
        // Variable for storing the strings/logs
        String s;
        // Variable for storing the process ID
        Process p;
        try {
            // Tells the system to run the command
            p = Runtime.getRuntime().exec("find backups/* -mtime +" + DaysBeforeDeletion +" -type f -delete");
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
