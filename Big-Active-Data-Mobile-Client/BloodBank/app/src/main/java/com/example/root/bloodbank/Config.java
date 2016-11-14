package com.example.root.bloodbank;

//package com.example.razin.gcminstall;

/**
 * Created by Razin on 4/7/2016.
 */
public class Config {
    static String addr="192.168.0.101";//"192.168.0.103";//"10.0.3.2";
    static final String REGISTRATION_URL = "http://"+addr+":8989/register";//172.20.44.15//10.0.2.2:
    static final String LOGIN_URL = "http://"+addr+":8989/login";

    static final String LIST_SUB = "http://"+addr+":8989/subscribe";
    static final String insert = "http://"+addr+":8989/insertrecords";
    static final String GET_DATA = "http://"+addr+":8989/getresults";
    static final String GOOGLE_PROJECT_ID = "693624733357";
    static final String MESSAGE_KEY = "message";
    static final String TAG = "GCM Android Example";
    static final String DISPLAY_MESSAGE_ACTION ="com.androidexample.gcm.DISPLAY_MESSAGE";
    static final String EXTRA_MESSAGE = "message";
}