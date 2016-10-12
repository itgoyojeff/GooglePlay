package org.itheima15.googleplay.protocol;

import org.itheima15.googleplay.bean.SubjectBean;

import java.util.List;

/*
 *  @项目名：  GooglePlay 
 *  @包名：    org.itheima15.googleplay.protocol
 *  @文件名:   SubjectProtocol
 *  @创建者:   Administrator
 *  @创建时间:  2015/11/26 9:14
 *  @描述：    TODO
 */
public class SubjectProtocol
        extends PageProtocol<List<SubjectBean>>
{
    @Override
    protected String getInterfacePath() {
        return "/subject";
    }

//    @Override
//    protected List<SubjectBean> parseJson(String json) {
//        return new Gson().fromJson(json, new TypeToken<List<SubjectBean>>() {}.getType());
//    }
}
