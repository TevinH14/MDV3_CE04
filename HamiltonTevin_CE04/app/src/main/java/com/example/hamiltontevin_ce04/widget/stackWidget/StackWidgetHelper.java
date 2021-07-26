package com.example.hamiltontevin_ce04.widget.stackWidget;

import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.RemoteViews;

import com.example.hamiltontevin_ce04.R;

public class StackWidgetHelper {
    private static void updateWidget(Context _context, AppWidgetManager _appWidgetManager
            , int _widgetId){
        RemoteViews widgetViews = new RemoteViews(_context.getPackageName(),
                R.layout.stack_widget_layout);

        Intent serviceIntent = new Intent(_context,StackWidgetService.class);
        serviceIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID,_widgetId);
        serviceIntent.setData(Uri.parse(serviceIntent.toUri(Intent.URI_INTENT_SCHEME)));
        widgetViews.setRemoteAdapter(R.id.stackWidgetView,serviceIntent);
        widgetViews.setEmptyView(R.id.stackWidgetView,R.id.stackWidgetEmptyView);

        _appWidgetManager.updateAppWidget(_widgetId,widgetViews);
    }
    public static void updateWidget(Context _context, AppWidgetManager _appWidgetManager,
                                    int[] _appWidgetIds) {
        for (int appWidgetId : _appWidgetIds) {
            updateWidget(_context, _appWidgetManager, appWidgetId);
        }
    }
}
