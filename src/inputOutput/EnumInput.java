package inputOutput;

import exeptions.IncorrectConstant;
import java.util.Scanner;

/**
 * A utility class for reading enum values from user input.
 */
public class EnumInput {

    /**
     * Reads an enum value from the console.
     *
     * @param enumType The enum class type that is expected to be received from the console.
     * @param <T> The type of the enum.
     * @return One of the constant enum values.
     * @throws IncorrectConstant if the input isn't a valid value from the enum.
     */
    public static <T extends Enum<T>> T inputFromConsole(Class<T> enumType) throws IncorrectConstant {
        try {
            T[] enumConstants = enumType.getEnumConstants();
            StringBuilder enumValues = new StringBuilder();
            for (T constant : enumConstants) {
                enumValues.append(constant.name()).append(" ");
            }
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter " + enumType.getSimpleName() + " (" + enumValues.toString().trim().toLowerCase() + "): ");
            String input = scanner.nextLine().toUpperCase();
            return TransformToEnum(enumType, input);
        } catch (Exception e) {
            System.out.println("Invalid data. Try again");
        }
        return inputFromConsole(enumType);
    }

    /**
     * Converts a string input to an enum value.
     *
     * @param enumType The enum class type to convert to.
     * @param input The string input to convert.
     * @param <T> The type of the enum.
     * @return The corresponding enum value if valid.
     * @throws IncorrectConstant if the input isn't a valid value from the enum.
     */
    public static <T extends Enum<T>> T TransformToEnum(Class<T> enumType, String input) throws IncorrectConstant {
        try {
            return Enum.valueOf(enumType, input.toUpperCase());
        } catch (IllegalArgumentException e) {
            System.out.println(new IncorrectConstant(enumType.getSimpleName()).getMessage());
        } catch (Exception e) {
            System.out.println("Invalid data. Try again");
        }
        return inputFromConsole(enumType);
    }
}