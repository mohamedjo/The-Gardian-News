package com.example.android.garduannews;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.text.Html;

/**
 * Created by HP on 4/12/2018.
 */

public class News {
    private String tittle;
    private String imageUrl;
    private String detailsUrl;

    public News(String image, String tittle,String detailsUrl) {

        this.imageUrl = image;
        this.tittle = tittle;
        this.detailsUrl=detailsUrl;
    }

    public String getDetailsUrl() {
        return detailsUrl;
    }


    public String getImage() {
        return imageUrl;
    }

    public String getTittle() {
        return tittle;
    }






}
