package me.JordanPlayz158.GBackup.commands;

import me.JordanPlayz158.GBackup.Utilities;
import net.lingala.zip4j.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.model.ExcludeFileFilter;
import net.lingala.zip4j.model.ZipParameters;
import org.bukkit.command.CommandSender;

import java.io.File;
import java.util.List;

public class CreateZip implements Runnable {
    @Override
    public void run() {
        try {
            createZip(GBackup.zipName, GBackup.folderToBackup, GBackup.filesToExclude, GBackup.sender);
        } catch (ZipException e) {
            e.printStackTrace();
        }
    }

    public static void createZip(String zipName, File directoryToCompress, List<File> filesToExclude, CommandSender commandSender) throws ZipException {
        ExcludeFileFilter excludeFileFilter = filesToExclude::contains;
        ZipParameters zipParameters = new ZipParameters();
        zipParameters.setExcludeFileFilter(excludeFileFilter);

        new ZipFile(zipName).addFolder(directoryToCompress, zipParameters);

        commandSender.sendMessage(Utilities.pluginPrefix() + " Backup Complete");
    }
}
