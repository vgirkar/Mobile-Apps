package com.example.homework02;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collections;

public class FilterFragment extends Fragment {
    /**
     * Assignment:Homework 02
     * File Name: FilterFragment.java
     * Mahitha Garikipati, Pujan Patel
     */
    private static final String ARG_PARAM1 = "ARG_FILTER";
    private ArrayList<DataServices.User> user = new ArrayList<DataServices.User>();

    public FilterFragment() {
        // Required empty public constructor
    }

    public static FilterFragment newInstance(ArrayList<DataServices.User> user) {
        FilterFragment fragment = new FilterFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM1, user);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            this.user = (ArrayList<DataServices.User>) getArguments().getSerializable(ARG_PARAM1);
        }
    }
    ListView listView;
    ArrayAdapter<String> adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ArrayList<String> states = new ArrayList<String>();
        View view = inflater.inflate(R.layout.fragment_filter, container, false);
        getActivity().setTitle(R.string.FilterState);
        listView = view.findViewById(R.id.statesList);
        for(int i=0; i < user.size(); i++){
            if(!states.contains(user.get(i).state))
            states.add(user.get(i).state);
        }
        Collections.sort(states);
        states.add(0,getString(R.string.allStates));
        adapter = new ArrayAdapter(getContext(), android.R.layout.simple_list_item_1, android.R.id.text1, states);
        listView.setAdapter(adapter);
         listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                getActivity().getSupportFragmentManager().popBackStack();
                mListener.changeToUsers(states.get(position));
            }
        });

        return view;
    }
    IListener mListener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        if(context instanceof IListener){
            mListener = (IListener) context;
        }else{
            throw new RuntimeException(context.toString() + getString(R.string.InterfaceMsg));
        }

    }
}