package com.example.hamiltontevin_ce04.widget.flipperWidget;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

import com.example.hamiltontevin_ce04.R;

public class FlipperWidgetProvider extends AppWidgetProvider {
    public static final String ACTION_NEXT = "ACTION_NEXT";
    public static final String ACTION_BACK = "ACTION_BACK";
    public static final String ACTION_IMAGE_VIEW = "ACTION_IMAGE_VIEW";
    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        FlipperWidgetHelper.updateWidget(context,appWidgetManager,appWidgetIds);
        super.onUpdate(context, appWidgetManager, appWidgetIds);
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if(action != null){
            RemoteViews rv = FlipperWidgetHelper.getRemoteView(context);
            switch (action) {
                case ACTION_NEXT:
                    rv.showNext(R.id.avf_image);
                    break;
                case ACTION_BACK:
                    rv.showPrevious(R.id.avf_image);
                    break;
                case ACTION_IMAGE_VIEW:
                    intent.getStringExtra(ACTION_IMAGE_VIEW);
                    break;
            }

            AppWidgetManager
                    .getInstance(context)
                    .updateAppWidget(intent.getIntExtra
                            (AppWidgetManager.EXTRA_APPWIDGET_ID,
                                    AppWidgetManager.INVALID_APPWIDGET_ID),rv);
        }
        super.onReceive(context, intent);
    }
}
