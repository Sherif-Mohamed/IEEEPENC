package org.ieee.sites.ieeepenc.Home;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.ieee.sites.ieeepenc.NavigationDrawer.NavDrawerFragment;
import org.ieee.sites.ieeepenc.R;
import org.ieee.sites.ieeepenc.StringSingleton;

import static java.lang.Integer.parseInt;

public class HomeActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private SwipeRefreshLayout mRefresh;
    public static int count;
    private RecyclerView recyclerView;
    private FrameLayout nullFrame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        nullFrame = (FrameLayout) findViewById(R.id.null_page);
        recyclerView = (RecyclerView) findViewById(R.id.home);
        Toolbar_NavDrawer();
        LoadCounter();
        mRefresh = (SwipeRefreshLayout) findViewById(R.id.swiperefresh);
        mRefresh.setColorSchemeResources(R.color.red,R.color.green,R.color.blue,R.color.yellow);
        mRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                RequestQueue requestQueue = StringSingleton.getInstance(HomeActivity.this).getRequestQueue();
                StringRequest counter = new StringRequest(Request.Method.GET, "http://ieeepenc.16mb.com/count.txt", new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        int mCount = parseInt(response);
                        if (mCount != count) {
                            count = mCount;
                            LoadCounter();
                        }
                        mRefresh.setRefreshing(false);
                    }
                }, new Response.ErrorListener() {
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(HomeActivity.this, "There was an error fetching the data,please try again later", Toast.LENGTH_LONG).show();
                        mRefresh.setRefreshing(false);
                    }
                });
                requestQueue.add(counter);
            }
        });
    }

    private void LoadCounter() {
        if (count != 0) {
            recyclerView.setVisibility(View.VISIBLE);
            nullFrame.setVisibility(View.GONE);
            HomeAdapter adapter = new HomeAdapter(HomeActivity.this, count);
            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(HomeActivity.this));
        } else {
            recyclerView.setVisibility(View.GONE);
            nullFrame.setVisibility(View.VISIBLE);
        }
    }

    private void Toolbar_NavDrawer() {
        //creating toolbar in activity
        toolbar = (Toolbar) findViewById(R.id.action_bar);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        setSupportActionBar(toolbar);

        //navigation drawer creation
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        NavDrawerFragment navDrawerFragment = (NavDrawerFragment) getSupportFragmentManager().findFragmentById(R.id.nav_drawer);
        navDrawerFragment.setUp(R.id.nav_drawer, drawerLayout, toolbar);
    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setMessage("Are you sure you want to exit?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        HomeActivity.this.finish();
                    }
                })
                .setNegativeButton("No", null)
                .show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        //inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.go_live) {
            //TODO: Intent to Youtube channel
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
