package com.example.krillinswife;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.krillinswife.User;

import java.util.ArrayList;

public class UserRVAdapter extends RecyclerView.Adapter<UserRVAdapter.ViewHolder> {

    // variable for our array list and context
    private ArrayList<User> UserArrayList;
    private Context context;

    // constructor
    public UserRVAdapter(ArrayList<User> UserArrayList, Context context) {
        this.UserArrayList = UserArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // on below line we are inflating our layout
        // file for our recycler view items.
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_rv_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // on below line we are setting data
        // to our views of recycler view item.
        User user = UserArrayList.get(position);
        holder.username.setText(user.getUsername());
        holder.password.setText(user.getPassword());
    }

    @Override
    public int getItemCount() {
        // returning the size of our array list
        return UserArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        // creating variables for our text views.
        private TextView username, password;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            // initializing our text views
            username = itemView.findViewById(R.id.idTVUsername);
            password = itemView.findViewById(R.id.idTVPassword);
            System.out.println(username + " " + password);
        }
    }
}
