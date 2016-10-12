package org.itheima15.googleplay.vo;

import java.util.List;

/*
 *  @项目名：  GooglePlay 
 *  @包名：    org.itheima15.googleplay.bean
 *  @文件名:   CategoryVo
 *  @创建者:   Administrator
 *  @创建时间:  2015/11/27 8:33
 *  @描述：    TODO
 */
public class CategoryVo {

    public List<CategoryInfo> infos;
    public String             title;

    public class CategoryInfo {
        public String name1;
        public String name2;
        public String name3;
        public String url1;
        public String url2;
        public String url3;
    }
}
