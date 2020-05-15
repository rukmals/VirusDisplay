package com.example.virusdisplay;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button device;
    Button file;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        device = (Button) findViewById(R.id.device);

        file = (Button) findViewById(R.id.file);

        device.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //performFileSearch();
                //fileScanner();
                ListAppAcitivity();
            }
        });

        file.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //performFileSearch();
                //fileScanner();
                SelectType();
            }
        });
    }


    public void ListAppAcitivity(){
        Intent intent = new Intent(this,ListAppActivity.class);
        startActivity(intent);
    }
    public void SelectType(){
        Intent intent = new Intent(this,selectType.class);
        startActivity(intent);
    }



}





