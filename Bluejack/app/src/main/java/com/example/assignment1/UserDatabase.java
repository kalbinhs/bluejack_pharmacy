package com.example.assignment1;

import java.util.ArrayList;

public class UserDatabase {
    private static UserDatabase instance;
    private ArrayList<String[]> myList;

    private UserDatabase() {
        myList = new ArrayList<>();
    }

    public static synchronized UserDatabase getInstance() {
        if (instance == null) {
            instance = new UserDatabase();
        }
        return instance;
    }

    public ArrayList<String[]> getMyList() {
        return myList;
    }

    public void addToMyList(String[] details) {
        myList.add(details);
    }
}
