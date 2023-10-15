package com.example.krillinswife;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class DataBaseTestActivity extends AppCompatActivity {

    Button btnCreate;
    TextView username, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_base_test);

        btnCreate = (Button) findViewById(R.id.btnCreate);
        username = (TextView) findViewById(R.id.username);
        password = (TextView) findViewById(R.id.password);

       btnCreate.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {

               Person person;

                try {
                    person = new Person( -1, username.getText().toString(), password.getText().toString());
                    Toast.makeText(getApplicationContext(), person.toString(), Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    person = new Person( -1, "error", "error");
                    Toast.makeText(getApplicationContext(), "Error creating person", Toast.LENGTH_SHORT).show();
                }

               DataBaseHelper dataBaseHelper = new DataBaseHelper(DataBaseTestActivity.this);
               boolean success = dataBaseHelper.addOne(person);
                Toast.makeText(getApplicationContext(), "Success = " + success, Toast.LENGTH_SHORT).show();
           }
       });
    }
}