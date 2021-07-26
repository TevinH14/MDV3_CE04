package com.example.hamiltontevin_ce04.image;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.provider.MediaStore;

public class ImageHelper {
    private final Context mContext;
    @SuppressWarnings("deprecation")
    private final String IMAGE_DATA = MediaStore.Images.Media.DATA;
    private final String IMAGE_ID = MediaStore.Images.ImageColumns._ID;
    private final String[] mProjection = new String[] {IMAGE_ID, IMAGE_DATA};


    public ImageHelper(Context mContext) {
        this.mContext = mContext;
    }

    public String[] getAllShownImagesPath() {
        //Create an array to store path to all the images
        String[] imagePath;

        //Stores images from the gallery in Cursor
        Cursor cursor = mContext.getContentResolver().query(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                mProjection,
                null,
                null,
                IMAGE_ID);

        if(cursor != null) {
            //Total number of images
            int count = cursor.getCount();
            imagePath = new String[count];

            for (int i = 0; i < count; i++) {
                cursor.moveToPosition(i);
                int dataColumnIndex = cursor.getColumnIndex(IMAGE_DATA);

                //Store the path of the image
                imagePath[i] = cursor.getString(dataColumnIndex);
            }
            cursor.close();
            return imagePath;
        }
        return null;
    }

    public Bitmap filePathToBitmap(String filePath){

        Bitmap bitmap;
        int reqHeight = 120;
        int reqWidth = 120;

        BitmapFactory.Options options = new BitmapFactory.Options();

        BitmapFactory.decodeFile(String.valueOf(filePath), options);

        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);

        try {
            bitmap = BitmapFactory.decodeFile(filePath, options);
            return bitmap;
        }catch (Exception e ){
            e.printStackTrace();
        }
        return null;
    }

    public Bitmap filePathToBitmapWidget(String filePath){

        Bitmap bitmap;
        int reqHeight = 250;
        int reqWidth = 250;

        BitmapFactory.Options options = new BitmapFactory.Options();

        BitmapFactory.decodeFile(String.valueOf(filePath), options);

        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);

        try {
            bitmap = BitmapFactory.decodeFile(filePath, options);
            return bitmap;
        }catch (Exception e ){
            e.printStackTrace();
        }
        return null;
    }

   private int calculateInSampleSize(
           BitmapFactory.Options options, int reqWidth, int reqHeight) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {

            final int halfHeight = height / 2;
            final int halfWidth = width / 2;

            // Calculate the largest inSampleSize value that is a power of 2 and keeps both
            // height and width larger than the requested height and width.
            while ((halfHeight / inSampleSize) >= reqHeight
                    && (halfWidth / inSampleSize) >= reqWidth) {
                inSampleSize *= 2;
            }
        }

        return inSampleSize;
    }
}
