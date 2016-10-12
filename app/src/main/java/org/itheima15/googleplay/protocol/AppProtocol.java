package org.itheima15.googleplay.protocol;

import org.itheima15.googleplay.bean.HomeBean;

import java.util.List;

/*
 *  @项目名：  GooglePlay 
 *  @包名：    org.itheima15.googleplay.protocol
 *  @文件名:   AppProtocol
 *  @创建者:   Administrator
 *  @创建时间:  2015/11/24 15:16
 *  @描述：    TODO
 */
public class AppProtocol
        extends PageProtocol<List<HomeBean.AppBean>>
{
    @Override
    protected String getInterfacePath() {
        return "/app";
    }

//    @Override
//    protected List<HomeBean.AppBean> parseJson(String json) {
//        Gson gson = new Gson();
//        return gson.fromJson(json, new TypeToken<List<HomeBean.AppBean>>() {}.getType());
//    }
}
