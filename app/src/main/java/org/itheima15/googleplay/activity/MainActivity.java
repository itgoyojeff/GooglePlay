package org.itheima15.googleplay.activity;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import org.itheima.tabindicator.library.TabIndicator;
import org.itheima15.googleplay.R;
import org.itheima15.googleplay.base.BaseActivity;
import org.itheima15.googleplay.fragment.FragmentFactory;
import org.itheima15.googleplay.fragment.LoadDataFragment;
import org.itheima15.googleplay.utils.UIUtils;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity
        extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener, ViewPager.OnPageChangeListener
{
    private static final String TAG = "MainActivity";
    @Bind(R.id.main_indicator)
    protected TabIndicator mIndicator;

    @Bind(R.id.main_viewpager)
    protected ViewPager mViewPager;

    private String[] titles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //        ActionBar actionBar = getSupportActionBar();
        //        actionBar.setTitle();


        //开关的设置
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,
                                                                 drawer,
                                                                 toolbar,
                                                                 R.string.navigation_drawer_open,
                                                                 R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        //设置导航item的点击事件
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        //初始化view
        ButterKnife.bind(this);

        //加载数据
        initData();
    }

    private void initData() {
        //加载页面数据
        titles = UIUtils.getStringArray(R.array.pagers);

        //给viewpager 加载数据
        mViewPager.setAdapter(new MainAdapter(getSupportFragmentManager()));

        //给indicator加载viewpager
        mIndicator.setViewPager(mViewPager);

        //设置viewpager的监听
        mIndicator.addOnPageChangeListener(this);

        //设置viewpager的动画
        mViewPager.setPageTransformer(true,new ZoomOutPageTransformer());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        //移除监听
        mIndicator.removeOnPageChangeListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        //选中时，让fragment去加载数据
        LoadDataFragment fragment = FragmentFactory.getFragment(position);
        fragment.loadData();
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    //      FragmentPagerAdapter:
    // 用于加载少量的页面,FragmentManger将fragment缓存了,生命周期是由activity控制

    // FragmentStatePagerAdapter
    // 用于加载大量的页面，没有缓存到内存中，缓存到disk中bundle
    private class MainAdapter
            extends FragmentStatePagerAdapter
    {

        public MainAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            //当显示Item时的回调
            Log.d(TAG, "加载" + position);
            //不去新建,通过工厂生产
            return FragmentFactory.getFragment(position);
        }

        @Override
        public int getCount() {
            if (titles != null) {
                return titles.length;
            }
            return 0;
        }

        public CharSequence getPageTitle(int position) {
            return titles[position];
        }
    }

    //    private class MainAdapter
    //            extends PagerAdapter
    //    {
    //
    //        @Override
    //        public int getCount() {
    //            if (titles != null) {
    //                return titles.length;
    //            }
    //            return 0;
    //        }
    //
    //        @Override
    //        public boolean isViewFromObject(View view, Object object) {
    //            return view == object;
    //        }
    //
    //        @Override
    //        public Object instantiateItem(ViewGroup container, int position) {
    //            //显示textView 临时显示 TODO:
    //            TextView tv = new TextView(MainActivity.this);
    //            tv.setText(titles[position]);
    //            tv.setTextSize(24);
    //            tv.setGravity(Gravity.CENTER);
    //
    //            container.addView(tv);
    //
    //            return tv;
    //        }
    //
    //        @Override
    //        public void destroyItem(ViewGroup container, int position, Object object) {
    //            container.removeView((View) object);
    //        }
    //
    //        @Override
    //        public CharSequence getPageTitle(int position) {
    //            return titles[position];
    //        }
    //    }

    public class ZoomOutPageTransformer implements ViewPager.PageTransformer {
        private static final float MIN_SCALE = 0.85f;
        private static final float MIN_ALPHA = 0.5f;

        public void transformPage(View view, float position) {
            int pageWidth = view.getWidth();
            int pageHeight = view.getHeight();

            if (position < -1) { // [-Infinity,-1)
                // This page is way off-screen to the left.
                view.setAlpha(0);

            } else if (position <= 1) { // [-1,1]
                // Modify the default slide transition to shrink the page as well
                float scaleFactor = Math.max(MIN_SCALE, 1 - Math.abs(position));
                float vertMargin = pageHeight * (1 - scaleFactor) / 2;
                float horzMargin = pageWidth * (1 - scaleFactor) / 2;
                if (position < 0) {
                    view.setTranslationX(horzMargin - vertMargin / 2);
                } else {
                    view.setTranslationX(-horzMargin + vertMargin / 2);
                }

                // Scale the page down (between MIN_SCALE and 1)
                view.setScaleX(scaleFactor);
                view.setScaleY(scaleFactor);

                // Fade the page relative to its size.
                view.setAlpha(MIN_ALPHA +
                                      (scaleFactor - MIN_SCALE) /
                                              (1 - MIN_SCALE) * (1 - MIN_ALPHA));

            } else { // (1,+Infinity]
                // This page is way off-screen to the right.
                view.setAlpha(0);
            }
        }
    }
}
