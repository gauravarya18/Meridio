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

public class MainActivity extends AppCompatActivity {

    private ArrayList<String> al;
  //  private ArrayAdapter<String> arrayAdapter;
    private int i,a;
    private MyAdapter arrayAdapter;
    private TextView tv;
    //private int i;
    private ArrayList<String> a1; // = (ArrayList<String>) getIntent().getSerializableExtra("a1");

    private FirebaseAuth mAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();





                tv=(TextView) findViewById(R.id.tv1);
                a1 = new ArrayList<>();
                int x=(int)getIntent().getSerializableExtra("mapid");
                a=x;
                if(x==1)
                    tv.setText("Latin America");
                else if(x==2)
                    tv.setText("North America");
                else if(x==3)
                    tv.setText("Australia");
                else if(x==4)
                    tv.setText("Asia");
                else if(x==5)
                    tv.setText("Africa");
                else if(x==6)
                    tv.setText("Arab");
                else if(x==7)
                    tv.setText("Europe");



        ArrayList<String> list1 = (ArrayList<String>) getIntent().getSerializableExtra("a1");
                Log.d("suthar", list1.toString());
                //final ArrayList<String> a1 =  (ArrayList<String>)getIntent().getSerializableExtra("FILES_TO_SEND");

                final ArrayList<NEWS> list = new ArrayList<>();

                for (int i = 0; i < list1.size(); i++) {
                    list.add(new NEWS(list1.get(i), ""));
                }

                arrayAdapter = new MyAdapter(this, list);

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
                        Toast.makeText(MainActivity.this, "left", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onRightCardExit(Object dataObject) {
                        Toast.makeText(MainActivity.this, "right", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onAdapterAboutToEmpty(int itemsInAdapter) {
                        // Ask for more data here
                /*int x = 2;
                if (i % x == 0)
                    a1.add("<3 ");
                else
                    a1.add(":P ");
                arrayAdapter.notifyDataSetChanged();
                Log.d("LIST", "notified");
                i++;*/
                    }

                    @Override
                    public void onScroll(float scrollProgressPercent) {
                    }
                });


                // Optionally add an OnItemClickListener
                flingContainer.setOnItemClickListener(new SwipeFlingAdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClicked(int itemPosition, Object dataObject) {
                        Toast.makeText(MainActivity.this, "clicked", Toast.LENGTH_SHORT).show();
                    }
                });

            }




        public void logoutUser (View view){
            mAuth.signOut();
            Intent intent = new Intent(MainActivity.this, bootActivity.class);
            startActivity(intent);
            finish();

            return;
        }
    @Override
    public void onBackPressed() {

        finish();
        Intent intent = new Intent(MainActivity.this, ChooseTask.class);
        intent.putExtra("mapid",a);
        startActivity(intent);
    }

    }