package org.itheima15.googleplay.holder;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.itheima15.googleplay.R;
import org.itheima15.googleplay.base.BaseHolder;
import org.itheima15.googleplay.bean.HomeBean;
import org.itheima15.googleplay.utils.Constants;
import org.itheima15.googleplay.utils.UIUtils;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/*
 *  @项目名：  GooglePlay 
 *  @包名：    org.itheima15.googleplay.holder
 *  @文件名:   DetailSafeHolder
 *  @创建者:   Administrator
 *  @创建时间:  2015/11/27 14:27
 *  @描述：    TODO
 */
public class DetailSafeHolder
        extends BaseHolder<List<HomeBean.SafeBean>>
        implements View.OnClickListener
{
    @Bind(R.id.app_detail_safe_pic_container)
    protected LinearLayout mContainerPic;
    @Bind(R.id.app_detail_safe_des_container)
    protected LinearLayout mContainerDes;

    @Bind(R.id.app_detail_safe_iv_arrow)
    protected ImageView mIvArrow;

    private boolean isOpened = true;//默认为打开的

    @Override
    protected View initView() {
        View view = View.inflate(UIUtils.getContext(), R.layout.item_app_detail_safe, null);

        ButterKnife.bind(this, view);

        view.setOnClickListener(this);

        return view;
    }

    @Override
    protected void refreshUI(List<HomeBean.SafeBean> data) {


        for (int i = 0; i < data.size(); i++) {

            //1.动态的添加图片
            addPic(data.get(i));
            //2.动态添加文本
            addDes(data.get(i));
        }

        //隐藏显示
        toggle(false);
    }

    private void addPic(HomeBean.SafeBean safeBean) {

        ImageView iv  = new ImageView(UIUtils.getContext());
        String    url = Constants.IMAGE_BASE + safeBean.safeUrl;
        Picasso.with(UIUtils.getContext())
               .load(url)
               .into(iv);
        mContainerPic.addView(iv);

    }

    private void addDes(HomeBean.SafeBean safeBean) {
        LinearLayout layout = new LinearLayout(UIUtils.getContext());
        layout.setOrientation(LinearLayout.HORIZONTAL);
        layout.setPadding(4, 4, 4, 4);
        layout.setGravity(Gravity.CENTER_VERTICAL);

        //添加图标
        ImageView iv  = new ImageView(UIUtils.getContext());
        String    url = Constants.IMAGE_BASE + safeBean.safeDesUrl;
        Picasso.with(UIUtils.getContext())
               .load(url)
               .into(iv);
        layout.addView(iv);

        //添加描述
        TextView tv = new TextView(UIUtils.getContext());
        tv.setText(safeBean.safeDes);
        if (safeBean.safeDesColor == 0) {
            tv.setTextColor(Color.parseColor("#FFC3C3C3"));
        } else {
            tv.setTextColor(Color.parseColor("#FFFF9000"));
        }
        layout.addView(tv);

        mContainerDes.addView(layout);
    }

    @Override
    public void onClick(View v) {
        toggle(true);
    }

    private void toggle(boolean animation) {
        mContainerDes.measure(0, 0);
        int measuredHeight = mContainerDes.getMeasuredHeight();
        if (isOpened) {
            //如果是打开的就关闭
            if (animation) {
                int start = measuredHeight;
                int end   = 0;
                doAnimation(start, end);

                //箭头:从上往下旋转
                ObjectAnimator.ofFloat(mIvArrow, "rotation", -180, 0)
                              .setDuration(400)
                              .start();
            } else {
                //设置高度为0:
                ViewGroup.LayoutParams params = mContainerDes.getLayoutParams();
                params.height = 0;
                mContainerDes.setLayoutParams(params);
            }
        } else {
            //如果是关闭的就打开
            if (animation) {
                int start = 0;
                int end   = measuredHeight;
                doAnimation(start, end);

                //箭头:从下往上旋转
                ObjectAnimator.ofFloat(mIvArrow, "rotation", 0, 180)
                              .setDuration(400)
                              .start();
            } else {
                ViewGroup.LayoutParams params = mContainerDes.getLayoutParams();
                params.height = measuredHeight;
                mContainerDes.setLayoutParams(params);
            }
        }

        //状态改变
        isOpened = !isOpened;
    }

    private void doAnimation(int start, int end) {
        ValueAnimator animator = ValueAnimator.ofInt(start, end);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int value = (int) animation.getAnimatedValue();

                ViewGroup.LayoutParams params = mContainerDes.getLayoutParams();
                params.height = value;
                mContainerDes.setLayoutParams(params);
            }
        });
        animator.setDuration(400);
        animator.start();
    }
}
