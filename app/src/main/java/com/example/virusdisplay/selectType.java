package com.example.virusdisplay;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;

public class selectType extends AppCompatActivity {

    Button text,docx,html,photo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_type);


        text = (Button) findViewById(R.id.txt);
        docx = (Button) findViewById(R.id.docx);
        html = (Button) findViewById(R.id.html);
        photo = (Button) findViewById(R.id.photo);

        text.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                fileScanner();
            }
        });

        html.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                HTMLScanner();
            }
        });
        docx.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                DocScanner();
            }
        });



        /*photo.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                PhotoScanner();
            }
        });*/
    }


    public void fileScanner(){
        Intent intent = new Intent(this,Scanner.class);
        startActivity(intent);
    }
    public void HTMLScanner(){
        Intent intent = new Intent(this,HLMLScanner.class);
        startActivity(intent);
    }

    public void DocScanner(){
        Intent intent = new Intent(this,DocScanner.class);
        startActivity(intent);
    }


/*
    public void PhotoScanner(){
        Intent intent = new Intent(this,PhotoScanner.class);
        startActivity(intent);
    }*/


}
