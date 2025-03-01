package relatedToTheCollection;

import exeptions.EmptyLine;
import exeptions.ZeroValue;
import inputOutput.PrimitiveDataTransform;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Class representing a group admin (person) with their details.
 * It includes information like name, birthday, height, and passport ID.
 */
public record Person(String name, LocalDateTime birthday, Double height, String passportID) {

    private static final DateTimeFormatter BIRTHDAY_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    /**
     * Formats and returns the birthday of the group admin as a string.
     * @return the formatted birthday string in "dd/MM/yyyy" format.
     */
    public String getBirthdayString() {
        return birthday.format(BIRTHDAY_FORMATTER);
    }

    /**
     * Converts the person object to a readable string format.
     * @return a string representation of the group admin's details.
     */
    @Override
    public String toString() {
        return "Group admin {" +
                "\nname: " + name +
                "\nbirthday: " + getBirthdayString() +
                "\nheight: " + (height == null ? "" : height) +
                "\npassportID: " + passportID + '}';
    }

    /**
     * Checks if the person object is fully initialized (non-null values for name, birthday, and passportID).
     * @param person the person object to check.
     * @return true if all required fields are filled; false otherwise.
     */
    public static boolean isRightFill(Person person) {
        if (person == null) {
            return false;
        }
        return person.name != null && person.birthday != null && person.passportID != null;
    }

    /**
     * Converts the height to a string, handling null values.
     * @return the height as a string or an empty string if null.
     */
    public String heightToString() {
        return (height == null ? " " : height.toString());
    }

    /**
     * Input manager for creating a Person object (group admin) from user input.
     * @return a Person object created from user input.
     * @throws EmptyLine if an empty string is provided where it's not allowed.
     * @throws ZeroValue if the provided numeric value is less than or equal to zero.
     */
    public static Person input() throws EmptyLine, ZeroValue {
        try {
            System.out.println("Enter information about group admin");
            String name = PrimitiveDataTransform.input("name", String.class);
            LocalDateTime birthday = PrimitiveDataTransform.input("birthday data in format DD.MM.YYYY",
                    LocalDateTime.class, false, false,
                    true, DateTimeFormatter.ofPattern("dd.MM.yyyy"));
            Double height = PrimitiveDataTransform.input("height", Double.class, false, true, false, null);
            String passportID = PrimitiveDataTransform.input("passportID", String.class);
            return new Person(name, birthday, height, passportID);
        } catch (Exception e) {
            System.out.println("Invalid data. Try again");
        }
        return input();
    }

    /**
     * Creates a Person object (group admin) from input values, typically used for reading from a file.
     * @param name the name of the group admin.
     * @param birthday the birthday of the group admin in string format.
     * @param height the height of the group admin.
     * @param passportID the passport ID of the group admin.
     * @return a Person object created from the input values.
     */
    public static Person inputFromFile(String name, String birthday, String height, String passportID) {
        try {
            return new Person(PrimitiveDataTransform.inputFromFile("groupAdminName", name, String.class),
                    PrimitiveDataTransform.inputFromFile("adminBirthday", birthday, LocalDateTime.class, false, false,
                            true, BIRTHDAY_FORMATTER, false),
                    PrimitiveDataTransform.inputFromFile("adminHeight", height, Double.class, false, true,
                            false, null, false),
                    PrimitiveDataTransform.inputFromFile("adminPassportID", passportID, String.class));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
