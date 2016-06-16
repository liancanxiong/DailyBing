package com.brilliantbear.dailybing;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.brilliantbear.dailybing.view.ListFragment;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private ListFragment listFragment;
    private SettingsFragment settingsFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (savedInstanceState == null) {
            settingsFragment = new SettingsFragment();
            listFragment = new ListFragment();
            getFragmentManager().beginTransaction().add(R.id.frame_main, listFragment).commit();
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_actionbar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_settings) {
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            if (!settingsFragment.isAdded()) {
                ft.hide(listFragment)
                        .add(R.id.frame_main, settingsFragment)
                        .commit();
            } else {
                ft.remove(settingsFragment).show(listFragment).commit();
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (settingsFragment.isAdded()) {
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.remove(settingsFragment).show(listFragment).commit();
        } else {
            super.onBackPressed();
        }
    }
}
