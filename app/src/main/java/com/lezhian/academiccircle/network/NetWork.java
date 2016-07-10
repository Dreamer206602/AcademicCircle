package com.lezhian.academiccircle.network;

import com.lezhian.academiccircle.utils.LogUtils;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.ResponseBody;
import retrofit2.CallAdapter;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by hww on 2016/6/20.
 */
public class NetWork {
    private static AcademicCircleApi mApi;
    private static AcademicCircleApi mApiHeadLine;

    private static final Converter.Factory gsonConverterFactory = GsonConverterFactory.create();
    private static final CallAdapter.Factory rxJavaCallAdapterFactory = RxJavaCallAdapterFactory.create();

    public static AcademicCircleApi getApi() {
        if (mApi == null) {
            mApi = getRetrofit(Api.BASE_API).create(AcademicCircleApi.class);
        }
        return mApi;
    }



    public static AcademicCircleApi getHeadLineApi() {
        if (mApiHeadLine == null) {
            mApiHeadLine = getRetrofit(Api.BASE_API_HEAD_LINE).create(AcademicCircleApi.class);
        }
        return mApiHeadLine;
    }

    private static Retrofit getRetrofit(String baseUrl) {

        return new Retrofit.Builder()
                .client(new OkHttpClient.Builder().addInterceptor(new LogInterceptor()).build())
                .baseUrl(baseUrl)
                .addConverterFactory(gsonConverterFactory)
                .addCallAdapterFactory(rxJavaCallAdapterFactory)
                .build();
    }

    private static class LogInterceptor implements Interceptor {
        @Override
        public okhttp3.Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            LogUtils.i("嗷大喵来打印日志咯", "request:" + request.toString());
            okhttp3.Response response = chain.proceed(chain.request());
//            long t1 = System.nanoTime();
//            long t2 = System.nanoTime();
//            LogUtils.i("嗷大喵来打印日志咯", String.format(Locale.getDefault(), "Received response for %s in %.1fms%n%s", response.request().url(), (t2 - t1) / 1e6d, response.headers()));
            okhttp3.MediaType mediaType = response.body().contentType();
            String content = response.body().string();
            LogUtils.i("嗷大喵来打印日志咯", "response body:" + content);
            if (response.body() != null) {
                ResponseBody body = ResponseBody.create(mediaType, content);
                return response.newBuilder().body(body).build();
            } else {
                return response;
            }
        }
    }


}
