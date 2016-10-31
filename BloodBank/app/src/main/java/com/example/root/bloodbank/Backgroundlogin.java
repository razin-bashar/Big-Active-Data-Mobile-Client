package com.example.root.bloodbank;

//package com.example.razin.gcminstall;

        import android.app.ProgressDialog;
        import android.content.Context;
        import android.content.Intent;
        import android.content.SharedPreferences;
        import android.os.AsyncTask;
        import android.util.Log;
        import android.widget.Toast;

        import org.json.JSONArray;
        import org.json.JSONException;
        import org.json.JSONObject;

        import java.io.BufferedReader;
        import java.io.BufferedWriter;
        import java.io.IOException;
        import java.io.InputStream;
        import java.io.InputStreamReader;
        import java.io.OutputStream;
        import java.io.OutputStreamWriter;
        import java.net.HttpURLConnection;
        import java.net.MalformedURLException;
        import java.net.URL;
        import java.net.URLEncoder;
        import java.nio.Buffer;

/**
 * Created by Razin on 5/3/2016.
 */
public class Backgroundlogin extends AsyncTask<String,Void,String> {

    Context context;
    String url;
    String jsonn = "";
    private static final String TAG_SUCCESS = "status";
    private static final String TAG_CLIENT = "client";
    private JSONObject client;
    private JSONArray clientObjarray;
    String mail;
    String passw;
    ProgressDialog pDialog;
    public static String accesstoken;
    public static String userid;

    Backgroundlogin(Context ctx) {
        context = ctx;
        this.url =Config.LOGIN_URL;
    }

    @Override
    protected void onPreExecute() {
        Home.loginsession="yes";
        super.onPreExecute();
        pDialog = new ProgressDialog(context);
        pDialog.setMessage("Login is processing. Please wait...");
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(true);
        pDialog.show();
    }
    @Override
    protected String doInBackground(String... params) {
        String method = params[0];
        if (method.equals("login")) {
            mail = params[1];
            passw=params[2];
            String id=params[3];
            try {
                //String data = URLEncoder.encode("email", "UTF-8") + "=" + URLEncoder.encode(mail, "UTF-8");
                JSONObject loginfo=new JSONObject();
                loginfo.put("userName",mail);
                loginfo.put("password",passw);
                loginfo.put("dataverseName","channels");
                loginfo.put("platform","android");
                loginfo.put("gcmRegistrationToken",id);
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

        }

        return jsonn;
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(String aVoid) {
        JSONObject json = null;
        String success=null;
        try {
            json = new JSONObject(jsonn);
             success = json.getString("status");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        pDialog.cancel();
        if(success.equals("success")){
            Home.loginsession="yes";
            try {
                accesstoken=json.getString("accessToken");
                userid=json.getString("userId");
                SharedPreferences storage=context.getSharedPreferences("loginsession", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor=storage.edit();
                editor.putString("logses",accesstoken);
                editor.commit();


            } catch (JSONException e) {
                e.printStackTrace();
            }
            Intent intent = new Intent(context, Home.class);
            Toast.makeText(context,"Login Successful",Toast.LENGTH_LONG).show();
            context.startActivity(intent);
        }
        else Toast.makeText(context,"Login Unsuccessful",Toast.LENGTH_LONG).show();

    }


}
