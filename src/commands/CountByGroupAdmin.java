package commands;

import exeptions.InsufficientNumberOfArguments;
import relatedToTheCollection.Collection;
import relatedToTheCollection.Person;
import relatedToTheCollection.StudyGroup;

import java.util.Objects;
import java.util.TreeSet;

/**
 * Command that counts the number of study groups where a specified person is the admin.
 */
public class CountByGroupAdmin implements Helpable {

    /**
     * Counts the number of study groups where the user-specified person is the admin.
     */
    public static void countByGroupAdmin() {
        try {
            TreeSet<StudyGroup> studyGroups = Collection.getInstance().getCollection();
            Person person = Person.input();
            int adminCounter = (int) studyGroups.stream()
                    .filter(studyGroup -> Objects.equals(studyGroup.getGroupAdmin(), person))
                    .count();

            System.out.println("The person is an admin in " + adminCounter + " groups.");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    /**
     * Counts the number of study groups where the person from file input is the admin.
     *
     * @param input A string containing person attributes separated by commas.
     * @throws InsufficientNumberOfArguments if the provided input has insufficient arguments.
     */
    public static void countByGroupAdminFromFile(String input) {
        try {
            String[] inputSplit = input.split(",");
            if (inputSplit.length != 4) {
                throw new InsufficientNumberOfArguments("CountByGroupAdmin");
            }

            Person person = Person.inputFromFile(inputSplit[0], inputSplit[1], inputSplit[2], inputSplit[3]);
            TreeSet<StudyGroup> studyGroups = Collection.getInstance().getCollection();
            int adminCounter = 0;
            for (StudyGroup studyGroup : studyGroups) {
                if (Objects.equals(studyGroup.getGroupAdmin(), person)) {
                    adminCounter++;
                }
            }
            System.out.println("The person is an admin in " + adminCounter + " groups.");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    @Override
    public String getHelp() {
        return "Counts the number of study groups where the specified person is the admin.";
    }
}
