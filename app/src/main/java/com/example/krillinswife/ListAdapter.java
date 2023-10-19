package com.example.krillinswife;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class ListAdapter extends ArrayAdapter<ListData> {

    Home binding;

    public ListAdapter(@NonNull Context context, ArrayList<ListData> dataArrayList, Home binding) {
        super(context, R.layout.list_item, dataArrayList);
        this.binding = binding;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View view, @NonNull ViewGroup parent) {
        ListData listData = getItem(position);
        if (view == null) {
            view = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }

        TextView username = view.findViewById(R.id.idUsername);
        TextView name = view.findViewById(R.id.idName);
        assert listData != null;
        username.setText(listData.username);
        name.setText(listData.name);

        ImageView edit = view.findViewById(R.id.left_view);
        ImageView delete = view.findViewById(R.id.right_view);

        edit.setOnClickListener(view1 -> {
            Intent intent = new Intent(getContext(), activity_db_update.class);
            intent.putExtra("title", binding.getSessionName());
            intent.putExtra("name", listData.name);
            intent.putExtra("id", listData.id);
            getContext().startActivity(intent);
            binding.finish();
        });

        delete.setOnClickListener(view2 -> {
            try (DataBaseHelper dataBaseHelper = new DataBaseHelper(getContext())) {
                if (dataBaseHelper.deleteOne(listData.id)) {
                    Toast.makeText(getContext(), "User deleted successfully", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getContext(), "Error deleting user", Toast.LENGTH_SHORT).show();
                }
                Intent intent = new Intent(getContext(), Home.class);
                intent.putExtra("name", binding.getSessionName());
                getContext().startActivity(intent);
                binding.finish();
            } catch (Exception e) {
                Toast.makeText(getContext(), "Error deleting user", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }
}