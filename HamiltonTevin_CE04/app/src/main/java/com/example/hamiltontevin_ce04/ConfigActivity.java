package com.example.hamiltontevin_ce04;

import android.appwidget.AppWidgetManager;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


import com.example.hamiltontevin_ce04.fragment.ConfigFragment;
import com.example.hamiltontevin_ce04.widget.flipperWidget.FlipperWidgetHelper;

public class ConfigActivity extends AppCompatActivity {
    private int mWidgetId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.config_layout);

        //get the starting intent.
        Intent starter = getIntent();

        // Get the ID of the widget from extras.
        mWidgetId = starter.getIntExtra(AppWidgetManager.EXTRA_APPWIDGET_ID,
                AppWidgetManager.INVALID_APPWIDGET_ID);
        Log.i("test","widget id: "+mWidgetId);

        if(savedInstanceState == null){
            ConfigFragment fragment = ConfigFragment.newInstance();
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container,fragment,ConfigFragment.TAG)
                    .commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.config_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.action_save){
            try {
                Intent resultValue = new Intent();
                resultValue.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, mWidgetId);
                setResult(RESULT_OK, resultValue);
                AppWidgetManager mgr = AppWidgetManager.getInstance(this);
                FlipperWidgetHelper.updateWidget(this, mgr, mWidgetId);
                finish();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return true;
    }
}
