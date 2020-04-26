package net.stormmc.JordanPlayz158;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class GBackupCommand extends JavaPlugin implements CommandExecutor {
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        FileConfiguration config = getConfig();
        Backup(config.getInt("CompressionLevel"), config.getString("ServerName"));
        return false;
    }

    public static void Backup(int CompressionLevel, String ServerName) {
        String s;
        Process p;
        try {
            p = Runtime.getRuntime().exec("tar cvf - -C world/ . | gzip -" + CompressionLevel +" - > backups/" + ServerName + "-$(date +\"\\%Y_\\%m_\\%d_\\%I_\\%M_\\%p\").tar.gz");
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
