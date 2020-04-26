package net.stormmc.JordanPlayz158;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class GCleanupCommand extends JavaPlugin implements CommandExecutor {
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        FileConfiguration config = getConfig();
        Cleanup(config.getInt("DaysBeforeDeletion"));
        return false;
    }

    public static void Cleanup(int DaysBeforeDeletion) {
        String s;
        Process p;
        try {
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
