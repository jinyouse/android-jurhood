package com.chinasoft.jurhood;

public class AppDataManager {
	
	private static AppDataManager instance;
	
	public static AppDataManager sharedInstance() {
		if (instance == null) {
			instance = new AppDataManager();
		}
		
		return instance;
	}
	
	
	private int 	mScreenWidth, mScreenHeight;
	private float	mXRate, mYRate, mWRate, mHRate;
	
	public int getScreenWidth() {
		return mScreenWidth;
	}
	
	public int getScreenHeight() {
		return mScreenHeight;
	}
	
	public void setScreenSize(int screenWidth, int screenHeight) {
		mScreenWidth = screenWidth;
		mScreenHeight = screenHeight;
	}
	
	public void saveScaleRates(float xRate, float yRate, float wRate, float hRate) {
		mXRate = xRate;
		mYRate = yRate;
		mWRate = wRate;
		mHRate = hRate;
	}
	
	public float getXRate() {return mXRate;}
	public float getYRate() {return mYRate;}
	public float getWRate() {return mWRate;}
	public float getHRate() {return mHRate;}
}
