package org.itheima15.googleplay.protocol;

import org.itheima15.googleplay.bean.HomeBean;

/*
 *  @项目名：  GooglePlay 
 *  @包名：    org.itheima15.googleplay.protocol
 *  @文件名:   HomeProtocol
 *  @创建者:   Administrator
 *  @创建时间:  2015/11/24 11:07
 *  @描述：    TODO
 */
public class HomeProtocol
        extends PageProtocol<HomeBean>
{


    @Override
    protected String getInterfacePath() {
        return "/home";
    }


//    @Override
//    protected HomeBean parseJson(String json) {
//
//        Gson gson = new Gson();
//        return gson.fromJson(json, HomeBean.class);
//    }

    //    private static final String TAG = "HomeProtocol";
    //    private Map<String, String> mParameters;
    //
    //    public void setParameter(Map<String, String> maps) {
    //        this.mParameters = maps;
    //    }
    //
    //    private String initUrl() {
    //        String url = Constants.SERVER_URL + "/home";
    //
    //        StringBuffer sb = new StringBuffer(url);
    //        if (mParameters != null) {
    //            sb.append("?");
    //            //  ?index=0&key=value&
    //            int i = 0;
    //            for (Map.Entry<String, String> me : mParameters.entrySet()) {
    //
    //                String key = me.getKey();
    //                String value = me.getValue();
    //
    //                sb.append(key);
    //                sb.append("=");
    //                sb.append(value);
    //
    //                if (i != mParameters.size() - 1) {
    //                    sb.append("&");
    //                }
    //                i++;
    //            }
    //        }
    //        return sb.toString();
    //    }
    //
    //    public HomeBean loadData()
    //            throws Exception
    //    {
    //        OkHttpClient client = new OkHttpClient();
    //
    //        String url = initUrl();
    //
    //        Log.d(TAG,"url : " + url);
    //
    //        Request request = new Request.Builder().get()
    //                                               .url(url)
    //                                               .build();
    //
    //        Response response = client.newCall(request)
    //                                  .execute();
    //
    //        if (response.isSuccessful()) {
    //            String json = response.body()
    //                                  .string();
    //
    //            //解析json为对象
    //
    //            Gson gson = new Gson();
    //            return gson.fromJson(json, HomeBean.class);
    //        }
    //        return null;
    //    }

}
