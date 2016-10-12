package org.itheima15.googleplay.protocol;

import org.itheima15.googleplay.base.BaseProtocol;
import org.itheima15.googleplay.bean.HomeBean;

import java.util.HashMap;
import java.util.Map;

/*
 *  @项目名：  GooglePlay 
 *  @包名：    org.itheima15.googleplay.protocol
 *  @文件名:   AppDetailProtocol
 *  @创建者:   Administrator
 *  @创建时间:  2015/11/27 11:02
 *  @描述：    TODO
 */
public class AppDetailProtocol
        extends BaseProtocol<HomeBean.AppBean>
{

    private final String mPackageName;

    public AppDetailProtocol(String packageName) {
        this.mPackageName = packageName;
    }

    @Override
    protected String getInterfacePath() {
        return "/detail";
    }

    @Override
    protected Map<String, String> getParameters() {
        Map<String, String> map = new HashMap<>();
        map.put("packageName", mPackageName);
        return map;
    }

//    @Override
//    protected HomeBean.AppBean parseJson(String json) {
//        Gson gson = new Gson();
//        return gson.fromJson(json, HomeBean.AppBean.class);
//    }
}
