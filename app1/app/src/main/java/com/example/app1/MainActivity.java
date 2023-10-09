package com.example.app1;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.view.View;
import android.widget.AdapterView;
import android.content.Intent;
import android.net.Uri;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Class1 callClass = new Class1();
    private ListView dataShow;
    private List<String> ListaTelefonica;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dataShow = (ListView) findViewById(R.id.textView2);
        Spinner friends = (Spinner) findViewById(R.id.spinner);


        friends.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                String friend = parentView.getSelectedItem().toString();
                ListaTelefonica = callClass.getPhoneNumber(friend);
                ArrayAdapter<String> adapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, ListaTelefonica);
                dataShow.setAdapter(adapter);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {

            }
        });


        dataShow.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String phoneNumber = ListaTelefonica.get(position);
                if (!phoneNumber.equals("NA")) {

                    Intent callIntent = new Intent(Intent.ACTION_CALL);
                    callIntent.setData(Uri.parse("tel:" + phoneNumber));
                    startActivity(callIntent);
                }
            }
        });
    }

    public void onClickFindFriend(View view){

    }
}