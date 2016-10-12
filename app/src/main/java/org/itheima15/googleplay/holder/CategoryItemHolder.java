package org.itheima15.googleplay.holder;

import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.itheima15.googleplay.R;
import org.itheima15.googleplay.base.BaseHolder;
import org.itheima15.googleplay.bean.CategoryBean;
import org.itheima15.googleplay.utils.Constants;
import org.itheima15.googleplay.utils.UIUtils;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/*
 *  @项目名：  GooglePlay 
 *  @包名：    org.itheima15.googleplay.holder
 *  @文件名:   CategoryItemHolder
 *  @创建者:   Administrator
 *  @创建时间:  2015/11/27 9:03
 *  @描述：    TODO
 */
public class CategoryItemHolder
        extends BaseHolder<CategoryBean>
{
    @Bind(R.id.item_category_icon_1)
    protected ImageView mIvIcon1;
    @Bind(R.id.item_category_icon_2)
    protected ImageView mIvIcon2;
    @Bind(R.id.item_category_icon_3)
    protected ImageView mIvIcon3;

    @Bind(R.id.item_category_name_1)
    protected TextView mTvName1;
    @Bind(R.id.item_category_name_2)
    protected TextView mTvName2;
    @Bind(R.id.item_category_name_3)
    protected TextView mTvName3;

    @Bind(R.id.item_category_item_1)
    protected LinearLayout mContainer1;
    @Bind(R.id.item_category_item_2)
    protected LinearLayout mContainer2;
    @Bind(R.id.item_category_item_3)
    protected LinearLayout mContainer3;

    private CategoryBean mData;


    @Override
    protected View initView() {
        View view = View.inflate(UIUtils.getContext(), R.layout.item_category, null);

        //bind
        ButterKnife.bind(this, view);

        return view;
    }

    @Override
    protected void refreshUI(CategoryBean data) {
        this.mData = data;

        mTvName1.setText(data.name1);
        mTvName2.setText(data.name2);
        mTvName3.setText(data.name3);

        //        String url1 = Constants.IMAGE_BASE + data.url1;
        //        Picasso.with(UIUtils.getContext())
        //               .load(url1)
        //               .placeholder(R.mipmap.ic_default)
        //               .error(R.mipmap.ic_default)
        //               .into(mIvIcon1);
        //
        //        String url2 = Constants.IMAGE_BASE + data.url2;
        //        Picasso.with(UIUtils.getContext())
        //               .load(url2)
        //               .placeholder(R.mipmap.ic_default)
        //               .error(R.mipmap.ic_default)
        //               .into(mIvIcon2);
        //
        //        String url3 = Constants.IMAGE_BASE + data.url3;
        //        Picasso.with(UIUtils.getContext())
        //               .load(url3)
        //               .placeholder(R.mipmap.ic_default)
        //               .error(R.mipmap.ic_default)
        //               .into(mIvIcon3);

        //判断容器是否显示或隐藏
        showIcon(data.url1, mIvIcon1, mContainer1);
        showIcon(data.url2, mIvIcon2, mContainer2);
        showIcon(data.url3, mIvIcon3, mContainer3);
    }

    private void showIcon(String url, ImageView iv, LinearLayout container) {

        if (TextUtils.isEmpty(url)) {
            container.setVisibility(View.INVISIBLE);
        } else {
            container.setVisibility(View.VISIBLE);

            Picasso.with(UIUtils.getContext())
                   .load(Constants.IMAGE_BASE + url)
                   .placeholder(R.mipmap.ic_default)
                   .error(R.mipmap.ic_default)
                   .into(iv);
        }
    }

    @OnClick(R.id.item_category_item_1)
    public void clickItem1(View view) {
        Toast.makeText(UIUtils.getContext(), "" + mData.name1, Toast.LENGTH_SHORT)
             .show();
    }

    @OnClick(R.id.item_category_item_2)
    public void clickItem2(View view) {
        Toast.makeText(UIUtils.getContext(), "" + mData.name2, Toast.LENGTH_SHORT)
             .show();
    }

    @OnClick(R.id.item_category_item_3)
    public void clickItem3(View view) {
        Toast.makeText(UIUtils.getContext(), "" + mData.name3, Toast.LENGTH_SHORT)
             .show();
    }
}
