package org.itheima15.googleplay.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

import org.itheima15.googleplay.R;
import org.itheima15.googleplay.manager.ThreadManager;
import org.itheima15.googleplay.utils.UIUtils;

/*
 *  @项目名：  GooglePlay 
 *  @包名：    org.itheima15.googleplay.view
 *  @文件名:   LoadDataUI
 *  @创建者:   Administrator
 *  @创建时间:  2015/11/22 16:34
 *  @描述：    负责加载数据的view
 */
public abstract class LoadDataUI
        extends FrameLayout
{
    //需要展示 ？？？
    //UI显示
    //共同点--> 确定
    // 1. 加载中UI
    // 2. 失败的UI
    // 3. 服务器未给数据UI
    //不同--> 不确定
    // 4. 数据成功UI
    //一次只可以显示一种view -->显示或隐藏

    private static final int STATE_NONE    = 0;
    private static final int STATE_LOADING = 1;
    private static final int STATE_ERROR   = 2;
    private static final int STATE_EMPTY   = 3;
    private static final int STATE_SUCCESS = 4;

    private int mCurrentState = STATE_NONE;//用来记录当前的状态

    private View mLoadingView;//加载中的view
    private View mErrorView;//错误的view
    private View mEmptyView;//empty的view
    private View mSuccessView;//成功的view

    public LoadDataUI(Context context) {
        this(context, null);
    }

    public LoadDataUI(Context context, AttributeSet attrs) {
        super(context, attrs);

        //加载确定的UI，控制UI的显示
        initUI();
    }

    private void initUI() {
        //1. 添加加载中的UI
        mLoadingView = View.inflate(getContext(), R.layout.pager_loading, null);
        addView(mLoadingView);

        //2.error
        mErrorView = View.inflate(getContext(), R.layout.pager_error, null);
        mErrorView.findViewById(R.id.error_btn_retry)
                  .setOnClickListener(new OnClickListener() {
                      @Override
                      public void onClick(View v) {
                          loadData();
                      }
                  });
        addView(mErrorView);

        // 3.empty
        mEmptyView = View.inflate(getContext(), R.layout.pager_empty, null);
        addView(mEmptyView);

        //通过状态正确的显示UI
        safeUpdateUI();
    }

    private void safeUpdateUI() {
        UIUtils.post(new Runnable() {
            @Override
            public void run() {
                updateUI();
            }
        });
    }

    private void updateUI() {
        //加载中的显示
        mLoadingView.setVisibility((mCurrentState == STATE_LOADING || mCurrentState == STATE_NONE)
                                   ? View.VISIBLE
                                   : View.GONE);

        //加载错误的显示
        mErrorView.setVisibility(mCurrentState == STATE_ERROR
                                 ? View.VISIBLE
                                 : View.GONE);
        //加载空的显示
        mEmptyView.setVisibility(mCurrentState == STATE_EMPTY
                                 ? View.VISIBLE
                                 : View.GONE);


        if (mCurrentState == STATE_SUCCESS && mSuccessView == null) {
            //没有添加过成功的view,不确定的
            mSuccessView = onLoadSuccessView();
            addView(mSuccessView);
        }

        if (mSuccessView != null) {
            //加载成功的显示
            mSuccessView.setVisibility(mCurrentState == STATE_SUCCESS
                                       ? View.VISIBLE
                                       : View.GONE);
        }
    }


    /**
     * 触发加载数据的行为,做一个耗时的操作
     */
    public void loadData() {

        // 如果 当前已经是成功的，或者是加载中的，不需要再去加载数据
        if (mCurrentState == STATE_SUCCESS || mCurrentState == STATE_LOADING) {
            return;
        }

        //加载失败，加载为空
        mCurrentState = STATE_LOADING;
        safeUpdateUI();

        //bitmap--->
        //线程 ---> oom
        //执行耗时操作
        //        new Thread(new LoadDataTask()).start();
        ThreadManager.getNormalPool()
                     .execute(new LoadDataTask());
    }

    protected abstract Result onLoadData();

    protected abstract View onLoadSuccessView();

    public enum Result {
        SUCCESS(STATE_SUCCESS),
        FAILED(STATE_ERROR),
        EMPTY(STATE_EMPTY);

        private final int mState;

        private Result(int state) {
            this.mState = state;
        }

        public int getState() {
            return mState;
        }
    }

    private class LoadDataTask
            implements Runnable
    {
        @Override
        public void run() {
            //耗时的操作
            //结果数的类型无法确定,关心加载数据后的结果
            //显示什么：
            // 1.成功的UI
            // 2. 失败的UI
            // 3. 空数据的UI

            Result result = onLoadData();

            mCurrentState = result.getState();

            //根据状态更新UI
            safeUpdateUI();
        }
    }
}
