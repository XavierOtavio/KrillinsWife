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


        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                User user;
                DataBaseHelper dataBaseHelper = new DataBaseHelper(DataBaseTestActivity.this);

                ArrayList<User> users = dataBaseHelper.readUsers();
                boolean userExists = false;
                for (User u : users) {
                    if (u.getUsername().equals(username.getText().toString())) {
                        userExists = true;
                        break;
                    }
                }

                if (userExists) {
                    Toast.makeText(getApplicationContext(), "Username already exists", Toast.LENGTH_SHORT).show();
                } else {
                    try {

                        String encryptedPassword = dataBaseHelper.encryptPassword(password.getText().toString());

                        user = new User(-1, username.getText().toString(), encryptedPassword, name.getText().toString());
                        Toast.makeText(getApplicationContext(), user.toString(), Toast.LENGTH_SHORT).show();
                    } catch (Exception e) {
                        user = new User(-1, "error", "error", "error");
                        Toast.makeText(getApplicationContext(), "Error creating user", Toast.LENGTH_SHORT).show();
                    }

                    boolean success = dataBaseHelper.addOne(user);
                    Toast.makeText(getApplicationContext(), "Success = " + success, Toast.LENGTH_SHORT).show();

                    if (success){
                        Intent intent = new Intent(getApplicationContext(), activity_db_login.class);
                        startActivity(intent);
                    }
                }

            }
        });

    }

    public void backToLogin(View view) {
        finish();
    }
}
