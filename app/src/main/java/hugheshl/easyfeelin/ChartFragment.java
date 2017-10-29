package hugheshl.easyfeelin;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
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
import com.jjoe64.graphview.series.PointsGraphSeries;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.UUID;

public class ChartFragment extends DialogFragment {

    public static final String EXTRA_DATE = "hugheshl.turniptimer2.date";
    private static final String ARG_DATE = "date";

    private TextView mTextView;
    private TextView mActivityCorrelations;

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

        graph.setTitle("Mood Chart");
        series.setDrawDataPoints(true);
        series.setDataPointsRadius(12);
        series.setDrawBackground(true);

        graph.getViewport().setScalable(true);
        graph.getViewport().setScrollable(true);
        graph.getViewport().setXAxisBoundsManual(true);
        graph.getViewport().setMinX(0);
        graph.getViewport().setMaxX(15);

        graph.getGridLabelRenderer().setNumHorizontalLabels(15);
        graph.getGridLabelRenderer().setVerticalAxisTitle("Mood");
        graph.getGridLabelRenderer().setHorizontalAxisTitle("2 Week Window");
        graph.getGridLabelRenderer().setHorizontalLabelsAngle(20);

        //----------------------------------------------------------------------
        //another set of data points to color code the graph (i.e. green dot in the chart for a great day)
        DataPoint[] data_1 = new DataPoint[assigns.size()];
        int count2 = 0;
        for(Assign a : assigns) {
            if(a.getMood() == 1) {
                data_1[count2] = new DataPoint(count2, a.getMood());
                count2++;
            }
        }
        PointsGraphSeries<DataPoint> series2 = new PointsGraphSeries<>(data_1);
        //graph.addSeries(series2);                                                  //ERROR
        series2.setShape(PointsGraphSeries.Shape.POINT);
        series2.setColor(Color.GREEN);
        //----------------------------------------------------------------------

        //Correlate activities to mood or vice versa - below line graph
        String[] activityArray = {"Working | ", "Relaxing | ", "Hanging out with friends | ", "Date | ",
                "Sports | ", "Partying | ", "Watching TV | ", "Reading | ", "Gaming | ", "Shopping | ", "Travel | ",
                "Good meal | ", "Cleaning | ", "Napping | ", "Homework | "};
        String top1 = "";

        int[] greatMoodActivities = new int[14];
        for(Assign a : assigns) {
            if(a.getMood() == 1) {
                //parse activity string data - put into array and keep count of great days for each activity
                String activities = a.getLogs();
                char[] actArray = new char[14];
                activities.getChars(0, 13, actArray, 0); // NULL POINTER EXCEPTION
                for(int i = 0; i < actArray.length; i++) {
                    if(actArray[i] == '1') {
                        greatMoodActivities[i]++;
                    }
                }
            }
        }
        int max = 0;
        for (int i = 0; i < greatMoodActivities.length; i++) {
            if (greatMoodActivities[i] > max) {
                max = i;
            }
        }
        top1 = activityArray[max];

        mActivityCorrelations = (TextView) view.findViewById(R.id.textView2);
        mActivityCorrelations.setText("Top Activity Correlated with a Great Mood: " + top1);


        if (savedInstanceState != null) {
        }

        return view;
    }
}
