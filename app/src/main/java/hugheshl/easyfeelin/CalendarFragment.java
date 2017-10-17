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
import android.widget.ImageView;
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
    private TextView mMood1Count;
    private TextView mMood2Count;
    private TextView mMood3Count;
    private TextView mMood4Count;
    private TextView mMood5Count;

    private int m1Count = 0;
    private int m2Count = 0;
    private int m3Count = 0;
    private int m4Count = 0;
    private int m5Count = 0;

    private ImageView face1ImageView;
    private ImageView face2ImageView;
    private ImageView face3ImageView;
    private ImageView face4ImageView;
    private ImageView face5ImageView;

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

        for(Assign a : assigns) {

            if(a.getMood() == 1 && month == a.getDate().getMonth()) {
                m1Count++;
                //color matching day to green

            }
            else if(a.getMood() == 2 && month == a.getDate().getMonth()) {
                m2Count++;
                //color change day to purple

            }
            else if(a.getMood() == 3 && month == a.getDate().getMonth()) {
                m3Count++;
                //color change day to blue

            }
            else if(a.getMood() == 4 && month == a.getDate().getMonth()) {
                m4Count++;
                //color change day or orange

            }
            else if(a.getMood() == 5 && month == a.getDate().getMonth()) {
                m5Count++;
                //color change day to red

            }
        }

        face1ImageView = (ImageView) v.findViewById(R.id.imageViewFace1);
        face2ImageView = (ImageView) v.findViewById(R.id.imageViewFace2);
        face3ImageView = (ImageView) v.findViewById(R.id.imageViewFace3);
        face4ImageView = (ImageView) v.findViewById(R.id.imageViewFace4);
        face5ImageView = (ImageView) v.findViewById(R.id.imageViewFace5);

        mMood1Count = (TextView) view.findViewById(R.id.mood1Count);
        mMood2Count = (TextView) view.findViewById(R.id.mood2Count);
        mMood3Count = (TextView) view.findViewById(R.id.mood3Count);
        mMood4Count = (TextView) view.findViewById(R.id.mood4Count);
        mMood5Count = (TextView) view.findViewById(R.id.mood5Count);

        mMood1Count.setText("Great: " + m1Count);
        mMood2Count.setText("Good: " + m2Count);
        mMood3Count.setText("Ok: " + m3Count);
        mMood4Count.setText("Bad: " + m4Count);
        mMood5Count.setText("Horrible: " + m5Count);

        return view;
    }
}
