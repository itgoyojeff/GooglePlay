package org.itheima15.googleplay.fragment;

import android.graphics.Color;
import android.view.View;
import android.widget.ListView;

import org.itheima15.googleplay.base.BaseHolder;
import org.itheima15.googleplay.base.SuperBaseAdapter;
import org.itheima15.googleplay.bean.CategoryBean;
import org.itheima15.googleplay.holder.CategoryItemHolder;
import org.itheima15.googleplay.holder.CategoryTitleHolder;
import org.itheima15.googleplay.protocol.CategoryProtocol;
import org.itheima15.googleplay.utils.UIUtils;
import org.itheima15.googleplay.view.LoadDataUI;

import java.util.List;

/*
 *  @项目名：  GooglePlay 
 *  @包名：    org.itheima15.googleplay.fragment
 *  @文件名:   CategoryFragment
 *  @创建者:   Administrator
 *  @创建时间:  2015/11/27 8:27
 *  @描述：    TODO
 */
public class CategoryFragment
        extends LoadDataFragment
{

    private CategoryProtocol   mProtocol;
    private List<CategoryBean> mDatas;

    @Override
    protected LoadDataUI.Result doInBackground() {

        mProtocol = new CategoryProtocol();
        try {
            mDatas = mProtocol.loadData();

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

        //给listView设置数据
        mListView.setAdapter(new CategoryAdapter(mDatas));

        return mListView;
    }

    private class CategoryAdapter
            extends SuperBaseAdapter<CategoryBean>
    {

        public CategoryAdapter(List<CategoryBean> datas) {
            super(datas);
        }

        @Override
        protected BaseHolder getItemHolder(int position) {
            CategoryBean bean = mDatas.get(position);

            if (bean.type == CategoryBean.TYPE_TITLE) {
                //如果是title的类型，显示一个title的holder
                return new CategoryTitleHolder();
            } else {
                //如果是item的类型，显示item的holder
                return new CategoryItemHolder();
            }
        }

        @Override
        public int getViewTypeCount() {
            return super.getViewTypeCount() + 1;
        }

        @Override
        protected int getNormalItemType(int position) {
            CategoryBean bean = mDatas.get(position);

            if (bean.type == CategoryBean.TYPE_TITLE) {
                //title类型
                return super.getNormalItemType(position);
            } else {
                //item类型
                return super.getNormalItemType(position) + 1;
            }
        }
    }
}
