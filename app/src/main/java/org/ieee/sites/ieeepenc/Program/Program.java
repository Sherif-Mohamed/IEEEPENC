package org.ieee.sites.ieeepenc.Program;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.NavUtils;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import org.ieee.sites.ieeepenc.R;
import org.ieee.sites.ieeepenc.SlidingTabRaw.SlidingTabLayout;

public class Program extends AppCompatActivity {
    private ViewPager mPager;
    private SlidingTabLayout mTabs;
    private Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_program);
        createTabs();
        toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            NavUtils.navigateUpFromSameTask(this);
            finish();
        }
        return true;
    }

    private void createTabs() {
        mPager = (ViewPager) findViewById(R.id.program_pager);
        mTabs = (SlidingTabLayout) findViewById(R.id.program_tabs);
        //setting adapter to create tabs contents
        mPager.setAdapter(new Program.PagerAdapter(getSupportFragmentManager()));
        //weight = 1 for all tabs
        mTabs.setDistributeEvenly(true);
        //set the View pager to the tabs to attach them
        mTabs.setViewPager(mPager);
    }

    class PagerAdapter extends FragmentPagerAdapter {
        String[] tabs;
        String[] titles;
        String[][] time = new String[3][];
        String[][] content = new String[3][];

        public PagerAdapter(FragmentManager fm) {
            super(fm);
            tabs = new String[]{"1st Day", "2nd Day", "3rd Day"};
            titles = new String[]{"Dec 18 2016", "Dec 19 2016", "Dec 20 2016"};

            time[0] = getResources().getStringArray(R.array.time1);
            time[1] = getResources().getStringArray(R.array.time2);
            time[2] = getResources().getStringArray(R.array.time3);
            content[0] = getResources().getStringArray(R.array.content1);
            content[1] = getResources().getStringArray(R.array.content2);
            content[2] = getResources().getStringArray(R.array.content3);
        }
        @Override
        public Fragment getItem(int position) {
            DayProgram fragment = DayProgram.getInstance(titles[position],time[position],content[position]);
            return fragment;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return tabs[position];
        }

        @Override
        public int getCount() {
            return 3;
        }
    }
}
