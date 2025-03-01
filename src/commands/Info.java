package commands;

import relatedToTheCollection.Collection;

/**
 * Command that provides information about the collection.
 */
public class Info implements Helpable {

    /**
     * Prints information about the collection (type, initialization date, number of elements).
     */
    public static void info() {
        System.out.println(Collection.getInstance().getInfo());
    }

    @Override
    public String getHelp() {
        return "Returns information about the collection (type, initialization date, number of elements).";
    }
}
