package com.example.listviewrefresh;

import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.listviewrefresh.MainActivity.MyListItem;

public class MyListAdapter extends BaseAdapter
{

    /**
     * listview中的数据集
     */
    private ArrayList<MyListItem> mDataList;

    private Context               mContext;
    private ListView              mListView;

    public MyListAdapter(ArrayList<MyListItem> list, Context cont)
    {
        this.mDataList = list;
        this.mContext = cont;
    }

    /**
     * 设置listview对象
     * 
     * @param lisv
     */
    public void setListView(ListView lisv)
    {
        this.mListView = lisv;
    }

    /**
     * update listview 单条数据
     * 
     * @param item 新数据对象
     */
    public void updateItemData(MyListItem item)
    {
        Message msg = Message.obtain();
        int ids = -1;
        // 进行数据对比获取对应数据在list中的位置
        for (int i = 0; i < mDataList.size(); i++)
        {
            if (mDataList.get(i).getPosition() == item.getPosition())
            {
                ids = i;
            }
        }
        msg.arg1 = ids;
        // 更新mDataList对应位置的数据
        mDataList.set(ids, item);
        // handle刷新界面
        han.sendMessage(msg);
    }

    @SuppressLint("HandlerLeak")
    private Handler han = new Handler()
                        {
                            public void handleMessage(android.os.Message msg)
                            {
                                updateItem(msg.arg1);
                            };
                        };

    /**
     * 刷新指定item
     * 
     * @param index item在listview中的位置
     */
    private void updateItem(int index)
    {
        if (mListView == null)
        {
            return;
        }

        // 获取当前可以看到的item位置
        int visiblePosition = mListView.getFirstVisiblePosition();
        // 如添加headerview后 firstview就是hearderview
        // 所有索引+1 取第一个view
        // View view = listview.getChildAt(index - visiblePosition + 1);
        // 获取点击的view
        View view = mListView.getChildAt(index - visiblePosition);
        TextView txt = (TextView) view.findViewById(R.id.textView1);
        // 获取mDataList.set(ids, item);更新的数据
        MyListItem data = (MyListItem) getItem(index);
        // 重新设置界面显示数据
        txt.setText(data.getData());
    }

    @Override
    public int getCount()
    {
        // TODO Auto-generated method stub
        return mDataList.size();
    }

    @Override
    public Object getItem(int position)
    {
        // TODO Auto-generated method stub
        return mDataList.get(position);
    }

    @Override
    public long getItemId(int position)
    {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        // TODO Auto-generated method stub
        if (convertView == null)
        {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.list_item, null);
        }
        TextView txt = (TextView) convertView.findViewById(R.id.textView1);
        txt.setText(mDataList.get(position).getData());
        return convertView;
    }

}
