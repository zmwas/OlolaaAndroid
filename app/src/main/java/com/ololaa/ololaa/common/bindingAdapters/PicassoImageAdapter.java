package com.ololaa.ololaa.common.bindingAdapters;

import android.databinding.BindingAdapter;
import android.net.Uri;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class PicassoImageAdapter {
    @BindingAdapter("imageUrl")
    public static void loadImage(ImageView view, String url) {
        Picasso.get().load(url)
                .into(view);
    }

    @BindingAdapter("imageUri")
    public static void loadImageFromUri(ImageView view, Uri uri) {
        Picasso.get().load(uri.getPath())
                .into(view);
    }

}
