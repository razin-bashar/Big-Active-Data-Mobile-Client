package com.example.root.bloodbank;

import android.os.AsyncTask;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

/**
 * Created by root on 9/5/16.
 */
public class post_http extends AsyncTask<String,Void,String> {
    String url;
    String data;
    public String jsonn = "";

    public post_http(String url, String data) {
        this.url = url;
        this.data = data;
    }

    @Override
    protected String doInBackground(String... strings) {
        try {
            //String data = URLEncoder.encode("email", "UTF-8") + "=" + URLEncoder.encode(mail, "UTF-8");
            JSONObject loginfo=new JSONObject(data);
            URL urll = new URL(url);
            HttpURLConnection httpCon = (HttpURLConnection) urll.openConnection();
            httpCon.setDoOutput(true);
            httpCon.setUseCaches(false);
            httpCon.setFixedLengthStreamingMode(loginfo.toString().getBytes().length);
            httpCon.setRequestMethod("POST");
            httpCon.setRequestProperty("Content-Type",
                    "application/x-www-form-urlencoded;charset=UTF-8");
            OutputStream out = httpCon.getOutputStream();
            out.write(loginfo.toString().getBytes());
            out.close();


            InputStream is = httpCon.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    is, "iso-8859-1"), 8);
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
            //is.close();
            jsonn = sb.toString();
            System.out.println(jsonn);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return  jsonn;
    }

    @Override
    protected void onPostExecute(String s) {
        System.out.println("chosma:"+jsonn);
    }
}