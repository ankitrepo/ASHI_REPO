package com.mandi5.adapter;

import java.util.ArrayList;
import java.util.List;

import com.mandi5.R;
import com.mandi5.bean.Parent;
import com.mandi5.lazyloader.ImageLoader;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


public class ExpandableListAdapter extends BaseExpandableListAdapter {
	private Typeface typeFaceBold;
	private Typeface typeFace;
	private Context _context;
	List<Parent> baseList;
	LayoutInflater infalInflater;
	private ImageLoader imageLoader;

	public ExpandableListAdapter(Context context,
			List<Parent> parentList) {
		this._context = context;
		this.baseList =  parentList;
		this.infalInflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		imageLoader = new ImageLoader(context);
	
	}

	@Override
	public Object getChild(int groupPosition, int childPosititon) {

		return baseList.get(groupPosition).getChildCategoryList();

	}

	@Override
	public long getChildId(int groupPosition, int childPosition) {
		return childPosition;
	}

	@Override
	public View getChildView(int groupPosition, final int childPosition,
			boolean isLastChild, View convertView, ViewGroup parent) {

		if (convertView == null) {

			convertView = infalInflater.inflate(R.layout.list_item, null);
		}

		LinearLayout searchLayout=(LinearLayout)convertView.findViewById(R.id.searchLayout);
		searchLayout.setBackgroundColor(_context.getResources().getColor(R.color.backgroundProductScreen));
		TextView txtListChild = (TextView) convertView
				.findViewById(R.id.lblListItem);
		txtListChild.setTextColor(_context.getResources().getColor(R.color.gray));
		txtListChild.setText(baseList.get(groupPosition)
				.getChildCategoryList().get(childPosition).getName());
		txtListChild.setTypeface(typeFace);
		return convertView;
	}

	@Override
	public int getChildrenCount(int groupPosition) {

		return baseList.get(groupPosition).getChildCategoryList().size();

	}

	@Override
	public Object getGroup(int groupPosition) {
		return baseList.get(groupPosition);
	}

	@Override
	public int getGroupCount() {
		return baseList.size();
	}

	@Override
	public long getGroupId(int groupPosition) {
		return groupPosition;
	}

	@Override
	public View getGroupView(int groupPosition, boolean isExpanded,
			View convertView, ViewGroup parent) {

		if (convertView == null) {
			LayoutInflater infalInflater = (LayoutInflater) _context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = infalInflater.inflate(R.layout.list_group, null);
		}

		TextView lblListHeader = (TextView) convertView
				.findViewById(R.id.lblListHeader);
		ImageView imageView = (ImageView) convertView
				.findViewById(R.id.imageView);
		
		
		// lblListHeader.setBackgroundColor(Color.YELLOW);
		String str = baseList.get(groupPosition).getImageIconUrl();
		imageLoader.DisplayImage(str, imageView);
		lblListHeader.setTypeface(null, Typeface.BOLD);
		lblListHeader.setText(baseList.get(groupPosition).getName());
		lblListHeader.setTypeface(typeFace);
		ImageView plusSymbol = (ImageView) convertView
				.findViewById(R.id.plusSymbol);
		
		plusSymbol.setImageResource(isExpanded ? R.drawable.minus 
			    : R.drawable.plus);
		// ExpandableListView expListView =
		// (ExpandableListView)convertView.findViewById(R.id.expLstSubChild);
		// SecondLevelAdapter listAdapter = new SecondLevelAdapter(_context,
		// ary);

		return convertView;
	}

	@Override
	public boolean hasStableIds() {
		return false;
	}

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		return true;
	}
}