package org.itheima15.googleplay.protocol;

import org.itheima15.googleplay.bean.HomeBean;

import java.util.List;

/*
 *  @项目名：  GooglePlay 
 *  @包名：    org.itheima15.googleplay.protocol
 *  @文件名:   GameProtocol
 *  @创建者:   Administrator
 *  @创建时间:  2015/11/26 8:35
 *  @描述：    TODO
 */
public class GameProtocol
        extends PageProtocol<List<HomeBean.AppBean>>
{
    @Override
    protected String getInterfacePath() {
        return "/game";
    }

//    @Override
//    protected List<HomeBean.AppBean> parseJson(String json) {
//        Gson gson = new Gson();
//
//        //        //用泛型解析
//        //        return gson.fromJson(json, new TypeToken<List<HomeBean.AppBean>>() {}.getType());
//
//        //Gson 节点解析
//
//        //        JsonElement
//        //        JsonPrimitive: int,float,double,String,long,boolean
//        //        JsonObject:{}
//        //        JsonArray:数组[]
//        //        JsonNull:{key:NULL}
//
//        List<HomeBean.AppBean> list = new ArrayList<>();
//
//        //1.创建json解析器
//        JsonParser parser = new JsonParser();
//
//        //2.解析json
//        JsonElement rootElement = parser.parse(json);
//        //获得具体的类型
//        JsonArray root = rootElement.getAsJsonArray();
//
//        for (int i = 0; i < root.size(); i++) {
//            JsonElement element = root.get(i);
//            //获得具体的类型
//            JsonObject jsonObj = element.getAsJsonObject();
//
//            //des
//            JsonPrimitive desJson = jsonObj.getAsJsonPrimitive("des");
//            String        des     = desJson.getAsString();
//
//            //downloadUrl
//            JsonPrimitive downloadUrlJson = jsonObj.getAsJsonPrimitive("downloadUrl");
//            String     downloadUrl     = downloadUrlJson.getAsString();
//
//            //iconUrl
//            JsonPrimitive iconUrlJson = jsonObj.getAsJsonPrimitive("iconUrl");
//            String        iconUrl     = iconUrlJson.getAsString();
//
//            // id	1642739
//            JsonPrimitive idJson = jsonObj.getAsJsonPrimitive("id");
//            long       id     = idJson.getAsLong();
//
//            // name
//            JsonPrimitive nameJson = jsonObj.getAsJsonPrimitive("name");
//            String     name     = nameJson.getAsString();
//
//            //  packageName
//            JsonPrimitive packageNameJson = jsonObj.getAsJsonPrimitive("packageName");
//            String     packageName     = packageNameJson.getAsString();
//
//            //            size	9815944
//            JsonPrimitive sizeJson = jsonObj.getAsJsonPrimitive("size");
//            long       size     = sizeJson.getAsLong();
//
//            // stars	2.5
//            JsonPrimitive starsJson = jsonObj.getAsJsonPrimitive("stars");
//            float      stars     = starsJson.getAsFloat();
//
//
//            HomeBean.AppBean bean = new HomeBean.AppBean();
//            bean.des = des;
//            bean.downloadUrl = downloadUrl;
//            bean.iconUrl = iconUrl;
//            bean.id = id;
//            bean.name = name;
//            bean.packageName = packageName;
//            bean.size = size;
//            bean.stars = stars;
//
//
//            list.add(bean);
//
//        }
//
//        return list;
//    }
}
