package org.itheima15.googleplay.holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.itheima15.googleplay.R;
import org.itheima15.googleplay.base.BaseHolder;
import org.itheima15.googleplay.bean.SubjectBean;
import org.itheima15.googleplay.utils.Constants;
import org.itheima15.googleplay.utils.UIUtils;

import butterknife.Bind;
import butterknife.ButterKnife;

/*
 *  @项目名：  GooglePlay 
 *  @包名：    org.itheima15.googleplay.holder
 *  @文件名:   SubjectHolder
 *  @创建者:   Administrator
 *  @创建时间:  2015/11/26 9:20
 *  @描述：    TODO
 */
public class SubjectHolder
        extends BaseHolder<SubjectBean>
{

    @Bind(R.id.item_subject_iv_icon)
    protected ImageView mIvIcon;

    @Bind(R.id.item_subject_tv_title)
    protected TextView mTvTitle;

    @Override
    protected View initView() {
        View view = View.inflate(UIUtils.getContext(), R.layout.item_subject, null);

        //bind
        ButterKnife.bind(this, view);

        return view;
    }

    @Override
    protected void refreshUI(SubjectBean data) {

        //给文本赋值
        mTvTitle.setText(data.des);

        //图片
        String url = Constants.IMAGE_BASE + data.url;
        Picasso.with(UIUtils.getContext())
               .load(url)
               .placeholder(R.mipmap.ic_default)
               .error(R.mipmap.ic_default)
               .into(mIvIcon);

    }
}
