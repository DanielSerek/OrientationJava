package com.simbabank.bank.repositories;

import com.simbabank.bank.models.BankAccount;

import java.util.ArrayList;
import java.util.List;

public class Bank {
    List<BankAccount> accounts;

    public Bank() {
        this.accounts = new ArrayList<>();
        ;
        accounts.add(new BankAccount("King Lion", 999999.99, "lion", true, true));
        accounts.add(new BankAccount("Homer Simpson", 0.22, "human?", false, false));
        accounts.add(new BankAccount("Mickey Mouse", 1.22, "mouse", false, true));
        accounts.add(new BankAccount("Daffy Duck", 9999.99, "duck", false, true));
        accounts.add(new BankAccount("Satan", 666.66, "evil", false, false));
    }

    public List<BankAccount> getAccounts() {
        return accounts;
    }

    public void addAccount(BankAccount account) {
        this.accounts.add(account);
    }
}
