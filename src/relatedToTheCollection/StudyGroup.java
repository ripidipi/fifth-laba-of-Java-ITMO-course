package relatedToTheCollection;

import exeptions.CommandDataFromTheFileIsIncorrect;
import inputOutput.PrimitiveDataTransform;
import inputOutput.EnumInput;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Class representing a study group. This class implements the Comparable interface to allow comparison
 * of study groups based on their unique ID.
 */
public class StudyGroup implements Comparable<StudyGroup> {

    private static Map<Integer, Boolean> IDs = new ConcurrentHashMap<>();
    private final Integer id;
    private final String name;
    private final Coordinates coordinates;
    private final java.time.LocalDateTime creationDate;
    private final Integer studentCount;
    private final FormOfEducation formOfEducation;
    private final Semester semester;
    private final Person groupAdmin;

    /**
     * Constructs a StudyGroup with a generated unique ID.
     *
     * @param name The name of the study group.
     * @param coordinates The coordinates of the study group.
     * @param studentCount The number of students in the group.
     * @param formOfEducation The form of education of the group.
     * @param semester The semester of the group.
     * @param groupAdmin The admin of the group.
     */
    public StudyGroup(String name, Coordinates coordinates,
                      Integer studentCount, FormOfEducation formOfEducation,
                      Semester semester, Person groupAdmin) {
        int randomID;
        SecureRandom random = new SecureRandom();
        do {
            randomID = 10000000 + random.nextInt(90000000); // 8 digits
        } while (IDs.containsKey(randomID));
        this.id = randomID;
        IDs.put(randomID, true);
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = LocalDateTime.now();
        this.studentCount = studentCount;
        this.formOfEducation = formOfEducation;
        this.semester = semester;
        this.groupAdmin = groupAdmin;
    }

    /**
     * Constructs a StudyGroup with a provided unique ID.
     *
     * @param id The unique ID of the study group.
     * @param name The name of the study group.
     * @param coordinates The coordinates of the study group.
     * @param studentCount The number of students in the group.
     * @param formOfEducation The form of education of the group.
     * @param semester The semester of the group.
     * @param groupAdmin The admin of the group.
     */
    public StudyGroup(Integer id, String name, Coordinates coordinates, Integer studentCount,
                      FormOfEducation formOfEducation,
                      Semester semester, Person groupAdmin) {
        this.id = id;
        if (id != null)
            IDs.put(id, true);
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = LocalDateTime.now();
        this.studentCount = studentCount;
        this.formOfEducation = formOfEducation;
        this.semester = semester;
        this.groupAdmin = groupAdmin;
    }

    /**
     * Compares this StudyGroup to another StudyGroup based on the ID.
     *
     * @param other The StudyGroup to compare this one to.
     * @return A negative integer, zero, or a positive integer as this ID is less than, equal to, or greater than the ID of the other StudyGroup.
     */
    @Override
    public int compareTo(StudyGroup other) {
        return this.id.compareTo(other.id);
    }

    /**
     * Compares this StudyGroup to another object for equality. Two StudyGroups are equal if their IDs are the same.
     *
     * @param o The object to compare to.
     * @return true if the IDs are equal, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudyGroup that = (StudyGroup) o;
        return Objects.equals(id, that.id);
    }

    /**
     * Returns a hash code value for this StudyGroup. The hash code is based on the ID, name, coordinates,
     * creation date, student count, form of education, semester, and group admin.
     *
     * @return The hash code value for this StudyGroup.
     */
    @Override
    public int hashCode() {
        return Objects.hash(id, name, coordinates, creationDate, studentCount,
                formOfEducation, semester, groupAdmin);
    }

    /**
     * Returns a string representation of this StudyGroup. The string includes the ID, name, coordinates,
     * creation date, student count, form of education, semester, and group admin.
     *
     * @return The string representation of this StudyGroup.
     */
    @Override
    public String toString() {
        return "StudyGroup {" +
                "\nid: " + id +
                "\nname: " + name +
                "\n" + coordinates +
                "\ncreation date: " + getCreationDateString() +
                "\nstudent count: " + studentCount +
                "\nform of education: " + formOfEducation +
                "\nsemester: " + semester +
                "\n" + groupAdmin + '}';
    }

    /**
     * Gets the ID of the StudyGroup.
     *
     * @return The ID of the StudyGroup.
     */
    public Integer getId() { return id; }

    /**
     * Gets the name of the StudyGroup.
     *
     * @return The name of the StudyGroup.
     */
    public String getName() { return name; }

    /**
     * Gets the coordinates of the StudyGroup.
     *
     * @return The coordinates of the StudyGroup.
     */
    public Coordinates getCoordinates() { return coordinates; }

