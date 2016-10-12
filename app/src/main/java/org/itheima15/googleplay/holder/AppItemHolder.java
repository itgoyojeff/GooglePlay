package org.itheima15.googleplay.holder;

import android.text.format.Formatter;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.itheima15.googleplay.R;
import org.itheima15.googleplay.base.BaseHolder;
import org.itheima15.googleplay.bean.HomeBean;
import org.itheima15.googleplay.utils.Constants;
import org.itheima15.googleplay.utils.UIUtils;

import butterknife.Bind;
import butterknife.ButterKnife;

/*
 *  @项目名：  GooglePlay 
 *  @包名：    org.itheima15.googleplay.holder
 *  @文件名:   AppItemHolder
 *  @创建者:   Administrator
 *  @创建时间:  2015/11/23 16:09
 *  @描述：    TODO
 */
public class AppItemHolder
        extends BaseHolder<HomeBean.AppBean>
{
    //    private TextView tv;

    @Bind(R.id.item_appinfo_iv_icon)
    protected ImageView mIvIcon;
    @Bind(R.id.item_appinfo_rb_stars)
    protected RatingBar mRbStars;
    @Bind(R.id.item_appinfo_tv_des)
    protected TextView  mTvDes;
    @Bind(R.id.item_appinfo_tv_size)
    protected TextView  mTvSize;
    @Bind(R.id.item_appinfo_tv_title)
    protected TextView  mTvTitle;

    @Override
    protected View initView() {
        //        View view = View.inflate(UIUtils.getContext(), R.layout.item_tmp, null);
        //
        //        tv = (TextView) view.findViewById(R.id.item_tmp_tv_title);
        //
        //        return view;

        View view = View.inflate(UIUtils.getContext(), R.layout.item_app_info, null);

        //bindview
        ButterKnife.bind(this, view);

        return view;
    }

    @Override
    protected void refreshUI(HomeBean.AppBean data) {
        //        tv.setText(data);

        mTvDes.setText(data.des);
        mTvSize.setText(Formatter.formatFileSize(UIUtils.getContext(), data.size));
        mTvTitle.setText(data.name);

        mRbStars.setRating(data.stars);

        //设置图片
        String url = Constants.IMAGE_BASE + data.iconUrl;
        Picasso.with(UIUtils.getContext())
               .load(url)
               .placeholder(R.mipmap.ic_default)
               .error(R.mipmap.ic_default)
               .into(mIvIcon);

    }
}
