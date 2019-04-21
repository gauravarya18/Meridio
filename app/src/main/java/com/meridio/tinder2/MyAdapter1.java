package com.meridio.tinder2;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter1 extends ArrayAdapter<NEWS> {

    private Context mContext;
    private List<NEWS> list = new ArrayList<>();

    public MyAdapter1(@NonNull Context context, ArrayList<NEWS> list) {
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
            listItem = LayoutInflater.from(mContext).inflate(R.layout.item1, parent, false);

        NEWS news = list.get(position);

        TextView name = (TextView) listItem.findViewById(R.id.helloText);
        TextView news_content=listItem.findViewById(R.id.news_content);
        name.setText(news.getTitle());
        news_content.setText(news.getContent());




        return listItem;
    }

}
