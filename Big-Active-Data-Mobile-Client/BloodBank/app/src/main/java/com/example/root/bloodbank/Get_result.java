package com.example.root.bloodbank;

import android.content.Intent;

import BADclientPack.Data_receive_service;

/**
 * Created by root on 11/14/16.
 */
public class Get_result extends Data_receive_service {
    @Override
    protected void onHandleIntent(Intent intent) {
        intent.putExtra("Get_BAD_Result",Config.GET_DATA);
        intent.putExtra("BAD_Login_Session","loginsession");
        intent.putExtra("BAD_Data_Storage","mydatabase");
        super.onHandleIntent(intent);
    }
}
