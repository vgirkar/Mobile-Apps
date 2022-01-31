package com.example.homework02;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import java.util.ArrayList;

/**
 *Mahitha Garikipati, Pujan Patel
 *Homework 02
 * SortFragement.java
 */
public class SortFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    ArrayList<DataServices.User> users;
    RecyclerView recyclerView;
    sortAdapter adapter;
    Button asc;
    Button desc;
    public  ArrayList<String> sort = new ArrayList<String>();

    public SortFragment(ArrayList<DataServices.User> users){
        this.users = users;
    }

    public SortFragment() {
        // Required empty public constructor
    }

    public static SortFragment newInstance(ArrayList<DataServices.User> users) {
        SortFragment fragment = new SortFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM1,users );
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            users = (ArrayList<DataServices.User>)getArguments().get(ARG_PARAM1);
        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sort, container, false);
        recyclerView = view.findViewById(R.id.RecycleView);
        getActivity().setTitle(R.string.Sort);
        sort.add(getString(R.string.Age));
        sort.add(getString(R.string.Name));
        sort.add(getString(R.string.state));
        adapter = new sortAdapter(sort);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerView.setAdapter(adapter);
        asc = view.findViewById(R.id.ascending);
        desc = view.findViewById(R.id.descending);
        recyclerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int itemPosition = recyclerView.getChildLayoutPosition(view);
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

    public class sortAdapter extends RecyclerView.Adapter<sortAdapter.sortHolder>{
        ArrayList<String> arraySort = new ArrayList<String>();
        public class sortHolder extends  RecyclerView.ViewHolder{
            TextView sortTv;
            ImageButton ascending;
            ImageButton descending;
            int position;
            public sortHolder(@NonNull View itemView) {
                super(itemView);
                sortTv = itemView.findViewById(R.id.sortBy);
                ascending = itemView.findViewById(R.id.ascending);
                descending = itemView.findViewById(R.id.descending);
                ascending.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        getActivity().getSupportFragmentManager().popBackStack();
                        mListener.changeToUsersSort(sort.get(position),getString(R.string.Asc));
                    }
                });
                descending.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        getActivity().getSupportFragmentManager().popBackStack();
                        mListener.changeToUsersSort(sort.get(position),getString(R.string.Dsc));
                    }
                });
            }
        }
        public  sortAdapter(ArrayList<String> arraySort){
            this.arraySort = arraySort;
        }

        @NonNull
        @Override
        public sortHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.sort_details,parent,false);
            sortHolder sortHolder = new sortHolder(view);
            return sortHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull sortHolder holder, int position) {
            String sortName = sort.get(position);
            holder.sortTv.setText(sort.get(position));
            holder.position = holder.getAdapterPosition();
        }

        @Override
        public int getItemCount() {
            return this.arraySort.size();
        }


    }
}