package org.itheima15.googleplay.holder;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import org.itheima15.googleplay.R;
import org.itheima15.googleplay.base.BaseHolder;
import org.itheima15.googleplay.bean.HomeBean;
import org.itheima15.googleplay.utils.UIUtils;

import butterknife.Bind;
import butterknife.ButterKnife;

/*
 *  @项目名：  GooglePlay 
 *  @包名：    org.itheima15.googleplay.holder
 *  @文件名:   DetailDesHolder
 *  @创建者:   Administrator
 *  @创建时间:  2015/11/27 16:08
 *  @描述：    TODO
 */
public class DetailDesHolder
        extends BaseHolder<HomeBean.AppBean>
        implements View.OnClickListener
{
    private static final String TAG = "DetailDesHolder";

    @Bind(R.id.app_detail_des_tv_author)
    TextView mTvAuthor;

    @Bind(R.id.app_detail_des_tv_des)
    TextView mTvDes;

    @Bind(R.id.app_detail_des_iv_arrow)
    ImageView mIvArrow;

    private boolean isOpened = true;//用来记录是否打开的
    private int              mTextWidth;
    private HomeBean.AppBean mData;
    private View             mView;

    @Override
    protected View initView() {
        mView = View.inflate(UIUtils.getContext(), R.layout.item_app_detail_des, null);

        ButterKnife.bind(this, mView);

        mView.setOnClickListener(this);

        mView.getViewTreeObserver()
             .addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                 @Override
                 public void onGlobalLayout() {
                     mView.getViewTreeObserver()
                          .removeGlobalOnLayoutListener(this);

                     mTextWidth = mTvDes.getMeasuredWidth();

                 }
             });


        return mView;
    }

    @Override
    protected void refreshUI(HomeBean.AppBean data) {
        this.mData = data;


        mTvAuthor.setText("作者:" + data.author);
        mTvDes.setText(data.des);


        toggle(false);
    }

    @Override
    public void onClick(View v) {
        toggle(true);
    }

    private void toggle(boolean animation) {
        mTvDes.measure(View.MeasureSpec.makeMeasureSpec(mTextWidth, View.MeasureSpec.EXACTLY), 0);
        int totalHeight = mTvDes.getMeasuredHeight();

        int sevenHeight = get7LineHeight(mData.des);

        Log.d(TAG, "totalHeight : " + totalHeight);
        Log.d(TAG, "sevenHeight : " + sevenHeight);

        if (isOpened) {
            //如果是打开的，就关闭

            if (animation) {
                int start = totalHeight;
                int end   = sevenHeight;
                doAnimation(start, end);

                //箭头：从上往下旋转
                ObjectAnimator.ofFloat(mIvArrow, "rotation", -180, 0)
                              .setDuration(400)
                              .start();
            } else {
                ViewGroup.LayoutParams params = mTvDes.getLayoutParams();
                params.height = sevenHeight;// 7行显示的高度
                mTvDes.setLayoutParams(params);
            }
        } else {
            //如果是关闭的，就打开
            if (animation) {
                int start = sevenHeight;
                int end   = totalHeight;
                doAnimation(start, end);

                //箭头：从下往上旋转
                ObjectAnimator.ofFloat(mIvArrow, "rotation", 0, 180)
                              .setDuration(400)
                              .start();
            } else {
                ViewGroup.LayoutParams params = mTvDes.getLayoutParams();
                params.height = totalHeight;// 显示全部的高度
                mTvDes.setLayoutParams(params);
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

                ViewGroup.LayoutParams params = mTvDes.getLayoutParams();
                params.height = value;
                mTvDes.setLayoutParams(params);
            }
        });
        animator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                //动画停止时，需要滑动的底部
                //找到scrollview

                ScrollView scrollView = null;
                ViewParent parent     = mView.getParent();

                while (parent != null) {
                    if (parent instanceof ScrollView) {
                        //找到scrollveiw了
                        scrollView = (ScrollView) parent;
                        break;
                    } else {
                        parent = parent.getParent();
                    }
                }

                if (scrollView != null) {
                    scrollView.fullScroll(View.FOCUS_DOWN);
                }
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });

        animator.setDuration(400);
        animator.start();
    }

    private int get7LineHeight(String des) {

        TextView tv = new TextView(UIUtils.getContext());
        tv.setText(des);
        tv.setLines(7);

        tv.measure(View.MeasureSpec.makeMeasureSpec(mTextWidth, View.MeasureSpec.EXACTLY), 0);

        return tv.getMeasuredHeight();
    }
}
