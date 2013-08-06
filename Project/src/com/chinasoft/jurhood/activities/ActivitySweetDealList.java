package com.chinasoft.jurhood.activities;

import org.fodlife.library.LayoutAdjuster;
import org.fodlife.library.OnSwipeTouchListener;
import org.fodlife.library.OnSwipeTouchListener.SwipeListener;


import com.chinasoft.jurhood.AppDataManager;
import com.chinasoft.jurhood.R;
import com.chinasoft.jurhood.uicommons.FragmentBottomTabBar;
import com.chinasoft.jurhood.uicommons.FragmentBottomTabBar.BottomTabBarListener;
import com.chinasoft.jurhood.uicommons.FragmentTopNavigation;
import com.chinasoft.jurhood.uicommons.FragmentTopNavigation.TopNavigationChangedListener;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.Window;
import android.widget.FrameLayout;

public class ActivitySweetDealList extends FragmentActivity implements TopNavigationChangedListener, BottomTabBarListener, SwipeListener {
	
	private boolean 				mFocusChangeProcessed = false;
	private FragmentTopNavigation 	mTopNavigation;
	private FragmentBottomTabBar  	mTabBar;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_sweet_deal_main);

		setFonts();
		setListeners();
	}

	@Override
	public void onWindowFocusChanged(boolean hasFocus) {
		super.onWindowFocusChanged(hasFocus);
		if (hasFocus && !mFocusChangeProcessed) {
			mFocusChangeProcessed = true;
			
			FrameLayout container = (FrameLayout) findViewById(R.id.framelayout_container);
			
			LayoutAdjuster.sharedInstance().fitToScreen(this, container, 
											AppDataManager.sharedInstance().getXRate(), 
											AppDataManager.sharedInstance().getYRate(), 
											AppDataManager.sharedInstance().getWRate(), 
											AppDataManager.sharedInstance().getYRate());
			layoutInitialize();
		}
	}
	
	private void setFonts() {
		 
	}
	
	private void layoutInitialize() {
		topNavBarInitialize();
		bottomTabBarInitialize();
	}
	
	private void topNavBarInitialize() {
		FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
		
		FragmentTopNavigation topNavigation = new FragmentTopNavigation();
		
		topNavigation.configureWithTitles("SWEET DEALS", "MINE AKTIVE SWEET DEALS");
		topNavigation.setSelectionChangedListener(this);
		
		ft.replace(R.id.framelayout_topnavigation, topNavigation);
		ft.commit();
		
		mTopNavigation = topNavigation;
	}
	
	private void bottomTabBarInitialize() {
		FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
		
		FragmentBottomTabBar tabBar = new FragmentBottomTabBar();
		
		tabBar.setIcons(R.drawable.icon_tabbar_filter, R.drawable.icon_tabbar_vis, R.drawable.icon_tabbar_sort);
		tabBar.setOnTabBarChangedListener(this);
		
		ft.replace(R.id.framelayout_bottomtabbar, tabBar);
		ft.commit();
		
		mTabBar = tabBar;
	}
	
	private void setListeners() {
		FrameLayout layoutContent = (FrameLayout) findViewById(R.id.framelayout_content);
		
		OnSwipeTouchListener listener = new OnSwipeTouchListener(this);
		
		layoutContent.setOnTouchListener(listener);
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.chinasoft.jurhood.uicommons.FragmentTopNavigation.TopNavigationChangedListener#onTopNavigationSelectionChanged(int, java.lang.String)
	 * 
	 * Here, please add the process to change the tab bar icons
	 * 
	 */
	@Override
	public void onTopNavigationSelectionChanged(int newSelectionIdx, String newTitle) {
		// TODO Auto-generated method stub
		if (newSelectionIdx == 0) {
			mTabBar.setIcons(R.drawable.icon_tabbar_filter, R.drawable.icon_tabbar_vis, R.drawable.icon_tabbar_sort);
		} else if (newSelectionIdx == 1) {
			mTabBar.setIcons(R.drawable.icon_tabbar_vis, R.drawable.icon_tabbar_sort);
		}
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.chinasoft.jurhood.uicommons.FragmentBottomTabBar.BottomTabBarListener#onBottomTabBarChooseListener(int)
	 * Here, 
	 */
	@Override
	public void onBottomTabBarChooseListener(int itemIdx) {
		// TODO Auto-generated method stub
		if (itemIdx == 4) {
			finish();
		}
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.fodlife.library.OnSwipeTouchListener.SwipeListener#onSwipeToLeft()
	 */
	@Override
	public void onSwipeToLeft() {
		// TODO Auto-generated method stub
		mTopNavigation.selectNextGalleryItem();
	}

	@Override
	public void onSwipeToRight() {
		// TODO Auto-generated method stub
		mTopNavigation.selectPreviousGalleryItem();
	}
}
