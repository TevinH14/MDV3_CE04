package com.example.hamiltontevin_ce04.widget.flipperWidget;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.example.hamiltontevin_ce04.image.ImageHelper;
import com.example.hamiltontevin_ce04.R;

public class FlipperWidgetViewFactory implements RemoteViewsService.RemoteViewsFactory {

    private final Context mContext;
    private String[] mPhoneImages;

    public FlipperWidgetViewFactory(Context mContext) {
        this.mContext = mContext;
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
        String imagePath = mPhoneImages[position];
        ImageHelper ih = new ImageHelper(mContext);
        Bitmap image = ih.filePathToBitmapWidget(mPhoneImages[position]);

        RemoteViews remoteViews = new RemoteViews(mContext.getPackageName(),
                R.layout.flipper_widget_item_view);

        remoteViews.setImageViewBitmap(R.id.iv_phone_photo,image);

        // Create a blank intent.
        Intent fillIntent = new Intent();
        // Add the data to the intent.
        fillIntent.putExtra(FlipperWidgetProvider.ACTION_IMAGE_VIEW,imagePath);
        // Attach the fill-in intent to the root of the layout.
        remoteViews.setOnClickFillInIntent(R.id.iv_phone_photo,fillIntent);


        return remoteViews;
    }

    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return mPhoneImages.length;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }
}
