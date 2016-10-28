package com.naranya.npay.npaydemo;

import android.app.Application;

import com.naranya.npay.NPay;

/**
 * Created by Anselmo Hernández Bazaldúa. on 8/9/16.
 * ----------------------------------------------------------
 * Additional Information
 * ----------------------------------------------------------
 * Company name: Naranya Corp,
 * Company email: anselmo.hernandez@naranya.com,
 * Personal email: chemo.baza@gmail.com,
 * Phone: +520448119163771,
 * Skype: chemo.baza,
 * ----------------------------------------------------------
 * Happy Coding :)
 */
public class DemoApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        new NPay().setContext(this)
                .setClientSecret(R.string.client_secret)
                .setKeyword(R.string.keyword)
                .setMedia(R.string.media)
                .build();
    }
}
