package recycler.utils.lwb.blcs.rxjavaretrofit2;

import android.util.Log;
import android.widget.Toast;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import retrofit2.Response;

/**
 * 数据返回统一处理  参考https://www.jianshu.com/p/ff619fea7e22
 * @param <T>
 */
public abstract class BaseObserver<T> implements Observer<Response<BaseResponse<T>>> {
    private static final String TAG = "BaseObserver";
    @Override
    public void onNext(Response<BaseResponse<T>> tResponse) {
        Log.e(TAG,"code:==="+tResponse.code());
        Log.e(TAG,"errorBody===:"+tResponse.errorBody());
        Log.e(TAG,"Body===:"+tResponse.body());
        if(tResponse.code()==404){
            onFailure(new Exception("404"),"页面不存在");
            return;
        }
        BaseResponse<T> body = tResponse.body();
        if(body.getRes_code()==1){
            onSuccess(body.getDemo());
        }else{
            onFailure(new Exception(body.getErr_msg()), body.getErr_msg());
        }
    }

    @Override
    public void onError(Throwable e) {
        onFailure(e,RxExceptionUtil.exceptionHandler(e));
    }

    @Override
    public void onComplete() {

    }

    @Override
    public void onSubscribe(Disposable d) {

    }

    public abstract void onSuccess(T result);

    public abstract void onFailure(Throwable e,String errorMsg);

}
