package org.itheima15.googleplay.base;

import android.view.View;

/*
 *  @项目名：  GooglePlay 
 *  @包名：    org.itheima15.googleplay.base
 *  @文件名:   BaseHolder
 *  @创建者:   Administrator
 *  @创建时间:  2015/11/23 16:01
 *  @描述：    条目的Controller： MVC
 */
public abstract class BaseHolder<T> {

    private View mRootView;

    public BaseHolder() {
        //初始化view
        mRootView = initView();
    }

    protected abstract View initView();

    protected abstract void refreshUI(T data);

    public View getRootView() {
        return mRootView;
    }

    public void setData(T data) {
        //通过数据去更新UI
        refreshUI(data);
    }


}
