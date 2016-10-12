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
 *  @文件名:   DetailInfoHolder
 *  @创建者:   Administrator
 *  @创建时间:  2015/11/27 14:07
 *  @描述：    TODO
 */
public class DetailInfoHolder
        extends BaseHolder<HomeBean.AppBean>
{
    @Bind(R.id.app_detail_info_tv_name)
    protected TextView mTvName;
    @Bind(R.id.app_detail_info_tv_downloadnum)
    protected TextView mTvDownloadNum;
    @Bind(R.id.app_detail_info_tv_size)
    protected TextView mTvSize;
    @Bind(R.id.app_detail_info_tv_time)
    protected TextView mTvTime;
    @Bind(R.id.app_detail_info_tv_version)
    protected TextView mTvVersion;

    @Bind(R.id.app_detail_info_iv_icon)
    protected ImageView mIvIcon;

    @Bind(R.id.app_detail_info_rb_star)
    protected RatingBar mRbStar;

    @Override
    protected View initView() {
        View view = View.inflate(UIUtils.getContext(), R.layout.item_app_detail_info, null);

        ButterKnife.bind(this, view);

        return view;
    }

    @Override
    protected void refreshUI(HomeBean.AppBean data) {
        mTvDownloadNum.setText(UIUtils.getString(R.string.info_downloadnum, data.downloadNum));
        mTvName.setText(data.name);
        mTvSize.setText(UIUtils.getString(R.string.info_size,
                                          Formatter.formatFileSize(UIUtils.getContext(),
                                                                   data.size)));
        mTvTime.setText(UIUtils.getString(R.string.info_date, data.date));
        mTvVersion.setText(UIUtils.getString(R.string.info_version, data.version));

        mRbStar.setRating(data.stars);

        String url = Constants.IMAGE_BASE + data.iconUrl;
        Picasso.with(UIUtils.getContext())
               .load(url)
               .placeholder(R.mipmap.ic_default)
               .error(R.mipmap.ic_default)
               .into(mIvIcon);
    }
}