    /**
     * Gets the creation date of the StudyGroup.
     *
     * @return The creation date of the StudyGroup.
     */
    public java.time.LocalDateTime getCreationDate() { return creationDate; }

    /**
     * Gets the creation date of the StudyGroup as a formatted string.
     *
     * @return The creation date as a string in the format dd/MM/yyyy HH:mm:ss.
     */
    public String getCreationDateString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        return creationDate.format(formatter);
    }

    /**
     * Gets the student count of the StudyGroup.
     *
     * @return The number of students in the StudyGroup.
     */
    public Integer getStudentCount() { return studentCount; }

    /**
     * Gets the form of education of the StudyGroup.
     *
     * @return The form of education of the StudyGroup.
     */
    public FormOfEducation getFormOfEducation() { return formOfEducation; }

    /**
     * Gets the semester of the StudyGroup.
     *
     * @return The semester of the StudyGroup.
     */
    public Semester getSemester() { return semester; }

    /**
     * Gets the group admin of the StudyGroup.
     *
     * @return The group admin of the StudyGroup.
     */
    public Person getGroupAdmin() { return groupAdmin; }

    /**
     * Checks if the StudyGroup object has all required fields filled.
     *
     * @param studyGroup The StudyGroup object to check.
     * @return true if the StudyGroup is valid, false otherwise.
     */
    private static boolean isRightFill(StudyGroup studyGroup) {
        if (studyGroup == null) { return false; }
        return Person.isRightFill(studyGroup.groupAdmin) && Coordinates.isRightFill(studyGroup.coordinates) &&
                studyGroup.name != null && studyGroup.creationDate != null && studyGroup.studentCount != null &&
                studyGroup.formOfEducation != null && studyGroup.semester != null && studyGroup.id != null;
    }

    /**
     * Input manager for creating a StudyGroup object from user input.
     *
     * @param Arg Optional argument to specify the ID.
     * @return A new StudyGroup object created from the user input.
     */
    public static StudyGroup input(String... Arg) {
        try {
            Integer id = null;
            if (Arg.length > 0) {
                id = PrimitiveDataTransform.inputFromFile("id", Arg[0], Integer.class);
            }
            System.out.print("Enter information about study group");
            String name = PrimitiveDataTransform.input("name", String.class);
            Coordinates coordinates = Coordinates.input();
            Integer studentCount = PrimitiveDataTransform.input("students count", Integer.class);
            FormOfEducation formOfEducation = EnumInput.inputFromConsole(FormOfEducation.class);
            Semester semester = EnumInput.inputFromConsole(Semester.class);
            Person groupAdmin = Person.input();
            if (id != null) {
                return new StudyGroup(id, name, coordinates, studentCount,
                        formOfEducation, semester, groupAdmin);
            } else {
                return new StudyGroup(name, coordinates, studentCount, formOfEducation, semester, groupAdmin);
            }

        } catch (Exception e) {
            System.out.println("Invalid input. Try again.");
            return input(Arg);
        }
    }

    /**
     * Input manager for creating a StudyGroup object from data read from a file.
     *
     * @param inputSplit The input data split into parts.
     * @param notAdded Flag to indicate if the ID should not be added to the list of IDs.
     * @return A new StudyGroup object created from the file data.
     */
    public static StudyGroup inputFromFile(String[] inputSplit, boolean notAdded) {
        try {
            Integer id = Integer.parseInt(inputSplit[0]);
            String name = PrimitiveDataTransform.inputFromFile("name", inputSplit[1], String.class);
            Coordinates coordinates = Coordinates.inputFromFile(inputSplit[2], inputSplit[3]);
            Integer studentCount = PrimitiveDataTransform.inputFromFile("students count", inputSplit[4],
                    Integer.class);
            FormOfEducation formOfEducation = EnumInput.TransformToEnum(FormOfEducation.class, inputSplit[5]);
            Semester semester = EnumInput.TransformToEnum(Semester.class, inputSplit[6]);
            Person groupAdmin = Person.inputFromFile(inputSplit[7], inputSplit[8], inputSplit[9], inputSplit[10]);
            if (IDs.containsKey(id) & !notAdded) {
                id = null;
                System.out.print("Id is reserved ");
            }
            StudyGroup studyGroup = new StudyGroup(id, name, coordinates, studentCount,
                    formOfEducation, semester, groupAdmin);
            if (!isRightFill(studyGroup)) {
                throw new CommandDataFromTheFileIsIncorrect(String.join(",", inputSplit));
            }
            return studyGroup;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}