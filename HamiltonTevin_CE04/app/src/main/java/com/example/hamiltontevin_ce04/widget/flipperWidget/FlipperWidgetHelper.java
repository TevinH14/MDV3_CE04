package com.example.hamiltontevin_ce04.widget.flipperWidget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.util.Log;
import android.widget.RemoteViews;

import androidx.preference.PreferenceManager;

import com.example.hamiltontevin_ce04.R;

public class FlipperWidgetHelper {
    public static void updateWidget(Context _context, AppWidgetManager _appWidgetManager
            , int _widgetId){
        RemoteViews rv = getRemoteView(_context);
        Log.i("test","update widget id: "+_widgetId);
        Intent sI = new Intent(_context,FlipperWidgetService.class);
        sI.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID,_widgetId);
        sI.setData(Uri.parse(sI.toUri(Intent.URI_INTENT_SCHEME)));
        rv.setRemoteAdapter(R.id.avf_image,sI);

        Intent nextIntent = new Intent(_context,FlipperWidgetProvider.class);
        nextIntent.setAction(FlipperWidgetProvider.ACTION_NEXT);
        nextIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID,_widgetId);
        PendingIntent nextPendingIntent =  PendingIntent.getBroadcast(
                _context,
                _widgetId,
                nextIntent,
                PendingIntent.FLAG_UPDATE_CURRENT);

        Intent backIntent = new Intent(_context,FlipperWidgetProvider.class);
        backIntent.setAction(FlipperWidgetProvider.ACTION_BACK);
        backIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID,_widgetId);
        PendingIntent backPendingIntent =  PendingIntent.getBroadcast(
                _context,
                _widgetId,
                backIntent,
                PendingIntent.FLAG_UPDATE_CURRENT);

        Intent imageViewIntent = new Intent(_context,FlipperWidgetProvider.class);
        PendingIntent imageViewPendingIntent = PendingIntent.getBroadcast(
                _context,
                _widgetId,
                imageViewIntent,
                PendingIntent.FLAG_UPDATE_CURRENT);
        rv.setPendingIntentTemplate(R.id.avf_image,imageViewPendingIntent);


        rv.setOnClickPendingIntent(R.id.btn_next,nextPendingIntent);
        rv.setOnClickPendingIntent(R.id.btn_back,backPendingIntent);

        // Intent ViewImageIntent = new Intent(_context)
        _appWidgetManager.updateAppWidget(_widgetId,rv);
    }
    public static void updateWidget(Context _context, AppWidgetManager _appWidgetManager,
                                    int[] _appWidgetIds) {
        for (int appWidgetId : _appWidgetIds) {
            updateWidget(_context, _appWidgetManager, appWidgetId);
        }
    }

    public static RemoteViews getRemoteView(Context context){
        boolean flippingStatus;
        RemoteViews widgetViews;
        SharedPreferences flippingChoice = PreferenceManager.getDefaultSharedPreferences(context);
        flippingStatus = flippingChoice
                .getBoolean(context.getString(R.string.pref_switch),
                        true);

        Log.i("test","status flipping: "+flippingStatus);

        if(flippingStatus) {
            widgetViews = new RemoteViews(context.getPackageName(),
                    R.layout.auto_flipper_widget_layout);
            Log.i("test","auto flipper");
        }else {
            widgetViews = new RemoteViews(context.getPackageName(),
                    R.layout.non_flipper_widget_layout);
            Log.i("test","non auto flipper");

        }
        return widgetViews;
    }
}
