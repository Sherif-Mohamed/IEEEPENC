package org.ieee.sites.ieeepenc.Program;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.ieee.sites.ieeepenc.R;

/**
 * Created by Shiko on 18/12/2016.
 */

public class DayProgram extends Fragment {
    private RecyclerView recyclerView;
    private TextView titleText;
    private String[] content;
    private String[] time;
    private String title;

    public static DayProgram getInstance(String title, String[] time, String[] content) {
        DayProgram newFragment = new DayProgram();
        newFragment.content = content;
        newFragment.time = time;
        newFragment.title=title;
        return newFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.program_fragment, container, false);
        titleText = (TextView) view.findViewById(R.id.title_pro);
        titleText.setText(title);
        recyclerView = (RecyclerView) view.findViewById(R.id.program_page);
        DayAdapter adapter = new DayAdapter(getContext());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        return view;

    }
    class DayAdapter extends RecyclerView.Adapter<DayAdapter.ItemHolder>{
        private LayoutInflater inflater;

        public DayAdapter(Context context) {
            inflater = LayoutInflater.from(context);
        }

        @Override
        public ItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = inflater.inflate(R.layout.program_item,parent,false);
            ItemHolder holder = new ItemHolder(view);
            return holder;
        }

        @Override
        public void onBindViewHolder(ItemHolder holder, int position) {
            holder.time.setText(time[position]);
            holder.content.setText(content[position]);
        }

        @Override
        public int getItemCount() {
            return time.length;
        }

        class ItemHolder extends RecyclerView.ViewHolder{

            private TextView time;
            private TextView content;
            public ItemHolder(View itemView) {
                super(itemView);
                time = (TextView) itemView.findViewById(R.id.timeText);
                content = (TextView) itemView.findViewById(R.id.contentText);
            }
        }
    }
}
