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


public class PrefsFragmentSettings extends PreferenceFragment
        implements SharedPreferences.OnSharedPreferenceChangeListener {

    Context context;

    public PrefsFragmentSettings () {}


    @Override
    public void onCreate (Bundle b) {
        super.onCreate(b);

        // Load preferences from an XML resource
        addPreferencesFromResource(R.xml.prefs_fragment_settings);
    }
    @Override
    public void onResume () {
        super.onResume();

        // Set a click listener whenever key changes
        getPreferenceScreen().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);

        // Set up a click listener

        Preference pref1 = getPreferenceScreen().findPreference("key_New_Activity");
        pref1.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            public boolean onPreferenceClick (Preference preference) {

                try {

                    Intent intent =new Intent(getActivity(),PrefsActivity2.class);
                    startActivity(intent);

                    /*AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                    builder.setIcon(android.R.drawable.ic_dialog_info);
                    builder.setTitle("Close Application");
                    builder.setMessage("Are you sure you want to exit the PreferenceScreen?");
                    builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface arg0, int arg1)
                        {
                            //Assets.gametimer = System.nanoTime()/1000000000f;
                            //Assets.state=Assets.EggState.eggstage;
                            //Assets.i=1;

                            Assets.runningIntent = true;
                            Assets.gameTimer =System.nanoTime()/1000000000f;
                            android.os.Process.killProcess(android.os.Process.myPid());
                            Intent intent=new Intent(getActivity(),MainThread.class);
                            startActivity(intent);


                        }
                    });
                    builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface arg0, int arg1) {
                            onResume();
                        }
                    });

                    AlertDialog dialog = builder.create();
                    dialog.show();*/

                }
                catch (Exception e) {
                    Log.e ("ProjectLogging", "Browser failed", e);
                }
                return true;
            }
        });



        Preference pref = getPreferenceScreen().findPreference("key_company_info");
        pref.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            public boolean onPreferenceClick (Preference preference) {

                try {

                    Intent intent =new Intent(getActivity(),PrefsActivity3.class);
                    startActivity(intent);

                }
                catch (Exception e) {
                    Log.e ("ProjectLogging", "Browser failed", e);
                }
                return true;
            }
        });


    }
    public void onSharedPreferenceChanged (SharedPreferences sharedPreferences, String key) {


    }
}
