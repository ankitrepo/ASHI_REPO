package com.mandi5.adapter;

import java.util.ArrayList;
import java.util.List;

import com.mandi5.R;
import com.mandi5.bean.Banner;
import com.mandi5.lazyloader.ImageLoader;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


public class ViewPagerAdapter extends PagerAdapter {
	// Declare Variables
	Context context;
	List<Banner> banner;
	LayoutInflater inflater;
	ImageLoader imageLoader;
	String Serviceview_product = "view_product";
	String Serviceview_category_product = "view_category_product";
	String servicehtml = "html_link";

	public ViewPagerAdapter(Context context, List<Banner> banner) {
		this.context = context;

		this.banner = banner;
		imageLoader = new ImageLoader(context);

	}

	@Override
	public int getCount() {
		return banner.size();
	}

	@Override
	public boolean isViewFromObject(View view, Object object) {
		return view == ((RelativeLayout) object);
	}

	@Override
	public Object instantiateItem(ViewGroup container, final int position) {

		// Declare Variables
		ImageView imgflag;

		inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View itemView = inflater.inflate(R.layout.page, container, false);
		// Locate the ImageView in viewpager_item.xml
		imgflag = (ImageView) itemView.findViewById(R.id.slider_image);
		// Capture position and set to the ImageView
		imageLoader.DisplayImage(banner.get(position).getImageUrl(), imgflag);
		imgflag.setScaleType(ScaleType.FIT_XY);
		// TextView
		try {
			imgflag.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View arg0) {
					// view_product
					

					

				}
			});
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Add viewpager_item.xml to ViewPager
		((ViewPager) container).addView(itemView);

		return itemView;
	}

	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		// Remove viewpager_item.xml from ViewPager
		((ViewPager) container).removeView((RelativeLayout) object);

	}
}
