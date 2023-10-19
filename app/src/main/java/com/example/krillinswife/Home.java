package com.example.krillinswife;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.krillinswife.databinding.ActivityHomeBinding;

import java.util.ArrayList;

public class Home extends AppCompatActivity {
    ActivityHomeBinding binding;
    ListAdapter listAdapter;
    ArrayList<ListData> dataArrayList = new ArrayList<>();

    ArrayList<User> users = new ArrayList<>();
    ListData listData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot()); // Set the root view of the binding object

        // Get the username from the intent and display the welcome message
        String name = getIntent().getStringExtra("name");
        String welcomeMessage = "Bem vindo, " + name;

        // Access the TextView using the binding object
        TextView textViewWelcome = binding.Hello;

        // Set the text to the TextView
        textViewWelcome.setText(welcomeMessage);

        setContentView(binding.getRoot());

        try(DataBaseHelper dataBaseHelper = new DataBaseHelper(Home.this)) {
            users = dataBaseHelper.readUsers();
        } catch (Exception e) {
            Toast toast = Toast.makeText(getApplicationContext(), "Error reading users", Toast.LENGTH_SHORT);
            toast.show();
            users.add(new User(-1, "error", "error", "error"));
        }

        for (int i = 0; i < users.size(); i++) {
            listData = new ListData(users.get(i).getId(),users.get(i).getName(), users.get(i).getUsername());
            dataArrayList.add(listData);
        }


        listAdapter = new ListAdapter(this, dataArrayList, this);
        binding.listUsers.setAdapter(listAdapter);
        binding.listUsers.setClickable(true);
    }

    public void goToLogin(View view) {
        finish();
        Intent intent = new Intent(Home.this, activity_db_login.class);
        startActivity(intent);
    }

    public String getSessionName() {
        return getIntent().getStringExtra("name");
    }
}