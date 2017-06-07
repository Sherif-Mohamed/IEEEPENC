package org.ieee.sites.ieeepenc.FAQ;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.ieee.sites.ieeepenc.R;

public class FAQActivity extends AppCompatActivity {
    private Toolbar toolbar;
    String[] questions;
    String[] answers;
    private RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faq);
        toolbar = (Toolbar) findViewById(R.id.app_bar);
        questions = getResources().getStringArray(R.array.faq_questions);
        answers = getResources().getStringArray(R.array.faq_answers);
        recyclerView = (RecyclerView) findViewById(R.id.faq);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        FAQAdapter adapter =new FAQAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            NavUtils.navigateUpFromSameTask(this);
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    class FAQAdapter extends RecyclerView.Adapter<FAQAdapter.FAQHolder>{
        private LayoutInflater inflater;

        public FAQAdapter(Context context) {
            inflater = LayoutInflater.from(context);
        }

        @Override
        public FAQAdapter.FAQHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = inflater.inflate(R.layout.faq_item,parent,false);
            FAQAdapter.FAQHolder holder = new FAQAdapter.FAQHolder(view);
            return holder;
        }

        @Override
        public void onBindViewHolder(FAQAdapter.FAQHolder holder, int position) {
            holder.question.setText("Q: "+questions[position]);
            holder.answer.setText("A: "+answers[position]);
        }

        @Override
        public int getItemCount() {
            return questions.length;
        }
        class FAQHolder extends RecyclerView.ViewHolder{
            private TextView question;
            private TextView answer;
            public FAQHolder(View itemView) {
                super(itemView);
                question = (TextView) itemView.findViewById(R.id.faq_question);
                answer = (TextView) itemView.findViewById(R.id.faq_answer);
            }
        }
    }

}
