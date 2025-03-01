package inputOutput;

import commands.Add;

/**
 * A utility class to populate a collection from a file.
 */
public class FillCollectionFromFile {

    /**
     * Reads a collection from a file and populates it.
     * The file name is predefined as "collection.csv".
     * If an error occurs, the method prints an error message.
     */
    public static void fillCollectionFromFile() {
        try {
//            String fileName = System.getenv("CSV_FILE_NAME");
            String fileName = ("collection.csv");
            CommandsInput.inputFromFile(fileName, FillCollectionFromFile::adderForFill);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Processes an input line from the file and adds a study group if the ID is valid.
     *
     * @param input An array of strings representing the fields of a study group.
     * @return Always returns null.
     */
    public static Void adderForFill(String[] input) {
        try {
            if (PrimitiveDataTransform.inputFromFile("Id", input[0], Integer.class, false,
                    false, false, null, true) == null) {
                return null;
            }
            Add.addStudyGroupFromFile(String.join(",", input));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
