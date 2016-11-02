package BADclientPack;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

/**
 * Created by root on 11/2/16.
 */
public class BADclient {

    public JSONObject Login(String url,JSONObject data) throws ExecutionException, InterruptedException, JSONException {
        post_http forLogin=new post_http(url,data.toString());
        forLogin.execute();
        String result=forLogin.get().toString();
        JSONObject jsonResult=new JSONObject(result);
        return jsonResult;

    }
    public JSONObject Register(String url,JSONObject data) throws ExecutionException, InterruptedException, JSONException {
        post_http forRegister=new post_http(url,data.toString());
        forRegister.execute();
        String result=forRegister.get().toString();
        JSONObject jsonResult=new JSONObject(result);
        return jsonResult;
    }
    public JSONObject Subscribe(String url,JSONObject data) throws ExecutionException, InterruptedException, JSONException {
        post_http forSubscribe=new post_http(url,data.toString());
        forSubscribe.execute();
        String result=forSubscribe.get().toString();
        JSONObject jsonResult=new JSONObject(result);
        return jsonResult;
    }
    public JSONObject GetResult(String url,JSONObject data) throws ExecutionException, InterruptedException, JSONException {
        post_http forGetResult=new post_http(url,data.toString());
        forGetResult.execute();
        String result=forGetResult.get().toString();
        JSONObject jsonResult=new JSONObject(result);
        return jsonResult;
    }
    public JSONObject Insert(String url,JSONObject data) throws ExecutionException, InterruptedException, JSONException {
        post_http forInsert=new post_http(url,data.toString());
        forInsert.execute();
        String result=forInsert.get().toString();
        JSONObject jsonResult=new JSONObject(result);
        return jsonResult;
    }

}
