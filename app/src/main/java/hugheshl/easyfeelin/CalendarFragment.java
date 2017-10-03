package hugheshl.easyfeelin;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.List;
import java.util.UUID;

public class CalendarFragment extends DialogFragment {

    private TextView mTextView;

    public static CalendarFragment newInstance() {
        CalendarFragment calendarfragment = new CalendarFragment();
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
        mTextView.setText(mytext);

        if (savedInstanceState != null) {
        }

        return view;
    }
}
