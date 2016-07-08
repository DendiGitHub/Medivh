package com.a71.dendi.medivh.utils.EventChain;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.View;

/**
 * Created by Dendi on 2016/7/3.
 */

public class CloseEventStub extends EventStub<Activity> {
    public CloseEventStub(IEvent event,Activity viewStub){
        super(event,viewStub);
    }


    @Override protected boolean onEventImpl(@NonNull Activity activity) {
        if(!activity.isDestroyed()){
            activity.finish();
            return true;
        }
        return false;
    }
}
