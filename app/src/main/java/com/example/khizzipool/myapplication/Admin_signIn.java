package com.example.khizzipool.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Admin_signIn extends AppCompatActivity {
    EditText userName,pwdAdmin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_sign_in);

        userName = (EditText) findViewById(R.id.userNameAdmin);
        pwdAdmin = (EditText) findViewById(R.id.pwdAdmin);
    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(Admin_signIn.this,User_Login.class);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(i);
    }

    public void Adminhome(View view)
    {
        String Uname = userName.getText().toString();
        String pwd = pwdAdmin.getText().toString();

        if(!Uname.isEmpty() && !pwd.isEmpty())
        {
            if (Uname.equals("khizer")) {
                if (pwd.equals("abc")) {
                    Intent i = new Intent(Admin_signIn.this, Admin_home.class);
                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(i);
                    Toast.makeText(this, "Welcome Khizer", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "Invalid Password", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(this, "Invalid User Name", Toast.LENGTH_SHORT).show();
            }
        }
        else {
            Toast.makeText(this, "Please Fill all fields", Toast.LENGTH_SHORT).show();
        }
    }
}
