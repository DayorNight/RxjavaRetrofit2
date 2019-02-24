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
        Log.e(TAG,"code:==="+tResponse.code());//服务返回码
        Log.e(TAG,"errorBody===:"+tResponse.errorBody());//返回服务错误信息
        Log.e(TAG,"Body===:"+tResponse.body().toString());
        BaseResponse<T> body = tResponse.body();
        //后台统一风格
        int res_code = body.getRes_code();//成功/错误码
        String err_msg = body.getErr_msg();//成功/错误信息
        //可以在这边对后台返回错误信息做统一处理
        //  ......


//        if(res_code==200){
            onSuccess(body.getDemo());
            //若不需对返回数据进行处理 去掉BaseResponse封装类  传入所有数据
//            onSuccess(body);
//        }else{
//            onFailure(new Exception(body.getErr_msg()), err_msg);
//        }
    }

    @Override
    public void onError(Throwable e) {//服务器错误信息处理
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
