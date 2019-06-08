package com.ololaa.ololaa.common.bindingAdapters;

import android.app.DatePickerDialog;
import android.content.Context;
import android.databinding.BindingAdapter;
import android.databinding.InverseBindingAdapter;
import android.databinding.ObservableField;
import android.support.v7.widget.AppCompatEditText;

import com.ololaa.ololaa.common.Utils;

import java.util.Calendar;
import java.util.Date;

public class DatePickerBindingAdapter{


//    @BindingAdapter(value = {"onC"})
//    public static void bindEdittextClicks(final AppCompatEditText editText, final ObservableField<Date> date) {
//        editText.setOnClickListener(view -> selectDate(editText.getContext(), date));
//    }


    @BindingAdapter(value = {"bind:date"})
    public static void bindEditText(final AppCompatEditText editText, final Date date) {
        if (date != null) {
            editText.setText("" + Utils.formatDate(date, "yyyy-MM-dd"));
        }
    }

    @InverseBindingAdapter(attribute = "bind:date", event = "android:textAttrChanged")
    public static Date date(final AppCompatEditText editText) {
        return Utils.getDateFromString(editText.getText().toString(), "yyyy-MM-dd");
    }

    public static void selectDate(Context context,  Date date) {
        final Calendar calBefore = Calendar.getInstance();
        ObservableField<Date> d = new ObservableField<>();
//        if (date != null && date.get() != null) {
//            calBefore.setTime(date.get());
//        }

        DatePickerDialog dialog = new DatePickerDialog(context, (dpView, year, monthOfYear, dayOfMonth) -> {
            Calendar cal = Calendar.getInstance();
            cal.set(Calendar.YEAR, year);
            cal.set(Calendar.MONTH, monthOfYear);
            cal.set(Calendar.DAY_OF_MONTH, dayOfMonth);
//            date.set(cal.getTime());
//            date.notifyChange();
            d.set( cal.getTime());
            d.notifyChange();
        }, calBefore.get(Calendar.YEAR), calBefore.get(Calendar.MONTH), calBefore.get(Calendar.DAY_OF_MONTH));
        date = d.get();
        dialog.show();
    }
}
