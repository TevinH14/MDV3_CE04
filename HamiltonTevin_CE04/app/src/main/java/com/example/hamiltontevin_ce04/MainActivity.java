package com.example.hamiltontevin_ce04;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.example.hamiltontevin_ce04.image.ImageHelper;
import com.example.hamiltontevin_ce04.fragment.ImageGalleryFragment;

public class MainActivity extends AppCompatActivity {
    private static final String TAG_TEST = "TAG_TEST";
    private static final int GET_IMAGE_REQUEST = 0X0001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED
                && ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_MEDIA_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            setImage();
        }
        else {
            ActivityCompat.requestPermissions(MainActivity.this,
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,
                            Manifest.permission.ACCESS_MEDIA_LOCATION},GET_IMAGE_REQUEST);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main_activity_refreash,menu);
        return true;
    }

    //TODO: UPDATE WIDGET.
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.action_reload){
            Log.i(TAG_TEST,"START OF RELOADING WIDGET");
        }
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == GET_IMAGE_REQUEST){
            setImage();
        }
    }

    private void setImage(){
        //create new instance of Image Helper class
        ImageHelper ih = new ImageHelper(this);
        String[] imagesPath;
        imagesPath = ih.getAllShownImagesPath();

        //assign fragment to activity.
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.fl_imageContainer ,
                        ImageGalleryFragment
                                .newInstance(imagesPath))
                .commitNow();
    }
}
