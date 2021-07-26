package com.example.hamiltontevin_ce04.widget.flipperWidget;

import android.content.Intent;
import android.widget.RemoteViewsService;

public class FlipperWidgetService extends RemoteViewsService {
    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new FlipperWidgetViewFactory(getApplicationContext());
    }
}
