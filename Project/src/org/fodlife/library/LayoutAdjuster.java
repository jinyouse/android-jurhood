package org.fodlife.library;

import android.content.Context;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

public class LayoutAdjuster {
	
	private static LayoutAdjuster instance;
	
	public static LayoutAdjuster sharedInstance() {
		if (instance == null) {
			instance = new LayoutAdjuster();
		}
		
		return instance;
	}
	
	public void fitToScreen(Context context, ViewGroup topLayout, float xRate, float yRate, float wRate, float hRate) {
		
		for (int i = 0; i < topLayout.getChildCount(); i++) {
			if (topLayout.getChildAt(i) instanceof ViewGroup) {
				if (((ViewGroup) topLayout.getChildAt(i)).getChildCount() > 0)
					fitToScreen(context, (ViewGroup) topLayout.getChildAt(i), xRate, yRate, wRate, hRate);
			}
						
			View layout = topLayout.getChildAt(i);
			
			if (!(layout.getTag() instanceof String)) continue;
			
			String tag = (String) layout.getTag();
			
			processItem(layout, tag, xRate, yRate, wRate, hRate);
		}
	}
	
	private void processItem(View view, String tag, float xRate, float yRate, float wRate, float hRate) {
		
		ViewParent parentLayout = view.getParent();
		
		if (parentLayout instanceof LinearLayout) {
			
			LinearLayout.LayoutParams orgParam = (LinearLayout.LayoutParams) view.getLayoutParams();
			LinearLayout.LayoutParams param;
			
			int widthParam = view.getWidth();
			
			if (tag.contains("fw")) {
				widthParam = LinearLayout.LayoutParams.MATCH_PARENT;
			} else if (tag.contains("ww")) {
				widthParam = LinearLayout.LayoutParams.WRAP_CONTENT;
			} else if (tag.contains("sw")) {
				widthParam = (int) (view.getWidth() * wRate);
			}
			
			int heightParam = view.getHeight();
			
			if (tag.contains("fh")) {
				heightParam = LinearLayout.LayoutParams.MATCH_PARENT;
			} else if (tag.contains("wh")) {
				heightParam = LinearLayout.LayoutParams.WRAP_CONTENT;
			} else if (tag.contains("sh")) {
				heightParam = (int) (view.getHeight() * hRate);
			}
			
			param = new LinearLayout.LayoutParams(widthParam, heightParam, orgParam.weight);
			
			if (tag.contains("glt")) {
				param.gravity = Gravity.LEFT|Gravity.TOP;
			} else if (tag.contains("gcv")) {
				param.gravity = Gravity.CENTER_VERTICAL;
			} else if (tag.contains("gchb")) {
				param.gravity = Gravity.CENTER_HORIZONTAL | Gravity.BOTTOM;
			} else if (tag.contains("gch")) {
				param.gravity = Gravity.CENTER_HORIZONTAL;
			} else if (tag.contains("gct")) {
				param.gravity = Gravity.CENTER | Gravity.TOP;
			} else if (tag.contains("gc")) {
				param.gravity = Gravity.CENTER;
			} else if (tag.contains("gb")) {
				param.gravity = Gravity.BOTTOM;
			} else if (tag.contains("grt")) {
				param.gravity = Gravity.RIGHT | Gravity.BOTTOM;
			} else if (tag.contains("gr")) {
				param.gravity = Gravity.RIGHT;
			} else {
				param.gravity = orgParam.gravity;
			}
			
			int marginLeft = orgParam.leftMargin;
			int marginRight = orgParam.rightMargin;
			int marginTop = orgParam.topMargin;
			int marginBottom = orgParam.bottomMargin;
			
			if (tag.contains("sl")) {
				marginLeft = (int) (marginLeft * xRate);
			}
			
			if (tag.contains("sr")) {
				marginRight = (int) (marginRight * xRate);
			}
			
			if (tag.contains("st")) {
				marginTop = (int) (marginTop * yRate);
				
			}
			
			if (tag.contains("sb")) {
				marginBottom = (int) (marginBottom * yRate);
			}
			
			param.setMargins(marginLeft, marginTop, marginRight, marginBottom);
			
			view.setLayoutParams(param);
			
		} else if (parentLayout instanceof FrameLayout) {
			
			FrameLayout.LayoutParams orgParam = (FrameLayout.LayoutParams) view.getLayoutParams();
			FrameLayout.LayoutParams param;
			
			int widthParam = 0;
			
			if (tag.contains("fw")) {
				widthParam = LinearLayout.LayoutParams.MATCH_PARENT;
			} else if (tag.contains("ww")) {
				widthParam = LinearLayout.LayoutParams.WRAP_CONTENT;
			} else if (tag.contains("sw")) {
				widthParam = (int) (view.getWidth() * wRate);
			}
			
			int heightParam = 0;
			
			if (tag.contains("fh")) {
				heightParam = LinearLayout.LayoutParams.MATCH_PARENT;
			} else if (tag.contains("wh")) {
				heightParam = LinearLayout.LayoutParams.WRAP_CONTENT;
			} else if (tag.contains("sh")) {
				heightParam = (int) (view.getHeight() * hRate);
			}
			
			int gravity = orgParam.gravity;
			
			if (tag.contains("glt")) {
				gravity = Gravity.LEFT|Gravity.TOP;
			} else if (tag.contains("gcv")) {
				gravity = Gravity.CENTER_VERTICAL;
			} else if (tag.contains("gchb")) {
				gravity = Gravity.CENTER_HORIZONTAL | Gravity.BOTTOM;
			} else if (tag.contains("gch")) {
				gravity = Gravity.CENTER_HORIZONTAL;
			} else if (tag.contains("gct")) {
				gravity = Gravity.CENTER | Gravity.TOP;
			} else if (tag.contains("gc")) {
				gravity = Gravity.CENTER;
			} else if (tag.contains("gb")) {
				gravity = Gravity.BOTTOM;
			} else if (tag.contains("grt")) {
				gravity = Gravity.RIGHT|Gravity.TOP;
			} else if (tag.contains("gr")) {
				gravity = Gravity.RIGHT;
			} else {
				gravity = orgParam.gravity;
			}
			
			param = new FrameLayout.LayoutParams(widthParam, heightParam, gravity);
			
			int marginLeft = orgParam.leftMargin;
			int marginRight = orgParam.rightMargin;
			int marginTop = orgParam.topMargin;
			int marginBottom = orgParam.bottomMargin;
			
			if (tag.contains("sl")) {
				marginLeft = (int) (marginLeft * xRate);
			}
			
			if (tag.contains("sr")) {
				marginRight = (int) (marginRight * xRate);
			}
			
			if (tag.contains("st")) {
				marginTop = (int) (marginTop * yRate);
			}
			
			if (tag.contains("sb")) {
				marginBottom = (int) (marginBottom * yRate);
			}
			
			param.setMargins(marginLeft, marginTop, marginRight, marginBottom);
			
			view.setLayoutParams(param);
		}
		
		int paddingLeft = view.getPaddingLeft(), paddingRight = view.getPaddingRight();
		int paddingTop = view.getPaddingTop(), paddingBottom = view.getPaddingBottom();
		
		if (tag.contains("spl")) {
			paddingLeft = (int)(paddingLeft * xRate);
		}
		
		if (tag.contains("spr")) {
			paddingRight = (int)(paddingRight * xRate);
		}
		
		if (tag.contains("spt")) {
			paddingTop = (int)(paddingTop * xRate);
		}
		
		if (tag.contains("spb")) {
			paddingBottom = (int)(paddingBottom * xRate);
		}
		
		view.setPadding(paddingLeft, paddingTop, paddingRight, paddingBottom);
		
		if (tag.contains("sfont")) {
			TextView textView = (TextView) view;
			textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, textView.getTextSize() * hRate);
		}
	}
}
