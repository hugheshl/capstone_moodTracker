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

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.UUID;

public class ChartFragment extends DialogFragment {

    public static final String EXTRA_DATE = "hugheshl.turniptimer2.date";
    private static final String ARG_DATE = "date";

    private TextView mTextView;

    public static ChartFragment newInstance() {
        ChartFragment chartfragment = new ChartFragment();
        return chartfragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chart, container, false);

        AssignLab assignLab = AssignLab.get(getActivity());
        List<Assign> assigns = assignLab.getAssigns();

        mTextView = (TextView) view.findViewById(R.id.text);
        String mytext = "" + assigns.size();
        mTextView.setText("Total number of entries: " + mytext);

        GraphView graph = (GraphView) view.findViewById(R.id.graph);
        DataPoint[] data = new DataPoint[assigns.size()];

        int count = 0;

        for(Assign a : assigns) {
            data[count] = new DataPoint(count, a.getMood());
            count++;
        }
        LineGraphSeries<DataPoint> series = new LineGraphSeries<>(data);
        graph.addSeries(series);



        if (savedInstanceState != null) {
        }

        return view;
    }
}
