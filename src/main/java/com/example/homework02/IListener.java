package com.example.homework02;

import java.util.ArrayList;

public interface IListener {
    /**
     * Assignment:Homework 02
     * File Name: IListener.java
     * Mahitha Garikipati, Pujan Patel
     */
    void changeToFilter(ArrayList<DataServices.User> users);
    void changeToSort(ArrayList<DataServices.User> users);
    void changeToUsers(String users);
    void changeToUsersSort(String name, String sortType);
}
