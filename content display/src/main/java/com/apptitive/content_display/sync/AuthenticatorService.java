package com.apptitive.content_display.sync;

import android.accounts.Account;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import com.apptitive.content_display.utilities.Constants;

/**
 * Created by Sharif on 7/16/2014.
 */
public class AuthenticatorService extends Service {
    private Authenticator mAuthenticator;

    @Override
    public void onCreate() {
        mAuthenticator = new Authenticator(this);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mAuthenticator.getIBinder();
    }

    public static Account GetAccount(String accountType) {
        final String accountName = Constants.ACCOUNT_NAME;
        return new Account(accountName, accountType);
    }
}
