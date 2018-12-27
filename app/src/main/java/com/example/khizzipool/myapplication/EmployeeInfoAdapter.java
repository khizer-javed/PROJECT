package com.example.khizzipool.myapplication;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

public class EmployeeInfoAdapter extends RecyclerView.Adapter<EmployeeInfoAdapter.RecyclerViewHolder> {
    private Context context;
    private ArrayList<Employee> employeeList;

    public EmployeeInfoAdapter(Context context, ArrayList<Employee> employeeList) {
        this.context = context;
        this.employeeList = employeeList;
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View listview = LayoutInflater.from(context).inflate(R.layout.list_emp,viewGroup,false);
        return new RecyclerViewHolder(listview);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder recyclerViewHolder, int position) {
        Employee employee = employeeList.get(position);
       String name = employee.getFirst_name()+" "+employee.getLast_name();
       String type = employee.getEmployee_type();

        recyclerViewHolder.empname.setText(name);
        recyclerViewHolder.emptype.setText(type);

    }

    @Override
    public int getItemCount() {
       return employeeList.size();
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder
    {
        public TextView empname;
        public TextView emptype;

        public RecyclerViewHolder(@NonNull View listview) {
            super(listview);
            empname = (TextView) listview.findViewById(R.id.empName);
            emptype = (TextView) listview.findViewById(R.id.empType);
        }
    }
}
