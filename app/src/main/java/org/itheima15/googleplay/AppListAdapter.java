package org.itheima15.googleplay;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import org.itheima15.googleplay.activity.AppDetailActivity;
import org.itheima15.googleplay.base.BaseHolder;
import org.itheima15.googleplay.base.SuperBaseAdapter;
import org.itheima15.googleplay.bean.HomeBean;
import org.itheima15.googleplay.holder.AppItemHolder;
import org.itheima15.googleplay.utils.UIUtils;

import java.util.List;

/*
 *  @项目名：  GooglePlay 
 *  @包名：    org.itheima15.googleplay
 *  @文件名:   AppListAdapter
 *  @创建者:   Administrator
 *  @创建时间:  2015/11/27 11:15
 *  @描述：    TODO
 */
public abstract class AppListAdapter
        extends SuperBaseAdapter<HomeBean.AppBean>
        implements AdapterView.OnItemClickListener
{



     //go
    private ListView mListView;

    public AppListAdapter(List<HomeBean.AppBean> datas, ListView listView) {
        super(datas);
        this.mListView = listView;
        this.mListView.setOnItemClickListener(this);
    }

    @Override
    protected BaseHolder getItemHolder(int position) {
        return new AppItemHolder();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        position = position - mListView.getHeaderViewsCount();

        HomeBean.AppBean bean = (HomeBean.AppBean) getItem(position);

        Intent intent = new Intent(UIUtils.getContext(), AppDetailActivity.class);
        intent.putExtra(AppDetailActivity.KEY_PACKAGENAME, bean.packageName);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        UIUtils.getContext()
               .startActivity(intent);
    }
}
