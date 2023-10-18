package com.example.krillinswife;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Intent;
import java.util.ArrayList;

public class DataBaseTestActivity extends AppCompatActivity {

    Button btnCreate, btnView;
    TextView username, password, name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_db_register);

        btnCreate = findViewById(R.id.btnCreate);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        name = findViewById(R.id.name);
        btnView = findViewById(R.id.button); // Assuming you have assigned the button ID in your XML layout

        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                User user;

                try {
                    user = new User(-1, username.getText().toString(), password.getText().toString(), name.getText().toString());
                    Toast.makeText(getApplicationContext(), user.toString(), Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    user = new User(-1, "error", "error", "error");
                    Toast.makeText(getApplicationContext(), "Error creating user", Toast.LENGTH_SHORT).show();
                }

                DataBaseHelper dataBaseHelper = new DataBaseHelper(DataBaseTestActivity.this);
                boolean success = dataBaseHelper.addOne(user);
                Toast.makeText(getApplicationContext(), "Success = " + success, Toast.LENGTH_SHORT).show();
            }
        });

        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DataBaseTestActivity.this, Home.class);
                startActivity(intent);
            }
        });
    }

    public void goToLogin(View view) {
        Intent intent = new Intent(this, activity_db_login.class);
        startActivity(intent);
    }
}
