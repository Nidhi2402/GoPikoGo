package com.example.nidhi.gopikago;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Process;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.util.Log;

public class PrefsFragmentSettings3 extends PreferenceFragment
        implements SharedPreferences.OnSharedPreferenceChangeListener {

    Context context;

    public PrefsFragmentSettings3 () {}


    @Override
    public void onCreate (Bundle b) {
        super.onCreate(b);

        // Load preferences from an XML resource
        addPreferencesFromResource(R.xml.prefs_fragment_settings3);
    }
    @Override
    public void onResume () {
        super.onResume();

        // Set a click listener whenever key changes
        getPreferenceScreen().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);

        // Set up a click listener


    }
    public void onSharedPreferenceChanged (SharedPreferences sharedPreferences, String key) {


    }
}

