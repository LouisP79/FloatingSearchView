package com.arlib.floatingsearchview.util;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import androidx.annotation.ColorRes;
import androidx.annotation.DrawableRes;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Display;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;

import java.util.Objects;

public class Util {

    public static void showSoftKeyboard(final Context context, final EditText editText) {

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                InputMethodManager inputMethodManager = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
                inputMethodManager.showSoftInput(editText, InputMethodManager.SHOW_FORCED);
            }
        }, 100);
    }

    public static void closeSoftKeyboard(Activity activity) {

        View currentFocusView = activity.getCurrentFocus();
        if (currentFocusView != null) {
            InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(currentFocusView.getWindowToken(), 0);
        }
    }

    public static int dpToPx(int dp) {
        DisplayMetrics metrics = Resources.getSystem().getDisplayMetrics();
        return (int) (dp * metrics.density);
    }

    public static int spToPx(int sp) {
        DisplayMetrics metrics = Resources.getSystem().getDisplayMetrics();
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp, metrics);
    }

    public static int getScreenHeight(Activity activity) {

        Display display = activity.getWindowManager().getDefaultDisplay();
        DisplayMetrics outMetrics = new DisplayMetrics();
        display.getMetrics(outMetrics);

        return outMetrics.heightPixels;
    }

    public static void setIconColor(ImageView iconHolder, int color) {
        Drawable wrappedDrawable = DrawableCompat.wrap(iconHolder.getDrawable());
        DrawableCompat.setTint(wrappedDrawable, color);
        iconHolder.setImageDrawable(wrappedDrawable);
        iconHolder.invalidate();
    }

    public static Drawable getWrappedDrawable(Context context, @DrawableRes int resId) throws Resources.NotFoundException {
        return DrawableCompat.wrap(Objects.requireNonNull(ResourcesCompat.getDrawable(context.getResources(),
                resId, null)));
    }

    public static int getColor(Context context, @ColorRes int resId) throws Resources.NotFoundException {
        return ContextCompat.getColor(context, resId);
    }

    public static void removeGlobalLayoutObserver(View view, ViewTreeObserver.OnGlobalLayoutListener layoutListener) {
        view.getViewTreeObserver().removeOnGlobalLayoutListener(layoutListener);
    }

    public static Activity getHostActivity(Context context) {
        while (context instanceof ContextWrapper) {
            if (context instanceof Activity) {
                return (Activity) context;
            }
            context = ((ContextWrapper) context).getBaseContext();
        }
        return null;
    }
}
