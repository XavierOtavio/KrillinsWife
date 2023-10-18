package com.example.krillinswife;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.krillinswife.databinding.ActivityHomeBinding;

import java.util.ArrayList;

public class Home extends AppCompatActivity {
    ActivityHomeBinding binding;
    ListAdapter listAdapter;
    ArrayList<ListData> dataArrayList = new ArrayList<>();

    ListData listData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ArrayList<User> users = new DataBaseHelper(this).readUsers();
//        ArrayList<User> users = new ArrayList<>();
//        for(int i = 0; i < 10; i++){
//            users.add(new User(i, "user" + i, ""+i, "name" + i));
//        }
        for (int i = 0; i < users.size(); i++) {
            listData = new ListData(users.get(i).getName(), users.get(i).getUsername());
            dataArrayList.add(listData);
        }


        listAdapter = new ListAdapter(this, dataArrayList);
        binding.listUsers.setAdapter(listAdapter);
        binding.listUsers.setClickable(true);
//        binding.listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                Intent intent = new Intent(MainActivity.this, DetailedActivity.class);
//                intent.putExtra("name", nameList[i]);
//                intent.putExtra("time", timeList[i]);
//                intent.putExtra("ingredients", ingredientList[i]);
//                intent.putExtra("desc", descList[i]);
//                intent.putExtra("image", imageList[i]);
//                startActivity(intent);
//            }
//        });
    }
}