package com.company;

import java.util.*;

import com.company.Command.Status;

import javax.inject.Inject;
import com.company.Command.Result;

final class CommandRouter {
    private final Map<String, Command> commands;

    @Inject
    CommandRouter(Map<String, Command> commands) {
        this.commands = commands;
    }

    Result route(String input) {
        List<String> splitInput = split(input);
        if (splitInput.isEmpty()) {
            return invalidCommand(input);
        }

        String commandKey = splitInput.get(0);
        Command command = commands.get(commandKey);
        if (command == null) {
            return invalidCommand(input);
        }

        Result result =
                command.handleInput(splitInput.subList(1, splitInput.size()));
        return result.status().equals(Status.INVALID) ? invalidCommand(input) : result;
    }

    private Result invalidCommand(String input) {
        System.out.println(
                String.format("couldn't understand \"%s\". please try again.", input));
        return Result.invalid();
    }

    // Split on whitespace
    private static List<String> split(String string) {
        return Arrays.asList(string.trim().split("\\s+"));
    }
}
