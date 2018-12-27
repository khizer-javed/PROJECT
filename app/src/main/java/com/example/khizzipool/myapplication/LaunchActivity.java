package com.example.khizzipool.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class LaunchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);

        final Thread thread = new Thread()
        {
            @Override
            public void run()
            {
                try
                {
                    sleep(3000);

                }catch(Exception e)
                {e.printStackTrace();
                }finally
                {
                    Intent intent = new Intent(LaunchActivity.this,User_Login.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                    finish();
                }
            }
        }; thread.start();
    }
}
