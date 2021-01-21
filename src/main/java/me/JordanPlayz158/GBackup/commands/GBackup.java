package me.JordanPlayz158.GBackup.commands;

import me.JordanPlayz158.GBackup.Main;
import me.JordanPlayz158.GBackup.Utilities;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import javax.annotation.Nonnull;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class GBackup implements CommandExecutor {
    public static String zipName = "";
    public static File folderToBackup = new File("");
    public static List<File> filesToExclude = new ArrayList<>();
    public static CommandSender sender;

    public boolean onCommand(@Nonnull CommandSender commandSender, @Nonnull Command command, @Nonnull String s, @Nonnull String[] args) {
        sender = commandSender;

        String backupFolder = Utilities.config("backupFolder");
        String serverName = Utilities.config("serverName");
        String dateFormat = Utilities.config("dateFormat");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat);
        String date = simpleDateFormat.format(new Date());
        String archiveName = serverName + "-" + date + ".zip";
        zipName = backupFolder + "/" + archiveName;
        folderToBackup = new File(Utilities.config("folderToBackup"));

        filesToExclude.add(new File("./" + backupFolder));

        for(Object item : Objects.requireNonNull(Main.plugin.getConfig().getList("itemsToExclude"))) {
            filesToExclude.add(new File("./" + item.toString()));
        }

        if(args.length == 1) {
            zipName = "backups/" + args[0] + ".zip";
        }

        Thread t1 = new Thread(new CreateZip());
        t1.start();
        // Need to return true or false when dealing with booleans
        return true;
    }
}
