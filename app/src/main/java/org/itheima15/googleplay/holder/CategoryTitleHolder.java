package org.itheima15.googleplay.holder;

import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

import org.itheima15.googleplay.base.BaseHolder;
import org.itheima15.googleplay.bean.CategoryBean;
import org.itheima15.googleplay.utils.UIUtils;

/*
 *  @项目名：  GooglePlay 
 *  @包名：    org.itheima15.googleplay.holder
 *  @文件名:   CategoryTitleHolder
 *  @创建者:   Administrator
 *  @创建时间:  2015/11/27 9:01
 *  @描述：    TODO
 */
public class CategoryTitleHolder extends BaseHolder<CategoryBean> {

    private TextView mTextView;

    @Override
    protected View initView() {
        mTextView = new TextView(UIUtils.getContext());
        mTextView.setPadding(4,4,4,4);
        mTextView.setBackgroundColor(Color.WHITE);
        mTextView.setTextSize(16);
        mTextView.setTextColor(Color.BLACK);
        return mTextView;
    }

    @Override
    protected void refreshUI(CategoryBean data) {
        mTextView.setText(data.title);
    }
}
