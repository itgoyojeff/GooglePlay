package org.itheima15.googleplay.base;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import org.itheima15.googleplay.holder.LoadMoreHolder;
import org.itheima15.googleplay.manager.ThreadManager;
import org.itheima15.googleplay.utils.Constants;
import org.itheima15.googleplay.utils.UIUtils;

import java.util.List;

/*
 *  @项目名：  GooglePlay 
 *  @包名：    org.itheima15.googleplay.base
 *  @文件名:   SuperBaseAdapter
 *  @创建者:   Administrator
 *  @创建时间:  2015/11/23 15:48
 *  @描述：    adapter的基类
 */
public abstract class SuperBaseAdapter<T>
        extends BaseAdapter
{
    //提供加载更多的功能
    private final static int    TYPE_LOADM_MORE  = 0;
    private final static int    TYPE_NORMAL_ITEM = 1;
    private static final String TAG              = "SuperBaseAdapter";

    private List<T> mDatas;

    private LoadMoreHolder mLoadMoreHolder;
    private boolean isLoadingMore = false;//用来记录当前是否正在加载更多

    public SuperBaseAdapter(List<T> datas) {
        this.mDatas = datas;
    }

    @Override
    public int getItemViewType(int position) {
        //获得条目的类型
        //postition:获得具体position是什么类型
        //0，1，2，3

        if (position == getCount() - 1) {
            //是最后一条,是加载更多的类型
            return TYPE_LOADM_MORE;
        }

        return getNormalItemType(position);
    }

    /**
     * 如果子类普通的item类型比较多时，复习此方法
     * @param position
     * @return
     */
    protected int getNormalItemType(int position) {
        return TYPE_NORMAL_ITEM;
    }

    @Override
    public int getViewTypeCount() {
        //返回当前adapter item条目的类型个数
        return super.getViewTypeCount() + 1;
    }

    @Override
    public int getCount() {
        if (mDatas != null) {
            return mDatas.size() + 1;//提供多一个条目
        }
        return 0;
    }

    @Override
    public Object getItem(int position) {
        if (mDatas != null) {
            return mDatas.get(position);
        }
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //viewholder解决复用问题
        BaseHolder holder = null;
        // #### 1. 初始化view ########
        if (convertView == null) {
            // 没有复用
            if (getItemViewType(position) == TYPE_LOADM_MORE) {
                //是加载更多的item

                holder = getLoadMoreHolder();//确定一个具体的加载更多的holder
            } else {
                //2.新建holder
                holder = getItemHolder(position);
            }

            //1.加载布局(layout 指定死 TODO: ????--> 暴露给子类实现 可以显示的view)
            //            convertView = View.inflate(UIUtils.getContext(), R.layout.item_tmp, null);//
            convertView = holder.getRootView();

            //3.设置标记
            convertView.setTag(holder);

            //4.给holder找view (view太具体化 TODO: ????--> 暴露给子类实现,findViewId)-->交给holder实现
            //            holder.tvTitle = (TextView) convertView.findViewById(R.id.item_tmp_tv_title);
        } else {
            //有复用
            holder = (BaseHolder) convertView.getTag();
        }


        // #### 2. 给View设置数据 #########

        if (getItemViewType(position) == TYPE_LOADM_MORE) {
            //加载更多 TODO:

            if (!hasLoadMore()) {
                //                adapter不具备加载更多的功能
                holder.setData(LoadMoreHolder.STATE_EMPTY);
            } else {
                //具备
                //显示时，去加载更多的数据,---> List<T> more---> mDatas.addAll(more)--->更新UI
                loadMoreData();
            }

        } else {
            T data = mDatas.get(position);
            //给holder中的view设置数据(view 太具体 TODO: 给不具体的view设置数据 ???? setData )
            //        holder.tvTitle.setText(data);
            holder.setData(data);
        }
        return convertView;
    }

    private void loadMoreData() {

        if (isLoadingMore) {
            //如果当前正在加载更多,不执行
            return;
        }

        isLoadingMore = true;

        Log.d(TAG, "加载更多数据");

        //        加载更多的UI--》loading
        mLoadMoreHolder.setData(LoadMoreHolder.STATE_LOADING);

        //耗时的操作
        ThreadManager.getNormalPool()
                     .execute(new LoadMoreTask());
    }

    /**
     * 是否具备加载更多的功能，默认为没有，如果孩子要有的话，复写此方法
     * @return
     */
    protected boolean hasLoadMore() {
        return false;
    }

    /**
     * 加载更多数据的回调
     * @return
     */
    protected List<T> onLoadMore()
            throws Exception
    {
        return null;
    }

    protected LoadMoreHolder getLoadMoreHolder() {
        if (mLoadMoreHolder == null) {
            mLoadMoreHolder = new LoadMoreHolder();
        }

        return mLoadMoreHolder;
    }

    protected abstract BaseHolder getItemHolder(int position);


    private class LoadMoreTask
            implements Runnable
    {

        @Override
        public void run() {
            // 去加载更多的数据,---> List<T> more---> mDatas.addAll(more)--->更新UI
            List<T> more = null;
            int     s    = LoadMoreHolder.STATE_LOADING;
            try {
                Thread.sleep(1500);

                more = onLoadMore();
            } catch (Exception e) {
                e.printStackTrace();

                //UI显示
                s = LoadMoreHolder.STATE_ERROR;
            }

            final int     state = s;
            final List<T> datas = more;
            UIUtils.post(new Runnable() {
                @Override
                public void run() {

                    int current = state;
                    if (datas == null || datas.size() == 0) {
                        //加载的数据为空
                        current = LoadMoreHolder.STATE_EMPTY;
                    } else {

                        //  datas==1, 20 条记录,说明服务器已经没有更多的数据
                        //  datas==20, 说明服务器还有数据
                        if (datas.size() < Constants.PAGE_SIZE) {
                            //    说明服务器已经没有更多的数据
                            current = LoadMoreHolder.STATE_EMPTY;
                        } else {
                            current = LoadMoreHolder.STATE_LOADING;
                        }

                        mDatas.addAll(datas);
                        // 更新UI
                        notifyDataSetChanged();
                    }
                    //主线程
                    mLoadMoreHolder.setData(current);


                    //任务执行完成
                    isLoadingMore = false;
                }
            });
        }
    }
}
