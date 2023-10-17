package com.example.krillinswife;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class activity_view_user extends AppCompatActivity {

    // creating variables for our array list,
    // dbhandler, adapter and recycler view.
    private ArrayList<User> UserArrayList;
    private DataBaseHelper dbHandler;
    private UserRVAdapter courseRVAdapter;
    private RecyclerView coursesRV;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_user);

        // initializing our all variables.
        UserArrayList = new ArrayList<>();
        dbHandler = new DataBaseHelper(activity_view_user.this);

        // getting our course array
        // list from db handler class.
        UserArrayList = dbHandler.readUsers();
        System.out.println(UserArrayList.get(0) + " " + UserArrayList.get(1));

        // on below line passing our array list to our adapter class.
        courseRVAdapter = new UserRVAdapter(UserArrayList, activity_view_user.this);
        coursesRV = findViewById(R.id.idRVUser);

        // setting layout manager for our recycler view.
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(activity_view_user.this, RecyclerView.VERTICAL, false);
        coursesRV.setLayoutManager(linearLayoutManager);

        // setting our adapter to recycler view.
        coursesRV.setAdapter(courseRVAdapter);
    }
}
