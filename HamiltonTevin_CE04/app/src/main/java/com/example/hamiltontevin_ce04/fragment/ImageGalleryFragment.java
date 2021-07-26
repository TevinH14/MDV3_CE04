package com.example.hamiltontevin_ce04.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.hamiltontevin_ce04.R;
import com.example.hamiltontevin_ce04.adapter.ImageAdapter;

public class ImageGalleryFragment extends Fragment {
    private static final String ARGS_IMAGE_ARRAY = "ARGS_IMAGES_ARRAY";
   // private static ArrayList<Bitmap> mImageBitmap;

    public static ImageGalleryFragment newInstance(String[] images) {

        Bundle args = new Bundle();
       // mImageBitmap = bitmaps;
        //pass images path into bundle.
        args.putStringArray(ARGS_IMAGE_ARRAY,images);
        ImageGalleryFragment fragment = new ImageGalleryFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_image_display,container,false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if(getView() != null && getContext() != null && getArguments() != null){
            GridView grImages = getView().findViewById(R.id.gv_imageGallery);
            String[] images = getArguments().getStringArray(ARGS_IMAGE_ARRAY);

            ImageAdapter ia = new ImageAdapter(getContext(),images);
            grImages.setAdapter(ia);
        }
    }
}
