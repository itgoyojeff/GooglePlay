package org.itheima15.googleplay.fragment;

import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

import org.itheima15.googleplay.protocol.RecommendProtocol;
import org.itheima15.googleplay.utils.UIUtils;
import org.itheima15.googleplay.view.LoadDataUI;
import org.itheima15.googleplay.view.ShakeListener;
import org.itheima15.googleplay.view.StellarMap;

import java.util.List;
import java.util.Random;

/*
 *  @项目名：  GooglePlay 
 *  @包名：    org.itheima15.googleplay.fragment
 *  @文件名:   RecommendFragment
 *  @创建者:   Administrator
 *  @创建时间:  2015/11/27 10:03
 *  @描述：    TODO
 */
public class RecommendFragment
        extends LoadDataFragment
{
    private RecommendProtocol mProtocol;
    private List<String>      mDatas;
    private ShakeListener     mShake;

    @Override
    protected LoadDataUI.Result doInBackground() {
        try {
            mProtocol = new RecommendProtocol();
            mDatas = mProtocol.loadData();

            if (mDatas == null || mDatas.size() == 0) {
                return LoadDataUI.Result.EMPTY;
            }

            return LoadDataUI.Result.SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return LoadDataUI.Result.FAILED;
        }
    }

    @Override
    protected View initSuccessView() {
        final StellarMap layout = new StellarMap(UIUtils.getContext());

        layout.setInnerPadding(8, 8, 8, 8);

        //设置区域划分
        layout.setRegularity(10, 10);

        //设置数据
        final RecommondAdapter adapter = new RecommondAdapter();
        layout.setAdapter(adapter);

        //设置默认选中的页面
        layout.setGroup(0, true);

        //设置摇一摇的功能
        mShake = new ShakeListener(UIUtils.getContext());
        mShake.setOnShakeListener(new ShakeListener.OnShakeListener() {
            @Override
            public void onShake() {
                //摇一摇触发，选中下一页
                int group = layout.getCurrentGroup();

                int groupCount = adapter.getGroupCount();
                if (group == groupCount - 1) {
                    group = 0;
                } else {
                    group++;
                }
                layout.setGroup(group, true);
            }
        });

        return layout;
    }

    @Override
    public void onResume() {
        super.onResume();

        if (mShake != null) {
            mShake.resume();
        }
    }

    @Override
    public void onPause() {
        super.onPause();

        if (mShake != null) {
            mShake.pause();
        }
    }

    private class RecommondAdapter
            implements StellarMap.Adapter
    {

        private int    pageSize = 15;
        private Random rdm      = new Random();

        //返回的页面的数量
        @Override
        public int getGroupCount() {
            //            mDatas ---> 36:15,15,6
            int size = mDatas.size() / pageSize;
            if (mDatas.size() % pageSize != 0) {
                size++;
            }
            return size;
        }

        @Override
        public int getCount(int group) {
            //第group组数据的数量
            if (group == getGroupCount() - 1) {
                //最后一个页面时
                if (mDatas.size() % pageSize != 0) {
                    return mDatas.size() % pageSize;
                }
            }
            return pageSize;
        }

        @Override
        public View getView(int group, int position, View convertView) {
            //group，position
            //第group页面中的第position的数据
            int location = group * pageSize + position;

            String data = mDatas.get(location);


            int red   = rdm.nextInt(200) + 30;//30--230
            int green = rdm.nextInt(200) + 30;
            int blue  = rdm.nextInt(200) + 30;
            int argb  = Color.argb(255, red, green, blue);

            float size = rdm.nextInt(12) + 14;//14--->25

            //返回显示的view
            TextView tv = new TextView(UIUtils.getContext());
            tv.setText(data);
            tv.setTextColor(argb);
            tv.setTextSize(size);
            return tv;
        }

        @Override
        public int getNextGroupOnPan(int group, float degree) {
            return 0;
        }

        @Override
        public int getNextGroupOnZoom(int group, boolean isZoomIn) {
            return 0;
        }
    }
}
