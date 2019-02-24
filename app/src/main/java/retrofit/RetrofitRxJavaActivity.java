package retrofit;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.trello.rxlifecycle2.android.ActivityEvent;
import com.trello.rxlifecycle2.components.RxActivity;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import recycler.utils.lwb.blcs.rxjavaretrofit2.BaseResponse;
import recycler.utils.lwb.blcs.rxjavaretrofit2.Demo;
import recycler.utils.lwb.blcs.rxjavaretrofit2.LogInterceptor;
import recycler.utils.lwb.blcs.rxjavaretrofit2.R;
import retrofit.interfaces.ApiUrl;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitRxJavaActivity extends RxActivity {
    private static final String TAG = "RetrofitRxJavaActivity";
    private TextView tv_retrofit;
    private OkHttpClient client;
    private Retrofit retrofit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit);
        tv_retrofit = findViewById(R.id.tv_retrofit);
        // 初始化okhttp
        client = new OkHttpClient().newBuilder()
                .readTimeout(Constans.DEFAULT_TIME, TimeUnit.SECONDS)//设置读取超时时间
                .connectTimeout(Constans.DEFAULT_TIME, TimeUnit.SECONDS)//设置请求超时时间
                .writeTimeout(Constans.DEFAULT_TIME,TimeUnit.SECONDS)//设置写入超时时间
                .addInterceptor(new LogInterceptor())//添加打印拦截器
                .retryOnConnectionFailure(true)//设置出现错误进行重新连接。
                .build();

        retrofit = new Retrofit.Builder()
                .client(client)
                .baseUrl(Constans.BaseUrl)
                //添加GSON解析：返回数据转换成GSON类型
                .addConverterFactory(GsonConverterFactory.create())
                //添加Rxjava支持
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
//        getData1();
        //生命周期绑定
//        getData2();
        //返回数据绑定
        getData3();
    }

    private void getData3() {
        retrofit.create(ApiUrl.class)
                .getRetrofit2()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                //绑定生命周期
                .compose(bindUntilEvent(ActivityEvent.DESTROY))
                .subscribe(new Observer<BaseResponse<Demo>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.e(TAG, "onSubscribe: " );
                    }

                    @Override
                    public void onNext(BaseResponse<Demo> response) {
                        Log.e(TAG, "response: " +response.toString());
                        Log.e(TAG, "code: " +response.getRes_code());
                        Log.e(TAG, "msg: " +response.getErr_msg());
                        Log.e(TAG, "Demo: " +response.getDemo());
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "Throwable: " + e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        Log.e(TAG, "onComplete: " );
                    }
                });

    }

    private void getData2() {
        retrofit.create(ApiUrl.class)
                .getRetrofit1()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                //绑定生命周期
                .compose(bindUntilEvent(ActivityEvent.DESTROY))
                .subscribe(new Observer<Bean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.e(TAG, "onSubscribe: " );
                    }

                    @Override
                    public void onNext(Bean demo) {
                        Log.e(TAG, "onNext: " +demo.toString());
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "Throwable: " + e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        Log.e(TAG, "onComplete: " );
                    }
                });
    }

    private void getData1() {

        retrofit.create(ApiUrl.class)
                .getRetrofit1()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Bean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.e(TAG, "onSubscribe: " );
                    }

                    @Override
                    public void onNext(Bean demo) {
                        Log.e(TAG, "onNext: " +demo.toString());
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "Throwable: " + e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        Log.e(TAG, "onComplete: " );
                    }
                });

    }

}
