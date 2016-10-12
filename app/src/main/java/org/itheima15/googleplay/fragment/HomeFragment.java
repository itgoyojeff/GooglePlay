package org.itheima15.googleplay.fragment;

import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import org.itheima15.googleplay.AppListAdapter;
import org.itheima15.googleplay.bean.HomeBean;
import org.itheima15.googleplay.holder.TopPicHolder;
import org.itheima15.googleplay.protocol.HomeProtocol;
import org.itheima15.googleplay.utils.UIUtils;
import org.itheima15.googleplay.view.LoadDataUI;

import java.util.List;

/*
 *  @项目名：  GooglePlay 
 *  @包名：    org.itheima15.googleplay.fragment
 *  @文件名:   HomeFragment
 *  @创建者:   Administrator
 *  @创建时间:  2015/11/22 16:22
 *  @描述：    首页对应的fragment:只实现加载数据的方式，成功显示的view
 */
public class HomeFragment
        extends LoadDataFragment
{
    private static final String TAG = "HomeFragment";
    //加载过程中，viewpager预加载中，某些页面是成功的，销毁后会反复的加载(即使是成功)


    //    private List<String> mDatas;//假数据

    private List<HomeBean.AppBean> mDatas;
    private List<String>           mPictures;//轮播图的图片
    private HomeProtocol           mProtocol;

    @Override
    protected LoadDataUI.Result doInBackground() {
        Log.d(TAG, "耗时操作.....");

        //1.模拟随机数
        //首页来实现自己加载数据的方式
        //        try {
        //            Thread.sleep(2000);
        //        } catch (InterruptedException e) {
        //            e.printStackTrace();
        //        }
        //
        //        LoadDataUI.Result[] results = new LoadDataUI.Result[]{LoadDataUI.Result.SUCCESS,
        //                                                              LoadDataUI.Result.FAILED,
        //                                                              LoadDataUI.Result.EMPTY};
        //
        //        Random rdm = new Random();
        //
        //        return results[rdm.nextInt(results.length)];

        // 2.假数据
        //        mDatas = new ArrayList<>();
        //        for (int i = 0; i < 100; i++) {
        //
        //            mDatas.add("数据-" + i);
        //        }
        //
        //        return LoadDataUI.Result.SUCCESS;

        // 3.去网络获取数据
        //1). Request
        // url.method.paramater,header
        //2). response
        // statecode.headers.body

        // Volley:异步执行的，UI
        // Okhttp:httpUrlConnection,同步执行

        //        OkHttpClient client = new OkHttpClient();
        //        String       url    = Constants.SERVER_URL + "/home?index=0";
        //
        //        //        RequestBody.create(MediaType.parse("application/xml"),"key=value&")
        //
        //        //        client.cancel("aaa");//取消网络访问
        //
        //        Request request = new Request.Builder().get()
        //                                               .url(url)
        //                                               .tag("aaa")
        //                                               .build();
        //
        //        try {
        //            Response response = client.newCall(request)
        //                                      .execute();
        //
        //            if (response.isSuccessful()) {
        //
        //                //访问网络成功
        //                String json = response.body()
        //                                      .string();
        //
        //                Log.d(TAG, "json : " + json);
        //
        //                //解析json
        //                Gson gson = new Gson();
        //                HomeBean bean = gson.fromJson(json, HomeBean.class);
        //
        //                if (bean == null) {
        //                    return LoadDataUI.Result.EMPTY;
        //                }
        //
        //                mDatas = bean.list;
        //                mPictures = bean.picture;
        //                return LoadDataUI.Result.SUCCESS;
        //            } else {
        //                //网络访问失败
        //                return LoadDataUI.Result.FAILED;
        //            }
        //        } catch (IOException e) {
        //            e.printStackTrace();
        //            return LoadDataUI.Result.FAILED;
        //        }

        //4.网络获取的封装
        mProtocol = new HomeProtocol();
        try {
            HomeBean bean = mProtocol.loadPage(0);

            if (bean == null) {
                return LoadDataUI.Result.EMPTY;
            }

            mDatas = bean.list;
            mPictures = bean.picture;
            return LoadDataUI.Result.SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return LoadDataUI.Result.FAILED;
        }
    }

    @Override
    protected View initSuccessView() {
        //
        //        TextView tv = new TextView(UIUtils.getContext());
        //        tv.setText("首页");
        //        tv.setTextSize(24);
        //        tv.setGravity(Gravity.CENTER);
        //
        //        return tv;

        ListView mListView = new ListView(UIUtils.getContext());
        mListView.setBackgroundColor(Color.parseColor("#ffcccccc"));

        //添加轮播图的头
        TopPicHolder holder = new TopPicHolder();
        mListView.addHeaderView(holder.getRootView());
        holder.setData(mPictures);

        mListView.setAdapter(new HomeAdapter(mDatas, mListView));


        return mListView;
    }

    private List<HomeBean.AppBean> loadMore()
            throws Exception
    {
        //        OkHttpClient client = new OkHttpClient();
        //
        //        String url = Constants.SERVER_URL + "/home?index=" + mDatas.size();
        //        Request request = new Request.Builder().get()
        //                                               .url(url)
        //                                               .build();
        //
        //        Response response = client.newCall(request)
        //                                  .execute();
        //
        //        if (response.isSuccessful()) {
        //            //访问网络成功
        //            String json = response.body()
        //                                  .string();
        //
        //            Gson gson = new Gson();
        //            HomeBean bean = gson.fromJson(json, HomeBean.class);
        //
        //            if (bean != null) {
        //                return bean.list;
        //            }
        //        } else {
        //            //失败
        //            return null;
        //        }
        //        return null;
        HomeBean bean = mProtocol.loadPage(mDatas.size());
        if (bean != null) {
            return bean.list;
        }

        return null;
    }

    private class HomeAdapter
            extends AppListAdapter
    {


        public HomeAdapter(List<HomeBean.AppBean> datas, ListView listView) {
            super(datas, listView);
        }

        @Override
        protected boolean hasLoadMore() {
            return true;
        }

        @Override
        protected List<HomeBean.AppBean> onLoadMore()
                throws Exception
        {
            return loadMore();
        }
    }
}