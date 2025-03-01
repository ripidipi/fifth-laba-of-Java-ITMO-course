package commands;

import relatedToTheCollection.Collection;
import relatedToTheCollection.StudyGroup;

import java.util.Iterator;
import java.util.Objects;
import java.util.TreeSet;

/**
 * Command that removes a study group by its ID.
 */
public class RemoveById implements Helpable {

    /**
     * Removes a study group with the given ID from the collection.
     *
     * @param id The ID of the study group to remove.
     */
    public static void removeById(String id) {
        try {
            int ID = Integer.parseInt(id);
            TreeSet<StudyGroup> collection = Collection.getInstance().getCollection();
            Iterator<StudyGroup> iterator = collection.iterator();
            while (iterator.hasNext()) {
                StudyGroup studyGroup = iterator.next();
                if (Objects.equals(studyGroup.getId(), ID)) {
                    iterator.remove();
                    break;
                }
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid ID format: " + id);
        }
    }

    @Override
    public String getHelp() {
        return "Removes a study group by its ID.";
    }
}
