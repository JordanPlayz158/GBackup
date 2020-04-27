// The Directory in which the class file resides
package net.stormmc.JordanPlayz158;

// Required for using Command
import org.bukkit.command.Command;
// Required for implements CommandExecutor
import org.bukkit.command.CommandExecutor;
// Required for using CommandSender
import org.bukkit.command.CommandSender;

// Required for using BufferedReader
import java.io.BufferedReader;
// Required for using InputStreamReader
import java.io.InputStreamReader;

// Defines the class and what it extends or implements
public class GBackupCommand implements CommandExecutor {
    // When the command is used, do the steps below
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        System.out.println(GBackup.plugin.getConfig().getInt("CompressionLevel"));
        System.out.println(GBackup.plugin.getConfig().getString("ServerName"));
        // Run the Backup method with required variables pulled from the config
        Backup(GBackup.plugin.getConfig().getInt("CompressionLevel"), GBackup.plugin.getConfig().getString("ServerName"));
        // You need to return true or false when using a boolean method
        return false;
    }
    // This method doesn't return anything (void) and requests 2 arguments, a int (CompressionLevel) and a String (ServerName)
    public static void Backup(int CompressionLevel, String ServerName) {
        // Variable for storing the strings/logs
        String s;
        // Variable for storing the process ID
        Process p;
        // Try all the statements in the {} below
        try {
            // Tells the system to run the entered command, while combining arguments from the config
            p = Runtime.getRuntime().exec("tar cvf - -C world/ . | gzip -" + CompressionLevel +" - > backups/" + ServerName + "-$(date +\"\\%Y_\\%m_\\%d_\\%I_\\%M_\\%p\").tar.gz");
            // References BufferedReader as br and initializes a new BufferedReader
            BufferedReader br = new BufferedReader(
                    // Makes a new InputStreamReader to read the output of the executed command
                    new InputStreamReader(p.getInputStream()));
            // While lines are still going/being read, send do the command(s) below
            while ((s = br.readLine()) != null)
                // Send the output/results of the command executed
                System.out.println("line: " + s);
            // Wait for the process to complete before exiting
            p.waitFor();
            // Prints out the exit value
            System.out.println ("exit: " + p.exitValue());
            // Destroy the process when it ends/is done
            p.destroy();
        // Catch any errors
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
