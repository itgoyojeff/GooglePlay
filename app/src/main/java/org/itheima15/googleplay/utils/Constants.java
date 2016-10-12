package org.itheima15.googleplay.utils;

/*
 *  @项目名：  GooglePlay 
 *  @包名：    org.itheima15.googleplay.utils
 *  @文件名:   Constants
 *  @创建者:   Administrator
 *  @创建时间:  2015/11/24 9:11
 *  @描述：    TODO
 */
public interface Constants {

    //    String SERVER_URL = "http://188.188.7.100:8080/GooglePlayServer";
    String SERVER_URL = "http://192.168.56.1:8080/GooglePlayServer";
    //    String SERVER_URL = "http://192.168.191.1:8080/GooglePlayServer";

    String IMAGE_BASE = SERVER_URL + "/image?name=";


    int  PAGE_SIZE    = 20;
    long DELAYED_TIME = 10 * 1000;
}
