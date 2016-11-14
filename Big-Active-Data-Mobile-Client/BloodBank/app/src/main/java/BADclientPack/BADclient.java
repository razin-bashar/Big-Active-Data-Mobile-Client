package BADclientPack;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

/**
 * Created by root on 11/2/16.
 */
public class BADclient {

    public static JSONObject Login(String url,JSONObject data) throws ExecutionException, InterruptedException, JSONException {
        Http_post_call forLogin=new Http_post_call(url,data.toString());
        forLogin.execute();
        String result=forLogin.get().toString();
        JSONObject jsonResult=new JSONObject(result);
        return jsonResult;

    }
    public static  JSONObject Register(String url,JSONObject data) throws ExecutionException, InterruptedException, JSONException {
        Http_post_call forRegister=new Http_post_call(url,data.toString());
        forRegister.execute();
        String result=forRegister.get().toString();
        JSONObject jsonResult=new JSONObject(result);
        return jsonResult;
    }
    public static  JSONObject Subscribe(String url,JSONObject data) throws ExecutionException, InterruptedException, JSONException {
        Http_post_call forSubscribe=new Http_post_call(url,data.toString());
        forSubscribe.execute();
        String result=forSubscribe.get().toString();
        JSONObject jsonResult=new JSONObject(result);
        return jsonResult;
    }
    public static JSONObject GetResult(String url,JSONObject data) throws ExecutionException, InterruptedException, JSONException {
        Http_post_call forGetResult=new Http_post_call(url,data.toString());
        forGetResult.execute();
        String result=forGetResult.get().toString();
        JSONObject jsonResult=new JSONObject(result);
        return jsonResult;
    }
    public static JSONObject Insert(String url,JSONObject data) throws ExecutionException, InterruptedException, JSONException {
        Http_post_call forInsert=new Http_post_call(url,data.toString());
        forInsert.execute();
        String result=forInsert.get().toString();
        JSONObject jsonResult=new JSONObject(result);
        return jsonResult;
    }

}
