package com.apptitive.content_display.sync;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.ContentResolver;
import android.content.Context;
import android.os.Bundle;

import com.apptitive.content_display.utilities.Constants;
import com.apptitive.content_display.utilities.LogUtil;

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
            LogUtil.LOGE("first time call sync");
            triggerManualSync();
        }
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
}
