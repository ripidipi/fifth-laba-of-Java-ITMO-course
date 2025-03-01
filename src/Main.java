import commands.*;
import inputOutput.CommandsInput;
import inputOutput.FillCollectionFromFile;
import inputOutput.RunningFiles;
import relatedToTheCollection.Collection;

/**
 * The entry point of the application.
 */
public class Main {

    public static void main(String[] args) {
        initializeApplication();
        runCommandLoop();
    }

    /**
     * Initializes the application by loading necessary components and registering commands.
     */
    private static void initializeApplication() {
        Collection collection = Collection.getInstance();
        RunningFiles runningFiles = RunningFiles.getInstance();
        Help help = Help.getInstance();

        help.addCommand(
                help, new Add(), new Info(), new Show(),
                new Update(), new Exit(), new Save(),
                new AddIfMax(), new Clear(), new CountByGroupAdmin(),
                new ExecuteScript(), new GroupCountingById(),
                new RemoveAnyByGroupAdmin(), new RemoveById(),
                new RemoveGreater(), new RemoveLower()
        );

        try {
            FillCollectionFromFile.fillCollectionFromFile();
        } catch (Exception e) {
            System.out.println("Error loading collection from file: " + e.getMessage());
        }
    }

    /**
     * Runs the command input loop, continuously waiting for user input.
     */
    private static void runCommandLoop() {
        while (true) {
            System.out.print("Enter the command: ");
            CommandsInput.inputFromConsole();
        }
    }
}
