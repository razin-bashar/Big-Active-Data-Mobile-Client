package com.example.root.bloodbank;

//package com.example.razin.gcminstall;

        import java.io.IOException;
        import java.io.OutputStream;
        import java.net.HttpURLConnection;
        import java.net.MalformedURLException;
        import java.net.URL;
        import java.util.HashMap;
        import java.util.Iterator;
        import java.util.Map;
        import java.util.Map.Entry;

        import android.content.Context;
        import android.util.Log;

        import org.json.JSONException;
        import org.json.JSONObject;

public class ShareExternalServer {

    public String shareRegIdWithAppServer(final Context context,
                                          final String regId,final String name,final String email,final String userid,final  String password) {


        String result = "";
        Map<String, String> paramsMap = new HashMap<String, String>();

        paramsMap.put("regId", regId);
        paramsMap.put("name", name);
        paramsMap.put("email", email);
        paramsMap.put("userid",userid);
        paramsMap.put("password",password);

        try {
            URL serverUrl = null;
            try {
                serverUrl = new URL(Config.REGISTRATION_URL);
            } catch (MalformedURLException e) {
                Log.e("AppUtil", "URL Connection Error: "
                        + Config.REGISTRATION_URL, e);
                result = "Invalid URL: " + Config.REGISTRATION_URL;
            }

           /* StringBuilder postBody = new StringBuilder();
            Iterator<Entry<String, String>> iterator = paramsMap.entrySet()
                    .iterator();

            while (iterator.hasNext()) {
                Entry<String, String> param = iterator.next();
                postBody.append(param.getKey()).append('=')
                        .append(param.getValue());
                if (iterator.hasNext()) {
                    postBody.append('&');
                }
            }*/
           // String body = postBody.toString();
            /*
             dataverseName = 'channels'
        try:
            dataverseName = post_data['dataverseName']
            userName = post_data['userName']
            email = post_data['email']
            password = post_data['password']

            platform = 'desktop' if 'platform' not in post_data else post_data['platform']
            log.info(platform)
            gcmRegistrationId = '' if 'gcmRegistrationId' not in post_data else post_data['gcmRegistrationId']
            */

            //String body="{\"dataverseName\" = \"channels\",\"userName\"=\""+name+"\",\"email\"=\""+email+"\",\"password\"=\""+password+"\",\"platform\"=\"android device\",\"gcmRegistrationId\"=\""+regId+"\"}";
            //System.out.println("body is"+body);
            //JSONObject js=new JSONObject(body);
           // byte[] bytes = body.getBytes();
            JSONObject js=new JSONObject();
            js.put("dataverseName","channels");
            js.put("userName",name);
            js.put("email",email);
            js.put("password",password);
            js.put("platform","desktop");
            js.put("gcmRegistrationId",regId);
            byte[] bytes=js.toString().getBytes();
            HttpURLConnection httpCon = null;
            try {
                httpCon = (HttpURLConnection) serverUrl.openConnection();
                httpCon.setDoOutput(true);
                httpCon.setUseCaches(false);
                httpCon.setFixedLengthStreamingMode(bytes.length);
                httpCon.setRequestMethod("POST");
                httpCon.setRequestProperty("Content-Type",
                        "application/x-www-form-urlencoded;charset=UTF-8");
                OutputStream out = httpCon.getOutputStream();
                out.write(bytes);
                out.close();

                int status = httpCon.getResponseCode();
                if (status == 200) {
                    result = "RegId shared with Application Server. RegId: "
                            + regId;
                } else {
                    result = "Post Failure." + " Status: " + status;
                }
            } finally {
                if (httpCon != null) {
                    httpCon.disconnect();
                }
            }

        } catch (IOException e) {
            result = "Post Failure. Error in sharing with App Server.";
            Log.e("AppUtil", "Error in sharing with App Server: " + e);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return result;
    }
}
