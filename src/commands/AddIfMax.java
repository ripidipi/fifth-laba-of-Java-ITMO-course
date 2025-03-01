package commands;


import exeptions.InsufficientNumberOfArguments;
import inputOutput.PrimitiveDataTransform;
import relatedToTheCollection.Collection;
import relatedToTheCollection.StudyGroup;

import java.util.TreeSet;

/**
 * Command that adds a study group to the collection only if it is the largest.
 */
public class AddIfMax  implements Helpable {

    /**
     * Adds a new study group if it is the maximum in the collection.
     */
    public static void addStudyGroupIfMax() {
        Integer id = PrimitiveDataTransform.input("id", Integer.class);
        StudyGroup studyGroup = StudyGroup.input(id.toString());
        if (isMax(studyGroup)) {
            Collection.getInstance().addElement(studyGroup);
            System.out.println("Study group added successfully.");
        } else {
            System.out.println("The study group is not the maximum and was not added.");
        }
    }

    /**
     * Checks if the given study group is the maximum in the collection.
     *
     * @param studyGroup The study group to compare.
     * @return {@code true} if the study group is the largest; {@code false} otherwise.
     */
    private static boolean isMax(StudyGroup studyGroup) {
        try {
            TreeSet<StudyGroup> collection = Collection.getInstance().getCollection();
            if (collection.isEmpty()) {
                return true; // If collection is empty, any element is the max.
            }
            StudyGroup maxStudyGroup = collection.last();
            return maxStudyGroup.compareTo(studyGroup) < 0;
        } catch (Exception e) {
            System.out.println("Error checking max: " + e.getMessage());
            return false;
        }
    }

    /**
     * Adds a study group from file input if it is the maximum in the collection.
     *
     * @param input A string containing study group parameters separated by commas.
     * @throws InsufficientNumberOfArguments if the provided input has insufficient arguments.
     */
    public static void addStudyGroupIfMaxFromFile(String input) {
        try {
            String[] inputSplit = input.split(",");
            if (inputSplit.length != 11) {
                throw new InsufficientNumberOfArguments("AddIfMax");
            }
            StudyGroup studyGroup = StudyGroup.inputFromFile(inputSplit, true);
            if (studyGroup != null && isMax(studyGroup)) {
                Collection.getInstance().addElement(studyGroup);
                System.out.println("Study group added successfully from file.");
            } else {
                System.out.println("The study group is not the maximum and was not added.");
            }
        } catch (Exception e) {
            System.out.println("Error adding from file: " + e.getMessage());
        }
    }

    @Override
    public String getHelp() {
        return "Adds a new element to the collection if the element larger than the maximum in collection.";
    }
}


