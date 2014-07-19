package com.apptitive.content_display.sync;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.ContentResolver;
import android.content.Context;
import android.os.Bundle;

import com.apptitive.content_display.utilities.Constants;
import com.apptitive.content_display.utilities.LogUtil;

import org.json.JSONArray;

/**
 * Created by Sharif on 7/17/2014.
 */
public class SyncUtils {
    public static void triggerInitialSync(Context context) {
        LogUtil.LOGE("initial sync");
        Account account = AuthenticatorService.GetAccount(Constants.ACCOUNT_TYPE);
        AccountManager accountManager =
                (AccountManager) context.getSystemService(Context.ACCOUNT_SERVICE);
        if (accountManager.addAccountExplicitly(account, null, null)) {
            ContentResolver.requestSync(account, Constants.AUTHORITY, new Bundle());
        }
    }


    public static void triggerManualSync() {
        LogUtil.LOGE("manual sync");
        Bundle b = new Bundle();
        ContentResolver.requestSync(
                AuthenticatorService.GetAccount(Constants.ACCOUNT_TYPE),
                Constants.AUTHORITY,
                b);
    }

    public static void triggerPeriodicSync(long syncFrequency) {
       // ContentResolver.addPeriodicSync(AuthenticatorService.GetAccount(Constants.ACCOUNT_TYPE), Constants.AUTHORITY, new Bundle(), syncFrequency);
    }
}