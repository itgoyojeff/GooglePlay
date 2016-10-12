package org.itheima15.googleplay.holder;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.squareup.picasso.Picasso;

import org.itheima15.googleplay.R;
import org.itheima15.googleplay.base.BaseHolder;
import org.itheima15.googleplay.utils.Constants;
import org.itheima15.googleplay.utils.UIUtils;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/*
 *  @项目名：  GooglePlay 
 *  @包名：    org.itheima15.googleplay.holder
 *  @文件名:   TopPicHolder
 *  @创建者:   Administrator
 *  @创建时间:  2015/11/24 15:32
 *  @描述：    轮播图的holder
 */
public class TopPicHolder
        extends BaseHolder<List<String>>
        implements ViewPager.OnPageChangeListener, View.OnTouchListener
{

    @Bind(R.id.item_home_picture_pager)
    protected ViewPager mViewPager;

    @Bind(R.id.item_home_picture_container_indicator)
    protected LinearLayout mIndicatorContainer;

    private List<String> mDatas;

    private SwitchTask mTask;

    @Override
    protected View initView() {
        View view = View.inflate(UIUtils.getContext(), R.layout.item_home_picture, null);

        //bind
        ButterKnife.bind(this, view);

        return view;
    }

    @Override
    protected void refreshUI(List<String> data) {
        this.mDatas = data;

        //给Viewpager设置数据
        mViewPager.setAdapter(new PicAdapter());

        //动态 的添加点
        mIndicatorContainer.removeAllViews();

        for (int i = 0; i < data.size(); i++) {

            View point = new View(UIUtils.getContext());
            point.setBackgroundResource(R.mipmap.indicator_normal);

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams((int) (UIUtils.dp2px(6) + 0.5f),
                                                                             (int) (UIUtils.dp2px(6) + 0.5f));

            if (i != 0) {
                params.leftMargin = (int) UIUtils.dp2px(6);
                params.bottomMargin = (int) UIUtils.dp2px(10);
            } else {
                //设置默认选中
                point.setBackgroundResource(R.mipmap.indicator_selected);
            }
            mIndicatorContainer.addView(point, params);
        }


        //给viewpager设置监听
        mViewPager.addOnPageChangeListener(this);

        //设置选中
        int item = Integer.MAX_VALUE / 2;
        item = item - item % mDatas.size();
        mViewPager.setCurrentItem(item);

        //自动轮播
        if (mTask == null) {
            mTask = new SwitchTask();
        }
        mTask.start();

        //设置touch监听
        mViewPager.setOnTouchListener(this);

        //给viewpager设置动画
        mViewPager.setPageTransformer(true, new DepthPageTransformer());
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        position = position % mDatas.size();

        //改变indicator选中
        int count = mIndicatorContainer.getChildCount();

        for (int i = 0; i < count; i++) {
            View view = mIndicatorContainer.getChildAt(i);

            view.setBackgroundResource(position == i
                                       ? R.mipmap.indicator_selected
                                       : R.mipmap.indicator_normal);
        }

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                //停止轮播
                mTask.stop();
                break;
            case MotionEvent.ACTION_UP:

                mTask.start();
                break;
        }
        return false;
    }

    private class SwitchTask
            implements Runnable
    {

        @Override
        public void run() {
            int item = mViewPager.getCurrentItem();
            mViewPager.setCurrentItem(++item);

            UIUtils.postDelayed(this, 2000);
        }

        public void start() {
            stop();
            UIUtils.postDelayed(this, 2000);
        }

        public void stop() {
            UIUtils.removeCallbacks(this);
        }
    }

    private class PicAdapter
            extends PagerAdapter
    {

        @Override
        public int getCount() {
            if (mDatas != null) {
                //                return mDatas.size();
                return Integer.MAX_VALUE;
            }
            return 0;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            position = position % mDatas.size();

            ImageView iv = new ImageView(UIUtils.getContext());

            String url = Constants.IMAGE_BASE + mDatas.get(position);
            Picasso.with(UIUtils.getContext())
                   .load(url)
                   .error(R.mipmap.ic_default)
                   .placeholder(R.mipmap.ic_default)
                   .into(iv);

            iv.setScaleType(ImageView.ScaleType.FIT_XY);

            container.addView(iv);

            return iv;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

    }


    public class DepthPageTransformer
            implements ViewPager.PageTransformer
    {
        private static final float MIN_SCALE = 0.75f;

        public void transformPage(View view, float position) {
            int pageWidth = view.getWidth();

            if (position < -1) { // [-Infinity,-1)
                // This page is way off-screen to the left.
                view.setAlpha(0);

            } else if (position <= 0) { // [-1,0]
                // Use the default slide transition when moving to the left page
                view.setAlpha(1);
                view.setTranslationX(0);
                view.setScaleX(1);
                view.setScaleY(1);

            } else if (position <= 1) { // (0,1]
                // Fade the page out.
                view.setAlpha(1 - position);

                // Counteract the default slide transition
                view.setTranslationX(pageWidth * -position);

                // Scale the page down (between MIN_SCALE and 1)
                float scaleFactor = MIN_SCALE + (1 - MIN_SCALE) * (1 - Math.abs(position));
                view.setScaleX(scaleFactor);
                view.setScaleY(scaleFactor);

            } else { // (1,+Infinity]
                // This page is way off-screen to the right.
                view.setAlpha(0);
            }
        }
    }

}
