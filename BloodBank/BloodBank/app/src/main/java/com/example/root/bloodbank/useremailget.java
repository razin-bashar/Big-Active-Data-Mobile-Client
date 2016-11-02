package com.example.root.bloodbank;

//package com.example.razin.gcminstall;
        import android.accounts.Account;
        import android.accounts.AccountManager;
        import android.content.Context;

/**
 * Created by Razin on 4/25/2016.
 */
public class useremailget {
    public String getemail(Context context){
        AccountManager accountManager=AccountManager.get(context);
        Account acc=getaccount(accountManager);
        if(acc==null)return null;
        else return acc.name;
    }
    public static Account getaccount(AccountManager accountManager){
        Account[] account=accountManager.getAccountsByType("com.google");
        Account acc;
        if (account.length>0){
            acc=account[0];
        }
        else{
            acc=null;
        }
        return acc;
    }
}
