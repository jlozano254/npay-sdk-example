package com.naranya.npay.npaydemo;

import android.app.Application;

import com.naranya.npay.NPay;

/**
 * Company name: Naranya Corp,
 * Company email: npaydevs@naranya.com
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
