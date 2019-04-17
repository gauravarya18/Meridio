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
    private int i,a,count=0;
    private MyAdapter arrayAdapter;

    //private int i;
    private ArrayList<String> a1;
    // = (ArrayList<String>) getIntent().getSerializableExtra("a1");

    private FirebaseAuth mAuth;

    TextView level;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();

//         level=(TextView)findViewById(R.id.level);
//        level.animate().translationY(-2300).setDuration(2000).setStartDelay(500);






           int x=(int)getIntent().getSerializableExtra("mapid");
        a=x;
        ArrayList<String> list1 = (ArrayList<String>) getIntent().getSerializableExtra("a1");
        ArrayList<String> Titles = (ArrayList<String>) getIntent().getSerializableExtra("Titles");
        ArrayList<String> Urls = (ArrayList<String>) getIntent().getSerializableExtra("Urls");

        Log.d("suthar", Titles.toString());
        //final ArrayList<String> a1 =  (ArrayList<String>)getIntent().getSerializableExtra("FILES_TO_SEND");

        final ArrayList<NEWS> list = new ArrayList<>();

        for (int i = 1; i < Titles.size(); i++) {
            list.add(new NEWS(Titles.get(i),Urls.get(i-1)));
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
                if(count==6) {

                    Intent intent = new Intent(MainActivity.this, ChooseTask.class);
                    intent.putExtra("mapid", a);

                    startActivity(intent);
                    finish();
                }
                 count++;
            }

            @Override
            public void onRightCardExit(Object dataObject) {
                Toast.makeText(MainActivity.this, "right", Toast.LENGTH_SHORT).show();
                if(count==6) {
                    Intent intent=new Intent(MainActivity.this,ChooseTask.class);
                intent.putExtra("mapid",a);

                startActivity(intent);
                finish(); }
                count++;

            }

            @Override
            public void onAdapterAboutToEmpty(int itemsInAdapter) {

                list.add(new NEWS("Well Done !!","https://firebasestorage.googleapis.com/v0/b/tinder-2-bb59a.appspot.com/o/kisspng-trophy-clip-art-golden-trophy-5a713ae9136d04.1704861815173700890796.png?alt=media&token=d18bc99b-aa1d-4851-a3da-4770f5e1db6d"));
                arrayAdapter.notifyDataSetChanged();
                Log.d("LIST", "notified");

//                Intent intent=new Intent(MainActivity.this,NewsActivity.class);
//                intent.putExtra("mapid",a);
//                intent.putExtra("level",2);
//                startActivity(intent);

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





    @Override
    public void onBackPressed() {

        finish();
        Intent intent = new Intent(MainActivity.this, ChooseTask.class);
        intent.putExtra("mapid",a);
        startActivity(intent);
    }

}