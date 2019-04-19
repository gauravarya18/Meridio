package com.example.tinder2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;

import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.lorentzos.flingswipe.SwipeFlingAdapterView;

import java.util.ArrayList;

public class Main2Activity extends AppCompatActivity {

    private ArrayList<String> al;
    //  private ArrayAdapter<String> arrayAdapter;
    private int i,a;
    private MyAdapter1 arrayAdapter;

    //private int i;
    private ArrayList<String> a1;
    // = (ArrayList<String>) getIntent().getSerializableExtra("a1");

    private FirebaseAuth mAuth;
    int count=0;
    int share[]=new int[9];

    TextView level;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        mAuth = FirebaseAuth.getInstance();




//        level=(TextView)findViewById(R.id.level);
//        level.animate().translationY(-2300).setDuration(2000).setStartDelay(500);


        int x=(int)getIntent().getSerializableExtra("mapid");
        a=x;

        ArrayList<String> Contents = (ArrayList<String>) getIntent().getSerializableExtra("Contents");
        ArrayList<String> Titles = (ArrayList<String>) getIntent().getSerializableExtra("Titles");



        Log.d("suthar", Titles.toString());
        //final ArrayList<String> a1 =  (ArrayList<String>)getIntent().getSerializableExtra("FILES_TO_SEND");

        final ArrayList<NEWS> list = new ArrayList<>();

        for (int i = 0; i < Titles.size(); i++) {
            list.add(new NEWS(Titles.get(i),Contents.get(i)));
        }

        arrayAdapter = new MyAdapter1(this, list);

        SwipeFlingAdapterView flingContainer = (SwipeFlingAdapterView) findViewById(R.id.frame);
        flingContainer.setAdapter(arrayAdapter);
        flingContainer.setFlingListener(new SwipeFlingAdapterView.onFlingListener() {
            @Override
            public void removeFirstObjectInAdapter() {
                // this is the simplest way to delete an object from the Adapter (/AdapterView)
                Log.d("LIST", "removed object!");
                list.remove(0);
                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onLeftCardExit(Object dataObject) {
                //Do something on the left!
                //You also have access to the original object.
                //If you want to use it just cast it (String) dataObject
                Toast.makeText(Main2Activity.this, "left", Toast.LENGTH_SHORT).show();
                share[count]=0;
                if(count==8)
                {
                    Intent intent=new Intent(Main2Activity.this,NewsActivity.class);
                    intent.putExtra("share",share);
                    intent.putExtra("mapid",a);

                    intent.putExtra("level",2);
                    startActivity(intent);
                    finish();
                }

                count++;
            }

            @Override
            public void onRightCardExit(Object dataObject) {
                Toast.makeText(Main2Activity.this, "right", Toast.LENGTH_SHORT).show();
                share[count]=1;

                if(count==8)
                {
                    Intent intent=new Intent(Main2Activity.this,NewsActivity.class);
                    intent.putExtra("share",share);
                    intent.putExtra("mapid",a);

                    intent.putExtra("level",2);
                    startActivity(intent);
                    finish();
                }
                count++;
            }

            @Override
            public void onAdapterAboutToEmpty(int itemsInAdapter) {
                list.add(new NEWS("Round 2 \n Let's Play !!",""));
                arrayAdapter.notifyDataSetChanged();
                Log.d("LIST", "notified");


            }

            @Override
            public void onScroll(float scrollProgressPercent) {
            }
        });


        // Optionally add an OnItemClickListener
        flingContainer.setOnItemClickListener(new SwipeFlingAdapterView.OnItemClickListener() {
            @Override
            public void onItemClicked(int itemPosition, Object dataObject) {
                Toast.makeText(Main2Activity.this, "clicked", Toast.LENGTH_SHORT).show();
            }
        });

    }





    @Override
    public void onBackPressed() {

        finish();
        Intent intent = new Intent(Main2Activity.this, ChooseTask.class);
        intent.putExtra("mapid",a);
        startActivity(intent);
    }

}