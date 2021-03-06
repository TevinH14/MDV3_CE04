package com.example.hamiltontevin_ce04.widget.stackWidget;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;

public class StackWidgetProvider extends AppWidgetProvider {
    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        StackWidgetHelper.updateWidget(context,appWidgetManager,appWidgetIds);
        super.onUpdate(context, appWidgetManager, appWidgetIds);
    }
}
