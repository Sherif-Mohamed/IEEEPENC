package org.ieee.sites.ieeepenc.NavigationDrawer;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.andexert.library.RippleView;

import org.ieee.sites.ieeepenc.R;

/**
 * Created by Shiko on 22/11/2016.
 */

public class NavMenuAdapter extends RecyclerView.Adapter<NavMenuAdapter.NavVH> {
    private LayoutInflater inflater;
    String[] names;
    ClickListener clickListener;

    public NavMenuAdapter(Context context, String[] data) {
        inflater = LayoutInflater.from(context);
        names = data;
    }

    @Override
    public NavMenuAdapter.NavVH onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.nav_menu_row, parent, false);
        NavVH navVH = new NavVH(view);
        return navVH;
    }

    public void setClickListener(ClickListener clickListener) {
        this.clickListener = clickListener;
    }

    @Override
    public void onBindViewHolder(NavMenuAdapter.NavVH holder, int position) {
        holder.text.setText(names[position]);
    }

    @Override
    public int getItemCount() {
        return names.length;
    }

    class NavVH extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView text;
        private RippleView rippleView;

        public NavVH(final View itemView) {
            super(itemView);
            text = (TextView) itemView.findViewById(R.id.row_name);
            rippleView = (RippleView) itemView.findViewById(R.id.ripple);
            itemView.setOnClickListener(NavVH.this);
        }

        @Override
        public void onClick(final View view) {
            if (clickListener != null)
                rippleView.setOnRippleCompleteListener(new RippleView.OnRippleCompleteListener() {
                    @Override
                    public void onComplete(RippleView rippleView) {
                        clickListener.onItemClick(view, getAdapterPosition());
                    }
                });
        }
    }

    public interface ClickListener {
        public void onItemClick(View view, int position);
    }
}
