package oops.designPatterns.command.avoiding_if_else_using_command_pattern.good.executors;


import oops.designPatterns.command.avoiding_if_else_using_command_pattern.other.Command;
import oops.designPatterns.command.avoiding_if_else_using_command_pattern.other.Database;

abstract public class CommandExecutor {

    protected Database database;

    public CommandExecutor(Database database) {
        this.database = database;
    }

    public String execute(final Command command) {
        if (!isValid(command)) {
            return "Invalid Command";
        }
        return executeValidCommand(command);
    }

    public abstract Boolean isApplicable(final Command command);

    protected abstract Boolean isValid(final Command command);

    protected abstract String executeValidCommand(final Command command);
}
