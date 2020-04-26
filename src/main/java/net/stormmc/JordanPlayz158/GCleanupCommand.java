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
public class GCleanupCommand implements CommandExecutor {
    // Defines the class to reference/use when using the prefix "plugin."
    public static GBackup plugin;
    // When the command is used, do the steps below
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        // Run the Cleanup method with required variable pulled from the config
        Cleanup(plugin.getConfig().getInt("DaysBeforeDeletion"));
        // You need to return true or false when using a boolean method
        return false;
    }
    // This method doesn't return anything (void) and requests 1 argument, a int (DaysBeforeDeletion)
    public static void Cleanup(int DaysBeforeDeletion) {
        // Variable for storing the strings/logs
        String s;
        // Variable for storing the process ID
        Process p;
        // Try all the statements in the {} below
        try {
            // Tells the system to run the entered command, while combining argument from the config
            p = Runtime.getRuntime().exec("find backups/* -mtime +" + DaysBeforeDeletion +" -type f -delete");
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
        } catch (Exception e) {}
    }
}
