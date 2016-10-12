package org.itheima15.googleplay.base;

import android.os.Environment;
import android.util.Log;

import com.google.gson.Gson;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.itheima15.googleplay.utils.Constants;
import org.itheima15.googleplay.utils.MD5Utils;
import org.itheima15.googleplay.utils.UIUtils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Map;

/*
 *  @项目名：  GooglePlay 
 *  @包名：    org.itheima15.googleplay.base
 *  @文件名:   BaseProtocol
 *  @创建者:   Administrator
 *  @创建时间:  2015/11/24 11:27
 *  @描述：    TODO
 */
public abstract class BaseProtocol<T> {
    private static final String TAG = "BaseProtocol";
    // 1. url(接口地址)home.subject.game
    // 2. url中的参数(key=value)
    // 3. 返回的值bean.????

    protected abstract String getInterfacePath();

    protected abstract Map<String, String> getParameters();

    protected Map<String, String> getHeaders() {
        return null;
    }

    protected T parseJson(String json) {
        //抽象泛型类型的解析
        Gson gson = new Gson();
        ParameterizedType genericType = (ParameterizedType) this.getClass()
                                                                .getGenericSuperclass();

        Type[] types = genericType.getActualTypeArguments();
        Type   type  = types[0];

        return gson.fromJson(json, type);
    }

    public T loadData()
            throws Exception
    {
        //1.去本地获取缓存数据
        // 如果有缓存数据，显示缓存数据
        T t = getDataFromLocal();
        if (t != null) {
            Log.d(TAG, "从本地加载数据....");

            return t;
        }

        Log.d(TAG, "从网络加载数据....");
        return getDataFromNet();
    }

    private T getDataFromLocal()
            throws Exception
    {
        //读去文件---> json ---->object
        //1.去什么位置(sd存在时，dir)
        File file = getCacheFile();

        if (!file.exists()) {
            //没有缓存
            return null;
        }

        //json
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));

            //1.时间戳
            String time = reader.readLine();
            if (Long.valueOf(time) + Constants.DELAYED_TIME <= System.currentTimeMillis()) {
                //数据超时了
                return null;
            }

            //2.内容
            String json = reader.readLine();

            Log.d(TAG, "" + json);
            return parseJson(json);
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                }
                reader = null;
            }
        }
    }

    private File getCacheFile() {

        File dir = null;
        if (Environment.getExternalStorageState()
                       .equals(Environment.MEDIA_MOUNTED))
        {
            //sd卡存在
            dir = new File(Environment.getExternalStorageDirectory(),
                           "/Android/data/" + UIUtils.getPackageName() + "/json");

        } else {
            dir = new File(UIUtils.getCacheDir(), "/json");
        }

        if (!dir.exists()) {
            dir.mkdirs();
        }

        String name = MD5Utils.encode(initUrl());
        return new File(dir, name);
    }

    private T getDataFromNet()
            throws Exception
    {
        OkHttpClient client = new OkHttpClient();

        String url = initUrl();
        Log.d(TAG, "url : " + url);

        Request.Builder builder = new Request.Builder().get()
                                                       .url(url);
        //添加自定义的头
        Map<String, String> headers = getHeaders();
        if (headers != null) {
            for (Map.Entry<String, String> me : headers.entrySet()) {
                String key   = me.getKey();
                String value = me.getValue();

                builder.addHeader(key, value);
            }
        }

        Request request = builder.build();
        Response response = client.newCall(request)
                                  .execute();

        if (response.isSuccessful()) {
            String json = response.body()
                                  .string();

            T t = parseJson(json);

            //存储缓存
            saveCache(new Gson().toJson(t));


            //解析json为对象
            return t;
        }
        return null;
    }

    private void saveCache(String json)
            throws Exception
    {
        //文件位置
        File file = getCacheFile();


        //文件的存储格式 ：时间戳 + "\n" + 内容
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(file));

            writer.write("" + System.currentTimeMillis());
            writer.write("\r\n");
            writer.write(json);
        } finally {
            if (writer != null) {
                writer.close();
                writer = null;
            }
        }
    }

    private String initUrl() {
        String url = Constants.SERVER_URL + getInterfacePath();

        StringBuffer        sb         = new StringBuffer(url);
        Map<String, String> parameters = getParameters();
        if (parameters != null) {
            sb.append("?");
            //  ?index=0&key=value&
            int i = 0;
            for (Map.Entry<String, String> me : parameters.entrySet()) {

                String key   = me.getKey();
                String value = me.getValue();

                sb.append(key);
                sb.append("=");
                sb.append(value);

                if (i != parameters.size() - 1) {
                    sb.append("&");
                }
                i++;
            }
        }
        return sb.toString();
    }
}
