package com.example.appicationex;

import android.app.Application;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MyApplication  extends Application {
    private String globalString;

    @Override
    public void onCreate() {
        super.onCreate();
        globalString = getGlobalString();
    }


    public String getGlobalString() {
        return globalString;
    }

    public void setGlobalString(String globalString) {
        this.globalString = globalString;
    }




}
