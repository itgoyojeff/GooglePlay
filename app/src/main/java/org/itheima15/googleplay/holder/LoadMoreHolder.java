package org.itheima15.googleplay.holder;

import android.view.View;

import org.itheima15.googleplay.R;
import org.itheima15.googleplay.base.BaseHolder;
import org.itheima15.googleplay.utils.UIUtils;

import butterknife.Bind;
import butterknife.ButterKnife;

/*
 *  @项目名：  GooglePlay 
 *  @包名：    org.itheima15.googleplay.holder
 *  @文件名:   LoadMoreHolder
 *  @创建者:   Administrator
 *  @创建时间:  2015/11/24 9:28
 *  @描述：    TODO
 */
public class LoadMoreHolder
        extends BaseHolder<Integer>
{

    public final static int STATE_LOADING = 0;
    public final static int STATE_ERROR   = 1;
    public final static int STATE_EMPTY   = 2;

    @Bind(R.id.item_loadmore_container_loading)
    protected View mLoadingContainer;

    @Bind(R.id.item_loadmore_container_retry)
    protected View mRetryContainer;

    @Override
    protected View initView() {
        //        TextView tv = new TextView(UIUtils.getContext());
        //
        //        tv.setText("加载更多");
        //        tv.setPadding(10, 10, 10, 10);
        //        tv.setTextColor(Color.RED);
        //        tv.setGravity(Gravity.CENTER);
        //
        //        return tv;

        View view = View.inflate(UIUtils.getContext(), R.layout.item_load_more, null);

        //bind
        ButterKnife.bind(this, view);

        return view;
    }

    @Override
    protected void refreshUI(Integer data) {
        switch (data) {
            case STATE_LOADING:
                mLoadingContainer.setVisibility(View.VISIBLE);
                mRetryContainer.setVisibility(View.GONE);
                break;
            case STATE_ERROR:
                mLoadingContainer.setVisibility(View.GONE);
                mRetryContainer.setVisibility(View.VISIBLE);
                break;
            case STATE_EMPTY:
                mLoadingContainer.setVisibility(View.GONE);
                mRetryContainer.setVisibility(View.GONE);
                break;
        }
    }
}
