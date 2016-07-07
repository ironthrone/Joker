package com.udacity.gradle.builditbigger;

import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertTrue;

/**
 * Created by Administrator on 2016/7/7.
 */
@RunWith(AndroidJUnit4.class)
public class EndpointsAsyncTaskTest {

    private EndpointsAsyncTask mAsyncTask;

    @Before
    public void createAsyncTask(){
        mAsyncTask = new EndpointsAsyncTask();
    }

    @Test
    public void testDoInBackground() throws Exception {
        String joke = mAsyncTask.execute().get();
        assertTrue("to get joke failed",joke != null);
    }
}