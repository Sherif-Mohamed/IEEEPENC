package org.ieee.sites.ieeepenc.NavigationDrawer;


import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.ieee.sites.ieeepenc.About.AboutActivity;
import org.ieee.sites.ieeepenc.FAQ.FAQActivity;
import org.ieee.sites.ieeepenc.Program.Program;
import org.ieee.sites.ieeepenc.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class NavDrawerFragment extends Fragment implements NavMenuAdapter.ClickListener {

    private ActionBarDrawerToggle drawerToggle;
    private DrawerLayout mDrawerLayout;
    private RecyclerView recyclerView2;
    private NavMenuAdapter adapter;

    public NavDrawerFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_nav_drawer, container, false);

        recyclerView2 = (RecyclerView) view.findViewById(R.id.nav_recycler);
        String[] data = {"FAQs", "Program", "Registration", "About", "Exit app"};
        adapter = new NavMenuAdapter(getActivity(), data);
        adapter.setClickListener(this);
        recyclerView2.setAdapter(adapter);
        recyclerView2.setLayoutManager(new LinearLayoutManager(getActivity()));
        return view;
    }

    public void setUp(int id, DrawerLayout drawerLayout, final Toolbar toolbar) {
        mDrawerLayout = drawerLayout;
        drawerToggle = new ActionBarDrawerToggle(getActivity(), mDrawerLayout, toolbar, R.string.open, R.string.close) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                getActivity().invalidateOptionsMenu();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                getActivity().invalidateOptionsMenu();
            }
        };
        mDrawerLayout.addDrawerListener(drawerToggle);
        //to make the hamburger button appear
        mDrawerLayout.post(new Runnable() {
            @Override
            public void run() {
                drawerToggle.syncState();
            }
        });
    }

    @Override
    public void onItemClick(View view, int position) {
        switch (position) {
            case 0:
                startActivity(new Intent(this.getActivity(), FAQActivity.class));
                break;
            case 1:
                startActivity(new Intent(this.getActivity(),Program.class));
                break;
            case 2:
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://sites.ieee.org/penc/support-training/ieee-penc-registration/registration/"));
                startActivity(browserIntent);
                break;
            case 3:
                startActivity(new Intent(this.getActivity(), AboutActivity.class));
                break;
            case 4:
                new AlertDialog.Builder(getActivity())
                        .setMessage("Are you sure you want to exit?")
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                getActivity().finish();
                            }
                        })
                        .setNegativeButton("No", null)
                        .show();
                break;
        }
    }
}
