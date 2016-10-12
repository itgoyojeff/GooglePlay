package org.itheima15.googleplay.bean;

import java.util.List;

/*
 *  @项目名：  GooglePlay 
 *  @包名：    org.itheima15.googleplay.bean
 *  @文件名:   HomeBean
 *  @创建者:   Administrator
 *  @创建时间:  2015/11/23 16:45
 *  @描述：    首页数据
 */
public class HomeBean {

    public List<AppBean> list;
    public List<String>  picture;


    public static class AppBean {
        public String des;
        public String downloadUrl;
        public String iconUrl;
        public long   id;
        public String name;
        public String packageName;
        public long   size;
        public float  stars;


        public String         author;
        public String         date;
        public String         downloadNum;
        public List<SafeBean> safe;
        public List<String>   screen;
        public String         version;

        @Override
        public String toString() {
            return "AppBean{" +
                    "des='" + des + '\'' +
                    ", downloadUrl='" + downloadUrl + '\'' +
                    ", iconUrl='" + iconUrl + '\'' +
                    ", id=" + id +
                    ", name='" + name + '\'' +
                    ", packageName='" + packageName + '\'' +
                    ", size=" + size +
                    ", stars=" + stars +
                    ", author='" + author + '\'' +
                    ", date='" + date + '\'' +
                    ", downloadNum='" + downloadNum + '\'' +
                    ", safe=" + safe +
                    ", screen=" + screen +
                    ", version='" + version + '\'' +
                    '}';
        }
    }

    public static class SafeBean {
        public String safeDes;
        public int    safeDesColor;
        public String safeDesUrl;
        public String safeUrl;
    }


}
