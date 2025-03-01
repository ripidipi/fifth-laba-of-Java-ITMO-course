package commands;

import relatedToTheCollection.Collection;

/**
 * Command that saves the collection data to a file.
 */
public class Save implements Helpable {

    /**
     * Saves the collection data to a file.
     */
    public static void save() {
        Collection.output();
    }

    public String getHelp() {
        return "Save the collection data to a file";
    }
}
