package com.example.krillinswife;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class ListAdapter extends ArrayAdapter<ListData> {
    public ListAdapter(@NonNull Context context, ArrayList<ListData> dataArrayList) {
        super(context, R.layout.list_item, dataArrayList);
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
        username.setText(listData.username);
        name.setText(listData.name);

        return view;
    }
}