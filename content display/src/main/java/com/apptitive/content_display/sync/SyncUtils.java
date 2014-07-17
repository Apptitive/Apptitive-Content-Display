package com.apptitive.content_display.sync;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.ContentResolver;
import android.content.Context;
import android.os.Bundle;

import com.apptitive.content_display.utilities.Constants;

/**
 * Created by Sharif on 7/17/2014.
 */
public class SyncUtils {
    public static void triggerInitialSync(Context context) {
        Account account = AuthenticatorService.GetAccount(Constants.ACCOUNT_TYPE);
        AccountManager accountManager =
                (AccountManager) context.getSystemService(Context.ACCOUNT_SERVICE);
        if (accountManager.addAccountExplicitly(account, null, null)) {
            ContentResolver.requestSync(account, Constants.AUTHORITY, new Bundle());
        }
    }



    public static void triggerManualSync() {
        Bundle b = new Bundle();
        ContentResolver.requestSync(
                AuthenticatorService.GetAccount(Constants.ACCOUNT_TYPE),
                Constants.AUTHORITY,
                b);
    }


}
