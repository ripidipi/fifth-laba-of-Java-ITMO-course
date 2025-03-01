package relatedToTheCollection;

import exeptions.EmptyLine;
import inputOutput.PrimitiveDataTransform;

/**
 * Represents coordinates (x, y) for a study group.
 * This class includes methods for input, validation, and conversion of coordinates.
 */
public record Coordinates(Long x, Float y) {

    /**
     * Returns a string representation of the Coordinates object.
     * It includes the x and y coordinates in a readable format.
     *
     * @return a string representation of the Coordinates object.
     */
    @Override
    public String toString() {
        return "Coordinates {" +
                "\nx coordinate: " + xToString() +
                "\nx coordinate: " + yToString() + '}';
    }

    /**
     * Converts the y coordinate to a string.
     * If the y coordinate is null, returns an empty space string.
     *
     * @return string representation of the y coordinate or an empty space if null.
     */
    public String yToString() {
        return (y == null ? " " : y.toString());
    }

    /**
     * Converts the x coordinate to a string.
     * If the x coordinate is null, returns an empty space string.
     *
     * @return string representation of the x coordinate or an empty space if null.
     */
    public String xToString() {
        return (x == null ? " " : x.toString());
    }

    /**
     * Checks if the coordinates are correctly filled (not null).
     *
     * @param coordinates the Coordinates object to check.
     * @return true if the coordinates are not null, false otherwise.
     */
    public static boolean isRightFill(Coordinates coordinates) {
        return coordinates != null;
    }

    /**
     * Prompts the user to input the coordinates (x and y).
     * Uses the {@link PrimitiveDataTransform} to read and validate the input.
     *
     * @return a new Coordinates object with the user-input values.
     * @throws EmptyLine if the input is empty and not allowed.
     */
    public static Coordinates input() throws EmptyLine {
        try {
            System.out.println("Enter information about coordinates");
            Long x = PrimitiveDataTransform.input("x coordinate", Long.class, false,
                    false, false, null);
            Float y = PrimitiveDataTransform.input("y coordinate", Float.class, false,
                    false, false, null);
            return new Coordinates(x, y);
        } catch (Exception e) {
            System.out.println("Invalid data. Try again");
        }
        System.out.flush();
        return input();
    }

    /**
     * Creates a Coordinates object from file input.
     * Uses {@link PrimitiveDataTransform} to read and convert the x and y values from the file.
     *
     * @param x the x coordinate value as a string.
     * @param y the y coordinate value as a string.
     * @return a new Coordinates object with the values from the file, or null if the values are invalid.
     */
    public static Coordinates inputFromFile(String x, String y) {
        try {
            return new Coordinates(PrimitiveDataTransform.inputFromFile("CoordinateX", x, Long.class,
                    false, false, false, null, false),
                    PrimitiveDataTransform.inputFromFile("CoordinateY", y, Float.class, false,
                            false, false, null, false));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
