package retrofit;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import recycler.utils.lwb.blcs.rxjavaretrofit2.Demo;
import recycler.utils.lwb.blcs.rxjavaretrofit2.LogInterceptor;
import recycler.utils.lwb.blcs.rxjavaretrofit2.R;
import retrofit.interfaces.ApiUrl;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitActivity extends Activity {
    private static final String TAG = "RetrofitActivity";
    private TextView tv_retrofit;
    private OkHttpClient client;

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

        getData();
    }
    private void getData() {
        Retrofit retrofit = new Retrofit.Builder()
                .client(client)
                .baseUrl(Constans.BaseUrl)
                //添加GSON解析：返回数据转换成GSON类型
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiUrl api = retrofit.create(ApiUrl.class);
        Call<Bean> demo = api.getRetrofit();
        demo.enqueue(new Callback<Bean>() {
            @Override
            public void onResponse(Call<Bean> call, Response<Bean> response) {
                Log.e(TAG, "请求成功信息: "+response.body().toString());
            }

            @Override
            public void onFailure(Call<Bean> call, Throwable t) {
                Log.e(TAG, "请求失败信息: " +t.getMessage());
            }
        });

    }
}
