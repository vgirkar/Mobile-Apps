package com.example.homework02;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.homework02.DataServices.User;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements IListener{
    /**
     * Assignment:Homework 02
     * File Name: MainActivity.java
     * Mahitha Garikipati, Pujan Patel
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle(R.string.Users);
        getSupportFragmentManager().beginTransaction()
                .add(R.id.mainFragment, new UsersFragment(), getString(R.string.Users))
                .commit();
    }

    @Override
    public void changeToFilter(ArrayList<User> users) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.mainFragment, FilterFragment.newInstance(users),getString(R.string.Filter))
                .addToBackStack(String.valueOf(new UsersFragment()))
                .commit();
    }

    @Override
    public void changeToSort(ArrayList<DataServices.User> users) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.mainFragment, SortFragment.newInstance(users),getString(R.string.Sort))
                .addToBackStack(String.valueOf(new UsersFragment()))
                .commit();
    }

    @Override
    public void changeToUsers(String filter) {
        getSupportFragmentManager().beginTransaction().
                replace(R.id.mainFragment, UsersFragment.newInstance(filter),getString(R.string.Users))
                .commit();
    }
    public void changeToUsersSort(String name,String sortType) {
        getSupportFragmentManager().beginTransaction().
                replace(R.id.mainFragment, UsersFragment.newInstance(name,sortType),getString(R.string.Users))
                .commit();
    }
}