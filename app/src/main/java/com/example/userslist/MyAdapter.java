package com.example.userslist;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    ArrayList<Users> listdata;
    Context mContext;
    public MyAdapter(ArrayList<Users> listdata, Context context) {
        this.listdata = listdata;
       this.mContext = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View listItem= layoutInflater.inflate(R.layout.layout, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(listItem,listdata,mContext);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int i) {


        viewHolder.round_button.setText(listdata.get(i).getName().substring(0,1));
        viewHolder.name.setText(listdata.get(i).getName());
        viewHolder.email.setText(listdata.get(i).getEmail());

    }

    @Override
    public int getItemCount() {
        return listdata.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public Button round_button;
        public TextView name;
        public TextView email;

        public ViewHolder(final View itemView, final ArrayList<Users> listdata, final Context mContext) {
            super(itemView);
            this.round_button = (Button) itemView.findViewById(R.id.round_icon);
            this.name = (TextView) itemView.findViewById(R.id.name);
            this.email = (TextView) itemView.findViewById(R.id.email);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(mContext,UserDetails.class);
                    i.putExtra("name",listdata.get(getLayoutPosition()).getName());
                    i.putExtra("email",listdata.get(getLayoutPosition()).getEmail());
                    i.putExtra("phone",listdata.get(getLayoutPosition()).getPhone());
                    i.putExtra("street",listdata.get(getLayoutPosition()).getStreet());
                    i.putExtra("city",listdata.get(getLayoutPosition()).getCity());
                    mContext.startActivity(i);

                }
            });
        }
    }
    }
