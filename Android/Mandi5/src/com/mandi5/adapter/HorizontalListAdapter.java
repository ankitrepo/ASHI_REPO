package com.mandi5.adapter;

import java.util.ArrayList;
import java.util.List;

import com.mandi5.R;
import com.mandi5.bean.HorizCategory;
import com.mandi5.lazyloader.ImageLoader;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class HorizontalListAdapter extends BaseAdapter {

	private Context con;
	private Typeface typeFaceBold;
	private Typeface typeFace;
	public ImageLoader imageLoader;
	private List<HorizCategory> horzList;

	public HorizontalListAdapter(Context con, List<HorizCategory> horzList) {
		this.con = con;
		this.horzList = horzList;
		imageLoader = new ImageLoader(con);

	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub

		int size = horzList.size();
		
		return size;

	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		View retval = LayoutInflater.from(parent.getContext()).inflate(
				R.layout.viewitem, parent, false);
		TextView title = (TextView) retval.findViewById(R.id.title);
		title.setTextSize(12);
		
		ImageView image = (ImageView) retval.findViewById(R.id.image);
		
		if (horzList.size() > 0) {
			title.setText(horzList.get(position).getCat_name());
				}
		if (horzList.size() > 0) {
			// title.setText(arr_Text_Name.get(position).toString());
			String finalImgUrl = horzList.get(position).getCat_img();
			// Log.i("imageee", finalImgUrl);
			imageLoader.DisplayImage(finalImgUrl, image);
		}
		return retval;
	}

}
