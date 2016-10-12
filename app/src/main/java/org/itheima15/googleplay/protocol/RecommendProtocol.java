package org.itheima15.googleplay.protocol;

import org.itheima15.googleplay.base.BaseProtocol;

import java.util.List;
import java.util.Map;

/*
 *  @项目名：  GooglePlay 
 *  @包名：    org.itheima15.googleplay.protocol
 *  @文件名:   RecommendProtocol
 *  @创建者:   Administrator
 *  @创建时间:  2015/11/27 10:03
 *  @描述：    TODO
 */
public class RecommendProtocol
        extends BaseProtocol<List<String>>
{
    @Override
    protected String getInterfacePath() {
        return "/recommend";
    }

    @Override
    protected Map<String, String> getParameters() {
        return null;
    }

//    @Override
//    protected List<String> parseJson(String json) {
//        return new Gson().fromJson(json, new TypeToken<List<String>>() {}.getType());
//    }
}
