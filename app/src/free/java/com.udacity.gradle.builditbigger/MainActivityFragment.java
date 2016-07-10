package com.udacity.gradle.builditbigger;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.jokerlib.JokerActivity;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment implements View.OnClickListener{

    public MainActivityFragment() {
    }

    private InterstitialAd interstitialAd;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        interstitialAd = new InterstitialAd(getActivity());
        interstitialAd.setAdUnitId(getString(R.string.banner_ad_unit_id));
        interstitialAd.setAdListener(new TheAdListener());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);

        Button mGetJoke = (Button) root.findViewById(R.id.get_joke);
        mGetJoke.setOnClickListener(this);
        AdView mAdView = (AdView) root.findViewById(R.id.adView);
        // Create an ad request. Check logcat output for the hashed device ID to
        // get test ads on a physical device. e.g.
        // "Use AdRequest.Builder.addTestDevice("ABCDEF012345") to get test ads on this device."
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        mAdView.loadAd(adRequest);
        return root;
    }



    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.get_joke){
        AdRequest adRequest = new AdRequest.Builder().build();
        interstitialAd.loadAd(adRequest);
        }
    }

    private class TheAdListener extends AdListener{
        @Override
        public void onAdLoaded() {
            super.onAdLoaded();
            interstitialAd.show();
        }

        @Override
        public void onAdClosed() {
            super.onAdClosed();
            EndpointsAsyncTask asyncTask = new EndpointsAsyncTask(getActivity());
            asyncTask.setmOnCompleteListener(new EndpointsAsyncTask.OnCompleteListener() {
                @Override
                public void onComplete(String s) {
                    Intent intent = new Intent(getActivity(), JokerActivity.class);
                    intent.putExtra(JokerActivity.KEY_JOKE, s);
                    startActivity(intent);

                }
            });
            asyncTask.execute();
        }
    }
}
