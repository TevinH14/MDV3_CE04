package com.example.hamiltontevin_ce04.adapter;

import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.example.hamiltontevin_ce04.image.ImageHelper;
import com.example.hamiltontevin_ce04.R;


public class ImageAdapter extends BaseAdapter {

    private final String[] mImagesArray;
    private final Context mContext;

    public ImageAdapter(Context _context , String[] _imagesArray) {
        this.mImagesArray = _imagesArray;
        mContext = _context;
    }

    @Override
    public int getCount() {
        if (mImagesArray != null){
            return mImagesArray.length;
        }
        return 0;
    }

    @Override
    public Object getItem(int position) {
        if(mImagesArray != null && mImagesArray.length > position){
            return mImagesArray[position];
        }
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageHelper ih = new ImageHelper(mContext);

        ViewHolder vh;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext)
                    .inflate(R.layout.gridview_image_layout, parent, false);
            vh = new ViewHolder(convertView);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }
        if (mImagesArray != null) {
            vh.imageHolder.setImageBitmap(ih.filePathToBitmap(mImagesArray[position]));
            return convertView;
        }
        return null;
    }

    static class ViewHolder{
        final ImageView imageHolder;

        ViewHolder(View layout) {
            this.imageHolder = layout.findViewById(R.id.iv_imageViewHolder);
        }
    }
}
