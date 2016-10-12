package org.itheima15.googleplay.fragment;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;
import android.view.Gravity;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import org.itheima15.googleplay.protocol.HotProtocol;
import org.itheima15.googleplay.utils.UIUtils;
import org.itheima15.googleplay.view.FlowLayout;
import org.itheima15.googleplay.view.LoadDataUI;

import java.util.List;
import java.util.Random;

/*
 *  @项目名：  GooglePlay 
 *  @包名：    org.itheima15.googleplay.fragment
 *  @文件名:   HotFragment
 *  @创建者:   Administrator
 *  @创建时间:  2015/11/26 16:21
 *  @描述：    TODO
 */
public class HotFragment
        extends LoadDataFragment
{
    private HotProtocol  mProtocol;
    private List<String> mDatas;

    @Override
    protected LoadDataUI.Result doInBackground() {

        mProtocol = new HotProtocol();

        try {
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
        ScrollView scrollView = new ScrollView(UIUtils.getContext());

        FlowLayout flowLayout = new FlowLayout(UIUtils.getContext());
        scrollView.setFillViewport(true);
        scrollView.addView(flowLayout);

        flowLayout.setSpace(10, 10);
        flowLayout.setPadding(10, 10, 10, 10);

        Random rdm = new Random();
        for (int i = 0; i < mDatas.size(); i++) {
            final String data = mDatas.get(i);
            TextView     view = new TextView(UIUtils.getContext());
            view.setText(data);
            //            view.setBackgroundColor(Color.GRAY);


            //            #aa bb cc ff
            int alpha = 255;//0-256
            int red   = rdm.nextInt(200) + 30;// 30 -- 230
            int green = rdm.nextInt(200) + 30;
            int blue  = rdm.nextInt(200) + 30;

            int argb = Color.argb(alpha, red, green, blue);

            GradientDrawable normalBg = new GradientDrawable();//normal
            normalBg.setShape(GradientDrawable.RECTANGLE);
            normalBg.setCornerRadius(5);
            normalBg.setColor(argb);

            GradientDrawable pressedBg = new GradientDrawable();//pressed
            pressedBg.setShape(GradientDrawable.RECTANGLE);
            pressedBg.setCornerRadius(5);
            pressedBg.setColor(Color.GRAY);

            StateListDrawable selector = new StateListDrawable();
            selector.addState(new int[]{android.R.attr.state_pressed}, pressedBg);
            selector.addState(new int[]{}, normalBg);

            view.setBackgroundDrawable(selector);

            view.setTextColor(Color.WHITE);
            view.setPadding(5, 5, 5, 5);
            view.setGravity(Gravity.CENTER);
            view.setTextSize(16);

            flowLayout.addView(view);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(UIUtils.getContext(), "" + data, Toast.LENGTH_SHORT)
                         .show();
                }
            });
        }

        return scrollView;
    }
}
