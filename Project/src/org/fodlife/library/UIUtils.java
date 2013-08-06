package org.fodlife.library;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Typeface;
import android.text.Html;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class UIUtils {
	
	public static float convertDpToPixel(float dp,Context context){
	    Resources resources = context.getResources();
	    DisplayMetrics metrics = resources.getDisplayMetrics();
	    float px = dp * (metrics.densityDpi/160f);
	    return px;
	}
	
	public static float convertPixelsToDp(float px,Context context){
	    Resources resources = context.getResources();
	    DisplayMetrics metrics = resources.getDisplayMetrics();
	    float dp = px / (metrics.densityDpi / 160f);
	    return dp;
	}
	
	public static String stipHtml(String html) {
		
		String test = Html.fromHtml(html).toString().replaceAll("\\<[^>]*>","").replaceAll("\t", "").replaceAll("\n", "").replaceAll("\r", "\n");
		
	    return test;
	}
	
	public static void showAlertWithOkButton(Context context, String msg, String title, DialogInterface.OnClickListener listener) {
		Builder builder = new AlertDialog.Builder(context);
   		builder.setMessage(msg);
   		builder.setTitle(title);
   		builder.setPositiveButton("OK", listener);
   		
   		Dialog dialog = builder.create();
   		dialog.show();
	}
	
	public static void setGrayScale(ImageView mTileButtons){
		ColorMatrix matrix = new ColorMatrix();
		matrix.setSaturation(0);
		ColorMatrixColorFilter cf = new ColorMatrixColorFilter(matrix);
		mTileButtons.setColorFilter(cf);
	}
	
	public static void hideKeyboardInActivity(Activity activity, ViewGroup topLayout) {
		InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
		for (int i = 0; i < topLayout.getChildCount(); i++) {
			if (topLayout.getChildAt(i) instanceof ViewGroup) {
				if (((ViewGroup) topLayout.getChildAt(i)).getChildCount() > 0)
					hideKeyboardInActivity(activity, (ViewGroup) topLayout.getChildAt(i));
			}
			
			View view = topLayout.getChildAt(i);
			if (view instanceof EditText) {
				imm.hideSoftInputFromWindow(((EditText)view).getWindowToken(), 0);
			}
		}
	}
	
	public static void setFont(TextView textView, String fontPath) {
		Typeface tf = Typeface.createFromAsset(textView.getContext().getAssets(), fontPath);
		textView.setTypeface(tf);
	}
	
	public static void setFont(Button button, String fontPath) {
		Typeface tf = Typeface.createFromAsset(button.getContext().getAssets(), fontPath);
		button.setTypeface(tf);
	}
}
