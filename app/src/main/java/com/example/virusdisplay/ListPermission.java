package com.example.virusdisplay;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import android.os.Bundle;

import android.widget.ArrayAdapter;

import android.widget.ListView;
import android.widget.TextView;


import java.util.ArrayList;

public class ListPermission extends AppCompatActivity {


    TextView naslov,critical;
    int position;
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_list_app);
        setContentView(R.layout.fragment_screen_slide_page);

        listView = (ListView) findViewById(R.id.Lista);


        for(int i=0;i<184;i++) {
            if (position == i) {
                Intent intent = getIntent();
                Bundle bundle = this.getIntent().getExtras();
                ArrayList<String> count = intent.getStringArrayListExtra("size1");
                ArrayAdapter arrayAdapter = new ArrayAdapter(this,R.layout.support_simple_spinner_dropdown_item,count);
                //critical.setText(count.get(0));
                listView.setAdapter(arrayAdapter);

            }
        }

    }

}
