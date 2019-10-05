package com.sarahman.projects.graph.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.annotation.SuppressLint;
import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.sarahman.projects.graph.R;
import com.sarahman.projects.graph.fragments.DetailsFragment;
import com.sarahman.projects.graph.models.ApiResponse;
import com.sarahman.projects.graph.models.Record;

import java.lang.reflect.Array;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

public class MainActivity extends AppCompatActivity {

    private MainViewModel viewModel;

    private SwipeRefreshLayout swipeRefresh;
    private BarChart chartOne;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewModel = ViewModelProviders.of(this).get(MainViewModel.class);

        // Init toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        TextView toolbarTitle = findViewById(R.id.toolbarTitle);
        toolbarTitle.setText(R.string.title_main);

        swipeRefresh = findViewById(R.id.swipeRefresh);
        chartOne = findViewById(R.id.chartOne);

        // Bind view models
        viewModel.getLoading().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (aBoolean) swipeRefresh.setRefreshing(true);
                else swipeRefresh.setRefreshing(false);
            }
        });

        viewModel.getApiResponse().observe(this, new Observer<ApiResponse>() {
            @Override
            public void onChanged(ApiResponse apiResponse) {
                if (apiResponse != null) {
                    List<Record> records = apiResponse.getRecords();
                    List<String> xAxisData = new ArrayList<>();
                    List<Integer> yAxisData = new ArrayList<>();
                    for (Record record : records) {
                        xAxisData.add(record.getFields().getStationCode());
                        yAxisData.add(record.getFields().getNbfreeedock());
                    }

                    String[] xValues = new String[xAxisData.size()];
                    for (int i=0; i<xAxisData.size(); i++) {
                        xValues[i] = xAxisData.get(i);
                    }

                    int[] yValues = new int[yAxisData.size()];
                    for (int i=0; i<yAxisData.size(); i++) {
                        yValues[i] = yAxisData.get(i);
                    }

                    initChart(xValues, yValues, records);
                }
            }
        });

        // Load graph
        viewModel.loadGraph(this);
    }

    @SuppressLint("SimpleDateFormat")
    private void initChart(String[] xAxisData, int[] yAxisData, final List<Record> records) {
        chartOne.getDescription().setEnabled(true);
        chartOne.setMaxVisibleValueCount(60);
        chartOne.setPinchZoom(false);

        chartOne.setDrawBarShadow(false);
        chartOne.setDrawGridBackground(false);

        XAxis xAxis = chartOne.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);

        xAxis.setTextColor(getResources().getColor(R.color.colorMediumBlack));
        xAxis.setTextSize(10f);
        xAxis.setValueFormatter(new XAxisFormatter(xAxisData));

        YAxis axisLeft = chartOne.getAxisLeft();
        axisLeft.setTextColor(getResources().getColor(R.color.colorMediumBlack));
        axisLeft.setTextSize(10f);

        YAxis axisRight = chartOne.getAxisRight();
        axisRight.setTextColor(getResources().getColor(R.color.colorMediumBlack));
        axisRight.setTextSize(10f);

        chartOne.animateY(1500);

        chartOne.getLegend().setEnabled(false);

        ArrayList<BarEntry> entries = new ArrayList<>();
        // Load yAxis
        for (int i=0; i<yAxisData.length; i++) {
            entries.add(new BarEntry((float) i, yAxisData[i]));
        }

        BarDataSet set = new BarDataSet(entries, "Statistics");
        set.setColors(ColorTemplate.VORDIPLOM_COLORS);
        set.setDrawValues(false);

        ArrayList<IBarDataSet> dataSets = new ArrayList<>();
        dataSets.add(set);

        BarData data = new BarData(dataSets);
        chartOne.setData(data);
        chartOne.setFitBars(false);
        chartOne.setDescription(null);

        chartOne.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, Highlight h) {
                Record record = records.get((int) e.getX());

                // parse date
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.S");
                format.setTimeZone(TimeZone.getTimeZone("UTC"));
                Date createdAt = null;
                try {
                    createdAt = format.parse(record.getRecordTimestamp());
                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
                    dateFormat.setTimeZone(TimeZone.getDefault());

                    // Open dialog
                    DetailsFragment fragment = DetailsFragment.newInstance(record.getFields().getStationName(), dateFormat.format(createdAt), record.getFields().getNbedock(), record.getFields().getNbfreeedock());
                    fragment.show(getSupportFragmentManager(), fragment.getTag());
                } catch (ParseException ex) {
                    ex.printStackTrace();
                }
            }

            @Override
            public void onNothingSelected() {

            }
        });

        chartOne.invalidate();
    }

    private class XAxisFormatter extends ValueFormatter {

        private String[] labels;

        public XAxisFormatter(String[] labels) {
            this.labels = labels;
        }

        @Override
        public String getAxisLabel(float value, AxisBase axis) {
            return labels[(int)value];
        }
    }
}
