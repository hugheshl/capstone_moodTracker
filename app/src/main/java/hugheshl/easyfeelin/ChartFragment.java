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
import android.widget.Toast;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.GridLabelRenderer;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.DataPointInterface;
import com.jjoe64.graphview.series.LineGraphSeries;
import com.jjoe64.graphview.series.OnDataPointTapListener;
import com.jjoe64.graphview.series.PointsGraphSeries;
import com.jjoe64.graphview.series.Series;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.UUID;

import static android.R.attr.value;
import static android.graphics.Color.rgb;
import static java.lang.Math.round;

public class ChartFragment extends DialogFragment {

    public static final String EXTRA_DATE = "hugheshl.turniptimer2.date";
    private static final String ARG_DATE = "date";
    private Assign assign;
    String dateText = "";
    SimpleDateFormat df;

    private TextView mTextView;
    private TextView mCorrelationsGreat;
    private TextView mCorrelationsHorrible;
    private TextView mActivityCorrelationsGreat;
    private TextView mActivityCorrelationsHorrible;
    private TextView mActivityAvgList0;
    private TextView mActivityAvgList1;
    private TextView mActivityAvgList2;
    private TextView mActivityAvgList3;
    private TextView mActivityAvgList4;
    private TextView mActivityAvgList5;
    private TextView mActivityAvgList6;
    private TextView mActivityAvgList7;
    private TextView mActivityAvgList8;
    private TextView mActivityAvgList9;
    private TextView mActivityAvgList10;
    private TextView mActivityAvgList11;
    private TextView mActivityAvgList12;
    private TextView mActivityAvgList13;
    private TextView mActivityAvgList14;

    public static ChartFragment newInstance() {
        ChartFragment chartfragment = new ChartFragment();
        return chartfragment;
    }

    public int convertMood(int originalMood) {
        switch (originalMood) {
            case 1:
                return 5;
            case 2:
                return 4;
            case 3:
                return 3;
            case 4:
                return 2;
            case 5:
                return 1;
            default:
                return 5;
        }
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
            data[count] = new DataPoint(count, convertMood(a.getMood()));
            count++;
        }
        LineGraphSeries<DataPoint> series = new LineGraphSeries<>(data);
        graph.addSeries(series);

        graph.setTitle("Mood Chart");
        graph.setTitleTextSize(60);

        series.setDrawDataPoints(true);
        series.setDataPointsRadius(12);
        series.setDrawBackground(false);
        series.setColor(rgb(160, 160, 160));

        series.setOnDataPointTapListener(new OnDataPointTapListener() {
            @Override
            public void onTap(Series series, DataPointInterface dataPoint) {
                Toast.makeText(getActivity(), "Placeholder for entry date", Toast.LENGTH_SHORT).show();
            }
        });

        graph.getViewport().setScalable(true);
        graph.getViewport().setScrollable(true);
        graph.getViewport().setXAxisBoundsManual(true);
        graph.getViewport().setMinX(1);
        graph.getViewport().setMaxX(15);
        graph.getViewport().setYAxisBoundsManual(true);
        graph.getViewport().setMinY(1);
        graph.getViewport().setMaxY(5);
        graph.getViewport().scrollToEnd();

        graph.getGridLabelRenderer().setNumHorizontalLabels(15);
        graph.getGridLabelRenderer().setNumVerticalLabels(5);
        graph.getGridLabelRenderer().setVerticalAxisTitle("Mood");
        graph.getGridLabelRenderer().setHorizontalAxisTitle("Time");
        graph.getGridLabelRenderer().setVerticalLabelsVisible(false);
        graph.getGridLabelRenderer().setHorizontalLabelsVisible(false);

        graph.getGridLabelRenderer().setGridStyle(GridLabelRenderer.GridStyle.NONE);
        graph.getViewport().setDrawBorder(true);

        //TO_DO: use dates as labels - currently is just an entry index

        //Inefficient workaround that allows for each data point to be a different color:
        int count2 = 0;

