package com.a71.dendi.medivh.EventChain;

import android.support.annotation.NonNull;

/**
 * Created by Dendi on 2016/7/3.
 */

public interface IEvent<T> {
    public boolean onEvent(@NonNull T obj);
}
