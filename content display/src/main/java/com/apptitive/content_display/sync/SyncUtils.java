package com.apptitive.content_display.sync;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.ContentResolver;
import android.content.Context;
import android.os.Bundle;

import com.apptitive.content_display.utilities.Constants;
import com.apptitive.content_display.utilities.LogUtil;
import com.apptitive.content_display.utilities.Utilities;

/**
 * Created by Sharif on 7/17/2014.
 */
public class SyncUtils {

    public static Account createSyncAccount(Context context) {
        LogUtil.LOGE("initial sync");
        Account account = AuthenticatorService.GetAccount(Constants.ACCOUNT_TYPE);
        AccountManager accountManager =
                (AccountManager) context.getSystemService(Context.ACCOUNT_SERVICE);
        if (accountManager.addAccountExplicitly(account, null, null)) {
            LogUtil.LOGE("first time call sync");
          //  Utilities.startSyncReceiver(context);
           // triggerManualSync();
        return account;
        }
        return null;
    }


    public static void triggerManualSync() {
        LogUtil.LOGE("manual sync");
        Bundle settingsBundle = new Bundle();
        settingsBundle.putBoolean(ContentResolver.SYNC_EXTRAS_MANUAL, true);
        settingsBundle.putBoolean(
                ContentResolver.SYNC_EXTRAS_EXPEDITED, true);
        ContentResolver.requestSync(
                AuthenticatorService.GetAccount(Constants.ACCOUNT_TYPE),
                Constants.AUTHORITY,
                settingsBundle);
    }

    public static void triggerPeriodicSync(long syncFrequency) {
        ContentResolver.addPeriodicSync(AuthenticatorService.GetAccount(Constants.ACCOUNT_TYPE), Constants.AUTHORITY, new Bundle(), syncFrequency);
    }
}
