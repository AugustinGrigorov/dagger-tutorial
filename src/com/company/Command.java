package com.company;

import java.util.List;
import java.util.Optional;

public interface Command {
    String key();

    /** Process the rest of the command's words and do something. */
    Result handleInput(List<String> input);

    final class Result {
        private final Status status;
        private final Optional<CommandRouter> nestedCommandRouter;

        Result (Status status, Optional<CommandRouter> commandRouter) {
            this.status = status;
            this.nestedCommandRouter = commandRouter;
        }

        static Result invalid() {
            return new Result(Status.INVALID, Optional.empty());
        }

        static Result handled() {
            return new Result(Status.HANDLED, Optional.empty());
        }

        static Result inputCompleted() {
            return new Result(Status.INPUT_COMPLETED, Optional.empty());
        }

        static Result enterNestedCommandSet(CommandRouter nestedCommandRouter) {
            return new Result(Status.HANDLED, Optional.of(nestedCommandRouter));
        }

        Status status() {
            return status;
        }

        Optional<CommandRouter> nestedCommandRouter() {
            return nestedCommandRouter;
        }
    }

    enum Status {
        INVALID,
        HANDLED,
        INPUT_COMPLETED
    }
}
