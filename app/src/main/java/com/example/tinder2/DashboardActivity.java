package com.example.tinder2;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Handler;
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
     String score0="0",shared_level10="0",shared_level20="0";
     int score,shared_level1,shared_level2;
     int g=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

//

            mAuth=FirebaseAuth.getInstance();
            FirebaseDatabase.getInstance().getReference("users/" + mAuth.getUid()).addValueEventListener(new ValueEventListener() {
                @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {



                    Log.d("suthar", dataSnapshot.toString());


                String s= dataSnapshot.child("Score").getValue().toString();
                String s1=dataSnapshot.child("Level1").getValue().toString();
                String s2=dataSnapshot.child("Level2").getValue().toString();



                score0=s;
                shared_level10=s1;
                shared_level20=s2;
               Log.d("score",s+" "+s1);
                 score=Integer.valueOf(score0);
                 shared_level1=Integer.valueOf(shared_level10);
                 shared_level2=Integer.valueOf(shared_level20);
                SCORE=score;
                SCORE/=6;
                SCORE*=100;

                textView3=findViewById(R.id.textView3);
                textView4=findViewById(R.id.textView4);
                textView3.setText("Level 1 \n Shared-"+shared_level1);
                textView4.setText("Level 2 \n Shared-"+shared_level2);



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
                g=1;



            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {


            }
        });




        int x=(int)getIntent().getSerializableExtra("mapid");
        a=x;

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

    void gaurav(int i){
        ProgressDialog progressDialog = new ProgressDialog(DashboardActivity.this);
        if(i==1)
        {
            progressDialog.dismiss();
        }
        else
        {

            progressDialog.setMessage("Analysing Responses" +"\n"+ "Please wait..");
            progressDialog.show();
        }
    }
}
