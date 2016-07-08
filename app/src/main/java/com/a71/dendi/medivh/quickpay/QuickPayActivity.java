package com.a71.dendi.medivh.quickpay;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.a71.dendi.medivh.R;

/**
 * Created by Dendi on 2016/7/7.
 */

public class QuickPayActivity extends AppCompatActivity {

    @BindView(R.id.alipay_button) Button ButtonAlipay;


    @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ativity_quickpay);
        ButterKnife.bind(this);
    }


    @OnClick(R.id.alipay_button)
    public void getOnClick(View view) {
    }


    public void aliPay() {
        AliPay.Builder builder = new AliPay.Builder(this);
        builder.setSELLER("443777124@qq.com")
            .setPARTNER("")
            .setRSA_PRIVATE("")
            .setNotifyURL("")
            .setOrderTitle("product")
            .setSubTitle("detail info")
            .setPrice("0.01")
            .setPayCallBackListener(new AliPay.Builder.PayCallBackListener() {
                @Override
                public void onPayCallBack(int status, String resultStatus, String progress) {
                    Toast.makeText(QuickPayActivity.this, progress, Toast.LENGTH_SHORT).show();
                }
            });
    }

}
