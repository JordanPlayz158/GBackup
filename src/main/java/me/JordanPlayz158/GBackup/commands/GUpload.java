package me.JordanPlayz158.GBackup.commands;

import me.JordanPlayz158.GBackup.Utilities;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import javax.annotation.Nonnull;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GUpload implements CommandExecutor {
    public static String name;

    public boolean onCommand(@Nonnull CommandSender commandSender, @Nonnull Command command, @Nonnull String s, @Nonnull String[] strings) {
        try {
            List<String> files = new ArrayList<>();

            for(Object file : Files.list(Paths.get(Utilities.config("backupFolder"))).toArray()) {
                files.add(String.valueOf(file).substring(Utilities.config("backupFolder").length() + 1));
            }

            if(files.size() == 0) {
                commandSender.sendMessage("There are no backups in your backups folder!");
                return false;
            }

            if(strings.length == 0) {
                Collections.sort(files);
                Collections.reverse(files);
                name = files.get(0);
            } else {
                name = strings[0];
            }

            Thread t1 = new Thread(new GoogleUpload());
            t1.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // Need to return true or false when dealing with booleans
        return true;
    }
}