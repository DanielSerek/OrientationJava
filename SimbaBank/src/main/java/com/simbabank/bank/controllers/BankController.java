package com.simbabank.bank.controllers;

import com.simbabank.bank.models.BankAccount;
import com.simbabank.bank.repositories.Bank;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class BankController {
    private Bank bank = new Bank();

    @GetMapping("/show")
    public String show(Model model) {
        BankAccount account1 = new BankAccount("Simba", 2000.01, "lion", true, true);
        model.addAttribute("account", account1);
        return "show";
    }

    @GetMapping("/displaytext")
    public String HTMLception(Model model) {
        model.addAttribute("news", "This is an <em>HTML</em> text. <b>Enjoy yourself!</b>");
        return "template";
    }

    @GetMapping("list-accounts")
    public String displayAllBooks(Model model) {
        model.addAttribute("accounts", bank.getAccounts());
        return "list-accounts";
    }

    @GetMapping("donate/{accountName}")
    public String donate(@PathVariable String accountName) {
        for (BankAccount account : bank.getAccounts()) {
            if (account.getName().equals(accountName)) {
                if (account.isKing()) account.setBalance(account.getBalance() + 100);
                else account.setBalance(account.getBalance() + 10);
            }
        }
        return "redirect:/list-accounts";
    }

    @GetMapping("create-account")
    public String createAccount() {
        return "create-account";
    }

    @PostMapping("create-account")
    public String addBook(@ModelAttribute BankAccount account) {
        if (account.getName().isEmpty() || account.getAnimalType().isEmpty())
            return "redirect:/list-accounts";
        bank.addAccount(account);
        return "redirect:/list-accounts";
    }
}
