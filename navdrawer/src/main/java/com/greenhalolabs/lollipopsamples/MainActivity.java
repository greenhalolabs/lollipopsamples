package com.greenhalolabs.lollipopsamples;

import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;

public class MainActivity extends ActionBarActivity {

    private DrawerLayout mDrawerLayout;
    private NavigationDrawerFragment mNavigationDrawerFragment;
    Toolbar mActionBarToolbar;
    DrawerArrowDrawable drawerArrowDrawable;
    private float offset;
    private boolean flipped;
    String appName;
    String toolbarTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Resources resources = getResources();

        appName = resources.getString(R.string.app_name);

        toolbarTitle = resources.getString(R.string.toolbar_title);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        mNavigationDrawerFragment = (NavigationDrawerFragment)
            getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);

        //initialize Toolbar
        getActionBarToolbar();

        //Toolbar title seems to get reset to the App Name if set on this (onCreate) method. Have to use Handler for setTitle to work.
        Handler mHandler = new Handler();
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                getActionBarToolbar().setTitle(toolbarTitle);
            }
        });

        mActionBarToolbar.setTitleTextAppearance(getApplicationContext(), R.style.ToolbarTitle);

        setupNavDrawer();
    }

    protected Toolbar getActionBarToolbar() {
        if (mActionBarToolbar == null) {
            mActionBarToolbar = (Toolbar) findViewById(R.id.toolbar_actionbar);
            if (mActionBarToolbar != null) {
                setSupportActionBar(mActionBarToolbar);
            }
        }

        return mActionBarToolbar;
    }

    private void setupNavDrawer() {

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (mDrawerLayout == null) {
            return;
        }

        mDrawerLayout.setStatusBarBackgroundColor(getResources().getColor(R.color
                                                                              .theme_primary_dark));

        if (mActionBarToolbar != null) {

            final Resources resources = getResources();

            drawerArrowDrawable = new DrawerArrowDrawable(resources);
            drawerArrowDrawable.setStrokeColor(resources.getColor(R.color.light_gray));

            mActionBarToolbar.setNavigationIcon(drawerArrowDrawable);
            mActionBarToolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if (mDrawerLayout.isDrawerOpen(Gravity.START)) {
                        mDrawerLayout.closeDrawer(Gravity.START);
                    }
                    else {
                        mDrawerLayout.openDrawer(Gravity.START);
                    }
                }
            });
        }

        mDrawerLayout.setDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerClosed(View drawerView) {
                getActionBarToolbar().setTitle(toolbarTitle);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                getActionBarToolbar().setTitle(appName);
            }

            @Override
            public void onDrawerStateChanged(int newState) {
            }

            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {

                offset = slideOffset;

                // Sometimes slideOffset ends up so close to but not quite 1 or 0.
                if (slideOffset >= .995) {
                    flipped = true;
                    drawerArrowDrawable.setFlip(flipped);
                } else if (slideOffset <= .005) {
                    flipped = false;
                    drawerArrowDrawable.setFlip(flipped);
                }

                drawerArrowDrawable.setParameter(offset);
            }
        });



    }

}
