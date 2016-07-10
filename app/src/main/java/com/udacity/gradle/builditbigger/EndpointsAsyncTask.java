package com.udacity.gradle.builditbigger;

import android.content.Context;
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
    //must be activity
    private final Context mContext;
    private final ProgressDialog mProgress;

    public EndpointsAsyncTask(Context context) {
        mContext = context;
        mProgress = new ProgressDialog(mContext);
    }

    private OnCompleteListener mOnCompleteListener;

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
mProgress.show();
    }

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
        mProgress.dismiss();
        if(s == null) return;
        if(mOnCompleteListener != null){

        mOnCompleteListener.onComplete(s);
        }
    }

    public void setmOnCompleteListener(OnCompleteListener mOnCompleteListener){
        this.mOnCompleteListener = mOnCompleteListener;
    }

    public interface OnCompleteListener {
        void onComplete(String s);
    }
}
