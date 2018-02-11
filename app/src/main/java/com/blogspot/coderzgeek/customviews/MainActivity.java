package com.blogspot.coderzgeek.customviews;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.blogspot.coderzgeek.customviews.views.CustomView;

import java.util.ArrayList;

/* Inflates a simple recycler view with test data*/

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Static data
        ArrayList<String> list=getTestData();

        RecyclerView rv =(RecyclerView)findViewById(R.id.rv);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);

        rv.setLayoutManager(mLayoutManager);
        rv.setAdapter(new testAdapter(list,this));
    }

    private ArrayList<String> getTestData()
    {

        ArrayList<String> list=new ArrayList<>();
        list.add("MANGO.jpg");
        list.add("PINAPPLE.png");
        list.add("MANGO.pdf");
        list.add("MANGO.xls");
        list.add("MANGO.docx");
        list.add("MANGO.doc");
        list.add("MANGO.mp4");
        list.add("MANGO.mp3");
        list.add("MANGO.flv");
        list.add("MANGO.jpg");
        list.add("PINAPPLE.png");
        list.add("MANGO.pdf");
        list.add("MANGO.xls");
        list.add("MANGO.docx");
        list.add("MANGO.doc");
        list.add("MANGO.mp4");
        list.add("MANGO.mp3");
        list.add("MANGO.flv");
        list.add("MANGO.jpg");
        list.add("PINAPPLE.png");
        list.add("MANGO.pdf");
        list.add("MANGO.xls");
        list.add("MANGO.docx");
        list.add("MANGO.doc");
        list.add("MANGO.mp4");
        list.add("MANGO.mp3");
        list.add("MANGO.flv");

        return list;

    }
}
