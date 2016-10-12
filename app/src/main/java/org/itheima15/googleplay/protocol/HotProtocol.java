package org.itheima15.googleplay.protocol;

import org.itheima15.googleplay.base.BaseProtocol;

import java.util.List;
import java.util.Map;

/*
 *  @项目名：  GooglePlay 
 *  @包名：    org.itheima15.googleplay.protocol
 *  @文件名:   HotProtocol
 *  @创建者:   Administrator
 *  @创建时间:  2015/11/26 16:21
 *  @描述：    TODO
 */
public class HotProtocol
        extends BaseProtocol<List<String>>
{
    @Override
    protected String getInterfacePath() {
        return "/hot";
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
