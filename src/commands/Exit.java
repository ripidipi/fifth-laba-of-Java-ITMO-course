package commands;

/**
 * Command that exits the program.
 */
public class Exit implements Helpable {

    /**
     * Exits the program with a status code of 0 (successful termination).
     */
    public static void exit() {
        System.out.println("Exiting the program.");
        System.exit(0);
    }

    @Override
    public String getHelp() {
        return "Exits the program.";
    }
}
