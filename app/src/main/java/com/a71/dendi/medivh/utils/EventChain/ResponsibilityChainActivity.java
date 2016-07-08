package com.a71.dendi.medivh.utils.EventChain;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.a71.dendi.medivh.R;

/**
 * Created by Dendi on 2016/7/3.
 */

public class ResponsibilityChainActivity extends AppCompatActivity{
    EventStub eventStub;
    @BindView(R.id.textView) TextView textView1;
    @BindView(R.id.textView2) TextView textView2;
    @BindView(R.id.textView3) TextView textView3;

    @BindView(R.id.button) Button button1;
    @BindView(R.id.button2) Button button2;
    @BindView(R.id.dismiss) Button buttonDismiss;


    @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chain);
        ButterKnife.bind(this);

        initEventStub();
    }

    @OnClick(R.id.dismiss)
    public void dismiss(Button button){
        dismiss();
    }

    private void initEventStub(){
        EventStub tempStub = new ViewEventStub(null,null);
        tempStub = new ViewEventStub(tempStub,textView1);
        tempStub = new ViewEventStub(tempStub,textView2);
        tempStub = new ViewEventStub(tempStub,textView3);
        eventStub = new ViewEventStub(tempStub,button1);
    }

    private void dismiss(){
        eventStub.onEvent(button2);
    }
}
