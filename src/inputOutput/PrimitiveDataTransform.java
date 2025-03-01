package inputOutput;

import exeptions.DataInTheFuture;
import exeptions.EmptyLine;
import exeptions.ZeroValue;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.Scanner;

/**
 * A utility class for reading and transforming basic data types from user input or file.
 */
public class PrimitiveDataTransform {

    private static final Scanner scanner = new Scanner(System.in);

    /**
     * Reads and transforms user input into a specified data type.
     *
     * @param name                The name of the expected input.
     * @param type                The expected data type.
     * @param emptyLineCheck      Whether to check for empty input.
     * @param zeroValueCheck      Whether to check for zero or negative values.
     * @param dateInTheFutureCheck Whether to check if a date is in the future.
     * @param formatter           The formatter to use for date parsing.
     * @param <T>                 The type of the expected data.
     * @return The transformed user input.
     * @throws EmptyLine        If input is empty and emptyLineCheck is enabled.
     * @throws ZeroValue        If input is a non-positive number and zeroValueCheck is enabled.
     * @throws DataInTheFuture  If input is a future date and dateInTheFutureCheck is enabled.
     */
    public static <T> T input(String name, Class<T> type, Boolean emptyLineCheck, Boolean zeroValueCheck,
                              Boolean dateInTheFutureCheck, DateTimeFormatter formatter)
            throws EmptyLine, ZeroValue, DataInTheFuture {
        try {
            System.out.print("Enter " + name + ": ");
            String input = scanner.nextLine();
            return transformToBasicType(name, type, emptyLineCheck,
                    zeroValueCheck, dateInTheFutureCheck, input,
                    false, formatter, false);
        } catch (Exception e) {
            System.out.println("Invalid input. Try again");
        }
        return input(name, type, emptyLineCheck, zeroValueCheck, dateInTheFutureCheck, formatter);
    }

