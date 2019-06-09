package com.ololaa.ololaa.booking;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {
    private List<String> urls;
    private int currentPosition;

    public ViewPagerAdapter(FragmentManager fm, List<String> urls) {
        super(fm);
        this.urls = urls;
    }

    @Override
    public Fragment getItem(int i) {
        ImageSliderFragment fragment = new ImageSliderFragment();
        String url = urls.get(i);
        currentPosition = i;
        Bundle bundle = new Bundle();
        bundle.putString("url", url);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public int getCount() {
        return urls.size();
    }

    @Override
    public int getItemPosition(Object object) {
        return ViewPagerAdapter.POSITION_NONE;
    }

    /**
     * Get's the current fragment's position.
     * @return currentPosition - int CurrentPosition
     */
    public int getCurrentPosition() {
        return currentPosition;
    }

}
