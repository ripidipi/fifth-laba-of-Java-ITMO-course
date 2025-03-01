package commands;

import exeptions.IncorrectValue;
import exeptions.InfiniteRecursion;
import inputOutput.CommandsInput;
import inputOutput.RunningFiles;

/**
 * Command that executes a script from a specified file.
 */
public class ExecuteScript implements Helpable {

    /**
     * Executes a script from the given file.
     *
     * @param fileName The name of the script file to execute.
     * @throws IncorrectValue If the file name is empty.
     * @throws InfiniteRecursion If the script is already being executed (to prevent recursion).
     */
    public static void executeScript(String fileName) {
        try {
            if (fileName == null || fileName.isEmpty()) {
                throw new IncorrectValue("ExecuteScript: File name cannot be empty.");
            }

            if (RunningFiles.getInstance().isThere(fileName.toUpperCase())) {
                throw new InfiniteRecursion("ExecuteScript: Infinite recursion detected with file: " + fileName);
            }

            RunningFiles.getInstance().addFileName(fileName.toUpperCase());
            CommandsInput.inputFromFile(fileName, CommandsInput::isCommand);
            RunningFiles.getInstance().removeFileName(fileName.toUpperCase());

            System.out.println("Script executed successfully from file: " + fileName);
        } catch (Exception e) {
            System.out.println("Error executing script: " + e.getMessage());
        }
    }

    @Override
    public String getHelp() {
        return "Executes a script from the specified file.";
    }
}
