package com.example.hamiltontevin_ce04.widget.stackWidget;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.example.hamiltontevin_ce04.image.ImageHelper;
import com.example.hamiltontevin_ce04.R;

public class StackWidgetViewFactory implements RemoteViewsService.RemoteViewsFactory {

    private final Context mContext;
    private String[] mPhoneImages;
    public StackWidgetViewFactory(Context _context) {
        mContext = _context;
    }

    @Override
    public void onCreate() {
        ImageHelper ih = new ImageHelper(mContext);
        mPhoneImages = ih.getAllShownImagesPath();
    }

    @Override
    public void onDataSetChanged() {
        ImageHelper ih = new ImageHelper(mContext);
        mPhoneImages = ih.getAllShownImagesPath();
    }

    @Override
    public void onDestroy() {
        mPhoneImages = null;

    }

    @Override
    public int getCount() {
        return mPhoneImages.length;
    }

    @Override
    public RemoteViews getViewAt(int position) {
        ImageHelper ih = new ImageHelper(mContext);
        Bitmap image = ih.filePathToBitmapWidget(mPhoneImages[position]);

        RemoteViews remoteViews = new RemoteViews(mContext.getPackageName(),
                R.layout.stack_widge_item_view);

        remoteViews.setImageViewBitmap(R.id.stack_widget_picture,image);
        return remoteViews;
    }
    
    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public long getItemId(int position) {
        return 1;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }
}
