package com.example.krillinswife;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
public class activity_db_update extends AppCompatActivity {

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_db_update);
        }

        public void goToRegister(View view) {
            Intent intent = new Intent(this, DataBaseTestActivity.class);
            startActivity(intent);
        }
}
