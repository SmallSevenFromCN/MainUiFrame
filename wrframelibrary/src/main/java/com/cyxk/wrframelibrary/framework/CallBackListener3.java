package com.cyxk.wrframelibrary.framework;

/**
 * Created by 51425 on 2017/8/22.
 */

public interface CallBackListener3<T> {
    void onSuccess(T t);
    void onFailure(T t);
}
