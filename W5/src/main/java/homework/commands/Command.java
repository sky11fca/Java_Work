package homework.commands;

import homework.exceptions.BadCommand;
import homework.exceptions.BadDataType;

public interface Command
{
    void execute(String[] args) throws BadCommand, BadDataType;
    String getDescription();
}
