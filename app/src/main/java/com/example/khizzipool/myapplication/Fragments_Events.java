package com.example.khizzipool.myapplication;

import android.app.ProgressDialog;
import android.app.usage.UsageEvents;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Fragments_Events extends Fragment {
    View view;
    RecyclerView recyclerView;
    ProgressDialog progressDialog;
    EventAdapter eventAdapter;
    private ArrayList<Events> eventslist;
    DatabaseReference databaseReference;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_fragment_events,container,false);
        databaseReference = FirebaseDatabase.getInstance().getReference("Events");
        eventslist = new ArrayList<>();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setTitle("Loading");
        progressDialog.setMessage("Syncing");
        progressDialog.setCancelable(false);
        progressDialog.show();
        loadData();
    }

    private void loadData() {
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot eventsSnapshot:dataSnapshot.getChildren())
                {
                    Events events = eventsSnapshot.getValue(Events.class);
                    eventslist.add(events);
                }
                progressDialog.dismiss();
                EventAdapter eventAdapter = new EventAdapter(getContext(),eventslist);
                recyclerView.setAdapter(eventAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void init() {
        recyclerView = (RecyclerView) view.findViewById(R.id.RecyclerEvents);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }


}
