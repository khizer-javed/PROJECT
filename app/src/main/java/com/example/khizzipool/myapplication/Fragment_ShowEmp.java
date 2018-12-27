package com.example.khizzipool.myapplication;

import android.app.ProgressDialog;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.Query;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class Fragment_ShowEmp extends Fragment {
    View view;
    RecyclerView recyclerView;
    ProgressDialog progressDialog;
    EmployeeInfoAdapter employeeInfoAdapter;
    private ArrayList<Employee> employeeList;
    DatabaseReference databaseReference;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_fragment_showemp,container,false);

        databaseReference = FirebaseDatabase.getInstance().getReference("Employees");
        employeeList = new ArrayList<>();
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

    private void loadData()
    {
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot employeeSnapshot:dataSnapshot.getChildren())
                {
                    Employee employee = employeeSnapshot.getValue(Employee.class);
                    employeeList.add(employee);
                }
                progressDialog.dismiss();
                EmployeeInfoAdapter employeeInfoAdapter = new EmployeeInfoAdapter(getContext(),employeeList);
                recyclerView.setAdapter(employeeInfoAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void init()
    {
        recyclerView = (RecyclerView) view.findViewById(R.id.listemp);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }
}
