package com.simbabank.bank.controllers;

import com.simbabank.bank.models.BankAccount;
import com.simbabank.bank.repositories.Bank;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BankController {
    private Bank bank = new Bank();

    @GetMapping("/show")
    public String show(Model model) {
        BankAccount account1 = new BankAccount("Simba", 2000.01, "lion", true);
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

    @GetMapping("donate")
    public String donateMoney() {
        return "donate";
    }

    @PostMapping("donate")
    public String donate(@RequestParam double amount, @RequestParam String animal) {
        for (BankAccount account : bank.getAccounts()) {
            if (account.getName().equals(animal)) {
                account.setBalance(account.getBalance() + amount);
            }
        }
        return "redirect:/list-accounts";
    }
}
