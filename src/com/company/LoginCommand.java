package com.company;

import javax.inject.Inject;
import com.company.Database.Account;

final class LoginCommand extends SingleArgCommand {
    private final Outputter outputter;
    private final Database database;

    @Inject
    LoginCommand(Outputter outputter, Database database) {
        this.outputter = outputter;
        this.database = database;
    }

    @Override
    public String key() {
        return "login";
    }

    @Override
    public Result handleArg(String username) {
        Account account = database.getAccount(username);
        outputter.output(
                username + " is logged in with balance: " + account.balance());
        return Result.handled();
    }
}