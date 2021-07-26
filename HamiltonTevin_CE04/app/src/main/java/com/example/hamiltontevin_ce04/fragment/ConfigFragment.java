package com.example.hamiltontevin_ce04.fragment;

import android.os.Bundle;

import androidx.preference.PreferenceFragmentCompat;

import com.example.hamiltontevin_ce04.R;

public class ConfigFragment extends PreferenceFragmentCompat {
    public static final String TAG = "ConfigFragment.TAG";

    public static ConfigFragment newInstance() {
        return new ConfigFragment();
    }


    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.flipper_pref_config,rootKey);
    }


}
