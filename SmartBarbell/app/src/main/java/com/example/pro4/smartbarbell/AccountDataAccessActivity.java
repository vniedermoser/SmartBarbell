package com.example.pro4.smartbarbell;

import android.accounts.Account;
import android.accounts.AccountManager;

class AccountDataAccessActivity extends MainActivity {

    public String getName() {
        Account account = getAccount(AccountManager.get(this));
        String accountName = account.name;
        String fullName = accountName.substring(0, accountName.lastIndexOf("@"));
        return fullName;
    }

    public static Account getAccount(AccountManager accountManager) {
        Account[] accounts = accountManager.getAccountsByType("com.google");
        Account account;
        if (accounts.length > 0) {
            account = accounts[0];
        } else {
            account = null;
        }
        return account;
    }
}
