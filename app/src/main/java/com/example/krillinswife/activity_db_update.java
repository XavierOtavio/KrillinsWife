package com.example.krillinswife;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class activity_db_update extends AppCompatActivity {

    DataBaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_db_update);

        try (DataBaseHelper dataBaseHelper = new DataBaseHelper(this)) {
            dbHelper = dataBaseHelper;

            String title = getIntent().getStringExtra("name");
            int Id = getIntent().getIntExtra("id", -1);
            if (Id == -1) {
                Toast.makeText(getApplicationContext(), "Error getting user", Toast.LENGTH_SHORT).show();
                finish();
            }

            TextView titleView = findViewById(R.id.title);
            titleView.setText(String.format("Edit %s", title));

            User user = dbHelper.findUser(Id);

            EditText editTextUsername = findViewById(R.id.username);
            EditText editTextPassword = findViewById(R.id.password);
            EditText editTextName = findViewById(R.id.name);

            editTextUsername.setText(user.getUsername());
            editTextPassword.setText(user.getPassword());
            editTextName.setText(user.getName());

            Button buttonUpdate = findViewById(R.id.btnUpdate);
            Button buttonCancel = findViewById(R.id.btnCancel);

            buttonUpdate.setOnClickListener(view -> {
                String username = editTextUsername.getText().toString();
                String password = editTextPassword.getText().toString();
                String name = editTextName.getText().toString();

                User user1 = new User(Id, username, password, name);
                if (dbHelper.updateOne(user1)) {
                    Toast.makeText(getApplicationContext(), "User updated successfully", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Error updating user", Toast.LENGTH_SHORT).show();
                }
            });

            buttonCancel.setOnClickListener(view -> {
                Intent intent = new Intent(activity_db_update.this, Home.class);
                intent.putExtra("name", getIntent().getStringExtra("title"));
                startActivity(intent);
                finish();
            });
        } catch (Exception e) {
            Toast toast = Toast.makeText(getApplicationContext(), "Error reading users", Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    public void goToRegister(View view) {
        finish();
    }
}
