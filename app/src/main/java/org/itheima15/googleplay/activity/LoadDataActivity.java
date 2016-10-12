package org.itheima15.googleplay.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import org.itheima15.googleplay.base.BaseActivity;
import org.itheima15.googleplay.view.LoadDataUI;

/*
 *  @项目名：  GooglePlay 
 *  @包名：    org.itheima15.googleplay.activity
 *  @文件名:   LoadDataActivity
 *  @创建者:   Administrator
 *  @创建时间:  2015/11/27 10:54
 *  @描述：    数据加载的activity的基类
 */
public abstract class LoadDataActivity
        extends BaseActivity
{
    private LoadDataUI mUI;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mUI = new LoadDataUI(this) {
            @Override
            protected Result onLoadData() {
                return doInBackground();
            }

            @Override
            protected View onLoadSuccessView() {
                return initSuccessView();
            }
        };

        setContentView(mUI);
    }

    protected abstract LoadDataUI.Result doInBackground();

    protected abstract View initSuccessView();

    public void loadData() {
        if (mUI != null) {
            mUI.loadData();
        }
    }
}
