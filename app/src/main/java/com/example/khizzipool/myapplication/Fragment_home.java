package com.example.khizzipool.myapplication;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Map;
import java.util.zip.Inflater;

public class Fragment_home extends Fragment {
    private TextView frontType,frontName,drawerName;
    View myview;
    DatabaseReference databaseReference;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        myview = inflater.inflate(R.layout.activity_fragment_home,container,false);

        frontType = (TextView) myview.findViewById(R.id.frontType);
        frontName = (TextView) myview.findViewById(R.id.frontName);
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Employees");
        setValues();
        return myview;
    }

    public void setValues()
    {
        String key = this.getArguments().getString("userkey");
        databaseReference.child(key).addValueEventListener(new ValueEventListener() {
            String Type = null , fName = null , lName = null;
            String Name;
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                Map<String,Object> map1 = (Map<String,Object>) dataSnapshot.getValue();
                Object type = map1.get("employee_type");
                Type = type.toString();

                Map<String,Object> map2 = (Map<String,Object>) dataSnapshot.getValue();
                Object name1 = map2.get("first_name");
                fName = name1.toString();

                Map<String,Object> map3 = (Map<String,Object>) dataSnapshot.getValue();
                Object name2 = map3.get("last_name");
                lName = name2.toString();
                Name = fName +" "+ lName;

                frontName.setText(Name);
                frontType.setText(Type);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