        for(Assign a : assigns) {
            if(a.getMood() == 1) {
                DataPoint[] data2 = new DataPoint[1];
                data2[0] = new DataPoint(count2, convertMood(a.getMood()));
                PointsGraphSeries<DataPoint> series1 = new PointsGraphSeries<>(data2);
                series1.setShape(PointsGraphSeries.Shape.POINT);
                series1.setColor(rgb(0, 199, 13));
                series.setDataPointsRadius(12);
                graph.addSeries(series1);
                count2++;
                assign = a;
                /*series.setOnDataPointTapListener(new OnDataPointTapListener() {
                    @Override
                    public void onTap(Series series, DataPointInterface dataPoint) {
                        df = new SimpleDateFormat("EEE, dd MMM yyyy");
                        dateText = df.format(assign.getDate());
                        Toast.makeText(getActivity(), "" + dateText, Toast.LENGTH_SHORT).show();
                    }
                });*/
            }
            else if(a.getMood() == 2) {
                DataPoint[] data2 = new DataPoint[1];
                data2[0] = new DataPoint(count2, convertMood(a.getMood()));
                PointsGraphSeries<DataPoint> series1 = new PointsGraphSeries<>(data2);
                series1.setShape(PointsGraphSeries.Shape.POINT);
                series1.setColor(rgb(147, 62, 197));
                series.setDataPointsRadius(12);
                graph.addSeries(series1);
                count2++;
                assign = a;
                /*series.setOnDataPointTapListener(new OnDataPointTapListener() {
                    @Override
                    public void onTap(Series series, DataPointInterface dataPoint) {
                        df = new SimpleDateFormat("EEE, dd MMM yyyy");
                        dateText = df.format(assign.getDate());
                        Toast.makeText(getActivity(), "" + dateText, Toast.LENGTH_SHORT).show();
                    }
                });*/
            }
            else if(a.getMood() == 3) {
                DataPoint[] data2 = new DataPoint[1];
                data2[0] = new DataPoint(count2, convertMood(a.getMood()));
                PointsGraphSeries<DataPoint> series1 = new PointsGraphSeries<>(data2);
                series1.setShape(PointsGraphSeries.Shape.POINT);
                series1.setColor(rgb(0, 109, 240));
                series.setDataPointsRadius(12);
                graph.addSeries(series1);
                count2++;
                assign = a;
                /*series.setOnDataPointTapListener(new OnDataPointTapListener() {
                    @Override
                    public void onTap(Series series, DataPointInterface dataPoint) {
                        df = new SimpleDateFormat("EEE, dd MMM yyyy");
                        dateText = df.format(assign.getDate());
                        Toast.makeText(getActivity(), "" + dateText, Toast.LENGTH_SHORT).show();
                    }
                });*/
            }
            else if(a.getMood() == 4) {
                DataPoint[] data2 = new DataPoint[1];
                data2[0] = new DataPoint(count2, convertMood(a.getMood()));
                PointsGraphSeries<DataPoint> series1 = new PointsGraphSeries<>(data2);
                series1.setShape(PointsGraphSeries.Shape.POINT);
                series1.setColor(rgb(240, 92, 0));
                series.setDataPointsRadius(12);
                graph.addSeries(series1);
                count2++;
                assign = a;
                /*series.setOnDataPointTapListener(new OnDataPointTapListener() {
                    @Override
                    public void onTap(Series series, DataPointInterface dataPoint) {
                        df = new SimpleDateFormat("EEE, dd MMM yyyy");
                        dateText = df.format(assign.getDate());
                        Toast.makeText(getActivity(), "" + dateText, Toast.LENGTH_SHORT).show();
                    }
                });*/
            }
            else if(a.getMood() == 5) {
                DataPoint[] data2 = new DataPoint[1];
                data2[0] = new DataPoint(count2, convertMood(a.getMood()));
                PointsGraphSeries<DataPoint> series1 = new PointsGraphSeries<>(data2);
                series1.setShape(PointsGraphSeries.Shape.POINT);
                series1.setColor(rgb(216, 0, 39));
                series.setDataPointsRadius(12);
                graph.addSeries(series1);
                count2++;
                assign = a;
                /*series.setOnDataPointTapListener(new OnDataPointTapListener() {
                    @Override
                    public void onTap(Series series, DataPointInterface dataPoint) {
                        //df = new SimpleDateFormat("EEE, dd MMM yyyy");
                        //dateText = df.format(assign.getDate());
                        //Toast.makeText(getActivity(), "" + dateText, Toast.LENGTH_SHORT).show();
                        Toast.makeText(getActivity(), "" + assign.getDate(), Toast.LENGTH_SHORT).show(); //date-on-click has bug
                    }
                });*/
            }
        }

