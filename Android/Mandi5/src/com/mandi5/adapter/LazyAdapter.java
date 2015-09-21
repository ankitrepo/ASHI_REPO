package com.mandi5.adapter;

import java.util.ArrayList;
import java.util.List;

import com.mandi5.R;
import com.mandi5.bean.SubChild;

import android.app.Activity;
import android.content.Context;
import android.opengl.Visibility;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

public class LazyAdapter extends BaseAdapter {

	private Context activity;

	private static LayoutInflater inflater = null;
	int data;
	

	private ViewHolder holder;
	List<SubChild> listing;
	
	
	
	public static class ViewHolder{
		TextView tv;
		
	}
	
	

	public LazyAdapter(Activity act, List<SubChild> listing) {
		activity = act;
		this.listing = listing;
		inflater = (LayoutInflater) activity
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

	}
	
	
	

	
	

	public int getCount() {
		
			data = listing.size();
		
		return data;
	}

	public Object getItem(int position) {
		return position;
	}

	public long getItemId(int position) {
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		View vi = convertView;
		if (convertView == null) {
			holder = new ViewHolder();
			vi = inflater.inflate(R.layout.subchild_list_item, null);
			  holder.tv=(TextView)vi.findViewById(R.id.subChildName);;
			vi.setTag(holder);
		} else {
			holder = (ViewHolder) vi.getTag();
		}
		
		holder.tv.setText(listing.get(position).getName());
		

		return vi;
	}

}