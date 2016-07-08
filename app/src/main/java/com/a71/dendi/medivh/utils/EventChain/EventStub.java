package com.a71.dendi.medivh.utils.EventChain;

import android.support.annotation.NonNull;

/**
 * Created by Dendi on 2016/7/3.
 */

public abstract class EventStub<T> implements IEvent<T> {
    protected IEvent mEvent;
    protected T viewStub;

    public EventStub(IEvent event,T viewStub){
        this.mEvent = event;
        this.viewStub = viewStub;
    }


    @Override public boolean onEvent(@NonNull T obj) {
        boolean flag = onEventImpl(obj);
        if( !flag && mEvent!=null){
            return mEvent.onEvent(viewStub);
        }
        return true;
    }

    protected abstract boolean onEventImpl(@NonNull T viewStub);
}
