package com.example.android.garduannews;

import android.app.LoaderManager;
import android.content.Intent;
import android.content.Loader;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private static final String GARDUAN_URL_BASE="https://content.guardianapis.com/search?show-fields=thumbnail&api-key=c26c2c17-7468-470c-b86c-d90c566b3054";

    NewsAdapter adapter;
    TextView TVforEmptyList;
    ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
            this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        // the list to desplay news
        ListView newsList=(ListView)findViewById(R.id.listNews);
        // this text view to shoe when there is no news to show
        TVforEmptyList=(TextView)findViewById(R.id.TVforEmptyList);
        progressBar=(ProgressBar)findViewById(R.id.progressBar);

        newsList.setEmptyView(TVforEmptyList);

        ArrayList<News> list=new ArrayList<>();

         adapter=new NewsAdapter(this,list);
        newsList.setAdapter(adapter);
        LoadNewsTask loadNewsTask=new LoadNewsTask();
        loadNewsTask.execute(GARDUAN_URL_BASE);

        newsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                News news =(News)adapterView.getAdapter().getItem(i);
Intent intent=new Intent(MainActivity.this,DetailActivity.class);
                intent.putExtra("urlOfDetails",news.getDetailsUrl());
                startActivity(intent);
            }
        });


    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        Uri uri=Uri.parse(GARDUAN_URL_BASE);
        Uri.Builder builder=uri.buildUpon();

    if (id == R.id.nav_navworldnews) {

        builder.appendQueryParameter("section","world");
        new LoadNewsTask().execute(builder.toString());

        }
        if (id == R.id.nav_ukNews) {

            builder.appendQueryParameter("section","uk-news");
            new LoadNewsTask().execute(builder.toString());

        } if (id == R.id.nav_science) {

            builder.appendQueryParameter("section","science");
            new LoadNewsTask().execute(builder.toString());

        } if (id == R.id.nav_cities) {

            builder.appendQueryParameter("section","cities");
            new LoadNewsTask().execute(builder.toString());

        } if (id == R.id.nav_business) {

            builder.appendQueryParameter("section","business");
            new LoadNewsTask().execute(builder.toString());

        }
     if (id == R.id.nav_tech) {

        builder.appendQueryParameter("section","technology");
        new LoadNewsTask().execute(builder.toString());

    }

/*
         if (id == R.id.nav_sport) {
             builder.appendQueryParameter("section","football");
             new LoadNewsTask().execute(builder.toString());


        }
*/

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    /*

    @Override
    public Loader<List<News>> onCreateLoader(int i, Bundle bundle) {
      String section=  bundle.getString("section");
        Uri base=Uri.parse(GARDUAN_URL_BASE);
        Uri.Builder uriBuilder=base.buildUpon();
        uriBuilder.appendQueryParameter("section",section);

        NewsLoader loader=new NewsLoader(this,uriBuilder.toString());

        return loader;
    }

    @Override
    public void onLoadFinished(Loader<List<News>> loader, List<News> news) {
        if(news!=null&&news.size()!=0)
        {
            adapter.clear();
            adapter.addAll(news);




        }
    }

    @Override
    public void onLoaderReset(Loader<List<News>> news) {
        adapter.clear();

    }

    */
    // this class to do our loading task instead of loader task
    private class LoadNewsTask extends AsyncTask<String,Void,List<News>>{

        @Override
        protected void onPreExecute() {
            progressBar.setVisibility(View.VISIBLE);

        }

        @Override
        protected List<News> doInBackground(String... url) {
            if(url==null||url.length==0){

                return null;


            }
            return QueryUtils.fetchEarthquakeData(url[0]);


        }


        @Override
        protected void onPostExecute(List<News> news) {
            progressBar.setVisibility(View.GONE);

            if (news != null && news.size() != 0) {
                adapter.clear();
                adapter.addAll(news);


            }
        }
        }
    }






