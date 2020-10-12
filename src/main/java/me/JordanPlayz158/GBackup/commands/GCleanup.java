package me.JordanPlayz158.GBackup.commands;

import me.JordanPlayz158.GBackup.Utilities;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.io.File;
import java.util.Calendar;
import java.util.TimeZone;

public class GCleanup implements CommandExecutor {
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        String timeBeforeDeletion = Utilities.config("timeBeforeDeletion");

        File directory = new File(Utilities.config("backupFolder"));

        File[] listFiles = directory.listFiles();

        if(listFiles.length == 0) {
            commandSender.sendMessage(Utilities.pluginPrefix() + " No files exist in the directory");
            return false;
        }

        Calendar c = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        int time = -Integer.parseInt(timeBeforeDeletion.substring(0, timeBeforeDeletion.length() - 1));
        switch(timeBeforeDeletion.substring(timeBeforeDeletion.length() - 1)) {
            case "s":
                c.add(Calendar.SECOND, time);
                break;
            case "m":
                c.add(Calendar.MINUTE, time);
                break;
            case "h":
                c.add(Calendar.HOUR, time);
                break;
            case "d":
                c.add(Calendar.DAY_OF_MONTH, time);
                break;
            case "w":
                c.add(Calendar.WEEK_OF_MONTH, time);
                break;
            case "M":
                c.add(Calendar.MONTH, time);
                break;
            case "y":
                c.add(Calendar.YEAR, time);
                break;
        }

        long purgeTime = c.getTimeInMillis();

        for(File listFile : listFiles) {
            if (listFile.lastModified() < purgeTime) {
                if (!listFile.delete()) {
                    commandSender.sendMessage(Utilities.pluginPrefix() + " Unable to delete file: " + listFile);
                }
            }
        }
        commandSender.sendMessage(Utilities.pluginPrefix() + " Backups (older than " + timeBeforeDeletion + ") have been deleted!");

        // Need to return true or false when dealing with booleans
        return false;
    }
}
