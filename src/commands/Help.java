package commands;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Command that provides information about available commands.
 */
public class Help implements Helpable {

    private static ArrayList<Helpable> commands;
    private static Help instance;

    private Help() {
        commands = new ArrayList<>();
    }

    public static Help getInstance() {
        if (instance == null) {
            instance = new Help();
        }
        return instance;
    }

    /**
     * Displays information about all available commands.
     */
    public static void help() {
        for (Helpable command : commands) {
            System.out.println(command.getClass().getSimpleName());
            System.out.println("\t" + command.getHelp());
        }
    }

    /**
     * Adds commands to the list of supported commands.
     *
     * @param commandArgs array of supporting commands.
     */
    public void addCommand(Helpable... commandArgs) {
        Collections.addAll(commands, commandArgs);
    }

    public String getHelp() {
        return "Returns information about commands.";
    }
}
