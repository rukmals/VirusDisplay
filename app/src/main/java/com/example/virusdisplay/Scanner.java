package com.example.virusdisplay;


import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;


import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;

import android.content.Context;
import android.widget.ProgressBar;


import java.io.InputStreamReader;

import android.os.Environment;




public class Scanner extends AppCompatActivity {

    private static final int PERMISSION_REQUEST_STORAGE = 1000;
    private static final int READ_REQUEST_CODE = 42;

    Button b_load;
    TextView tv_output;
    String virusname;

    TextView v_text ;
    TextView num_v ;
    ProgressBar progress ;
    ProgressBar loader;
    TextView actions;


    InputStream inputStream;
    File textFile;
    public int i =0 ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scanner);

        b_load = (Button) findViewById(R.id.b_load);
        tv_output = (TextView) findViewById(R.id.tv_output);

        v_text = (TextView) findViewById(R.id.v_text);
        num_v = (TextView) findViewById(R.id.num_v);
        progress = (ProgressBar) findViewById(R.id.progress);
        loader = (ProgressBar) findViewById(R.id.loader);
        actions = (TextView) findViewById(R.id.action);



        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M && checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
            requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},PERMISSION_REQUEST_STORAGE);
        }


        b_load.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performFileSearch();




            }

        });
    }

    private String readText(String path){
        //File file = new File(Environment.getExternalStorageDirectory(),input);
        File file = new File(Environment.getExternalStorageDirectory()+"/"+path);
        StringBuilder text = new StringBuilder();

        try{
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            //for (int i=0;i<10;i++){
            while((line = br.readLine())!=null){
                //line = br.readLine();
                text.append(line);
                //text.append("\n");
            }
            br.close();
        }
        catch (IOException e){
            e.printStackTrace();
        }
        //text.append("baaa");
        //text.append("/n");
        return text.toString();
        //return br;
    }

    private void performFileSearch(){
        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("text/*");
        startActivityForResult(intent,READ_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode,  Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == READ_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            if (data != null) {
                Uri uri = data.getData();
                String path = uri.getPath();

                path = path.substring(path.indexOf(":") + 1);
                if (path.contains("emulated")) {
                    path = path.substring(path.indexOf("0") + 1);
                }
                Toast.makeText(this, "" + path, Toast.LENGTH_SHORT).show();
                //Toast.makeText(this,readText(path),Toast.LENGTH_LONG).show();
                File filex = new File(path);
                String fn = filex.getName();
                //tv_output.setText(readText(path));
                //tv_output.setText(fn);
                File file = new File(Environment.getExternalStorageDirectory() + "/" + path);
                SignatureUnpacker(file);

            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        //super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode==PERMISSION_REQUEST_STORAGE){
            if(grantResults[0]==PackageManager.PERMISSION_GRANTED){
                Toast.makeText(this,"Permission granted",Toast.LENGTH_LONG).show();
            }
            else{
                Toast.makeText(this,"Permission not granted",Toast.LENGTH_LONG).show();
                finish();
            }
        }
    }

    public void SignatureUnpacker(File file){
        StringBuilder text = new StringBuilder(); /// where i start comment
        BufferedReader reader, signature_reader;
        int num_virus= 0;
        try {
            //reader = new BufferedReader(new InputStreamReader(getAssets().open("file.txt")));
            reader = new BufferedReader(new FileReader(file));
            String strLine = reader.readLine();
            text.append(strLine);
            signature_reader = new BufferedReader(new InputStreamReader(getAssets().open("signature.db")));
            String line;
            //int i = 0;
            while ((line = signature_reader.readLine()) != null) {
                //reader = new BufferedReader(new InputStreamReader(getAssets().open("file.txt")));
                //text.append(line);
                //text.append("\n");
                //c++;

                String[] virus = line.split("=");

                virusname = virus[0];
                String virussig = virus[1];
                String[] virussigx = virussig.split(" ");
                virussig = virussigx[0];

                int index = strLine.indexOf(virussig);
                progress.setProgress(i*100/20);
                if (index != -1) // -1 means "not found"
                {
                    // virusfound
                    num_virus++;


                }

                //c++;
                //publishProgress((i * 100) / 21);
                i++;

            }
            reader.close();
        }catch (IOException e){
            e.printStackTrace();
        }
        //String text =readFile();

        num_v.setText("" + num_virus);

        if(num_virus >= 1) {
            v_text.setText("viruses found!");
        }
        else{
            v_text.setText("No viruses found!");
            virusname = "";
        }

        tv_output.setText(virusname);
        actions.setText("Scan Complete");
        loader.setVisibility(View.GONE);


    }


}
