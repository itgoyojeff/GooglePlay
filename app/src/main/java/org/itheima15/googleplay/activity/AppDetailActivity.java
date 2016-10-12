package org.itheima15.googleplay.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

import org.itheima15.googleplay.R;
import org.itheima15.googleplay.bean.HomeBean;
import org.itheima15.googleplay.holder.DetailDesHolder;
import org.itheima15.googleplay.holder.DetailDownloadHolder;
import org.itheima15.googleplay.holder.DetailInfoHolder;
import org.itheima15.googleplay.holder.DetailPicHolder;
import org.itheima15.googleplay.holder.DetailSafeHolder;
import org.itheima15.googleplay.protocol.AppDetailProtocol;
import org.itheima15.googleplay.view.LoadDataUI;

import butterknife.Bind;
import butterknife.ButterKnife;

public class AppDetailActivity
        extends LoadDataActivity
{
    public static final String KEY_PACKAGENAME = "packageName";
    @Bind(R.id.detail_download_container)
    protected FrameLayout       mContainerDownload;
    @Bind(R.id.detail_info_container)
    protected FrameLayout       mContainerInfo;
    @Bind(R.id.detail_safe_container)
    protected FrameLayout       mContainerSafe;
    @Bind(R.id.detail_pic_container)
    protected FrameLayout       mContainerPic;
    @Bind(R.id.detail_des_container)
    protected FrameLayout       mContainerDes;
    private   AppDetailProtocol mProtocol;
    private   HomeBean.AppBean  mData;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        loadData();
    }

    @Override
    protected LoadDataUI.Result doInBackground() {
        String packageName = getIntent().getStringExtra(KEY_PACKAGENAME);
        mProtocol = new AppDetailProtocol(packageName);

        try {
            mData = mProtocol.loadData();

            if (mData == null) {
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
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(mData.name);
        actionBar.setDisplayHomeAsUpEnabled(true);

        //        //显示页面
        //        TextView tv = new TextView(this);
        //        tv.setText(mData.toString());
        //        return tv;
        View view = View.inflate(this, R.layout.activity_app_detail, null);

        //bind
        ButterKnife.bind(this, view);

        //加载holder

        //1.加载info部分
        DetailInfoHolder infoHolder = new DetailInfoHolder();
        mContainerInfo.addView(infoHolder.getRootView());
        infoHolder.setData(mData);

        //2.加载safe部分
        DetailSafeHolder safeHolder = new DetailSafeHolder();
        mContainerSafe.addView(safeHolder.getRootView());
        safeHolder.setData(mData.safe);

        //3.图片展示部分
        DetailPicHolder picHolder = new DetailPicHolder();
        mContainerPic.addView(picHolder.getRootView());
        picHolder.setData(mData.screen);

        //4.应用描述部分
        DetailDesHolder desHolder = new DetailDesHolder();
        mContainerDes.addView(desHolder.getRootView());
        desHolder.setData(mData);

        //5.下载部分
        DetailDownloadHolder downloadHolder = new DetailDownloadHolder();
        mContainerDownload.addView(downloadHolder.getRootView());
        downloadHolder.setData(mData);

        return view;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case android.R.id.home:
                finish();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
