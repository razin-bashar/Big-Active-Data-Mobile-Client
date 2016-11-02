package com.example.root.bloodbank;

//package com.example.razin.gcminstall;

        import android.app.ProgressDialog;
        import android.content.Context;
        import android.os.AsyncTask;
        import android.os.Bundle;
        import android.support.v7.app.AppCompatActivity;
        import android.view.View;
        import android.widget.Button;
        import android.widget.TextView;
        import android.widget.Toast;

        import org.json.JSONException;
        import org.json.JSONObject;

        import java.util.concurrent.ExecutionException;

        import BADclientPack.BADclient;

public class Register extends AppCompatActivity {
    String name="";
    String email="";
    String password="";
    TextView text;
    Button button;
    String regId;
    String userid;
    Context ctx;
    ProgressDialog pd;
    AsyncTask<Void, Void, String> shareRegidTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        ctx=this;
        button=(Button)findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View arg0){

                text=(TextView)findViewById(R.id.editText);
                name=text.getText().toString();
                text=(TextView)findViewById(R.id.editText2);
                email=text.getText().toString();
                text=(TextView)findViewById(R.id.editText3);
                password=text.getText().toString();
                if(name.isEmpty()||email.isEmpty()||password.isEmpty()){
                    Toast.makeText(getApplicationContext(),"Please enter in correct ways",Toast.LENGTH_LONG).show();
                }
                else {
                    String validemail = new useremailget().getemail(getApplicationContext());
                    if (validemail == null)
                        Toast.makeText(getApplicationContext(), "email..null ", Toast.LENGTH_LONG).show();
                    else if (!validemail.equals(email))
                        Toast.makeText(getApplicationContext(), "enter valid email..", Toast.LENGTH_LONG).show();
                }
                getregId reg=new getregId(getApplicationContext()) ;
                regId=reg.registerGCM();
                System.out.println("in register"+reg.regId);

                JSONObject js=new JSONObject();
                try {
                    js.put("dataverseName","channels");
                    js.put("userName",name);
                    js.put("email",email);
                    js.put("password",password);
                   // js.put("platform","android");

                   // js.put("gcmRegistrationId",regId);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

             //   post_http register=new post_http(Config.REGISTRATION_URL,js.toString());
              //  register.execute();


                pd = ProgressDialog.show(ctx, "Fancy App",
                        "Loading...Please wait...", true, false);
                BADclient client=new BADclient();
                JSONObject result= null;
               /* try {
                  //  result = new JSONObject("nei");
                } catch (JSONException e) {
                    e.printStackTrace();
                }*/
                try {
                    result=client.Register(Config.REGISTRATION_URL,js);
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                pd.cancel();
                Toast.makeText(getApplicationContext(),result.toString(),Toast.LENGTH_LONG).show();

            }
        });


    }




}
