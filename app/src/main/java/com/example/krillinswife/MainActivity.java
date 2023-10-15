package com.example.krillinswife;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.content.Intent;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void goToDataBaseTest(View view) {
        Intent intent = new Intent(this, DataBaseTestActivity.class);
        startActivity(intent);
    }
}

