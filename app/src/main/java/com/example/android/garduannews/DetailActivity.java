package com.example.android.garduannews;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class DetailActivity extends AppCompatActivity {

    WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);


        webView=(WebView)findViewById(R.id.web_view_detail);
        webView.setWebViewClient(new mybrowser());
        webView.getSettings().setJavaScriptEnabled(true);
        String urlOfDetails=getIntent().getStringExtra("urlOfDetails");

       webView.loadUrl(urlOfDetails);





    }
    class mybrowser extends WebViewClient {

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }


}
