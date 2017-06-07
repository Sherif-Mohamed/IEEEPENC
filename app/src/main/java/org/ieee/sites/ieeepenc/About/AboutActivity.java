package org.ieee.sites.ieeepenc.About;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.NavUtils;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.ieee.sites.ieeepenc.R;
import org.ieee.sites.ieeepenc.SlidingTabRaw.SlidingTabLayout;

public class AboutActivity extends AppCompatActivity {
    private ViewPager mPager;
    private SlidingTabLayout mTabs;
    String[] tabs;
    String[] content;
    int[] imageID;
    String[] titles;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        Create_Tabs();
        toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home){
            NavUtils.navigateUpFromSameTask(this);
            finish();
            return true;
        }
        return true;
    }

    private void Create_Tabs() {
        mPager = (ViewPager) findViewById(R.id.pager);
        mTabs = (SlidingTabLayout) findViewById(R.id.tabs);
        //setting adapter to create tabs contents
        mPager.setAdapter(new PagerAdapter(getSupportFragmentManager()));
        //weight = 1 for all tabs
        mTabs.setDistributeEvenly(true);
        //set the View pager to the tabs to attach them
        mTabs.setViewPager(mPager);
    }

    class PagerAdapter extends FragmentPagerAdapter {

        public PagerAdapter(FragmentManager fm) {
            super(fm);
            tabs = getResources().getStringArray(R.array.tab_names);
            content = getResources().getStringArray(R.array.content_about);
            imageID = new int[]{R.drawable.logo, R.drawable.tunisia, R.drawable.diarelmadina};
            titles = getResources().getStringArray(R.array.titles_about);
        }

        @Override
        public Fragment getItem(int position) {
            AboutFragment fragment = AboutFragment.getInstance(content[position], imageID[position], titles[position]);
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

    public static class AboutFragment extends Fragment {
        private TextView textView;
        private TextView title;
        private ImageView imageView;

        public static AboutFragment getInstance(String text, int imageId, String title_text) {
            AboutFragment newFragment = new AboutFragment();
            Bundle bundle = new Bundle();
            bundle.putString("text", text);
            bundle.putInt("image", imageId);
            bundle.putString("title", title_text);
            newFragment.setArguments(bundle);
            return newFragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.about_fragment, container, false);
            textView = (TextView) view.findViewById(R.id.about_text);
            imageView = (ImageView) view.findViewById(R.id.about_pic);
            title = (TextView) view.findViewById(R.id.title_text);
            Bundle bundle = getArguments();
            textView.setText(bundle.getString("text"));
            imageView.setImageResource(bundle.getInt("image"));
            title.setText(bundle.getString("title"));
            return view;

        }
    }
}
