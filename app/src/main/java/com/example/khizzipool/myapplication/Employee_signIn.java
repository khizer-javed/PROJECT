package com.example.khizzipool.myapplication;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.Map;

public class Employee_signIn extends AppCompatActivity {

    EditText userName, pwdEmp;
    private DatabaseReference databaseReference;
    boolean gotUser = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_sign_in);
        userName = (EditText) findViewById(R.id.userNameEmp);
        pwdEmp = (EditText) findViewById(R.id.pwdEmp);



        databaseReference = FirebaseDatabase.getInstance().getReference().child("Employees");
    }

    public void gotoHome(View view)
    {
        final String Uname = userName.getText().toString();
        final String pwd = pwdEmp.getText().toString();

        if(!Uname.isEmpty() && !pwd.isEmpty())
        {
            databaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    String Name=null,pass=null,Type=null , Last_Name = null;
                    for(DataSnapshot empSnapshot:dataSnapshot.getChildren())
                    {
                        Map<String,Object> map1 = (Map<String,Object>) empSnapshot.getValue();
                        Object name = map1.get("first_name");
                        Name = name.toString();

                        Map<String,Object> map2 = (Map<String,Object>) empSnapshot.getValue();
                        Object ps = map2.get("password");
                        pass = ps.toString();

                        if(Name.equals(Uname) && pass.equals(pwd))
                        {
                            Map<String,Object> map3 = (Map<String,Object>) empSnapshot.getValue();
                            Object last_name = map3.get("last_name");
                            Last_Name = last_name.toString();

                            gotUser = true;
                            String key = empSnapshot.getKey();

                            Bundle bundle = new Bundle();
                            bundle.putString("Name",Name);
                            bundle.putString("Last_Name",Last_Name);
                            bundle.putString("userkey",key);
                            Intent i = new Intent(Employee_signIn.this,Emp_home.class);
                            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK |Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            i.putExtras(bundle);
                            startActivity(i);
                            Toast.makeText(getApplicationContext(), "Welcome "+ Uname,Toast.LENGTH_SHORT).show();
                        }
                    }
                    if(!gotUser)
                        Toast.makeText(getApplicationContext(), "Wrong User Name OR Password", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }
        else {
            Toast.makeText(this, "Please Fill all fields",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(Employee_signIn.this, User_Login.class);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(i);
    }
}