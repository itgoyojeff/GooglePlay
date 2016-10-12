package org.itheima15.googleplay.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import org.itheima15.googleplay.base.BaseFragment;
import org.itheima15.googleplay.view.LoadDataUI;

/*
 *  @项目名：  GooglePlay 
 *  @包名：    org.itheima15.googleplay.fragment
 *  @文件名:   LoadDataFragment
 *  @创建者:   Administrator
 *  @创建时间:  2015/11/22 16:28
 *  @描述：    加载数据的fragment的基类
 */
public abstract class LoadDataFragment
        extends BaseFragment
{

    private static final String TAG = "LoadDataFragment";
    private LoadDataUI mUi;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState)
    {
        Log.d(TAG, "onCreateView");

        //需要展示 ？？？
        //UI显示
        //共同点
        // 1. 加载数据UI
        // 2. 失败的UI
        // 3. 服务器未给数据UI
        //不同
        // 4. 数据成功UI
        //一次只可以显示一种view TODO:

        if (mUi == null) {
            mUi = new LoadDataUI(getActivity()) {
                @Override
                protected Result onLoadData() {
                    //实现加载数据的方式
                    return doInBackground();
                }

                @Override
                protected View onLoadSuccessView() {
                    return initSuccessView();
                }
            };
        } else {
            //不等于null,说明mui已经有父容器了
            ViewParent parent = mUi.getParent();

            if (parent != null && parent instanceof ViewGroup) {
                ((ViewGroup) parent).removeView(mUi);
            }

        }
        return mUi;
    }

    // //数据的角度
    //加载数据-->行为相同
    //方式不同


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Log.d(TAG, "onActivityCreated");


    }

    public void loadData() {
        if (mUi != null) {
            //加载数据
            mUi.loadData();
        }
    }

    protected abstract LoadDataUI.Result doInBackground();

    protected abstract View initSuccessView();
}
