package com.meridio.tinder2;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class NewsActivity extends AppCompatActivity {

    ListView lvRss;
    ArrayList<String> titles;
    ArrayList<String> links;
    ArrayList<String> a1;
    public ArrayList<String> Titles;

    String tv;
    private ArrayList<String> Urls;
    private  ArrayList<String> Contents;
    int i=0,x,levelid;
    int share[]=new int[9];



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        int a=(int)getIntent().getSerializableExtra("mapid");
        int share_1[]=(int[])getIntent().getSerializableExtra("share");
        share=share_1;
        x=a;
        int l=levelid=(int)getIntent().getSerializableExtra("level");
        levelid=l;
        lvRss = (ListView) findViewById(R.id.lvRss);

        titles = new ArrayList<String>();
        links = new ArrayList<String>();

        lvRss.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Uri uri = Uri.parse(links.get(position));
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);

            }
        });

        new ProcessInBackground().execute();
    }

    public InputStream getInputStream(URL url)
    {
        try
        {
            //openConnection() returns instance that represents a connection to the remote object referred to by the URL
            //getInputStream() returns a stream that reads from the open connection
            return url.openConnection().getInputStream();
        }
        catch (IOException e)
        {
            return null;
        }
    }

    public class ProcessInBackground extends AsyncTask<Integer, Void, Exception>
    {
        ProgressDialog progressDialog = new ProgressDialog(NewsActivity.this);

        Exception exception = null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog.setMessage("Loading" +"\n"+ "please wait...");
            progressDialog.show();
        }

        @Override
        protected Exception doInBackground(Integer... params) {

            String sample=String.valueOf(x);
            while (i != 3)
            { URL url=null,url1=null,url2=null;

                try {
//
                    switch (x)
                    {

                        case 1: {
                            url = new URL("https://www.telesurenglish.net/rss/sport.xml");
                             url1 = new URL("https://www.telesurenglish.net/rss/culture.xml");
                             url2 = new URL("https://www.telesurenglish.net/rss/latinaamerica.xml");


                        }
                        break;
                        case 2: {
                            url = new URL("https://api.foxsports.com/v1/rss?partnerKey=zBaFxRyGKCfxBagJG9b8pqLyndmvo7UU&tag=nba");
                            url1 = new URL("http://feeds.bbci.co.uk/news/health/rss.xml");
                            url2 = new URL("http://feeds.bbci.co.uk/news/politics/rss.xml");

                        }
                        break;
                        case 3: {
                            url = new URL("https://www.news.com.au/content-feeds/latest-news-sport/");
                            url1 = new URL("https://www.news.com.au/content-feeds/latest-news-lifestyle/");
                            url2 = new URL("https://www.news.com.au/content-feeds/latest-news-national/");

                        }
                        break;
                        case 4: {

                            url = new URL("https://www.asianetnews.net/rss/news-releases/sport");
                            url1 = new URL("https://www.asianetnews.net/rss/news-releases/health-medical");
                            url2 = new URL("https://www.asianetnews.net/rss/news-releases/social-welfare");

                        }
                        break;

                        case 5: {

                             url = new URL("http://feeds.reuters.com/reuters/AFRICASportNews");
                             url1 = new URL("https://www.afro.who.int/rss/featured-news.xml");
                             url2 = new URL("http://feeds.reuters.com/reuters/AFRICATopNews");

                        }
                        break;

                        case 6: {

                            url = new URL("http://www.arabnews.com/cat/5/rss.xml");
                            url1 = new URL("http://www.arabnews.com/cat/2/rss.xml");
                            url2 = new URL("http://www.arabnews.com/cat/2/rss.xml");

                        }
                        break;

                        case 7: {

                            url = new URL("https://api.foxsports.com/v1/rss?partnerKey=zBaFxRyGKCfxBagJG9b8pqLyndmvo7UU&tag=soccer");
                            url1 = new URL("http://europa.eu/rapid/search-result.htm?query=89&language=EN&format=RSS");
                            url2 = new URL("http://europa.eu/rapid/search-result.htm?query=55&language=EN&format=RSS");

                        }

                    }




                    //creates new instance of PullParserFactory that can be used to create XML pull parsers
                    XmlPullParserFactory factory = XmlPullParserFactory.newInstance();

                    //Specifies whether the parser produced by this factory will provide support
                    //for XML namespaces
                    factory.setNamespaceAware(false);

                    //creates a new instance of a XML pull parser using the currently configured
                    //factory features
                    XmlPullParser xpp = factory.newPullParser();

                    // We will get the XML from an input stream
                    if (i == 0)
                        xpp.setInput(getInputStream(url), "UTF_8");
                    if (i == 1)
                        xpp.setInput(getInputStream(url1), "UTF_8");
                    if (i == 2)
                        xpp.setInput(getInputStream(url2), "UTF_8");

                    /* We will parse the XML content looking for the "<title>" tag which appears inside the "<item>" tag.
                     * We should take into consideration that the rss feed name is also enclosed in a "<title>" tag.
                     * Every feed begins with these lines: "<channel><title>Feed_Name</title> etc."
                     * We should skip the "<title>" tag which is a child of "<channel>" tag,
                     * and take into consideration only the "<title>" tag which is a child of the "<item>" tag
                     *
                     * In order to achieve this, we will make use of a boolean variable called "insideItem".
                     */
                    boolean insideItem = false;

                    // Returns the type of current event: START_TAG, END_TAG, START_DOCUMENT, END_DOCUMENT etc..
                    int eventType = xpp.getEventType(); //loop control variable
                    int count=0;
                    while (eventType != XmlPullParser.END_DOCUMENT) {
                        //if we are at a START_TAG (opening tag)
                        if (eventType == XmlPullParser.START_TAG) {
                            //if the tag is called "item"
                            if (xpp.getName().equalsIgnoreCase("item")) {
                                insideItem = true;
                            }
                            //if the tag is called "title"
                            else if (xpp.getName().equalsIgnoreCase("title")) {
                                if (insideItem) {
                                    // extract the text between <title> and </title>

                                    titles.add(xpp.nextText());
                                    count++;
                                    if(count==2)
                                    break;
                                    // Log.d("gaurav",titles.toString());
                                }
                            }
                            //if the tag is called "link"
                            else if (xpp.getName().equalsIgnoreCase("link")) {
                                if (insideItem) {
                                    // extract the text between <link> and </link>
                                    links.add(xpp.nextText());
                                }
                            }
                        }
                        //if we are at an END_TAG and the END_TAG is called "item"
                        else if (eventType == XmlPullParser.END_TAG && xpp.getName().equalsIgnoreCase("item")) {
                            insideItem = false;
                        }
                        //
                        eventType = xpp.next(); //move to next element


                    }
                    a1 = new ArrayList<String>(titles);
                    Log.d("sur", titles.toString());

                } catch (MalformedURLException e) {
                    exception = e;
                } catch (XmlPullParserException e) {
                    exception = e;
                } catch (IOException e) {
                    exception = e;
                }
                i++;
            }
            return exception;
        }

        @Override
        protected void onPostExecute(Exception s) {
            super.onPostExecute(s);




            if (x == 1) tv = "Latin America";
            else if (x == 2) tv = "North America";
            else if (x == 3) tv = "Australia";
            else if (x == 4) tv = "Asia";
            else if (x == 5) tv = "Africa";
            else if (x == 6) tv = "arab";
            else if (x == 7) tv = "Europe";


            FirebaseDatabase.getInstance().getReference("news/" + tv).addValueEventListener(new ValueEventListener() {

                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    Titles = new ArrayList<>();
                    Urls = new ArrayList<>();
                    Contents =new ArrayList<>();
                    Log.d("shubh", dataSnapshot.toString());
                    Titles.add("Instructions");
                    Contents.add("Swipe Right, if you want to share the NEWS \n Left, if you don't. \n For detailed Instructions, kindly refer to \n GUIDE ME");
                    Titles.add("Round 1 \n Get Ready !!");
                    Contents.add("");

                    Titles.add(dataSnapshot.child("sports1").getValue(String.class));
                    Titles.add(dataSnapshot.child("sports2").getValue(String.class));
                    Titles.add(dataSnapshot.child("lifestyle1").getValue(String.class));
                    Titles.add(dataSnapshot.child("lifestyle2").getValue(String.class));
                    Titles.add(dataSnapshot.child("politics1").getValue(String.class));
                    Titles.add(dataSnapshot.child("politics2").getValue(String.class));

                    Contents.add(dataSnapshot.child("sports1_con").getValue(String.class));
                    Contents.add(dataSnapshot.child("sports2_con").getValue(String.class));
                    Contents.add(dataSnapshot.child("lifestyle1_con").getValue(String.class));
                    Contents.add(dataSnapshot.child("lifestyle2_con").getValue(String.class));
                    Contents.add(dataSnapshot.child("politics1_con").getValue(String.class));
                    Contents.add(dataSnapshot.child("politics2_con").getValue(String.class));

                    Urls.add(dataSnapshot.child("sports1_url").getValue(String.class));
                    Urls.add(dataSnapshot.child("sports2_url").getValue(String.class));
                    Urls.add(dataSnapshot.child("lifestyle1_url").getValue(String.class));
                    Urls.add(dataSnapshot.child("lifestyle2_url").getValue(String.class));
                    Urls.add(dataSnapshot.child("politics1_url").getValue(String.class));
                    Urls.add(dataSnapshot.child("politics2_url").getValue(String.class));

                    Log.d("suthar",Titles.toString());
                    if(levelid==1)
                    {Intent intent = new Intent(NewsActivity.this, Main2Activity.class);
                        intent.putExtra("a1", a1);
                        intent.putExtra("Titles",Titles);
                        intent.putExtra("Contents",Contents);
                        intent.putExtra("Urls",Urls);
                        intent.putExtra("mapid",x);
                        intent.putExtra("share",share);
                        startActivity(intent);
                        finish();
                    }
                    else if(levelid==2)
                    {
                        Intent intent = new Intent(NewsActivity.this, MainActivity.class);
                        intent.putExtra("a1", a1);
                        intent.putExtra("Titles",Titles);
                        intent.putExtra("Contents",Contents);
                        intent.putExtra("Urls",Urls);
                        intent.putExtra("mapid",x);
                        intent.putExtra("share",share);
                        startActivity(intent);
                        finish();
                    }



                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    Log.d("suthar", databaseError.toString());
                }
            });





//            ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, titles);
//
//            lvRss.setAdapter(adapter);


            progressDialog.dismiss();
        }
    }
    @Override
    public void onBackPressed() {

        finish();
        Intent intent = new Intent(NewsActivity.this, ChooseTask.class);
        intent.putExtra("mapid",x);
        startActivity(intent);
    }
}