package com.prototype.triptop.factory;

import com.squareup.okhttp.OkHttpClient;

public class HttpClientFactory {
    public static OkHttpClient createClient() {
        return new OkHttpClient();
    }
}
