package com.udacity.gradle.builditbigger;

import android.app.Dialog;
import android.content.Context;
import android.widget.ProgressBar;

/**
 * Created by Administrator on 2016/7/9.
 */
public class ProgressDialog extends Dialog {

    public ProgressDialog(Context context) {
        super(context);
        init(context);
    }

    private void init(Context context) {
        ProgressBar progress = new ProgressBar(context);
        setContentView(progress);
    }
}
