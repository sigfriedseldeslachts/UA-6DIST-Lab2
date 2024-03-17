package be.uantwerpen.lab2.models;

import java.util.ArrayList;

public class User {

    private long id;

    private String name;

    private final ArrayList<Long> availableAccounts = new ArrayList<>();

    public User(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addAccount(long accountId) {
        availableAccounts.add(accountId);
    }

    public void removeAccount(long accountId) {
        availableAccounts.remove(accountId);
    }

    public ArrayList<Long> getAvailableAccounts() {
        return availableAccounts;
    }
}
