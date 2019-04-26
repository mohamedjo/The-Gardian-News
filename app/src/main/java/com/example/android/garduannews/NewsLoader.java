package com.example.android.garduannews;

import android.content.AsyncTaskLoader;
import android.content.Context;

import java.util.List;

/**
 * Created by HP on 4/14/2018.
 */

public class NewsLoader extends AsyncTaskLoader<List<News>> {
    String url;
    public NewsLoader(Context context,String url) {
        super(context);
        this.url=url;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public List<News> loadInBackground() {
        if(url==null||url.length()==0){

return null;


        }
        return QueryUtils.fetchEarthquakeData(url);


    }
}
