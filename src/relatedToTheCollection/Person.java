package relatedToTheCollection;

import exeptions.EmptyLine;
import exeptions.ZeroValue;
import inputOutput.BasicDataTypesInput;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public record Person(String name, LocalDateTime birthday, Double height, String passportID) {

    public String getBirthdayString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return birthday.format(formatter);
    }

    @Override
    public String toString() {
        return "Group admin {" +
                "\nname: " + name +
                "\nbirthday: " + getBirthdayString() +
                "\nheight: " + (height==null ? "" : height) +
                "\npassportID: " + passportID + '}';
    }

    public static boolean isRightFill(Person person) {
        if (person == null) {
            return false;
        }
        return person.name != null && person.birthday != null && person.passportID != null;
    }

    /**
     * Input manager to create object with class Person
     * @return object with class Person
     * @throws EmptyLine for empty gaps, if it incorrect format for it
     * @throws ZeroValue for numeric gaps <= 0, if it incorrect format for it
     */
    public static Person Input() throws EmptyLine, ZeroValue {
        try {
            System.out.println("Enter information about group admin");
            String name = BasicDataTypesInput.Input("name", String.class);
            LocalDateTime birthday = BasicDataTypesInput.Input("birthday data in format DD.MM.YYYY",
                    LocalDateTime.class, false, false,
                    false, DateTimeFormatter.ofPattern("dd.MM.yyyy"));
            Double height = BasicDataTypesInput.Input("height", Double.class, false, true, false, null);
            String passportID = BasicDataTypesInput.Input("passportID", String.class);
            return new Person(name, birthday, height, passportID);
        } catch (Exception e) {
            System.out.println("Invalid data. Try again");
        }
        return Input();

    }

    public static Person InputFromFile(String name, String birthday, String height, String passportID) {
        try {
            return new Person(BasicDataTypesInput.InputFromFile("groupAdminName", name, String.class),
                    BasicDataTypesInput.InputFromFile("adminBirthday", birthday, LocalDateTime.class, false, false,
                            false, DateTimeFormatter.ofPattern("dd/MM/yyyy"), false),
                    BasicDataTypesInput.InputFromFile("adminHeight", height, Double.class, false, true,
                            false, null, false),
                    BasicDataTypesInput.InputFromFile("adminPassportID", passportID, String.class));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
