package com.company;

import javax.inject.Inject;
import java.math.BigDecimal;
import java.util.List;
import com.company.Database.Account;

final class DepositCommand implements Command {
    private final Outputter outputter;
    private final Database database;

    @Inject
    DepositCommand(Database database, Outputter outputter) {
        this.outputter = outputter;
        this.database = database;
    }

    @Override
    public String key() {
        return "deposit";
    }

    @Override
    public Result handleInput(List<String> input) {
        if (input.size() != 2) {
            return Result.invalid();
        }

        Account account = database.getAccount(input.get(0));
        account.deposit(new BigDecimal(input.get(1)));
        outputter.output(account.username() + " now has: " + account.balance());
        return Result.handled();
    }
}