package com.udacity.gradle.builditbigger;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.jokerlib.JokerActivity;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment implements View.OnClickListener{

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);

        Button getJoke =  (Button) root.findViewById(R.id.get_joke);
        getJoke.setOnClickListener(this);

        return root;
    }
    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.get_joke){
            EndpointsAsyncTask asyncTask = new EndpointsAsyncTask();
            asyncTask.setDialog(new ProgressDialog(getActivity()));
            asyncTask.setOnCompleteListener(new EndpointsAsyncTask.OnCompleteListener() {
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
