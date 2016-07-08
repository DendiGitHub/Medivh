package com.a71.dendi.medivh.utils.EventChain;

import android.support.annotation.NonNull;
import android.view.View;

/**
 * Created by Dendi on 2016/7/3.
 */

public class ViewEventStub extends EventStub<View> {
    public ViewEventStub(IEvent event,View viewStub){
        super(event,viewStub);
    }

    @Override protected boolean onEventImpl(@NonNull View viewStub) {
        View tempView = viewStub;
        if(tempView.getVisibility() == View.VISIBLE){
            tempView.setVisibility(View.INVISIBLE);
            return true;
        }
        return false;
    }
}
