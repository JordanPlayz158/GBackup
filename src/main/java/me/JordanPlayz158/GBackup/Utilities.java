package me.JordanPlayz158.GBackup;

public class Utilities {
    public static String pluginPrefix() {
        return "[" + Main.plugin.getName() + "]";
    }

    public static String config(String variableName) {
        return Main.plugin.getConfig().getString(variableName);
    }
}
