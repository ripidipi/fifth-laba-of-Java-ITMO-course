package commands;

import java.util.function.Consumer;

/**
 * Enum representing available commands in the system.
 * Each command corresponds to a specific action that can be executed.
 * The commands are either parameterless (using Runnable) or require arguments (using Consumer<String>).
 */
public enum Commands {
    /**
     * Add a new study group to the collection.
     */
    ADD(Add::addStudyGroup),

    /**
     * Add a new study group from a file to the collection.
     */
    ADD_F(Add::addStudyGroupFromFile),

    /**
     * Print information about the collection (type, initialization date, number of elements).
     */
    INFO(Info::info),

    /**
     * Print help information about available commands.
     */
    HELP(Help::help),

    /**
     * Show all the study groups in the collection.
     */
    SHOW(Show::show),

    /**
     * Clear the entire collection of study groups.
     */
    CLEAR(Clear::clearCollection),

    /**
     * Update an existing study group in the collection by ID.
     */
    UPDATE(Update::update),

    /**
     * Update an existing study group from a file.
     */
    UPDATE_F(Update::updateFromFile),

    /**
     * Exit the program.
     */
    EXIT(Exit::exit),

    /**
     * Save the current state of the collection to a file.
     */
    SAVE(Save::save),

    /**
     * Remove a study group from the collection by its ID.
     */
    REMOVE_BY_ID(RemoveById::removeById),

    /**
     * Execute a script.
     */
    EXECUTE_SCRIPT(ExecuteScript::executeScript),

    /**
     * Add a study group if its ID is greater than the maximum ID in the collection.
     */
    ADD_IF_MAX(AddIfMax::addStudyGroupIfMax),

    /**
     * Add a study group from a file if its ID is greater than the maximum ID in the collection.
     */
    ADD_IF_MAX_F(AddIfMax::addStudyGroupIfMaxFromFile),

    /**
     * Remove study groups greater than a specified one.
     */
    REMOVE_GREATER(RemoveGreater::removeGreater),

    /**
     * Remove study groups greater than a specified one from a file.
     */
    REMOVE_GREATER_F(RemoveGreater::removeGreaterFromFile),

    /**
     * Remove study groups lower than a specified one.
     */
    REMOVE_LOWER(RemoveLower::removeLower),

    /**
     * Remove study groups lower than a specified one from a file.
     */
    REMOVE_LOWER_F(RemoveLower::removeLowerFromFile),

    /**
     * Count the number of study groups managed by a specific group admin.
     */
    COUNT_BY_GROUP_ADMIN(CountByGroupAdmin::countByGroupAdmin),

    /**
     * Count the number of study groups managed by a specific group admin from a file.
     */
    COUNT_BY_GROUP_ADMIN_F(CountByGroupAdmin::countByGroupAdminFromFile),

    /**
     * Remove a study group managed by a specific group admin.
     */
    REMOVE_ANY_BY_GROUP_ADMIN(RemoveAnyByGroupAdmin::removeAnyByGroupAdmin),

    /**
     * Remove a study group managed by a specific group admin from a file.
     */
    REMOVE_ANY_BY_GROUP_ADMIN_F(RemoveAnyByGroupAdmin::removeAnyByGroupAdminFromFile),

    /**
     * Group the study groups by ID and display the result.
     */
    GROUP_COUNTING_BY_ID(GroupCountingById::groupCountingById);

    private final Consumer<String> commandWithArg;
    private final Runnable commandWithoutArg;

    /**
     * Constructor for commands that require an argument.
     *
     * @param commandWithArg Command that requires an argument of type String.
     */
    Commands(Consumer<String> commandWithArg) {
        this.commandWithArg = commandWithArg;
        this.commandWithoutArg = null;
    }

    /**
     * Constructor for commands that do not require any arguments.
     *
     * @param commandWithoutArg Command that does not require an argument.
     */
    Commands(Runnable commandWithoutArg) {
        this.commandWithArg = null;
        this.commandWithoutArg = commandWithoutArg;
    }

    /**
     * Executes the command with the provided argument.
     * If the command does not accept arguments, it does nothing.
     *
     * @param arg The argument to pass to the command.
     */
    public void execute(String arg) {
        if (commandWithArg != null) {
            commandWithArg.accept(arg);
        }else if (commandWithoutArg != null) {
            commandWithoutArg.run();
        }else {
            System.out.println("Problems with set of argument");
        }
    }
}
