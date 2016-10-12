package org.itheima15.googleplay.holder;

import android.view.View;

import org.itheima15.googleplay.R;
import org.itheima15.googleplay.base.BaseHolder;
import org.itheima15.googleplay.bean.HomeBean;
import org.itheima15.googleplay.utils.UIUtils;

import butterknife.ButterKnife;

/*
 *  @项目名：  GooglePlay 
 *  @包名：    org.itheima15.googleplay.holder
 *  @文件名:   DetailDownloadHolder
 *  @创建者:   Administrator
 *  @创建时间:  2015/11/27 16:47
 *  @描述：    TODO
 */
public class DetailDownloadHolder
        extends BaseHolder<HomeBean.AppBean>
{
    @Override
    protected View initView() {
        View view = View.inflate(UIUtils.getContext(), R.layout.item_app_detail_bottom, null);

        ButterKnife.bind(this, view);

        return view;
    }

    @Override
    protected void refreshUI(HomeBean.AppBean data) {

    }
}
