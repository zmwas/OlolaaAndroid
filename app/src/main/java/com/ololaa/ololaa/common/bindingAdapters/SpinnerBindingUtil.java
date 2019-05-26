package com.ololaa.ololaa.common.bindingAdapters;


import android.databinding.BindingAdapter;
import android.databinding.InverseBindingAdapter;
import android.databinding.InverseBindingListener;
import android.support.v7.widget.AppCompatSpinner;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;

public class SpinnerBindingUtil {
    @BindingAdapter(value = {"selectedValue", "selectedValuePosition", "selectedValueAttrChanged", "selectedValuePositionAttrChanged"}, requireAll = false)
    public static void bindSpinnerData(AppCompatSpinner appCompatSpinner, String newSelectedValue, Integer selectedValuePosition, final InverseBindingListener newTextAttrChanged, final InverseBindingListener positionChanged) {
        appCompatSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                positionChanged.onChange();
                newTextAttrChanged.onChange();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }

        });

        if (newSelectedValue != null) {
            int pos = ((ArrayAdapter<String>) appCompatSpinner.getAdapter()).getPosition(newSelectedValue);
            appCompatSpinner.setSelection(pos, true);
        }
    }

    @InverseBindingAdapter(attribute = "selectedValue", event = "selectedValueAttrChanged")
    public static String captureSelectedValue(AppCompatSpinner appCompatSpinner) {
        return appCompatSpinner.getSelectedItem().toString().toLowerCase();
    }

    @InverseBindingAdapter(attribute = "selectedValuePosition", event = "selectedValuePositionAttrChanged")
    public static Integer getSelectedValuePosition(AppCompatSpinner appCompatSpinner) {
        String value =  appCompatSpinner.getSelectedItem().toString();
        return ((ArrayAdapter<String>) appCompatSpinner.getAdapter()).getPosition(value);
    }


}