    /**
     * Reads and transforms user input with default validation settings.
     *
     * @param name The name of the expected input.
     * @param type The expected data type.
     * @param <T>  The type of the expected data.
     * @return The transformed user input.
     * @throws EmptyLine        If input is empty.
     * @throws ZeroValue        If input is a non-positive number.
     * @throws DataInTheFuture  If input is a future date.
     */
    public static <T> T input(String name, Class<T> type) throws EmptyLine, ZeroValue, DataInTheFuture {
        try {
            System.out.print("Enter " + name + ": ");
            String input = scanner.nextLine();
            return transformToBasicType(name, type, true, true, true, input,
                    false, DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"), false);
        } catch (Exception e) {
            System.out.println("Invalid input. Try again");
        }
        return input(name, type);
    }

    /**
     * Transforms an input string from a file into a specified data type.
     *
     * @param name The name of the expected input.
     * @param input The input string to transform.
     * @param type The expected data type.
     * @param <T> The type of the expected data.
     * @return The transformed input.
     * @throws EmptyLine        If input is empty.
     * @throws ZeroValue        If input is a non-positive number.
     * @throws DataInTheFuture  If input is a future date.
     */
    public static <T> T inputFromFile(String name, String input, Class<T> type) throws EmptyLine, ZeroValue, DataInTheFuture {
        try {
            return transformToBasicType(name, type, true, true, true,
                    (Objects.equals(input, " ") ? "" : input), true, DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"), false);
        } catch (Exception e) {
            System.out.println("Invalid input. Try again");
        }
        return null;
    }

    /**
     * Transforms an input string from a file into a specified data type with custom validation settings.
     *
     * @param name The name of the expected input.
     * @param input The input string to transform.
     * @param type The expected data type.
     * @param emptyLineCheck Whether to check for empty input.
     * @param zeroValueCheck Whether to check for zero or negative values.
     * @param dateInTheFutureCheck Whether to check if a date is in the future.
     * @param formatter The formatter to use for date parsing.
     * @param muteMode Whether to suppress error messages.
     * @param <T> The type of the expected data.
     * @return The transformed input.
     * @throws EmptyLine        If input is empty and emptyLineCheck is enabled.
     * @throws ZeroValue        If input is a non-positive number and zeroValueCheck is enabled.
     * @throws DataInTheFuture  If input is a future date and dateInTheFutureCheck is enabled.
     */
    public static <T> T inputFromFile(String name, String input, Class<T> type, Boolean emptyLineCheck,
                                      Boolean zeroValueCheck, Boolean dateInTheFutureCheck,
                                      DateTimeFormatter formatter, Boolean muteMode) throws EmptyLine, ZeroValue, DataInTheFuture {
        try {
            return transformToBasicType(name, type, emptyLineCheck, zeroValueCheck, dateInTheFutureCheck, (Objects.equals(input,
                    " ") ? "" : input), true, formatter, muteMode);
        } catch (Exception e) {
            System.out.println("Invalid input. Try again");
        }
        return null;
    }

    /**
     * Parses a date-time string using a specified formatter.
     *
     * @param input The input string to parse.
     * @param formatter The formatter to use.
     * @return The parsed LocalDateTime.
     */
    private static LocalDateTime applyFormater(String input, DateTimeFormatter formatter) {
        try {
            return LocalDateTime.parse(input, formatter);
        } catch (Exception e) {
            return LocalDate.parse(input, formatter).atStartOfDay();
        }
    }

    /**
     * Transforms an input string into a specified basic data type.
     *
     * @param name The name of the expected input.
     * @param type The expected data type.
     * @param emptyLineCheck Whether to check for empty input.
     * @param zeroValueCheck Whether to check for zero or negative values.
     * @param dateInTheFutureCheck Whether to check if a date is in the future.
     * @param input The input string to transform.
     * @param fileMode Whether the input comes from a file.
     * @param formatter The formatter to use for date parsing.
     * @param muteMode Whether to suppress error messages.
     * @param <T> The type of the expected data.
     * @return The transformed input.
     * @throws EmptyLine        If input is empty and emptyLineCheck is enabled.
     * @throws ZeroValue        If input is a non-positive number and zeroValueCheck is enabled.
     * @throws DataInTheFuture  If input is a future date and dateInTheFutureCheck is enabled.
     */
    public static <T> T transformToBasicType(String name, Class<T> type, Boolean emptyLineCheck, Boolean zeroValueCheck,
                                             Boolean dateInTheFutureCheck, String input, Boolean fileMode,
                                             DateTimeFormatter formatter, Boolean muteMode) throws EmptyLine, ZeroValue, DataInTheFuture {
        try {
            if (input.isEmpty() & emptyLineCheck)
                throw new EmptyLine(name);
            if (input.isEmpty())
                return type.cast(null);
            if (type == String.class) {
                return type.cast(input);
            } else if (type == Integer.class) {
                int value = Integer.parseInt(input);
                if (value <= 0 & zeroValueCheck)
                    throw new ZeroValue(name);
                return type.cast(value);
            } else if (type == Double.class) {
                double value = Double.parseDouble(input);
                if (value <= 0 & zeroValueCheck)
                    throw new ZeroValue(name);
                return type.cast(value);
            } else if (type == Float.class) {
                float value = Float.parseFloat(input);
                if (value <= 0 & zeroValueCheck)
                    throw new ZeroValue(name);
                return type.cast(value);
            } else if (type == Long.class) {
                long value = Long.parseLong(input);
                if (value <= 0 & zeroValueCheck)
                    throw new ZeroValue(name);
                return type.cast(value);
            } else if (type == LocalDateTime.class) {
                LocalDateTime date = applyFormater(input, formatter);
                if (date.isAfter(LocalDateTime.now()) & dateInTheFutureCheck) {
                    throw new DataInTheFuture(name);
                }
                return type.cast(date);
            }
        } catch (Exception e) {
            if (!muteMode)
                System.out.println("Invalid input. Try again");
        }
        if (fileMode) {
            return null;
        }
        return input(name, type, emptyLineCheck, zeroValueCheck, dateInTheFutureCheck, formatter);
    }
}
