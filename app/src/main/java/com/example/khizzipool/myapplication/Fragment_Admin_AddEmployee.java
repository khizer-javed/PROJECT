package com.example.khizzipool.myapplication;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Fragment_Admin_AddEmployee extends Fragment {
    View view;
    private Spinner spinner;
    private DatabaseReference databaseReference;
    private EditText F_name,L_name,pwd,con_pwd,phone_num,email;
    private Button Done;
    Admin_Createjob CJ;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_fragment__admin__add_employee,container,false);

        String[] category = {"Photographer","Videographer","Editor"};
        spinner = (Spinner) view.findViewById(R.id.spinner);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this.getActivity(),android.R.layout.simple_spinner_item,category);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_expandable_list_item_1);
        spinner.setAdapter(arrayAdapter);

        F_name = (EditText) view.findViewById(R.id.F_name);
        L_name = (EditText) view.findViewById(R.id.L_name);
        pwd = (EditText) view.findViewById(R.id.pwd);
        con_pwd = (EditText) view.findViewById(R.id.con_pwd);
        phone_num = (EditText) view.findViewById(R.id.phone_num);
        email = (EditText) view.findViewById(R.id.email);

        databaseReference = FirebaseDatabase.getInstance().getReference("Employees");
        Done = view.findViewById(R.id.done);
        //Done button Action
        Done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String first_name = F_name.getText().toString();
                String last_name = L_name.getText().toString();
                String phoneNum = phone_num.getText().toString();
                String password = pwd.getText().toString();
                String con_password = con_pwd.getText().toString();
                String Email = email.getText().toString();
                String employee_type = spinner.getSelectedItem().toString();

                if(!first_name.isEmpty() && !last_name.isEmpty() && !phoneNum.isEmpty() &&
                        !password.isEmpty() && !con_password.isEmpty() && !employee_type.isEmpty()) //Checking Empty Fields
                {
                    if(password.equals(con_password)) //Checking Password Match
                    {
                        if(matches(phoneNum)) //Checking Valid Phone# (line # 99)
                        {
                            if(Email.isEmpty() || android.util.Patterns.EMAIL_ADDRESS.matcher(Email).matches())  //Checking Valid Email
                            {
                                String id = databaseReference.push().getKey();
                                Employee employee = new Employee(first_name, last_name, password, phoneNum, Email, employee_type,id);
                                databaseReference.child(id).setValue(employee);
                                Toast.makeText(getActivity(),"Employee Added",Toast.LENGTH_SHORT).show();
                            }
                            else
                            {
                                email.setError("Enter Valid Email");
                            }
                        }
                        else
                        {
                            phone_num.setError("Enter Correct Phone #");
                        }
                    }
                    else
                    {
                        con_pwd.setError("Password do not Match");
                    }
                }
                else
                {
                    Toast.makeText(getActivity(),"Please fill All fields", Toast.LENGTH_LONG).show();
                }
            }
        });
        return view;
    }

    public boolean matches(String phone)
    {
        boolean check;
        Pattern pattern = Pattern.compile("^03\\d{9}$");
        Matcher matcher = pattern.matcher(phone);
        if(matcher.find())
            check = true;
        else
            check = false;

        return check;
    }
}