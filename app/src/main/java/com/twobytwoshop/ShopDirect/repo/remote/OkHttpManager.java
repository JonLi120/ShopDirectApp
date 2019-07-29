package com.twobytwoshop.ShopDirect.repo.remote;

import android.util.Log;

import com.twobytwoshop.ShopDirect.BuildConfig;
import com.twobytwoshop.ShopDirect.core.ConstantConfig;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.FormBody;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

public class OkHttpManager {
    private static OkHttpClient okHttpClient;

    public static OkHttpClient getInstance() {
        if (okHttpClient == null) {
            synchronized (OkHttpManager.class) {
                if (okHttpClient == null) {
                    OkHttpClient.Builder builder = new OkHttpClient.Builder();

                    if (BuildConfig.DEBUG) {
                        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
                        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
                        builder.addInterceptor(interceptor);
                    }

                    builder.addInterceptor(new ParamsInterceptor());
                    builder.addNetworkInterceptor(new NetInterceptor());
                    builder.connectTimeout(10, TimeUnit.SECONDS);
                    builder.readTimeout(10, TimeUnit.SECONDS);
                    builder.writeTimeout(10, TimeUnit.SECONDS);
                    builder.retryOnConnectionFailure(false);
                    okHttpClient = builder.build();
                }
            }
        }
        return okHttpClient;
    }

    static class NetInterceptor implements Interceptor {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request().newBuilder()
                    .addHeader("Connection", "close")
                    .addHeader("ContentType", "application/x-www-form-urlencoded; charset=UTF-8")
                    .build();

            return chain.proceed(request);
        }
    }

    static class ParamsInterceptor implements Interceptor {

        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            //post请求追加参数
            FormBody.Builder newBody = new FormBody.Builder();
            newBody.add("VER", ConstantConfig.APP_VERSION_CODE)
                    .add("APIKEY", ConstantConfig.APP_KEY)
                    .add("SSID", ConstantConfig.APP_DEVICE)
                    .add("uuid", ConstantConfig.APP_UUID)
                    .build();
            //拦截请求中用户传入的数据，把参数遍历放入新的body里面，然后进行一块提交
            FormBody oldBody = (FormBody) request.body();
            for (int i = 0; i < oldBody.size(); i++)
            {
                newBody.add(oldBody.encodedName(i), oldBody.encodedValue(i));
            }
            request = request.newBuilder()
                    .post(newBody.build())
                    .build();
            return chain.proceed(request);
        }
    }
}
