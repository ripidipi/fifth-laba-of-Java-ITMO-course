package commands;

import exeptions.InsufficientNumberOfArguments;
import relatedToTheCollection.Collection;
import relatedToTheCollection.Person;
import relatedToTheCollection.StudyGroup;

import java.util.Iterator;
import java.util.Objects;
import java.util.TreeSet;

/**
 * Command that removes a study group by its group admin.
 */
public class RemoveAnyByGroupAdmin implements Helpable{

    /**
     * Removes the first study group with the given group admin from the collection.
     *
     * @param person The group admin whose group needs to be removed.
     */
    private static void removeGroupByAdmin(Person person) {
        TreeSet<StudyGroup> studyGroups = Collection.getInstance().getCollection();
        Iterator<StudyGroup> iterator = studyGroups.iterator();
        while (iterator.hasNext()) {
            StudyGroup studyGroup = iterator.next();
            if (Objects.equals(studyGroup.getGroupAdmin(), person)) {
                iterator.remove();
                break;
            }
        }
    }

    /**
     * Removes the first study group with the given group admin from the collection based on user input.
     */
    public static void removeAnyByGroupAdmin() {
        try {
            Person person = Person.input();
            removeGroupByAdmin(person);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Removes the first study group with the given group admin from the collection based on input from a file.
     *
     * @param input The input string containing information to identify the person.
     */
    public static void removeAnyByGroupAdminFromFile(String input) {
        try {
            String[] inputSplit = input.split(",");
            if (inputSplit.length != 4) {
                throw new InsufficientNumberOfArguments("");
            }
            Person person = Person.inputFromFile(inputSplit[0], inputSplit[1], inputSplit[2], inputSplit[3]);
            removeGroupByAdmin(person);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public String getHelp() {
        return "Removes the first study group with the specified group admin.";
    }
}
