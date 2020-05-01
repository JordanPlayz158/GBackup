package net.stormmc.JordanPlayz158.commands;

import net.stormmc.JordanPlayz158.GBackup;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.io.File;

public class GCleanupCommand implements CommandExecutor {
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        // Run Cleanup method with DaysBeforeDeletion value from config.yml
        Cleanup(GBackup.plugin.getConfig().getInt("DaysBeforeDeletion"));
        // Need to return true or false when dealing with booleans
        return false;
    }

    public static void Cleanup(int DaysBeforeDeletion) {
        File directory = new File("backups/");
        if(directory.exists()){

            File[] listFiles = directory.listFiles();
            long purgeTime = System.currentTimeMillis() - (DaysBeforeDeletion * 24 * 60 * 60 * 1000);
            for(File listFile : listFiles) {
                if(listFile.lastModified() < purgeTime) {
                    if(!listFile.delete()) {
                        System.err.println("Unable to delete file: " + listFile);
                    }
                }
            }
        }
    }
}
