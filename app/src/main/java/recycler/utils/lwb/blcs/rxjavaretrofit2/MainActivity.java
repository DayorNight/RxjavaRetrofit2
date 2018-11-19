package recycler.utils.lwb.blcs.rxjavaretrofit2;

import android.os.Bundle;
import android.util.Log;

import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import retrofit2.Response;

public class MainActivity extends RxAppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        get();
        Post();
    }


    public void get() {
        RequestUtils.getDemo(this, new MyObserver<Demo>(this) {
            @Override
            public void onSuccess(Demo result) {
                Log.e("=========","==========="+result.toString());
            }
            @Override
            public void onFailure(Throwable e, String errorMsg) {
                Log.e("====code====="+e.getMessage(),"==========="+errorMsg);
            }
        });
    }

    public void Post() {
        RequestUtils.postDemo(this, "aaa", "sss", new Observer<Response<Demo>>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.e("=========","======1=====");
            }
            @Override
            public void onNext(Response<Demo> loginBeanResponse) {
                Log.e("=========","=====2======"+loginBeanResponse.code());
            }
            @Override
            public void onError(Throwable e) {
                Log.e("=========","=====3======"+e.toString());
            }
            @Override
            public void onComplete() {
                Log.e("=========","=====4======");
            }
        });
    }
}
