package org.itheima15.googleplay.fragment;

import android.graphics.Color;
import android.view.View;
import android.widget.ListView;

import org.itheima15.googleplay.AppListAdapter;
import org.itheima15.googleplay.bean.HomeBean;
import org.itheima15.googleplay.protocol.GameProtocol;
import org.itheima15.googleplay.utils.UIUtils;
import org.itheima15.googleplay.view.LoadDataUI;

import java.util.List;

/*
 *  @项目名：  GooglePlay 
 *  @包名：    org.itheima15.googleplay.fragment
 *  @文件名:   GameFragment
 *  @创建者:   Administrator
 *  @创建时间:  2015/11/26 8:35
 *  @描述：    游戏界面
 */
public class GameFragment
        extends LoadDataFragment
{
    private GameProtocol           mProtocol;
    private List<HomeBean.AppBean> mDatas;

    @Override
    protected LoadDataUI.Result doInBackground() {

        mProtocol = new GameProtocol();

        try {
            mDatas = mProtocol.loadPage(0);


            if (mDatas == null || mDatas.size() == 0) {
                return LoadDataUI.Result.EMPTY;
            }

            return LoadDataUI.Result.SUCCESS;

        } catch (Exception e) {
            e.printStackTrace();

            return LoadDataUI.Result.FAILED;
        }
    }

    @Override
    protected View initSuccessView() {
        ListView mListView = new ListView(UIUtils.getContext());
        mListView.setBackgroundColor(Color.parseColor("#ffcccccc"));


        //给listView设置adapter
        mListView.setAdapter(new GameAdapter(mDatas, mListView));

        return mListView;
    }

    private class GameAdapter
            extends AppListAdapter
    {


        public GameAdapter(List<HomeBean.AppBean> datas, ListView listView) {
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
