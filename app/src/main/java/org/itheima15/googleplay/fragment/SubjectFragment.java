package org.itheima15.googleplay.fragment;

import android.graphics.Color;
import android.view.View;
import android.widget.ListView;

import org.itheima15.googleplay.base.BaseHolder;
import org.itheima15.googleplay.base.SuperBaseAdapter;
import org.itheima15.googleplay.bean.SubjectBean;
import org.itheima15.googleplay.holder.SubjectHolder;
import org.itheima15.googleplay.protocol.SubjectProtocol;
import org.itheima15.googleplay.utils.UIUtils;
import org.itheima15.googleplay.view.LoadDataUI;

import java.util.List;

/*
 *  @项目名：  GooglePlay 
 *  @包名：    org.itheima15.googleplay.fragment
 *  @文件名:   SubjectFragment
 *  @创建者:   Administrator
 *  @创建时间:  2015/11/26 9:14
 *  @描述：    TODO
 */
public class SubjectFragment
        extends LoadDataFragment
{
    private SubjectProtocol   mProtocol;
    private List<SubjectBean> mDatas;

    @Override
    protected LoadDataUI.Result doInBackground() {
        mProtocol = new SubjectProtocol();

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
        mListView.setAdapter(new SubjectAdapter(mDatas));

        return mListView;
    }

    private class SubjectAdapter extends SuperBaseAdapter<SubjectBean> {

        public SubjectAdapter(List<SubjectBean> datas) {
            super(datas);
        }

        @Override
        protected BaseHolder getItemHolder(int position) {
            return new SubjectHolder();
        }
    }
}
