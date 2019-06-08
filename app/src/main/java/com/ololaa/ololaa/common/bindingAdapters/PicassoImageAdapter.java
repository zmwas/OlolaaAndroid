package com.ololaa.ololaa.common.bindingAdapters;

import android.databinding.BindingAdapter;
import android.net.Uri;
import android.util.Log;
import android.widget.ImageView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

public class PicassoImageAdapter {
    @BindingAdapter("imageUrl")
    public static void loadImage(ImageView view, String url) {
        Picasso.get().load(url)
                .into(view, new Callback() {
                    @Override
                    public void onSuccess() {

                    }

                    @Override
                    public void onError(Exception e) {
                        Log.d(e.getLocalizedMessage(), e.getMessage());
                    }
                });
    }

    @BindingAdapter("imageUri")
    public static void loadImageFromUri(ImageView view, Uri uri) {
        Picasso.get().load(uri.getPath())
                .into(view);
    }

}
