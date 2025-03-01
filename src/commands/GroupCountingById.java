package commands;

import relatedToTheCollection.Collection;
import relatedToTheCollection.StudyGroup;

import java.util.*;

/**
 * Command that groups study groups by ID and counts the number of elements in each group.
 */
public class GroupCountingById implements Helpable {

    /**
     * Groups study groups by their ID and counts the number of elements in each group.
     */
    public static void groupCountingById() {
        try {
            TreeSet<StudyGroup> studyGroups = Collection.getInstance().getCollection();
            int setSize = studyGroups.size();

            if (setSize == 0) {
                System.out.println("The collection is empty.");
                return;
            }

            int groupCount = (int) Math.ceil(Math.sqrt(setSize));
            List<List<StudyGroup>> groups = new ArrayList<>(groupCount);

            for (int i = 0; i < groupCount; i++) {
                groups.add(new ArrayList<>());
            }

            int index = 0;
            for (StudyGroup studyGroup : studyGroups) {
                groups.get(index / groupCount).add(studyGroup);
                index++;
            }

            groups.removeIf(List::isEmpty);

            System.out.println("There are " + groups.size() + " groups.");
            int lastID = 1;
            for (List<StudyGroup> group : groups) {
                if (!group.isEmpty()) {
                    int groupSize = group.size();
                    int endID = group.getLast().getId();
                    System.out.print("In ID range " + lastID + "-" + (endID + 1) + " - " + groupSize + " elements, ");
                    lastID = endID + 1;
                }
            }
            System.out.println();

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    @Override
    public String getHelp() {
        return "Groups study groups by their ID and counts the number of elements in each group.";
    }
}
