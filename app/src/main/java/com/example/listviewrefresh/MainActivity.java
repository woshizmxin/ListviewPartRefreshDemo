package com.example.listviewrefresh;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class MainActivity extends Activity
{

    private ArrayList<MyListItem> list = null;
    private ListView              lv;
    private MyListAdapter         adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        intitData();
        lv = (ListView) findViewById(R.id.listView1);
        adapter = new MyListAdapter(list, getApplicationContext());
        adapter.setListView(lv);
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new OnItemClickListener()
        {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                // 获取listview中点击item的数据
                MyListItem item = (MyListItem) parent.getItemAtPosition(position);
                Log.i("eee", item.getData() + " == " + item.getPosition());
                // 更新数据
                item.setData("update item " + position);
                // 更新界面
                adapter.updateItemData(item);
            }
        });

    }

    /**
     * 初始化数据
     */
    private void intitData()
    {
        list = new ArrayList<MyListItem>();
        for (int i = 0; i < 20; i++)
        {
            MyListItem item = new MyListItem();
            item.setData("item " + i);
            item.setPosition(i);
            list.add(item);
        }
    }

    /**
     * 自定义item数据类型
     */
    class MyListItem
    {
        /**
         * 数据id
         */
        private int    dataId;
        /**
         * 数据
         */
        private String data;

        public int getPosition()
        {
            return dataId;
        }

        public void setPosition(int position)
        {
            this.dataId = position;
        }

        public String getData()
        {
            return data;
        }

        public void setData(String data)
        {
            this.data = data;
        }

    }
}
