package com.ololaa.ololaa.common;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.widget.DatePicker;

import java.util.Calendar;

public class DatePickerFragment extends DialogFragment implements
        DatePickerDialog.OnDateSetListener {

    public static final String MIN_DATE = "minDate";
    public static final String MAX_DATE = "maxDate";
    public static final String INITIAL_DATE = "initialDate";
    public static final String VIEW_ID = "viewId";
    public static final String DATE_FORMAT = "dateFormat";

    public DatePickerDialogListener getmListener() {
        return mListener;
    }

    public void setmListener(DatePickerDialogListener mListener) {
        this.mListener = mListener;
    }

    public interface DatePickerDialogListener {
        void onDatePicked(DialogFragment dialog, Calendar c,
                          int viewId, String dateFormat);
    }

    // Use this instance of the interface to deliver action events
    private DatePickerDialogListener mListener;

    int viewId;
    String dateFormat;
    private long minDate = 0;
    private long maxDate = 0;
    private long initialDate = 0;

    public static DatePickerFragment newInstance(int viewId, String dateFormat) {
        DatePickerFragment instance = new DatePickerFragment();

        instance = new DatePickerFragment();
        Bundle args = new Bundle();
        args.putInt(VIEW_ID, viewId);
        args.putString(DATE_FORMAT, dateFormat);
        instance.setArguments(args);
        return instance;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle b = getArguments();
        if(b != null) {
            minDate = b.getLong(MIN_DATE, 0);
            maxDate = b.getLong(MAX_DATE, 0);
            initialDate = b.getLong(INITIAL_DATE, 0);
        }

        try {
            viewId = getArguments().getInt("viewId");
            dateFormat = getArguments().getString("dateFormat");
        } catch(Exception e) {}
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        // Use the current date as the default date in the picker
        final Calendar c = Calendar.getInstance();
        if(initialDate > 0) {
            c.setTimeInMillis(initialDate);
        }
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        // Create a new instance of DatePickerDialog and return it
        DatePickerDialog dialog = new DatePickerDialog(getActivity(),android.R.style.Theme_Holo_Light_Dialog_MinWidth, this, year, month, day);
        if (minDate > 0) {
            dialog.getDatePicker().setMinDate(minDate);
        }
        if (maxDate > 0) {
            dialog.getDatePicker().setMaxDate(maxDate);
        }
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        return dialog;
    }

    public void onDateSet(DatePicker view, int year, int month, int day) {
        Calendar c = Calendar.getInstance();

        c.set(year, month, day);
        getmListener().onDatePicked(this, c, viewId, dateFormat);
    }

}