package org.ieee.sites.ieeepenc.Home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import org.ieee.sites.ieeepenc.R;
import org.ieee.sites.ieeepenc.StringSingleton;

/**
 * Created by Shiko on 28/11/2016.
 */

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.HomeViewHolder> {
    private LayoutInflater inflater;
    private static String titleText;
    private static String bodyText;
    private static String pictureURL;
    private int count;
    private Context context;

    public HomeAdapter(Context context, int count) {
        this.count = count;
        this.context = context;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public HomeAdapter.HomeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.home_item, parent, false);
        HomeViewHolder homeViewHolder = new HomeViewHolder(view);
        return homeViewHolder;
    }

    @Override
    public void onBindViewHolder(final HomeViewHolder holder, final int position) {

        if (count > 0) {
            //texts
            RequestQueue requestQueue = StringSingleton.getInstance(context).getRequestQueue();
            StringRequest stringBring = new StringRequest(Request.Method.GET, "http://ieeepenc.16mb.com/files/" + (count - position) + ".txt", new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    titleText = response.substring(0, response.indexOf("\n"));
                    holder.titleText.setText(titleText);
                    bodyText =response.substring(response.indexOf("\n") + 1);
                    holder.bodyText.setText(bodyText);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(context, "There was an error fetching the data,please try again later", Toast.LENGTH_SHORT).show();
                }
            });
            requestQueue.add(stringBring);
            //image
            Picasso.with(context).load("http://ieeepenc.16mb.com/files/" + (count - position) + ".jpg").into(holder.image, new Callback() {
                @Override
                public void onSuccess() {
                    holder.progressBar.setVisibility(View.GONE);
                    pictureURL ="http://ieeepenc.16mb.com/files/" + (count - position) + ".jpg";
                }

                @Override
                public void onError() {

                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return count;
    }

    class HomeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView image;
        private TextView titleText;
        private TextView bodyText;
        private ProgressBar progressBar;
        public HomeViewHolder(View itemView) {
            super(itemView);
            image = (ImageView) itemView.findViewById(R.id.news_pic);
            titleText = (TextView) itemView.findViewById(R.id.news_title);
            bodyText = (TextView) itemView.findViewById(R.id.paragraph);
            progressBar = (ProgressBar) itemView.findViewById(R.id.progressBar);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Bundle bundle = new Bundle();
            bundle.putString("image",pictureURL);
            bundle.putString("title",HomeAdapter.titleText);
            bundle.putString("body",HomeAdapter.bodyText);
            Intent intent = new Intent(context,HomeItem.class);
            intent.putExtras(bundle);
            context.startActivity(intent);
        }
    }
}
