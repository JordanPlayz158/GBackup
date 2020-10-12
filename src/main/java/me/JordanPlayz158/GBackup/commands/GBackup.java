package me.JordanPlayz158.GBackup.commands;

import me.JordanPlayz158.GBackup.Main;
import me.JordanPlayz158.GBackup.Utilities;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class GBackup implements CommandExecutor {
    public static String zipName = "";
    public static File folderToBackup = new File("");
    public static List<File> filesToExclude = Arrays.asList(new File(""));
    public static CommandSender sender;

    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        sender = commandSender;

        String backupFolder = Utilities.config("BackupFolder");
        String serverName = Utilities.config("ServerName");
        String dateFormat = Utilities.config("DateFormat");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat);
        String date = simpleDateFormat.format(new Date());
        String archiveName = serverName + "-" + date + ".zip";
        zipName = backupFolder + "/" + archiveName;
        folderToBackup = new File(Utilities.config("FolderToBackup"));
        filesToExclude = new ArrayList<>();

        filesToExclude.add(new File("./" + backupFolder));

        for(Object item : Main.plugin.getConfig().getList("itemsToExclude")) {
            filesToExclude.add(new File("./" + item.toString()));
        }

        if(args.length == 1) {
            zipName = args[0];
        }

        Thread t1 = new Thread(new CreateZip());
        t1.start();
        // Need to return true or false when dealing with booleans
        return true;
    }
}
