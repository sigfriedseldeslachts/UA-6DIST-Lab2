package be.uantwerpen.lab2.models;

import java.util.ArrayList;
import java.util.Random;

public class BankAccount {

    private float balance = 0;

    private final long bankNumber;
    private final long ownerId;

    private final ArrayList<Long> extraMembers = new ArrayList<>();

    public BankAccount(long ownerId) {
        this.bankNumber = 100000 + (new Random()).nextInt(900000); // Generate a random bank account number
        this.ownerId = ownerId;
    }

    public long getBankNumber() {
        return bankNumber;
    }

    public long getOwnerId() {
        return ownerId;
    }

    public ArrayList<Long> getExtraMembers() {
        return extraMembers;
    }

    public boolean addExtraMember(long userId) {
        return extraMembers.add(userId);
    }

    public boolean removeExtraMember(long userId) {
        return extraMembers.remove(userId);
    }

    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }
}
