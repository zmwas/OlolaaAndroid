package com.ololaa.ololaa.common.bindingAdapters;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Context;
import android.databinding.BindingAdapter;
import android.databinding.ObservableField;
import android.widget.EditText;
import android.widget.TextView;

import com.ololaa.ololaa.common.Utils;

import java.util.Calendar;
import java.util.Date;

public class DatePickerBindingUtil{


    @BindingAdapter(value = {"bind:date", "bind:minDate", "bind:maxDate"}, requireAll = false)
    public static void bindTextViewDateClicks(final TextView textView, final ObservableField<Date> date, final ObservableField<Date> minDate, final ObservableField<Date> maxDate) {
        textView.setOnClickListener(view -> selectDate(textView.getContext(), date, minDate, maxDate));
    }

    @SuppressLint("SetTextI18n")
    @BindingAdapter(value = {"bind:date", "bind:defaultDateText"}, requireAll = false)
    public static void bindTextViewDate(final TextView textView, final Date date, String defaultDateText) {
        if (date != null) {
            textView.setText("" + Utils.formatDate(date, "yyyy-MM-dd"));
        } else if (defaultDateText != null) {
            textView.setText(defaultDateText);
        }
    }

    @SuppressLint("SetTextI18n")
    @BindingAdapter(value = {"bind:date"})
    public static void bindEditTextBirthday(final EditText editText, final Date date) {
        if (date != null) {
            editText.setText("" + Utils.formatDate(date, "yyyy-MM-dd"));
        }
    }


    public static void selectDate(Context context, final ObservableField<Date> date, final ObservableField<Date> minDate, ObservableField<Date> maxDate) {
        final Calendar calBefore = Calendar.getInstance();

        if (date != null && date.get() != null) {
            calBefore.setTime(date.get());
        }

        DatePickerDialog dialog = new DatePickerDialog(context, (dpView, year, monthOfYear, dayOfMonth) -> {
            Calendar cal = Calendar.getInstance();
            cal.set(Calendar.YEAR, year);
            cal.set(Calendar.MONTH, monthOfYear);
            cal.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            date.set(cal.getTime());
            date.notifyChange();
        }, calBefore.get(Calendar.YEAR), calBefore.get(Calendar.MONTH), calBefore.get(Calendar.DAY_OF_MONTH));

        if (minDate != null && minDate.get() != null) {
            dialog.getDatePicker().setMinDate(minDate.get().getTime());
        }

        if (maxDate != null && maxDate.get() != null) {
            dialog.getDatePicker().setMaxDate(maxDate.get().getTime());
        }

        dialog.show();
    }
}
