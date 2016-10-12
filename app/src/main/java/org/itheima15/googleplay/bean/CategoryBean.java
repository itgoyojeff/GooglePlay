package org.itheima15.googleplay.bean;

/*
 *  @项目名：  GooglePlay 
 *  @包名：    org.itheima15.googleplay.bean
 *  @文件名:   CategoryBean
 *  @创建者:   Administrator
 *  @创建时间:  2015/11/27 8:44
 *  @描述：    TODO
 */
public class CategoryBean {
    public final static int TYPE_TITLE = 0;
    public final static int TYPE_ITEM  = 1;

    //用来记录item的类型，为了ui区分
    public int type;

    //适用于 title的条目
    //###### TYPE_TITLE #################
    public String title;


    //###### TYPE_ITEM #################
    public String name1;
    public String name2;
    public String name3;
    public String url1;
    public String url2;
    public String url3;

}
