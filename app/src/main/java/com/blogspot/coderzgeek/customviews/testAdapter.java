package com.blogspot.coderzgeek.customviews;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.blogspot.coderzgeek.customviews.views.CustomView;

import java.util.ArrayList;

/**
 * Created by home on 2/10/2018.
 */

public class testAdapter extends RecyclerView.Adapter<testAdapter.MyViewHolder> {

    ArrayList<String> list;
    Context c;

    public testAdapter(ArrayList<String> list, Context c) {
        this.list = list;
        this.c=c;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_main, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.tv.setText(list.get(position));
        if (list.get(position).contains(".")) {
            String[] type = list.get(position).split("\\.");
            type[1] = type[1].toLowerCase();

            if (type[1].contains("doc"))
                holder.cv.setmTextColor(R.color.docx,c);
            else if (type[1].contains("pdf"))
                holder.cv.setmTextColor(R.color.pdf,c);
            else if (type[1].contains("txt"))
                holder.cv.setmTextColor(R.color.txt,c);
            else if (type[1].contains("xls"))
                holder.cv.setmTextColor(R.color.xlx,c);
            else if (type[1].contains("jpg"))
                holder.cv.setmTextColor(R.color.jpg, c);
            else if (type[1].contains("ppt"))
                holder.cv.setmTextColor(R.color.ppt,c);
            else if (type[1].contains("png"))
                holder.cv.setmTextColor(R.color.png,c);
            else if (type[1].contains("gif"))
                holder.cv.setmTextColor(R.color.gif,c);
            else if (type[1].contains("zip"))
                holder.cv.setmTextColor(R.color.zip,c);
            else if (type[1].contains("mp"))
                holder.cv.setmTextColor(R.color.mp3,c);
            else
                holder.cv.setmTextColor(R.color.others,c);

            holder.cv.setText(type[1].toUpperCase());
        }
        else
            holder.cv.setText("FILE");

        holder.cv.postInvalidate();

    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tv;
        CustomView cv;

        public MyViewHolder(View itemView) {
            super(itemView);
            tv = (TextView) itemView.findViewById(R.id.tv);
            cv = (CustomView) itemView.findViewById(R.id.customView);
        }
    }
}
