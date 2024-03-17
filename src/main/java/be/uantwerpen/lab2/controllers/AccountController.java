package be.uantwerpen.lab2.controllers;

import be.uantwerpen.lab2.models.BankAccount;
import be.uantwerpen.lab2.models.User;
import be.uantwerpen.lab2.services.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/accounts/{userId}")
public class AccountController {

    @Autowired
    private BankService service;

    @GetMapping
    public List<BankAccount> index(@PathVariable long userId) {
        User user = service.getUser(userId);
        if (user == null) return null;

        return service.getAccounts(user.getAvailableAccounts());
    }

    @GetMapping("/{id}")
    public BankAccount show(@PathVariable long userId, @PathVariable long id) {
        User user = service.getUser(userId);
        if (user == null) return null;

        if (!user.getAvailableAccounts().contains(id)) {
            return null;
        }

        return service.getAccount(id);
    }

    @PostMapping
    public BankAccount store(@PathVariable long userId) {
        User user = service.getUser(userId);
        if (user == null) return null;

        return service.createNewBankAccount(userId);
    }

    @PostMapping("/{id}/members/{memberId}")
    public boolean addMember(@PathVariable long userId, @PathVariable long id, @PathVariable long memberId) {
        User user = service.getUser(userId);
        if (user == null) return false;

        if (!user.getAvailableAccounts().contains(id)) {
            return false;
        }

        BankAccount account = service.getAccount(id);
        return account.addExtraMember(memberId);
    }

    @PutMapping("/{id}/balance/{amount}")
    public String update(@PathVariable long userId, @PathVariable long id, @PathVariable float amount) {
        if (amount < 0) {
            return "You can't add a negative amount!";
        }

        User user = service.getUser(userId);
        if (user == null) return null;

        // Check if the user has access to the account
        if (!user.getAvailableAccounts().contains(id)) {
            return "You don't have access to this account!";
        }

        BankAccount account = service.getAccount(id);
        service.addMoney(account, amount);
        return "Successfully added " + amount + " to account " + id + "! You now have " + account.getBalance() + ".";
    }

    @DeleteMapping("/{id}/balance/{amount}")
    public String delete(@PathVariable long userId, @PathVariable long id, @PathVariable float amount) {
        if (amount < 0) {
            return "You can't deduct a negative amount!";
        }

        User user = service.getUser(userId);
        if (user == null) return null;

        // Check if the user has access to the account
        if (!user.getAvailableAccounts().contains(id)) {
            return "You don't have access to this account!";
        }

        BankAccount account = service.getAccount(id);

        if (!service.canDeductMoney(account, amount)) {
            return "You don't have enough money!";
        }

        service.removeMoney(account, amount);
        return "Successfully deducted " + amount + " from account " + id + "! You now have " + account.getBalance() + ".";
    }

}
