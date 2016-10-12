package org.itheima15.googleplay.fragment;

import android.graphics.Color;
import android.view.View;
import android.widget.ListView;

import org.itheima15.googleplay.AppListAdapter;
import org.itheima15.googleplay.bean.HomeBean;
import org.itheima15.googleplay.protocol.AppProtocol;
import org.itheima15.googleplay.utils.UIUtils;
import org.itheima15.googleplay.view.LoadDataUI;

import java.util.List;

/*
 *  @项目名：  GooglePlay 
 *  @包名：    org.itheima15.googleplay.fragment
 *  @文件名:   AppFragment
 *  @创建者:   Administrator
 *  @创建时间:  2015/11/24 15:15
 *  @描述：    应用页面
 */
public class AppFragment
        extends LoadDataFragment
{
    private AppProtocol            mProtocol;
    private List<HomeBean.AppBean> mDatas;

    @Override
    protected LoadDataUI.Result doInBackground() {
        //去加载网络数据
        mProtocol = new AppProtocol();

        try {
            mDatas = mProtocol.loadPage(0);

            if (mDatas == null || mDatas.size() == 0) {
                return LoadDataUI.Result.EMPTY;
            }

            return LoadDataUI.Result.SUCCESS;
        } catch (Exception e) {
            return LoadDataUI.Result.FAILED;
        }
    }

    @Override
    protected View initSuccessView() {
        ListView mListView = new ListView(UIUtils.getContext());
        mListView.setBackgroundColor(Color.parseColor("#ffcccccc"));

        //给listView设置adapter
        mListView.setAdapter(new AppAdapter(mDatas, mListView));

        return mListView;
    }

    private class AppAdapter
            extends AppListAdapter
    {


        public AppAdapter(List<HomeBean.AppBean> datas, ListView listView) {
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
            return mProtocol.loadPage(mDatas.size());
        }
    }
}
