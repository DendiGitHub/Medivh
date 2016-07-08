package com.a71.dendi.medivh.common.Navigation;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import com.a71.dendi.medivh.radar.RadarActivity;
import com.a71.dendi.medivh.R;
import com.a71.dendi.medivh.quickpay.QuickPayActivity;
import com.a71.dendi.medivh.utils.EventChain.ResponsibilityChainActivity;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dendi on 2016/6/10.
 */

public class MenuActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private List<String> mList;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        initData();
        mRecyclerView = (RecyclerView)findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mAdapter=new MyAdapter());
        mRecyclerView.addItemDecoration(
            new DividerItemDecoration(this,DividerItemDecoration.VERTICAL_LIST));
    }

    private void initData(){
        mList = new ArrayList<String>();
        mList.add("Camera");
        mList.add("RadarMap");
        mList.add("ResponsibilityChain");
        mList.add("Notificatioin");
        mList.add("quickPay");
        for(int i=4;i<1000;i++){
            mList.add(String.valueOf(i));
        }
    }


    @Override protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //super.onActivityResult(requestCode, resultCode, data);
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
                    Toast.makeText(MenuActivity.this,"clicked :"+position,Toast.LENGTH_LONG).show();
                    if(position == 0){
                        dispatchTakePictureIntent();
                    }
                    if(position==1){
                        Intent radarIntent = new Intent(MenuActivity.this,RadarActivity.class);
                        startActivity(radarIntent);
                    }
                    if(position==2){
                        Intent intent = new Intent(MenuActivity.this,ResponsibilityChainActivity.class);
                        startActivity(intent);
                    }
                    if(position==3){
                        //Intent radarIntent = new Intent(MenuActivity.this,ResponsibilityChainActivity.class);
                        //startActivity(radarIntent);
                    }
                    if(position==4){
                        Intent intent = new Intent(MenuActivity.this,QuickPayActivity.class);
                        startActivity(intent);
                    }

                }
            });
        }

        static final int REQUEST_IMAGE_CAPTURE = 0;
        private void dispatchTakePictureIntent(){
            Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            if(takePictureIntent.resolveActivity(getPackageManager()) != null){
                startActivityForResult(takePictureIntent,REQUEST_IMAGE_CAPTURE);
            }
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
