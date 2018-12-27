package com.example.khizzipool.myapplication;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class User_Login extends AppCompatActivity {

    private ImageButton emp, gst;
    final Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_login);

        emp= (ImageButton) findViewById(R.id.empId);
        gst= (ImageButton) findViewById(R.id.guestId);
    }

    public void employee(View view)
    {
        Intent i = new Intent(User_Login.this,Employee_signIn.class);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(i);
    }

    public void admin(View view)
    {
        Intent i = new Intent(User_Login.this,Admin_signIn.class);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(i);
    }
}
