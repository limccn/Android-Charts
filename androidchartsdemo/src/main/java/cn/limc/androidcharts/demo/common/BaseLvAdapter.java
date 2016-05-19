package cn.limc.androidcharts.demo.common;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.ListView;

public abstract class BaseLvAdapter<T> extends BaseAdapter {
	//绑定器
	public LayoutInflater inflater;
	public List<T> dataList;
	public Context context;
	public View view;
	public int res;
	
	public boolean isScroll;
	public int startItem;
	public int visibleCount;

	public BaseLvAdapter(Context context, List<T> data, View view){
		super();
		this.context = context;
		this.dataList = data;
		this.view = view;
		inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		isScroll = false;
		
		if (view instanceof ListView) {
			((ListView)view).setAdapter(this);
		}
	}
	
	@Override
	public int getCount() {
		try {
			if(dataList == null){
				return 0;
			}
			return dataList.size();
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public Object getItem(int position) {
		try {
			if(dataList == null){
				return null;
			}
			return dataList.get(position);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public long getItemId(int position) {
		try {
			if(dataList == null){
				return 0;
			}
			return position;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
		
	}

	public void reSet(List<T> data){
		try {
			this.dataList = data;
			notifyDataSetChanged();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
