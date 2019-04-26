package com.example.android.garduannews;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by HP on 4/12/2018.
 */

public class NewsAdapter extends ArrayAdapter<News > {
    public NewsAdapter(Context context, ArrayList<News> newsList) {
        super(context,0,newsList);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View itemView=convertView;
    if(itemView==null){
      itemView= LayoutInflater.from(getContext()).inflate(R.layout.list_item_view,parent,false);



    }
        News currentNews=getItem(position);
        TextView tittleTV=(TextView) itemView.findViewById(R.id.newsTittleTV);
        tittleTV.setText(currentNews.getTittle());
        ImageView newsImage=(ImageView)itemView.findViewById(R.id.newsImagr);
       new LoadImageTask(newsImage).execute(currentNews.getImage());


        return itemView;


    }
    // this is an inner class to do the task of load the image from url to the imageView
    private class LoadImageTask extends AsyncTask<String,Void,Bitmap>{
      // this object hold the real image view in get view method
       ImageView imageOfNews;
        LoadImageTask(ImageView imageOfNews){

            this.imageOfNews=imageOfNews;
        }

        @Override
        protected Bitmap doInBackground(String... urls) {
            String urlOfImage = urls[0];
            Bitmap logo = null;
            try{
                InputStream is = new URL(urlOfImage).openStream();
                /*
                    decodeStream(InputStream is)
                        Decode an input stream into a bitmap.
                 */
                logo = BitmapFactory.decodeStream(is);
            }catch(Exception e){ // Catch the download exception
                e.printStackTrace();
            }
            return logo;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
imageOfNews.setImageBitmap(bitmap);
        }
    }
}
