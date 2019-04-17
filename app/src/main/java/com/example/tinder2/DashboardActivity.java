package com.example.tinder2;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import java.util.ArrayList;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

public class DashboardActivity extends AppCompatActivity {
    int a;
    TextView textView3,textView4,textView5;
    PieChart pieChart ;
    ArrayList<Entry> entries ;
    ArrayList<String> PieEntryLabels ;
    PieDataSet pieDataSet ;
    PieData pieData ;
    float SCORE;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        int shared_level1=(int)getIntent().getSerializableExtra("share");
        final int score=(int)getIntent().getSerializableExtra("score");
        int shared_level2=(int)getIntent().getSerializableExtra("shared_level2");


           mAuth=FirebaseAuth.getInstance();




        int x=(int)getIntent().getSerializableExtra("mapid");
        a=x;
//        int score=Integer.valueOf(score0);
//        int shared_level1=Integer.valueOf(shared_level10);
//        int shared_level2=Integer.valueOf(shared_level20);
        SCORE=score;
        SCORE/=6;
        SCORE*=100;
//        int shared_level1=-2;
//
//        if(shared_level2==-2||shared_level2==-1)
//            shared_level2=0;

       textView3=findViewById(R.id.textView3);
        textView4=findViewById(R.id.textView4);
//        textView5=findViewById(R.id.textView5);
      textView3.setText(""+shared_level1);
      textView4.setText(""+shared_level2);
//      textView5.setText("Your Final Score is "+score);

        pieChart = (PieChart) findViewById(R.id.chart1);

        entries = new ArrayList<>();

        PieEntryLabels = new ArrayList<String>();


        AddValuesToPIEENTRY();

        AddValuesToPieEntryLabels();

        pieDataSet = new PieDataSet(entries, "");


        pieData = new PieData(PieEntryLabels, pieDataSet);

        pieDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        pieDataSet.setValueTextSize(18);

        pieChart.setData(pieData);

        pieChart.animateY(3000);
    }
    @Override
    public void onBackPressed() {

        finish();
        Intent intent = new Intent(DashboardActivity.this, ChooseTask.class);
        intent.putExtra("mapid",a);
        startActivity(intent);
    }


    public void AddValuesToPIEENTRY(){

        entries.add(new BarEntry(SCORE, 0));
        entries.add(new BarEntry(100-SCORE, 1));

    }

    public void AddValuesToPieEntryLabels(){

        PieEntryLabels.add("Matching");
        PieEntryLabels.add("Not Matching");
    }
}
