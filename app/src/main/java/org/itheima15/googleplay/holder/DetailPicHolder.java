package org.itheima15.googleplay.holder;

import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.squareup.picasso.Picasso;

import org.itheima15.googleplay.R;
import org.itheima15.googleplay.base.BaseHolder;
import org.itheima15.googleplay.utils.Constants;
import org.itheima15.googleplay.utils.UIUtils;
import org.itheima15.googleplay.view.RatioLayout;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/*
 *  @项目名：  GooglePlay 
 *  @包名：    org.itheima15.googleplay.holder
 *  @文件名:   DetailPicHolder
 *  @创建者:   Administrator
 *  @创建时间:  2015/11/27 15:33
 *  @描述：    TODO
 */
public class DetailPicHolder
        extends BaseHolder<List<String>>
{
    @Bind(R.id.app_detail_pic_iv_container)
    protected LinearLayout mContainer;

    @Override
    protected View initView() {
        View view = View.inflate(UIUtils.getContext(), R.layout.item_app_detail_pic, null);

        ButterKnife.bind(this, view);

        return view;
    }

    @Override
    protected void refreshUI(List<String> data) {
        mContainer.removeAllViews();

        for (int i = 0; i < data.size(); i++) {
            ImageView iv  = new ImageView(UIUtils.getContext());
            String    d   = data.get(i);
            String    url = Constants.IMAGE_BASE + d;
            Picasso.with(UIUtils.getContext())
                   .load(url)
                   .into(iv);

            iv.setBackgroundColor(Color.RED);

            //宽高比布局
            RatioLayout layout = new RatioLayout(UIUtils.getContext());
            layout.setRelative(RatioLayout.RELATIVE_WIDTH);
            layout.setRatio(0.6f);
            layout.addView(iv);

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(90,
                                                                             ViewGroup.LayoutParams.WRAP_CONTENT);

            if (i != data.size() - 1) {
                params.rightMargin = 4;
            }
            mContainer.addView(layout, params);
        }


    }
}
