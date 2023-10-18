package com.example.krillinswife;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class activity_db_login extends AppCompatActivity {

    private EditText editTextUsername, editTextPassword;
    private Button buttonLogin;
    private TextView textViewMessage;
    private SQLiteDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_db_login);

        DataBaseHelper dbHelper = new DataBaseHelper(this);
        database = dbHelper.getWritableDatabase();

        buttonLogin = findViewById(R.id.btnSignin);
        textViewMessage = findViewById(R.id.textViewMessage);

        buttonLogin.setOnClickListener(view -> {
            editTextUsername = findViewById(R.id.username);
            editTextPassword = findViewById(R.id.password);

            String username = editTextUsername.getText().toString();
            String password = editTextPassword.getText().toString();

            // Check credentials in the database
            Cursor cursor = database.rawQuery("SELECT * FROM USER_TABLE WHERE username=? AND password=?",
                    new String[]{username, password});

            if (cursor.moveToFirst()) {

                String name = cursor.getString(3);
                // Login successful, redirect to another activity
                String welcomeMessage = "Olá " + name;
                textViewMessage.setText(welcomeMessage);
                Intent intent = new Intent(activity_db_login.this, Home.class);
                intent.putExtra("name", name);
                startActivity(intent);
                finish();
            } else {
                // Login failed, show error message
                textViewMessage.setText("Invalid username or password");
            }

            cursor.close();
        });
    }

    public void goToRegister(View view) {
        Intent intent = new Intent(this, DataBaseTestActivity.class);
        startActivity(intent);
    }

}