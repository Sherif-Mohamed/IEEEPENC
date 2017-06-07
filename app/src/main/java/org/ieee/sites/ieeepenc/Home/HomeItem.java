package org.ieee.sites.ieeepenc.Home;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.ieee.sites.ieeepenc.R;

public class HomeItem extends AppCompatActivity {
    private TextView title;
    private Toolbar toolbar;
    private TextView paragraph;
    private ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_item);

        title = (TextView) findViewById(R.id.title_act);
        paragraph = (TextView) findViewById(R.id.paragraph_act);
        image = (ImageView) findViewById(R.id.pic_act);

        toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Bundle extras = getIntent().getExtras();
        title.setText(extras.getString("title"));
        paragraph.setText(extras.getString("body"));
        Picasso.with(this).load(extras.getString("image")).into(image);
    }
}
