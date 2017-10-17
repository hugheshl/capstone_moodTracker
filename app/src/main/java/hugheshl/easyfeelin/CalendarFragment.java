package hugheshl.easyfeelin;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.UUID;

public class CalendarFragment extends DialogFragment {

    public static final String EXTRA_DATE = "hugheshl.turniptimer2.date";
    private static final String ARG_DATE = "date";
    private DatePicker mDatePicker;

    private TextView mTextView;

    public static CalendarFragment newInstance(Date date) {
        CalendarFragment calendarfragment = new CalendarFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_DATE, date);
        calendarfragment.setArguments(args);
        return calendarfragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_calendar, container, false);

        AssignLab assignLab = AssignLab.get(getActivity());
        List<Assign> assigns = assignLab.getAssigns();

        mTextView = (TextView) view.findViewById(R.id.text);

        String mytext = "" + assigns.size();
        mTextView.setText("Total number of entries: " + mytext);


        if (savedInstanceState != null) {
        }

        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        View v = LayoutInflater.from(getActivity())
                .inflate(R.layout.dialog_date, null);

        mDatePicker = (DatePicker) v.findViewById(R.id.dialog_date_picker);
        mDatePicker.init(year, month, day, null);

        for(int i = 0; i < assigns.size(); i++)
        {
            //get total for each mood per month,
            //color-code days
            for(Assign a : assigns) {
                if(a.getMood() == 1) {
                    //color matching day to green
                    
                }
            }

        }

        return view;
    }
}
