package be.uantwerpen.lab2.services;

import be.uantwerpen.lab2.models.BankAccount;
import be.uantwerpen.lab2.models.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;

@Service
public class BankService {

    private HashMap<Long, BankAccount> bankMap = new HashMap<>();
    private HashMap<Long, User> userMap = new HashMap<>();

    public ArrayList<BankAccount> getAccounts(ArrayList<Long> accountNumbers) {
        ArrayList<BankAccount> accounts = new ArrayList<>();

        for (long accountNumber : accountNumbers) {
            accounts.add(bankMap.get(accountNumber));
        }

        return accounts;
    }

    public BankAccount getAccount(long accountNumber) {
        return bankMap.get(accountNumber);
    }

    public BankAccount createNewBankAccount(long userId) {
        User user = userMap.get(userId);

        BankAccount account = new BankAccount(user.getId());
        bankMap.put(account.getBankNumber(), account);
        user.addAccount(account.getBankNumber());

        return account;
    }

    public boolean canDeductMoney(BankAccount account, float amount) {
        return account.getBalance() >= amount;
    }

    public boolean removeMoney(BankAccount account, float amount) {
        if (account == null) return false;

        account.setBalance(account.getBalance() - amount);
        return true;
    }

    public boolean addMoney(BankAccount account, float amount) {
        if (account == null) return false;

        account.setBalance(account.getBalance() + amount);
        return true;
    }

    public User getUser(long userId)
    {
        return userMap.get(userId);
    }

    public long getNextUserId() {
        return userMap.size() + 1;
    }

    public void addUser(User user) {
        userMap.put(user.getId(), user);
    }
}
