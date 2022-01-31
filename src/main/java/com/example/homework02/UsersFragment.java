package com.example.homework02;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Assignment:Homework 02
 * File Name: UsersFragment.java
 * Mahitha Garikipati, Pujan Patel
 */
public class UsersFragment extends Fragment {
    ListView listView;
    private static final String ARG_PARAM1 = "ARG_USER";
    private static final String ARG_PARAM2 = "ARG_USER_SORT";
    private static final String ARG_PARAM3 = "ARG_USER_TYPE";
    ArrayList<DataServices.User> users;
    UserAdapter adapter;
    Button filter;
    Button sort;
    ImageView imgView;
    TextView name;
    TextView state;
    TextView age;
    TextView friend;



    public class UserAdapter extends ArrayAdapter<DataServices.User> {


        public UserAdapter(@NonNull Context context, int resource, ArrayList<DataServices.User> users) {
            super(context, resource,users);
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

            if(convertView == null){
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.user_details, parent, false);
            }
            imgView = convertView.findViewById(R.id.imageView);
            name = convertView.findViewById(R.id.name);
            state = convertView.findViewById(R.id.state);
            age = convertView.findViewById(R.id.age);
            friend = convertView.findViewById(R.id.friend);
            DataServices.User user = getItem(position);
            name.setText(user.name);
            state.setText(user.state);
            age.setText(user.age + " "+getString(R.string.Ageold));
            friend.setText(user.group);
            if(user.gender.equalsIgnoreCase(getString(R.string.female))) {
                imgView.setImageResource(R.drawable.avatar_female);
            }
            else{
                imgView.setImageResource(R.drawable.avatar_male);
            }

            return convertView;
        }

    }


    public UsersFragment() {
        // Required empty public constructor
    }
    public static UsersFragment newInstance(String user) {
        UsersFragment fragment = new UsersFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM1, user);
        fragment.setArguments(args);
        return fragment;
    }
    public static UsersFragment newInstance(String sort,String sortType) {
        UsersFragment fragment = new UsersFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM2, sort);
        args.putSerializable(ARG_PARAM3, sortType);
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_users, container, false);
        getActivity().setTitle(R.string.Users);
        listView = view.findViewById(R.id.llistview);
        filter = view.findViewById(R.id.Filter);
        sort = view.findViewById(R.id.sort);
        users = DataServices.getAllUsers();
        if(getArguments()!=null && (String) getArguments().getSerializable(ARG_PARAM1) != null && getArguments().size() == 1){
            if((String) getArguments().getSerializable(ARG_PARAM1) != getString(R.string.allStates)) {
                ArrayList<DataServices.User> latestUsers = new ArrayList<DataServices.User>();
                for (int i = 0; i < users.size(); i++) {
                    if (users.get(i).state == (String) getArguments().getSerializable(ARG_PARAM1)) {
                        latestUsers.add(users.get(i));
                    }
                }
                users = latestUsers;
            }
        }
        if(getArguments()!=null && (String) getArguments().getSerializable(ARG_PARAM2) != null
                && getArguments().size() == 2
        && (String) getArguments().getSerializable(ARG_PARAM3) != null){
            String type = (String) getArguments().getSerializable(ARG_PARAM2);
            String ascending = (String) getArguments().getSerializable(ARG_PARAM3);
            if(ascending.equalsIgnoreCase(getString(R.string.Asc))){
                if(type.equalsIgnoreCase(getString(R.string.Age))){
                    Collections.sort(users, new Comparator<DataServices.User>() {
                        public int compare(DataServices.User v1, DataServices.User v2) {
                            return v1.age- v2.age;
                        }
                    });
                }
                if(type.equalsIgnoreCase(getString(R.string.Name))){
                    Collections.sort(users, new Comparator<DataServices.User>() {
                        public int compare(DataServices.User v1, DataServices.User v2) {
                            return v1.name.compareTo(v2.name);
                        }
                    });
                }
                if(type.equalsIgnoreCase(getString(R.string.state))){
                    Collections.sort(users, new Comparator<DataServices.User>() {
                        public int compare(DataServices.User v1, DataServices.User v2) {
                            return v1.state.compareTo(v2.state);
                        }
                    });
                }
            }
            if(ascending.equalsIgnoreCase(getString(R.string.Dsc))){
                if(type.equalsIgnoreCase(getString(R.string.Age))){
                    Collections.sort(users, new Comparator<DataServices.User>() {
                        public int compare(DataServices.User v1, DataServices.User v2) {
                            return v2.age- v1.age;
                        }
                    });
                }
                if(type.equalsIgnoreCase(getString(R.string.Name))){
                    Collections.sort(users, new Comparator<DataServices.User>() {
                        public int compare(DataServices.User v1, DataServices.User v2) {
                            return v2.name.compareTo(v1.name);
                        }
                    });
                }
                if(type.equalsIgnoreCase(getString(R.string.state))){
                    Collections.sort(users, new Comparator<DataServices.User>() {
                        public int compare(DataServices.User v1, DataServices.User v2) {
                            return v2.state.compareTo(v1.state);
                        }
                    });
                }
            }

            }
        adapter = new UserAdapter(getContext(), R.layout.user_details, users);
        listView.setAdapter(adapter);
        filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.changeToFilter(users);
            }
        });
        sort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.changeToSort(users);
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