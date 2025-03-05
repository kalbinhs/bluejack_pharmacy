package com.example.assignment1;

import java.util.ArrayList;

public class TransactionDatabase {
    private static TransactionDatabase instance;
    private ArrayList<Transaction> myList;
    private String loggedIn;

    public String getLoggedIn() {
        return loggedIn;
    }

    public void setLoggedIn(String loggedIn) {
        this.loggedIn = loggedIn;
    }

    private TransactionDatabase() {
        myList = new ArrayList<>();
    }

    public static synchronized TransactionDatabase getInstance() {
        if (instance == null) {
            instance = new TransactionDatabase();
        }
        return instance;
    }

    public ArrayList<Transaction> getMyList() {
        return myList;
    }

    public void addToMyList(String[] items) {
        Transaction transaction = new Transaction(items[0],items[1],items[2],items[3],loggedIn);
        myList.add(transaction);
    }

    public void removeFromMyList(int position) {
        myList.remove(position);
    }

    public void updateMyList(int index, String[] items) {
        Transaction transaction = new Transaction(items[0],items[1],items[2],items[3],loggedIn);
        myList.set(index, transaction);
    }
}