        //Possible bug - sometimes not updating correctly?
        //Correlate activities to mood or vice versa - below line graph
        String[] activityArray = {"Working", "Relaxing", "Hanging out with friends", "Date",
                "Sports", "Partying", "Watching TV", "Reading", "Gaming", "Shopping", "Travel",
                "Good meal", "Cleaning", "Napping", "Homework"};
        String topGreat = "";
        String topHorrible = "";

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
        topGreat = activityArray[max];

        mActivityCorrelationsGreat = (TextView) view.findViewById(R.id.textViewGreatTitle);
        mActivityCorrelationsGreat.setText("Top Correlation to Great Mood: ");

        mCorrelationsGreat = (TextView) view.findViewById(R.id.textViewGreat);
        if(assigns.size() > 0)
            mCorrelationsGreat.setText("" + topGreat);
        else
            mCorrelationsGreat.setText("No entries yet!");

        //--------------------------------------------------------------------------------------------------------------

        int[] horribleMoodActivities = new int[15]; //array to keep total count of horrible mood entries for each activity
        for(Assign a : assigns) {
            StringBuilder activities = new StringBuilder("");
            if(a.getMood() == 5) {
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
                        horribleMoodActivities[i]++;
                    }
                }
            }
        }

        int max2 = 0;
        for (int i = 0; i < horribleMoodActivities.length; i++) { //find top correlated activity by finding max count
            if (horribleMoodActivities[i] > max2) {
                max2 = i;
            }
        }
        topHorrible = activityArray[max2];

        mActivityCorrelationsHorrible = (TextView) view.findViewById(R.id.textViewHorribleTitle);
        mActivityCorrelationsHorrible.setText("Top Correlation to Horrible Mood: ");

        mCorrelationsHorrible = (TextView) view.findViewById(R.id.textViewHorrible);
        if(assigns.size() > 0)
            mCorrelationsHorrible.setText("" + topHorrible);
        else
            mCorrelationsHorrible.setText("No entries yet!");

        //---------------------------------------------------------------------------------------------------------------------

        mActivityAvgList0 = (TextView) view.findViewById(R.id.activityAvg0);
        mActivityAvgList1 = (TextView) view.findViewById(R.id.activityAvg1);
        mActivityAvgList2 = (TextView) view.findViewById(R.id.activityAvg2);
        mActivityAvgList3 = (TextView) view.findViewById(R.id.activityAvg3);
        mActivityAvgList4 = (TextView) view.findViewById(R.id.activityAvg4);
        mActivityAvgList5 = (TextView) view.findViewById(R.id.activityAvg5);
        mActivityAvgList6 = (TextView) view.findViewById(R.id.activityAvg6);
        mActivityAvgList7 = (TextView) view.findViewById(R.id.activityAvg7);
        mActivityAvgList8 = (TextView) view.findViewById(R.id.activityAvg8);
        mActivityAvgList9 = (TextView) view.findViewById(R.id.activityAvg9);
        mActivityAvgList10 = (TextView) view.findViewById(R.id.activityAvg10);
        mActivityAvgList11 = (TextView) view.findViewById(R.id.activityAvg11);
        mActivityAvgList12 = (TextView) view.findViewById(R.id.activityAvg12);
        mActivityAvgList13 = (TextView) view.findViewById(R.id.activityAvg13);
        mActivityAvgList14 = (TextView) view.findViewById(R.id.activityAvg14);

        int[][] computeAvgs = new int[15][2]; // [][0] is count of how many times activity listed
                                                //[][1] is total mood points for activity

        for(Assign a : assigns) {
            StringBuilder activities = new StringBuilder("");
                if (a.getLogs() == null) {
                    //activities.append("000000000000000");
                }
                else {
                    activities.append(a.getLogs());
                    String activityString = activities.toString();

                    for (int i = 0; i < 15; i++) {
                        if (activityString.charAt(i) == '1') {
                            computeAvgs[i][0]++;
                            computeAvgs[i][1] += convertMood(a.getMood());
                        }
                    }
                }

        }

        double computedAvg = 0.0;
        double[] allAvgs = new double[15];

        for(int i = 0; i < 15; i++) {
            if (computeAvgs[i][0] != 0) {
                computedAvg = (double) computeAvgs[i][1] / (double) computeAvgs[i][0];
                BigDecimal bd = new BigDecimal(Double.toString(computedAvg));
                bd = bd.setScale(2, RoundingMode.HALF_UP);
                allAvgs[i] = bd.doubleValue();
            }
        }

        if(allAvgs[0] == 0)
            mActivityAvgList0.setText(activityArray[0] + ": n/a");
        else
            mActivityAvgList0.setText("" + activityArray[0] + ": " + allAvgs[0]);
        if(allAvgs[1] == 0)
            mActivityAvgList1.setText(activityArray[1] + ": n/a");
        else
            mActivityAvgList1.setText("" + activityArray[1] + ": " + allAvgs[1]);
        if(allAvgs[2] == 0)
            mActivityAvgList2.setText(activityArray[2] + ": n/a");
        else
            mActivityAvgList2.setText("" + activityArray[2] + ": " + allAvgs[2]);
        if(allAvgs[3] == 0)
            mActivityAvgList3.setText(activityArray[3] + ": n/a");
        else
            mActivityAvgList3.setText("" + activityArray[3] + ": " + allAvgs[3]);
        if(allAvgs[4] == 0)
            mActivityAvgList4.setText(activityArray[4] + ": n/a");
        else
            mActivityAvgList4.setText("" + activityArray[4] + ": " + allAvgs[4]);
        if(allAvgs[5] == 0)
            mActivityAvgList5.setText(activityArray[5] + ": n/a");
        else
            mActivityAvgList5.setText("" + activityArray[5] + ": " + allAvgs[5]);
        if(allAvgs[6] == 0)
            mActivityAvgList6.setText(activityArray[6] + ": n/a");
        else
            mActivityAvgList6.setText("" + activityArray[6] + ": " + allAvgs[6]);
        if(allAvgs[7] == 0)
            mActivityAvgList7.setText(activityArray[7] + ": n/a");
        else
            mActivityAvgList7.setText("" + activityArray[7] + ": " + allAvgs[7]);
        if(allAvgs[8] == 0)
            mActivityAvgList8.setText(activityArray[8] + ": n/a");
        else
            mActivityAvgList8.setText("" + activityArray[8] + ": " + allAvgs[8]);
        if(allAvgs[9] == 0)
            mActivityAvgList9.setText(activityArray[9] + ": n/a");
        else
            mActivityAvgList9.setText("" + activityArray[9] + ": " + allAvgs[9]);
        if(allAvgs[10] == 0)
            mActivityAvgList10.setText(activityArray[10] + ": n/a");
        else
            mActivityAvgList10.setText("" + activityArray[10] + ": " + allAvgs[10]);
        if(allAvgs[11] == 0)
            mActivityAvgList11.setText(activityArray[11] + ": n/a");
        else
            mActivityAvgList11.setText("" + activityArray[11] + ": " + allAvgs[11]);
        if(allAvgs[12] == 0)
            mActivityAvgList12.setText(activityArray[12] + ": n/a");
        else
            mActivityAvgList12.setText("" + activityArray[12] + ": " + allAvgs[12]);
        if(allAvgs[13] == 0)
            mActivityAvgList13.setText(activityArray[13] + ": n/a");
        else
            mActivityAvgList13.setText("" + activityArray[13] + ": " + allAvgs[13]);
        if(allAvgs[14] == 0)
            mActivityAvgList14.setText(activityArray[14] + ": n/a");
        else
            mActivityAvgList14.setText("" + activityArray[14] + ": " + allAvgs[14]);

        if (savedInstanceState != null) {
        }

        return view;
    }
}
