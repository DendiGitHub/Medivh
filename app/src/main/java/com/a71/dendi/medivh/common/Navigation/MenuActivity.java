package com.a71.dendi.medivh.common.Navigation;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.TextView;
import android.widget.Toast;
import com.a71.dendi.medivh.R;
import java.util.List;

/**
 * Created by Dendi on 2016/6/10.
 */

public class MenuActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private List<String> mList;
    private NavigationDataHolder mDataHolder;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        mRecyclerView = (RecyclerView)findViewById(R.id.recycler_view);
        //mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(4,
            StaggeredGridLayoutManager.VERTICAL
            ));
        mRecyclerView.setAdapter(mAdapter=new MyAdapter());
        mRecyclerView.addItemDecoration(
            new DividerItemDecoration(this,DividerItemDecoration.VERTICAL_LIST));
        mRecyclerView.getViewTreeObserver().addOnGlobalLayoutListener(
            new ViewTreeObserver.OnGlobalLayoutListener() {
                @Override public void onGlobalLayout() {
                    mRecyclerView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                }
            });

        mDataHolder = new NavigationDataHolder(this);
        mList = mDataHolder.getList();
    }

    @Override protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Toast.makeText(MenuActivity.this,"Taken",Toast.LENGTH_LONG).show();
    }


    class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>{

        @Override public int getItemCount() {
            return mList.size();
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            MyViewHolder vh = new MyViewHolder(LayoutInflater.from(MenuActivity.this).inflate(R.layout.menu_item,parent,false));
            return vh;
        }


        @Override
        public void onBindViewHolder(MyViewHolder holder, final int position) {
            holder.textView.setText(mList.get(position));
            holder.textView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    Toast.makeText(MenuActivity.this,"clicked :"+position,Toast.LENGTH_SHORT).show();
                    mDataHolder.onItemClicked(position);
                }
            });
        }

        class MyViewHolder extends RecyclerView.ViewHolder{
            TextView textView;
            public MyViewHolder(View view){
                super(view);
                textView = (TextView)view.findViewById(R.id.menu_item_title);
            }
        }
    }
}
