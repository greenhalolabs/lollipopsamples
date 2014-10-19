package com.greenhalolabs.lollipopsamples;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by dannyroa on 10/19/14.
 */
public class NavigationDrawerFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(
            R.layout.fragment_navigation_drawer, container, false);
    }
}
