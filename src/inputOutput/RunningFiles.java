package inputOutput;

import java.util.HashSet;
import java.util.Set;

/**
 * Singleton class to manage running files.
 */
public class RunningFiles {

    private Set<String> filesNames = new HashSet<>();
    private static RunningFiles instance;

    private RunningFiles() {}

    /**
     * Returns the singleton instance of RunningFiles.
     *
     * @return the singleton instance
     */
    public static RunningFiles getInstance() {
        if (instance == null) {
            instance = new RunningFiles();
        }
        return instance;
    }

    /**
     * Returns the set of file names.
     *
     * @return the set of file names
     */
    public Set<String> getFilesNames() {
        return filesNames;
    }

    /**
     * Adds a file name to the set.
     *
     * @param fileName the file name to add
     */
    public void addFileName(String fileName) {
        this.filesNames.add(fileName);
    }

    /**
     * Checks if the file is present in the set of running files.
     *
     * @param fileName the file name to check
     * @return true if the file is present, false otherwise
     */
    public boolean isThere(String fileName) {
        return filesNames.contains(fileName);
    }

    /**
     * Removes a file name from the set.
     *
     * @param fileName the file name to remove
     */
    public void removeFileName(String fileName) {
        this.filesNames.remove(fileName);
    }
}
