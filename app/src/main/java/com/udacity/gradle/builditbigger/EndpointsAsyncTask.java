package com.udacity.gradle.builditbigger;

import android.os.AsyncTask;

import com.example.backend.myApi.MyApi;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;

import java.io.IOException;

/**
 * Created by Administrator on 2016/7/7.
 */
public class EndpointsAsyncTask extends AsyncTask<Void,Void,String> {
    private static MyApi myApiService = null;

    private AsyncResponse delegate;
    @Override
    protected String doInBackground(Void... params) {
        if(myApiService == null) {  // Only do this once
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    // options for running against local devappserver
                    // - 10.0.2.2 is localhost's IP address in Android emulator
                    // - turn off compression when running against local devappserver
                    .setRootUrl("https://guo1990jin6.appspot.com/_ah/api/");

            myApiService = builder.build();
        }
        try {
            return myApiService.getJoke().execute().getData();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    protected void onPostExecute(String s) {
        if(s == null) return;
        if(delegate != null){

        delegate.processResult(s);
        }
    }

    public void setDelegate(AsyncResponse delegate){
        this.delegate = delegate;
    }

    public interface AsyncResponse{
        void processResult(String s);
    }
}
