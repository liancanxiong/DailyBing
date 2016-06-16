package com.brilliantbear.dailybing;

import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;

import java.util.Calendar;

/**
 * Created by Bear on 2016-6-16.
 */
public class SettingsFragment extends PreferenceFragment {

    private static final int INTERVAL = 1000 * 60 * 60 * 24;// 24h
    //    private static final int INTERVAL = 1000 * 60;// 1minute
    private static final int REQUEST_CODE = 1;
    private Context context;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preference_settings);
        context = getActivity();


        findPreference("key_auto").setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object newValue) {
                Intent intent = new Intent(context, GetDataService.class);
                PendingIntent sender = PendingIntent.getService(context,
                        REQUEST_CODE, intent, PendingIntent.FLAG_CANCEL_CURRENT);

                AlarmManager am = (AlarmManager) context
                        .getSystemService(Context.ALARM_SERVICE);

                Calendar calendar = Calendar.getInstance();
                calendar.set(Calendar.HOUR_OF_DAY, 10);
                calendar.set(Calendar.MINUTE, 0);
                calendar.set(Calendar.SECOND, 0);
                calendar.set(Calendar.MILLISECOND, 0);

                if (newValue instanceof Boolean) {
                    boolean value = (boolean) newValue;
                    if (value) {
                        context.startService(intent);
                        am.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                                INTERVAL, sender);
                    } else {
                        am.cancel(sender);
                    }
                }

                return true;
            }
        });
//        findPreference("key_about").setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
//            @Override
//            public boolean onPreferenceClick(Preference preference) {
//
//                return false;
//            }
//        });
    }


}
