package com.ololaa.ololaa.booking;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ololaa.ololaa.R;
import com.ololaa.ololaa.databinding.FragmentImageSliderBinding;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ImageSliderFragment extends Fragment {
    FragmentImageSliderBinding binding;
    List<String> urls;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_image_slider, container, false);
        setDetails();
        return binding.getRoot();
    }

    public void setDetails() {
        if (!getArguments().isEmpty()) {
            String url = getArguments().getString("url");
            Picasso.get().load(url).into(binding.image, new Callback() {
                @Override
                public void onSuccess() {

                }

                @Override
                public void onError(Exception e) {
                    Log.d(e.getLocalizedMessage(), e.getMessage());
                }
            });
        }
    }
}
