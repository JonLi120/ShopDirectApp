package com.twobytwoshop.ShopDirect.core;

import android.widget.Toast;

public class NetworkError {
    public static void error(Throwable throwable) {
        RetrofitException.ResponseThrowable responseThrowable = RetrofitException.retrofitException(throwable);

        switch (responseThrowable.code) {
            case RetrofitException.ERROR.UNKNOWN:
            case RetrofitException.ERROR.PARSE_ERROR:
            case RetrofitException.ERROR.NETWORK_ERROR:
            case RetrofitException.ERROR.HTTP_ERROR:
            case RetrofitException.ERROR.SSL_ERROR:
//                Toast.makeText(context, responseThrowable.message, Toast.LENGTH_SHORT).show();
            default:
                Toast.makeText(BaseApplication.getInstance().getApplicationContext(), responseThrowable.message, Toast.LENGTH_LONG).show();
        }
    }
}
