package com.example.virusdisplay;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.app.ActionBar;
import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.ParseException;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import com.example.virusdisplay.ListAppActivity.PInfo;


public class Result extends AppCompatActivity implements ActionBar.TabListener{

    private ViewPager mViewPager;
    private PagerAdapter mPagerAdapter;
    private String title;

    public ArrayList<PInfo> lista;

    public ArrayList<PInfo> can_cost_money_obj;
    public ArrayList<PInfo> can_see_personal_info_obj;
    public ArrayList<PInfo> can_impact_battery_obj;
    public ArrayList<PInfo> can_change_system_obj;
    public ArrayList<PInfo> can_see_location_info_obj;

    //MainContructor.ResultPresenter resultPresenter;

    ListView listView;

    public ArrayList<PInfo> appovi;

    public Handler handler = new Handler();


    int number;
    @Override
    public void onCreate(Bundle savedInstanceState) {


        androidx.appcompat.app.ActionBar actionBar;
        actionBar = getSupportActionBar();

        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#000000"));
        actionBar.setBackgroundDrawable(colorDrawable);
        actionBar.setDisplayShowHomeEnabled(true);

        actionBar.setTitle("SEE PERMISSION INFORMATION");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_screen_slide_page);

        listView = (ListView) findViewById(R.id.Lista);

        //resultPresenter = new MainResultPresenter(this);

        can_cost_money_obj = getIntent().getParcelableArrayListExtra("can_cost_money_obj");
        can_see_personal_info_obj = getIntent().getParcelableArrayListExtra("can_see_personal_info_obj");
        can_impact_battery_obj = getIntent().getParcelableArrayListExtra("can_impact_battery_obj");
        can_change_system_obj = getIntent().getParcelableArrayListExtra("can_change_system_obj");
        can_see_location_info_obj = getIntent().getParcelableArrayListExtra("can_see_location_info_obj");


        lista = can_cost_money_obj;
        Sort(lista);
        final CustomAdapter adapter = new CustomAdapter(Result.this, appovi);

        listView.setAdapter(adapter);


        number = adapter.getCount();


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //for (int i=0;i<10;i++){
                int count = adapter.getCount();
                for(int i=0;i<count+1;i++){
                    if(position==i){
                        //Toast.makeText(Result.this,lista.get(position).appname ,Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(getApplicationContext(),ListPermission.class);

                        Bundle bundle = new Bundle();
                        //bundle.putInt("size",20);
                        intent.putExtras(bundle);
                        //bundle.putStringArrayList("critical",lista.get(position).critical);
                        //intent.putExtra("size1",String.valueOf(lista.get(position).critical.size()));
                        intent.putExtra("size1",lista.get(position).critical);
                        startActivity(intent);
                    }
               }


            }
        });





    }



    public void Sort(ArrayList<PInfo> lista){
        appovi = lista;

        Collections.sort(appovi, new Comparator<PInfo>() {
            public int compare(PInfo one, PInfo other) {

                Integer i = new Integer(other.critical.size());

                return i.compareTo(one.critical.size());
            }
        });
    }


    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
    }

    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
        // When the given tab is selected, switch to the corresponding page in the ViewPager.
        mViewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
    }


    public String getText(String text) {
        return "SEE VIRUS PERMISSION INFORMATION ";
    }


    public String getCol(Integer val){
        String col;
        if(val > 50){
            col = "#FFED0505";
        }else{
            col="#FF4BE30B";
        }
        return col;
    }



    /**
     * A simple pager adapter that represents 5 ScreenSlidePageFragment objects, in
     * sequence.
     */


    /**
     * CUSTOM ADAPTER
     */
    public class CustomAdapter extends BaseAdapter {
        private Activity activity;
        private LayoutInflater inflater;
        private ArrayList<PInfo> data;

        public CustomAdapter(Activity activity, ArrayList<PInfo> items) {
            this.activity = activity;
            this.data = items;
        }

        @Override
        public int getCount() {
            return data.size();
        }

        @Override
        public Object getItem(int position) {
            return data.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        public void onListItemClick(ListView l, View v, int position, long id) {
            Log.i("FragmentList", "Item clicked: " + id);
        }


        @Override
        public View getView(int position, View convertView, ViewGroup parent) {


            if (inflater == null)
                inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            if (convertView == null)
                convertView = inflater.inflate(R.layout.list_item, null);

            try {

                ImageView slika = (ImageView) convertView.findViewById(R.id.slika);
                //Button result = (Button) convertView.findViewById(R.id.result);
                TextView naslov = (TextView) convertView.findViewById(R.id.naslov);
                //Button naslov = (Button) convertView.findViewById(R.id.naslov);
                TextView paket = (TextView) convertView.findViewById(R.id.paket);
                TextView critical = (TextView) convertView.findViewById(R.id.critical);

                ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar2);

                //progressBar.setProgress(50);

                /*ProgressBar mProgress = (ProgressBar) findViewById(R.id.progressBar2);
                mProgress.setVisibility(View.VISIBLE);
                mProgress.setMax(100);*/

                //LinearLayout view = (LinearLayout) findViewById(R.id.)


                String pname = data.get(position).pname;
                Drawable icon;

                try {

                    /*progressBar.getProgress();
                    progressBar.setMax(100);
                    progressBar.setProgress(75);*/

                    icon = getPackageManager().getApplicationIcon(pname);

                    slika.setImageDrawable(icon);
                    //result.setText(data.get(position).appname);
                    naslov.setText(data.get(position).appname);

                    paket.setText(data.get(position).pname);
                    //critical.setText(""+1000);
                    //critical.setText(""+data.get(position).critical.size());
                    critical.setText(""+(data.get(position).critical.size()*100/14)+"%");
                    //progressBar.setMax(100);
                    //mProgress.setProgress(50);
                    //progressBar.setProgress(50);
                    //critical.setBackgroundColor(Color.parseColor(getCol(data.get(position).critical.size()*100/14)));

                    Drawable unwrapped = AppCompatResources.getDrawable(Result.this,R.drawable.round_textview);
                    Drawable wrapp = DrawableCompat.wrap(unwrapped);
                    DrawableCompat.setTint(wrapp,Color.RED);

                } catch (PackageManager.NameNotFoundException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }


            } catch (ParseException e) {

                e.printStackTrace();

            }

            return convertView;

        }
    }



}






















