package commands;

import exeptions.InsufficientNumberOfArguments;
import inputOutput.PrimitiveDataTransform;
import relatedToTheCollection.Collection;
import relatedToTheCollection.StudyGroup;

import java.util.Objects;
import java.util.TreeSet;

/**
 * Command that updates a study group in the collection by its ID.
 */
public class Update implements Helpable {

    /**
     * Updates a study group based on user input.
     *
     * @param id The ID of the study group to be updated.
     */
    public static void update(String id) {
        Integer parsedId = validateId(id);
        if (parsedId == null) {
            System.out.println("Invalid input. Please provide a valid ID.");
            return;
        }
        StudyGroup studyGroup = StudyGroup.input(id);
        replacementInTheCollection(studyGroup);
    }

    /**
     * Updates a study group based on data from a file.
     *
     * @param input The input string containing information to create the study group.
     */
    public static void updateFromFile(String input) {
        try {
            String[] inputSplit = input.split(",");
            if (inputSplit.length != 11) {
                throw new InsufficientNumberOfArguments("Update");
            }
            StudyGroup studyGroup = StudyGroup.inputFromFile(inputSplit, true);
            replacementInTheCollection(studyGroup);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Validates and parses the ID input.
     *
     * @param id The ID as a string.
     * @return The parsed ID, or null if invalid.
     */
    private static Integer validateId(String id) {
        return PrimitiveDataTransform.transformToBasicType("id", Integer.class, true,
                true, false, id, false, null, true);
    }

    /**
     * Replaces an existing study group in the collection with a new one.
     *
     * @param studyGroup The updated study group.
     */
    private static void replacementInTheCollection(StudyGroup studyGroup) {
        TreeSet<StudyGroup> collection = Collection.getInstance().getCollection();
        for (StudyGroup sG : collection) {
            if (Objects.equals(sG.getId(), studyGroup.getId())) {
                Collection.getInstance().removeElement(sG);
                Collection.getInstance().addElement(studyGroup);
                break;
            }
        }
    }

    @Override
    public String getHelp() {
        return "Updates an existing study group by its ID. You can update study groups either through user input or by loading data from a file.";
    }
}
