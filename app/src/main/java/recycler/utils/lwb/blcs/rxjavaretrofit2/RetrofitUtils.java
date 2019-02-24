package recycler.utils.lwb.blcs.rxjavaretrofit2;


import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import java.util.concurrent.TimeUnit;
import okhttp3.OkHttpClient;
import retrofit.Constans;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
/**
 * Retrofit封装
 */
public class RetrofitUtils {
    public final static String BaseUrl= "http://pndatsn5v.bkt.clouddn.com/";

    private static RetrofitUtils mInstance;
    /**
     * 单例模式
     */
    public static RetrofitUtils getInstance() {
        if (mInstance == null) {
            synchronized (RetrofitUtils.class) {
                if (mInstance == null) {
                    mInstance = new RetrofitUtils();
                }
            }
        }
        return mInstance;
    }

    /**
     * 初始化必要对象和参数
     * @param url  基础baseUrl
     * @return
     */
    public Retrofit getRetrofit(String url) {
        // 初始化okhttp
        OkHttpClient client = new OkHttpClient().newBuilder()
                .readTimeout(Constans.DEFAULT_TIME, TimeUnit.SECONDS)//设置读取超时时间
                .connectTimeout(Constans.DEFAULT_TIME, TimeUnit.SECONDS)//设置请求超时时间
                .writeTimeout(Constans.DEFAULT_TIME,TimeUnit.SECONDS)//设置写入超时时间
                .addInterceptor(new LogInterceptor())//添加打印拦截器
                .retryOnConnectionFailure(true)//设置出现错误进行重新连接。
                .build();
        // 初始化Retrofit
        return  new Retrofit.Builder()
                .client(client)
                .baseUrl(url)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    //返回一个泛型类
    public static  <T>T getService(Class<T> service){
        return getInstance().getRetrofit(BaseUrl).create(service);
    }

    public static ApiService  getApiService(){
        return getInstance().getRetrofit(BaseUrl).create(ApiService.class);
    }
}

