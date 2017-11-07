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
        for(Assign a : assigns) { //starts at beginning of time - need to make it start with current month?
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

        graph.getViewport().setYAxisBoundsManual(true);
        graph.getViewport().setMinY(1);
        graph.getViewport().setMaxY(5);

        graph.getGridLabelRenderer().setNumHorizontalLabels(15);
        graph.getGridLabelRenderer().setNumVerticalLabels(5);
        graph.getGridLabelRenderer().setVerticalAxisTitle("Mood");
        graph.getGridLabelRenderer().setHorizontalAxisTitle("2 Week Window");
        graph.getGridLabelRenderer().setHorizontalLabelsAngle(20);

        //TO_DO: use dates as labels

        //Inefficient workaround that allows for each data point to be a different color:
        // TO_DO: FIX COLORS
        int count2 = 0;
        for(Assign a : assigns) {
            if(a.getMood() == 1) {
                DataPoint[] data2 = new DataPoint[1];
                data2[0] = new DataPoint(count2, a.getMood());
                PointsGraphSeries<DataPoint> series1 = new PointsGraphSeries<>(data2);
                series1.setShape(PointsGraphSeries.Shape.POINT);
                series1.setColor(Color.GREEN);
                series.setDataPointsRadius(12);
                graph.addSeries(series1);
                count2++;
            }
            else if(a.getMood() == 2) {
                DataPoint[] data2 = new DataPoint[1];
                data2[0] = new DataPoint(count2, a.getMood());
                PointsGraphSeries<DataPoint> series1 = new PointsGraphSeries<>(data2);
                series1.setShape(PointsGraphSeries.Shape.POINT);
                series1.setColor(Color.MAGENTA);
                series.setDataPointsRadius(12);
                graph.addSeries(series1);
                count2++;
            }
            else if(a.getMood() == 3) {
                DataPoint[] data2 = new DataPoint[1];
                data2[0] = new DataPoint(count2, a.getMood());
                PointsGraphSeries<DataPoint> series1 = new PointsGraphSeries<>(data2);
                series1.setShape(PointsGraphSeries.Shape.POINT);
                series1.setColor(Color.BLUE);
                series.setDataPointsRadius(12);
                graph.addSeries(series1);
                count2++;
            }
            else if(a.getMood() == 4) {
                DataPoint[] data2 = new DataPoint[1];
                data2[0] = new DataPoint(count2, a.getMood());
                PointsGraphSeries<DataPoint> series1 = new PointsGraphSeries<>(data2);
                series1.setShape(PointsGraphSeries.Shape.POINT);
                series1.setColor(Color.RED);
                series.setDataPointsRadius(12);
                graph.addSeries(series1);
                count2++;
            }
            else if(a.getMood() == 5) {
                DataPoint[] data2 = new DataPoint[1];
                data2[0] = new DataPoint(count2, a.getMood());
                PointsGraphSeries<DataPoint> series1 = new PointsGraphSeries<>(data2);
                series1.setShape(PointsGraphSeries.Shape.POINT);
                series1.setColor(Color.BLACK);
                series.setDataPointsRadius(12);
                graph.addSeries(series1);
                count2++;
            }
        }

        //Correlate activities to mood or vice versa - below line graph
        String[] activityArray = {"Working", "Relaxing", "Hanging out with friends", "Date",
                "Sports", "Partying", "Watching TV", "Reading", "Gaming", "Shopping", "Travel",
                "Good meal", "Cleaning", "Napping", "Homework"};
        String top1 = "";

        int[] greatMoodActivities = new int[15]; //array to keep total count of great mood entries for each activity
        for(Assign a : assigns) {
            StringBuilder activities = new StringBuilder("");
            if(a.getMood() == 1) {
                if (a.getLogs() == null) {
                    activities.append("000000000000000");
                }
                else {
                    activities.append(a.getLogs());
                }
                //parse activity string data - put into array and keep count of great days for each activity
                char[] actArray = new char[15]; //activity array for individual entry
                activities.getChars(0, 14, actArray, 0);
                for(int i = 0; i < actArray.length; i++) {
                    if(actArray[i] == '1') {
                        greatMoodActivities[i]++;
                    }
                }
            }
        }
        int max = 0;
        for (int i = 0; i < greatMoodActivities.length; i++) { //find top correlated activity by finding max count
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
