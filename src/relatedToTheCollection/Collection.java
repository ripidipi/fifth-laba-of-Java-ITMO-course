package relatedToTheCollection;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.TreeSet;

/**
 * Singleton class managing a collection of study groups.
 */
public class Collection {

    private TreeSet<StudyGroup> collection = new TreeSet<>();
    private final LocalDateTime date;
    private static Collection instance;

    private Collection() {
        date = LocalDateTime.now();
    }

    /**
     * Returns the singleton instance of the collection.
     *
     * @return the singleton instance
     */
    public static Collection getInstance() {
        if (instance == null) {
            instance = new Collection();
        }
        return instance;
    }

    /**
     * Returns the info about the collection including size and the creation date.
     *
     * @return the collection info
     */
    public String getInfo() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");
        return "TreeSet " + date.format(formatter) + " " + collection.size();
    }

    /**
     * Clears the collection.
     */
    public void clearCollection() {
        collection.clear();
    }

    /**
     * Removes an element from the collection.
     *
     * @param studyGroup the study group to remove
     */
    public void removeElement(StudyGroup studyGroup) {
        collection.remove(studyGroup);
    }

    /**
     * Returns the collection of study groups.
     *
     * @return the collection
     */
    public TreeSet<StudyGroup> getCollection() {
        return collection;
    }

    /**
     * Adds a new element to the collection.
     *
     * @param studyGroup the study group to add
     */
    public void addElement(StudyGroup studyGroup) {
        collection.add(studyGroup);
    }

    /**
     * Saves the collection to a CSV file.
     */
    public static void output() {
        TreeSet<StudyGroup> collection = Collection.getInstance().getCollection();
        String csvFile = "collection.csv";
        File file = new File(csvFile);

        if (file.exists()) {
            file.delete();
        }

        try (OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(csvFile), StandardCharsets.UTF_8)) {
            writer.write("ID,Name,CoordinateX,CoordinateY,StudentsCount," +
                    "FormOfEducation,Semester,AdminName,AdminBirthday,Height,PassportID\n");

            for (StudyGroup studyGroup : collection) {
                String writeRequest = formatStudyGroupToCSV(studyGroup);
                writer.write(writeRequest);
            }
        } catch (Exception e) {
            System.out.println("Saving failed, try again later: " + e.getMessage());
        }
    }

    /**
     * Formats the StudyGroup object into a CSV string.
     *
     * @param studyGroup the study group to format
     * @return the formatted CSV string
     */
    private static String formatStudyGroupToCSV(StudyGroup studyGroup) {
        return studyGroup.getId().toString() + "," + studyGroup.getName() + "," +
                studyGroup.getCoordinates().xToString() + "," +
                studyGroup.getCoordinates().yToString() +  "," +
                studyGroup.getStudentCount().toString() + "," + studyGroup.getFormOfEducation().toString() + "," +
                studyGroup.getSemester().toString() + "," + studyGroup.getGroupAdmin().name() + "," +
                studyGroup.getGroupAdmin().getBirthdayString() + "," + studyGroup.getGroupAdmin().heightToString() + "," +
                studyGroup.getGroupAdmin().passportID() + '\n';
    }
}
