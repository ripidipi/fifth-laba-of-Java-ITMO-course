package commands;

import exeptions.InsufficientNumberOfArguments;
import inputOutput.PrimitiveDataTransform;
import relatedToTheCollection.Collection;
import relatedToTheCollection.StudyGroup;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.TreeSet;

/**
 * Command that removes study groups greater than a given one from the collection.
 */
public class RemoveGreater implements Helpable{

    /**
     * Removes study groups greater than the specified one, based on user input.
     */
    public static void removeGreater() {
        Integer id = PrimitiveDataTransform.input("id", Integer.class);
        StudyGroup studyGroup = StudyGroup.input(id.toString());
        try {
            removeGreaterFromCollection(studyGroup);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Removes study groups greater than the specified one, based on input from a file.
     *
     * @param input The input string containing the data to create the study group.
     */
    public static void removeGreaterFromFile(String input) {
        try {
            String[] inputSplit = input.split(",");
            if (inputSplit.length != 11) {
                throw new InsufficientNumberOfArguments("");
            }
            StudyGroup studyGroup = StudyGroup.inputFromFile(inputSplit, true);
            if (studyGroup == null) return;
            removeGreaterFromCollection(studyGroup);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Removes study groups greater than the given one from the collection.
     *
     * @param studyGroup The study group to compare with.
     */
    private static void removeGreaterFromCollection(StudyGroup studyGroup) {
        TreeSet<StudyGroup> collection = Collection.getInstance().getCollection();
        ArrayList<StudyGroup> toRemove = new ArrayList<>();
        for (StudyGroup sG : collection) {
            if(sG.compareTo(studyGroup) > 0) {
                toRemove.add(sG);
            }
        }
        for (StudyGroup sG : toRemove) {
            collection.remove(sG);
        }
    }

    @Override
    public String getHelp() {
        return "Removes all study groups greater than the specified one.";
    }
}
