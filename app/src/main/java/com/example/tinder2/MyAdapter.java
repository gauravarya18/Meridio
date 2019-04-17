package com.example.tinder2;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends ArrayAdapter<NEWS> {

    private Context mContext;
    private List<NEWS> list = new ArrayList<>();

    public MyAdapter(@NonNull Context context, ArrayList<NEWS> list) {
        super(context, 0, list);
        mContext = context;
        this.list = list;

        Log.d("suthar", "ADA " + list.size());
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Log.d("suthar", "" + position);
        View listItem = convertView;
        if (listItem == null)
            listItem = LayoutInflater.from(mContext).inflate(R.layout.item, parent, false);

        NEWS news = list.get(position);

        TextView name = (TextView) listItem.findViewById(R.id.helloText);
        ImageView news_photo = (ImageView) listItem.findViewById(R.id.news_photo);
        name.setText(news.getTitle());


        Glide.with(mContext)
                .load(news.getContent())
                .placeholder(R.drawable.profile_ic)

                .into(news_photo);

        return listItem;
    }

}
