package com.example.khizzipool.myapplication;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import static android.os.Build.VERSION_CODES.M;

public class Admin_Createjob extends AppCompatActivity {
    Spinner spinnerEvent;
    Button buttonPhoto,buttonVideo,DoneCJ;

    String[] Photographers;
    String[] Videographers;

    public String[] events = {"Mehendi","Baraat","Valima","Shooting","Concert"};
    List<String> photographers = new ArrayList<>();
    List<String> videographers = new ArrayList<>();

    public List<String> Selectedphotographers = new ArrayList<>();
    public List<String> Selectedvideographers = new ArrayList<>();

    boolean[] Photo_checkedItems;
    boolean[] Video_checkedItems;

    ArrayList<Integer> PhotoItems = new ArrayList<>();
    ArrayList<Integer> VideoItems = new ArrayList<>();

    DatabaseReference databaseReference;
    DatabaseReference dref;

    EditText setdate,AdvancePay,Budget,Client_name;
    DatePickerDialog.OnDateSetListener dateSetListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_job);

        databaseReference = FirebaseDatabase.getInstance().getReference().child("Employees");
        dref = FirebaseDatabase.getInstance().getReference("Events");

        spinnerEvent = (Spinner) findViewById(R.id.spinnerEvent);

        AdvancePay = (EditText) findViewById(R.id.AdvancePay);
        Budget = (EditText) findViewById(R.id.Budget);
        Client_name = (EditText) findViewById(R.id.Client_name);

        buttonPhoto = (Button) findViewById(R.id.buttonPhoto);
        buttonVideo = (Button) findViewById(R.id.buttonVideo);

        photographers = new ArrayList<>();
        videographers = new ArrayList<>();

        readData(new MyCallback() {
            @Override
            public void onCallback(List<String> photovalue, List<String> videovalue) {

                //converting ArrayList to String array
                Object[] objPhoto = photovalue.toArray();
                Photographers = Arrays.copyOf(objPhoto, objPhoto.length, String[].class);
                Object[] objVideo = videovalue.toArray();
                Videographers = Arrays.copyOf(objVideo, objVideo.length, String[].class);

                Photo_checkedItems = new boolean[Photographers.length];
                Video_checkedItems = new boolean[Videographers.length];

                buttonPhoto.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AlertDialog.Builder builder1 = new AlertDialog.Builder(Admin_Createjob.this);
                        builder1.setTitle("Photographers");
                        builder1.setMultiChoiceItems(Photographers, Photo_checkedItems, new DialogInterface.OnMultiChoiceClickListener()
                        {
                            @Override
                            public void onClick(DialogInterface dialog, int position, boolean isChecked) {
                                if(isChecked)
                                {
                                    if(!PhotoItems.contains(position))
                                    {
                                        PhotoItems.add(position);
                                    }
                                    else
                                    {
                                        PhotoItems.remove(position);
                                    }
                                }
                            }
                        });

                        builder1.setCancelable(false);
                        builder1.setPositiveButton("Done", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                for(int i =0 ; i<PhotoItems.size() ; i++)
                                {
                                    Selectedphotographers.add(Photographers[PhotoItems.get(i)]);
                                }
                            }
                        });

                        builder1.setNegativeButton("Dismiss", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });

                        builder1.setNeutralButton("Clear all", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                for(int i = 0 ; i<Photo_checkedItems.length;i++)
                                {
                                    Photo_checkedItems[i]=false;
                                    PhotoItems.clear();
                                }
                            }
                        });
                        AlertDialog alertDialog = builder1.create();
                        alertDialog.show();
                    }
                });

                buttonVideo.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AlertDialog.Builder builder1 = new AlertDialog.Builder(Admin_Createjob.this);
                        builder1.setTitle("Photographers");
                        builder1.setMultiChoiceItems(Videographers, Video_checkedItems, new DialogInterface.OnMultiChoiceClickListener()
                        {
                            @Override
                            public void onClick(DialogInterface dialog, int position, boolean isChecked) {
                                if(isChecked)
                                {
                                    if(!VideoItems.contains(position))
                                    {
                                        VideoItems.add(position);
                                    }
                                    else
                                    {
                                        VideoItems.remove(position);
                                    }
                                }
                            }
                        });

                        builder1.setCancelable(false);
                        builder1.setPositiveButton("Done", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                for(int i =0 ; i<VideoItems.size() ; i++)
                                {
                                    Selectedvideographers.add(Videographers[VideoItems.get(i)]);
                                }
                            }
                        });

                        builder1.setNegativeButton("Dismiss", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });

                        builder1.setNeutralButton("Clear all", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which)
                            {
                                for(int i = 0 ; i<Video_checkedItems.length;i++)
                                {
                                    Video_checkedItems[i]=false;
                                    VideoItems.clear();
                                }
                            }
                        });
                        AlertDialog alertDialog = builder1.create();
                        alertDialog.show();
                    }
                });
            }
        });

        ArrayAdapter arrayAdapter1 = new ArrayAdapter(Admin_Createjob.this,android.R.layout.simple_spinner_item,events);
        arrayAdapter1.setDropDownViewResource(android.R.layout.simple_expandable_list_item_1);

        spinnerEvent.setAdapter(arrayAdapter1);

        setdate = (EditText) findViewById(R.id.Date);

        setdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH); //To get exact number of days in the month selected

                DatePickerDialog datePickerDialog = new DatePickerDialog(Admin_Createjob.this,android.R.style.Theme_Holo_Light_Dialog_MinWidth,dateSetListener,year,month,day);
                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                datePickerDialog.show();
            }
        });

        dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month++;
                String date = month +"/"+ dayOfMonth +"/"+ year;
                setdate.setText(date);
            }
        };

        DoneCJ = (Button) findViewById(R.id.doneCJ);

        DoneCJ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String date = setdate.getText().toString();
                String clientName = Client_name.getText().toString();
                String Adpay = AdvancePay.getText().toString();
                String budget = Budget.getText().toString();
                String event = spinnerEvent.getSelectedItem().toString();

                if(!date.isEmpty() && !clientName.isEmpty() && !Adpay.isEmpty() && !budget.isEmpty()
                        && !event.isEmpty() && !Selectedphotographers.isEmpty() && !Selectedphotographers.isEmpty())
                {
                    try {
                        String id1 = dref.push().getKey();
                        Events events = new Events(date, clientName, Adpay, budget, event, Selectedphotographers, Selectedvideographers, id1);
                        dref.child(id1).setValue(events);
                        Toast.makeText(Admin_Createjob.this, "Job Created Successfully", Toast.LENGTH_SHORT).show();
                    }catch (Exception e)
                    {
                        Toast.makeText(Admin_Createjob.this,e.toString(),Toast.LENGTH_LONG).show();
                    }
                }
                else
                {
                    Toast.makeText(Admin_Createjob.this,"Please fill All fields",Toast.LENGTH_LONG).show();
                }
            }
        });
    }


    public interface MyCallback {
        void onCallback(List<String> photovalue,List<String> videovalue);
    }

    public void readData(final MyCallback myCallback) {
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String Type = null , name1 = null , name2 = null;
                for(DataSnapshot empSnapshot:dataSnapshot.getChildren())
                {
                    Map<String,Object> map1 = (Map<String,Object>) empSnapshot.getValue();
                    Object type = map1.get("employee_type");
                    Type = type.toString();

                    if(Type.equals("Videographer"))
                    {
                        Map<String,Object> map2a = (Map<String,Object>) empSnapshot.getValue();
                        Object Name1 = map2a.get("first_name");
                        name1 = Name1.toString();

                        Map<String,Object> map2b = (Map<String,Object>) empSnapshot.getValue();
                        Object Name2 = map2a.get("last_name");
                        name2 = Name2.toString();

                        videographers.add(name1 +" "+ name2);

                    }
                    else if(Type.equals("Photographer"))
                    {
                        Map<String,Object> map2a = (Map<String,Object>) empSnapshot.getValue();
                        Object Name1 = map2a.get("first_name");
                        name1 = Name1.toString();

                        Map<String,Object> map2b = (Map<String,Object>) empSnapshot.getValue();
                        Object Name2 = map2a.get("last_name");
                        name2 = Name2.toString();

                        photographers.add(name1 +" "+ name2);
                    }
                }
                myCallback.onCallback(photographers,videographers);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        Intent i = new Intent(Admin_Createjob.this, Admin_home.class);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(i);
    }
}
