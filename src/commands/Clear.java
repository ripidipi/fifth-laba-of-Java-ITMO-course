package commands;

import relatedToTheCollection.Collection;

/**
 * Command that clears the entire collection.
 */
public class Clear implements Helpable {

    /**
     * Clears all elements from the collection.
     */
    public static void clearCollection() {
        Collection.getInstance().clearCollection();
        System.out.println("The collection has been cleared.");
    }

    @Override
    public String getHelp() {
        return "Clears the collection.";
    }
}
