package commands;


import exeptions.InsufficientNumberOfArguments;
import relatedToTheCollection.Collection;
import relatedToTheCollection.StudyGroup;

/**
 * Command for adding study groups to the collection.
 */
public class Add implements Helpable {

    /**
     * Adds a new study group based on user input.
     */
    public static void addStudyGroup() {
        StudyGroup studyGroup = StudyGroup.input();
        Collection.getInstance().addElement(studyGroup);
    }

    /**
     * Adds a study group from a file input.
     *
     * @param input A string containing study group parameters separated by commas.
     * @throws InsufficientNumberOfArguments if the provided input has insufficient arguments.
     */
    public static void addStudyGroupFromFile(String input) {
        try {
            String[] inputSplit = input.split(",");
            if (inputSplit.length != 11) {
                throw new InsufficientNumberOfArguments("Add");
            }
            StudyGroup studyGroup = StudyGroup.inputFromFile(inputSplit, false);
            if (studyGroup != null) {
                Collection.getInstance().addElement(studyGroup);
            }
        } catch (Exception e) {
            System.out.println("Error adding from file: " + e.getMessage());
        }
    }

    @Override
    public String getHelp() {
        return "Adds a new element to the collection.";
    }
}
