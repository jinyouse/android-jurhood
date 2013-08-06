package com.chinasoft.jurhood.uicommons;



import org.fodlife.library.LayoutAdjuster;

import com.chinasoft.jurhood.AppDataManager;
import com.chinasoft.jurhood.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.FrameLayout;
import android.widget.ImageView;

public class FragmentBottomTabBar extends Fragment {
	
	public interface BottomTabBarListener {
		public void onBottomTabBarChooseListener(int itemIdx);
	}
	
    private boolean 						mLayoutAdjusted = false;
    private View							mView;
    private BottomTabBarListener			mTabBarChangedLister;
    private int[]							mIconResourceIdsArray;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
	  	final View view = inflater.inflate(R.layout.fragment_bottom_tab_bar, container, false);
    	ViewTreeObserver vto = view.getViewTreeObserver();
	    vto.addOnGlobalLayoutListener(new OnGlobalLayoutListener() {
	        @Override
	        public void onGlobalLayout() {
	        	if (!mLayoutAdjusted) {
	        		
	        		mLayoutAdjusted = true;
	        		
	    			LayoutAdjuster.sharedInstance().fitToScreen(getActivity(), (ViewGroup)view, 
	    					AppDataManager.sharedInstance().getXRate(), 
	    					AppDataManager.sharedInstance().getYRate(), 
	    					AppDataManager.sharedInstance().getWRate(), 
	    					AppDataManager.sharedInstance().getHRate());
	        	}
	        }
	    });
	    
	    mView = view;
	    
	    if (mIconResourceIdsArray != null) {
	    	changeIcons();
	    }
	    
	    viewInitialize(view);
    	return view;
    }

    public void viewInitialize(View view) {
    	int itemIds[] = {R.id.framelayout_item_1, R.id.framelayout_item_2, R.id.framelayout_item_3, R.id.framelayout_item_4, R.id.framelayout_item_5}; 
    	for (int idx = 0; idx < itemIds.length; idx++) {
    		FrameLayout item = (FrameLayout)view.findViewById(itemIds[idx]);
    		
    		final int index = idx;
    		item.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					if (mTabBarChangedLister != null) {
						mTabBarChangedLister.onBottomTabBarChooseListener(index);
					}
				}
    		});
    	}
    }
    
    public void setIcons(int...iconIds) {
    	
    	if (mIconResourceIdsArray != null) mIconResourceIdsArray = null;
    	
    	mIconResourceIdsArray = iconIds;
    	
    	if (mView != null) {
    		changeIcons();
    	}
    }
    
    public void setOnTabBarChangedListener(BottomTabBarListener listener) {
    	mTabBarChangedLister = listener;
    }
    
    private void changeIcons() {
    	int imageViewIds[] = {R.id.imageview_iconview_1, R.id.imageview_iconview_2, R.id.imageview_iconview_3, R.id.imageview_iconview_4};
    	
    	for (int idx = 0; idx < 4; idx++) {
    		ImageView imageView = (ImageView) mView.findViewById(imageViewIds[idx]);
    		imageView.setImageResource(0);
    	}
    	
    	for (int idx = 0; idx < mIconResourceIdsArray.length; idx++)  {
    		ImageView imageView = (ImageView) mView.findViewById(imageViewIds[idx]);
    		imageView.setImageResource(mIconResourceIdsArray[idx]);
    	}
    }
}
