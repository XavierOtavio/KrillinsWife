package com.example.krillinswife;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class activity_db_login extends AppCompatActivity {

    private EditText editTextUsername, editTextPassword;
    private TextView textViewMessage;
    private SQLiteDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_db_login);

        try (DataBaseHelper dbHelper = new DataBaseHelper(this)) {
            database = dbHelper.getWritableDatabase();


            Button buttonLogin = findViewById(R.id.btnSignin);
            textViewMessage = findViewById(R.id.textViewMessage);

            buttonLogin.setOnClickListener(view -> {
                editTextUsername = findViewById(R.id.username);
                editTextPassword = findViewById(R.id.password);

                String username = editTextUsername.getText().toString();
                String password = editTextPassword.getText().toString();

                // Check credentials in the database
                String encryptedPassword = dbHelper.encryptPassword(password);
                User user = dbHelper.checkUser(username, encryptedPassword);
                if (user != null) {
                    // Login successful, redirect to another activity
                    Intent intent = new Intent(activity_db_login.this, Home.class);
                    intent.putExtra("name", user.getName());
                    startActivity(intent);
                } else {
                    // Login failed, show error message
                    textViewMessage.setText(R.string.invalid_login);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void goToRegister(View view) {
        Intent intent = new Intent(this, DataBaseTestActivity.class);
        startActivity(intent);
    }

}