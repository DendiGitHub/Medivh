package com.a71.dendi.medivh.common.Navigation;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.widget.Toast;
import com.a71.dendi.medivh.EventChain.ResponsibilityChainActivity;
import com.a71.dendi.medivh.quickpay.QuickPayActivity;
import com.a71.dendi.medivh.radar.RadarActivity;
import com.a71.dendi.medivh.utils.Utils;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dendi on 2016/7/11.
 */

public class NavigationDataHolder{
    private List<String> mList;
    private Context mContext;

    public NavigationDataHolder(Context context){
        initList();
        mContext = context;
    }

    private void initList(){
        mList = new ArrayList<String>();
        mList.add("Camera");
        mList.add("RadarMap");
        mList.add("ResponsibilityChain");
        mList.add("Notificatioin");
        mList.add("quickPay");
        mList.add("RateInAppStore");
        for(int i=mList.size();i<1000;i++){
            mList.add(String.valueOf(i));
        }
    }

    public List<String> getList(){
        return mList;
    }

    public void onItemClicked(int position){
        Toast.makeText(mContext,"clicked :"+position,Toast.LENGTH_LONG).show();
        if(position == 0){
            dispatchTakePictureIntent();
        }
        if(position==1){
            Intent radarIntent = new Intent(mContext,RadarActivity.class);
            mContext.startActivity(radarIntent);
        }
        if(position==2){
            Intent intent = new Intent(mContext,ResponsibilityChainActivity.class);
            mContext.startActivity(intent);
        }
        if(position==3){
            //Intent radarIntent = new Intent(MenuActivity.this,ResponsibilityChainActivity.class);
            //startActivity(radarIntent);
        }
        if(position==4){
            Intent intent = new Intent(mContext,QuickPayActivity.class);
            mContext.startActivity(intent);
        }
        if(position==5){
            tryToGradeApp();
        }
    }

    static final int REQUEST_IMAGE_CAPTURE = 0;
    private void dispatchTakePictureIntent(){
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if(takePictureIntent.resolveActivity(mContext.getPackageManager()) != null){
            ((Activity)mContext).startActivityForResult(takePictureIntent,REQUEST_IMAGE_CAPTURE);
        }
    }

    private void tryToGradeApp(){
        ArrayList<String> packages;
        if(mContext==null) return;
        packages= Utils.queryInstalledMarketPkgs(mContext);

        if(null == packages || 0 == packages.size()){
            Toast.makeText(mContext,"Do not find any installed market in your phone.",Toast.LENGTH_SHORT).show();
            return;
        }

        String appName = mContext.getPackageName();
        Uri uri = Uri.parse("market://details?id=" + appName);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);

        mContext.startActivity(intent);
    }
}
