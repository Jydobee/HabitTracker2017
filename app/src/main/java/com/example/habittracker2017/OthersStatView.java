package com.example.habittracker2017;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by Jonah Cowan on 2017-12-03.
 */

public class OthersStatView extends AppCompatActivity{

    private String userName;
    private int habitLocation;
    private Date startDate;
    private Date currentDate;
    private TextView completeText;
    private TextView missText;
    private Button startDatePickerButton;
    private Button endDatePickerButton;
    private PieChart chart;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stat_view);

         userName = this.getIntent().getStringExtra("Username");
         habitLocation = this.getIntent().getIntExtra("habitLocation",0);

         ArrayList<String> following = new ArrayList<>();
         following.add(userName);
         RemoteClient.loadUsers task = new RemoteClient.loadUsers();
         task.execute(following);
         ArrayList<User> followedUser = new ArrayList<>();
         try{
             followedUser = task.get();
         }catch(Exception e){
             Log.i("Error", "Something went wrong when we tried to communicate with the elasticsearch server!");
         }

         User followed = followedUser.get(0);

         Habit habit = followed.getHabits().get(habitLocation);
         currentDate = Calendar.getInstance().getTime();
         startDate = habit.getStartDate();

         TextView statTitle = (TextView) findViewById(R.id.statsHeader);
         statTitle.setText("Statistics for " + habit.getTitle());
         startDatePickerButton = (Button) findViewById(R.id.startDatePicker);
         endDatePickerButton = (Button) findViewById(R.id.endDatePicker);
         DateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
         String newStartDateString = simpleDateFormat.format(startDate);
         startDatePickerButton.setText(newStartDateString);
         String newEndDateString = simpleDateFormat.format(currentDate);
         endDatePickerButton.setText(newEndDateString);

         ArrayList<Float> completed = StatManager.completedStats(startDate,currentDate,habit);

         pieChartDraw(completed);
    }



    private void pieChartDraw(ArrayList<Float> chartData){

        completeText = (TextView) findViewById(R.id.complete);
        missText = (TextView) findViewById(R.id.miss);
        completeText.setText("Missed: " + chartData.get(2));
        missText.setText("Completed: " + chartData.get(3));

        /*
        Pie chart filling and colouring stuff
         */
        float complete = chartData.get(0);
        float miss = chartData.get(1);
        chart = (PieChart) findViewById(R.id.pieChart);
        List<PieEntry> entries = new ArrayList<>();
        ArrayList<Integer> colors = new ArrayList<Integer>();

        entries.add(new PieEntry(complete, "Completed"));
        entries.add(new PieEntry(miss, "Missed"));

        chart.setHoleRadius(0f);
        chart.getDescription().setEnabled(false);

        colors.add(Color.rgb(0,153,51));//green for complete
        colors.add(Color.rgb(244,66,66));//red for missed


        PieDataSet set = new PieDataSet(entries,"Habits");
        set.setColors(colors);
        PieData data = new PieData(set);
        data.setValueTextSize(20f);
        data.setValueTextColor(Color.BLACK);
        chart.setData(data);
        chart.animateXY(1000, 1000);
        chart.invalidate();
    }
}