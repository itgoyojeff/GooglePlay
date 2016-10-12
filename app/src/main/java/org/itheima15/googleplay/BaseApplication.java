package org.itheima15.googleplay;

import android.app.Application;

import org.itheima15.googleplay.utils.UIUtils;

/*
 *  @项目名：  GooglePlay 
 *  @包名：    org.itheima15.googleplay
 *  @文件名:   BaseApplication
 *  @创建者:   Administrator
 *  @创建时间:  2015/11/22 11:26
 *  @描述：    TODO
 */
public class BaseApplication
        extends Application
{

    @Override
    public void onCreate() {
        super.onCreate();

        //初始化UI相关的工具
        UIUtils.init(this);
    }
}
