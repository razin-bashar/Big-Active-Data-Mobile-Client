package BADclientPack;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import com.example.root.bloodbank.Config;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

import BADclientPack.BADclient;

/**
 * Created by root on 9/1/16.
 */
public class Data_receive_service extends IntentService {
    public static String jsonn = "";
    public Data_receive_service(){
        super("dataservice");

    }
    @Override
    public void onCreate() {
        // TODO Auto-generated method stub
        super.onCreate();
    }
    @Override
    protected void onHandleIntent(Intent intent) {
        SharedPreferences loginfo=getSharedPreferences(intent.getExtras().getString("BAD_Login_Session"), Context.MODE_PRIVATE);

        Bundle extras = intent.getExtras();
        System.out.println(extras.toString());
        try{
        JSONObject datainfo=new JSONObject();
        datainfo.put("dataverseName",extras.getString("dataverseName"));
        datainfo.put("userId",loginfo.getString("userid",""));
        datainfo.put("accessToken",loginfo.getString("logses",""));
        datainfo.put("userSubscriptionId",extras.getString("userSubscriptionId"));
        datainfo.put("channelName",extras.getString("channelName"));
        datainfo.put("channelExecutionTime",extras.getString("channelExecutionTime"));
           // BADclient client=new BADclient();
            JSONObject result=BADclient.GetResult(intent.getExtras().getString("Get_BAD_Result"),datainfo);
            storedata(result.toString(),intent.getExtras().getString("BAD_Data_Storage"));
        }catch (JSONException e){
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }
    public void storedata(String s,String store){
        if(s.contains("error"))return;
        SharedPreferences storage = getSharedPreferences(store,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = storage.edit();
        String counter =storage.getString("counter", "");
        int i;
        if(!counter.isEmpty()){
         i=Integer.parseInt(counter);
        i++;}
        else i=1;
        editor.remove("counter");
        editor.putString("counter",i+"");
        editor.putString("counter"+i, s);

        editor.commit();
        Log.i("hihi", "Saving datares "+"counter"+i+":"+ storage.getString("counter"+i,""));
    }
}
