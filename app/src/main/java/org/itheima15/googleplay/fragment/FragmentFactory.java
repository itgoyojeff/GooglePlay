package org.itheima15.googleplay.fragment;

import android.support.v4.util.SparseArrayCompat;
import android.util.Log;

/*
 *  @项目名：  GooglePlay 
 *  @包名：    org.itheima15.googleplay.fragment
 *  @文件名:   FragmentFactory
 *  @创建者:   Administrator
 *  @创建时间:  2015/11/23 10:32
 *  @描述：    TODO
 */
public class FragmentFactory {
    private static final String                              TAG     = "FragmentFactory";
    //    private static       Map<Integer, Fragment> mCaches = new HashMap<>();//hash算法
    //key-value:key(int,long)
    private static       SparseArrayCompat<LoadDataFragment> mCaches = new SparseArrayCompat();//稀疏算法

    public static LoadDataFragment getFragment(int position) {

        LoadDataFragment fragment = mCaches.get(position);

        if (fragment != null) {
            //读取缓存
            Log.d(TAG, "读取缓存 : " + position);
            return fragment;
        }

        //        <item>首页</item>
        //        <item>应用</item>
        //        <item>游戏</item>
        //        <item>专题</item>
        //        <item>推荐</item>
        //        <item>分类</item>
        //        <item>排行</item>
        switch (position) {
            case 0:
                //首页
                fragment = new HomeFragment();
                break;
            case 1:
                //应用
                fragment = new AppFragment();
                break;
            case 2:
                //游戏
                fragment = new GameFragment();
                break;
            case 3:
                //专题
                fragment = new SubjectFragment();
                break;
            case 4:
                //推荐
                fragment = new RecommendFragment();
                break;
            case 5:
                //分类
                fragment = new CategoryFragment();
                break;
            case 6:
                //排行
                fragment = new HotFragment();
                break;
        }

        //存储缓存
        Log.d(TAG, "存储缓存 : " + position);
        mCaches.put(position, fragment);

        return fragment;
    }
}
