package com.example.virusdisplay;

import androidx.appcompat.app.AppCompatActivity;
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
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.ParseException;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

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
        CustomAdapter adapter = new CustomAdapter(Result.this, appovi);

        listView.setAdapter(adapter);

        /*mPagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());

        final ActionBar actionBar = getActionBar();
        actionBar.setHomeButtonEnabled(false);
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mPagerAdapter);

        mViewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                actionBar.setSelectedNavigationItem(position);
            }
        });

        // For each of the sections in the app, add a tab to the action bar.
        for (int i = 0; i < mPagerAdapter.getCount(); i++) {
            actionBar.addTab(actionBar.newTab().setText(mPagerAdapter.getPageTitle(i)).setTabListener(this));
            //getPagerAdapter(actionBar,i);
        }*/


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


    public void getPagerAdapter(ActionBar actionBar, int i) {
        actionBar.addTab(actionBar.newTab().setText(mPagerAdapter.getPageTitle(i)).setTabListener(this));
    }


    /**
     * A simple pager adapter that represents 5 ScreenSlidePageFragment objects, in
     * sequence.
     */
    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
        public ScreenSlidePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {

            //if(position =)
            //can_cost_money_obj

            switch(position){
                case 0:
                    lista = can_cost_money_obj;
                    break;
	    		/*case 1:
	    			lista = can_see_personal_info_obj;
	    		break;
	    		case 2:
	    			lista = can_impact_battery_obj;
	    		break;
	    		case 3:
	    			lista = can_change_system_obj;
	    		break;
	    		case 4:
	    			lista = can_see_location_info_obj;
	    		break;*/
            }

            return new ScreenSlidePageFragment(lista);

        }

        @Override
        public int getCount() {
            return 1;
        }

        public CharSequence getPageTitle(int position) {
            switch(position){
                case 0:
                    title = getText("");
                    break;
        		/*case 1:
        			title = "CAN SEE PERSONAL INFO";
        		break;
        		case 2:
        			title = "CAN IMPACT BATTERY";
        		break;
        		case 3:
        			title = "CAN CHANGE SYSTEM";
        		break;
        		case 4:
        			title = "CAN SEE LOCATION INFO";
        		break;*/
            }
            return title;
        }
    }


    public class ScreenSlidePageFragment extends Fragment{

        public ArrayList<PInfo> appovi;
        public ListView Lista;
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_screen_slide_page, container, false);


            CustomAdapter adapter = new CustomAdapter(getActivity(), appovi);
            ListView Lista = (ListView) rootView.findViewById( R.id.Lista );
            Lista.setAdapter(adapter);



            return rootView;
        }


        public ScreenSlidePageFragment(ArrayList<PInfo> lista){
            appovi = lista;

            Collections.sort(appovi, new Comparator<PInfo>() {
                public int compare(PInfo one, PInfo other) {

                    Integer i = new Integer(other.critical.size());

                    return i.compareTo(one.critical.size());
                }
            });
        }
    }
    public String getClor(int val){
        String color = "";
        if (0<= val && val <=60){
            color="#FF2EC705";

        }
        if (60< val && val <=100){
            color="#FFD61919";

        }
        return color;

    }

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
                TextView naslov = (TextView) convertView.findViewById(R.id.naslov);
                TextView paket = (TextView) convertView.findViewById(R.id.paket);
                TextView critical = (TextView) convertView.findViewById(R.id.critical);

                //LinearLayout view = (LinearLayout) findViewById(R.id.)


                String pname = data.get(position).pname;
                Drawable icon;

                try {

                    icon = getPackageManager().getApplicationIcon(pname);

                    slika.setImageDrawable(icon);
                    naslov.setText(data.get(position).appname);
                    paket.setText(data.get(position).pname);
                    //critical.setText(""+1000);
                    //critical.setText(""+data.get(position).critical.size());
                    critical.setText(""+(data.get(position).critical.size()*100/19)+"%");
                    //critical.setBackgroundColor(Color.parseColor(getClor(data.get(position).critical.size()*100/19)));

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






















