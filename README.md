# RxjavaRetrofit2
Rxjava+Retrofit封装

####Get方法  调用
```
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
```
####Post方法  调用
```
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
```
csdn:[https://blog.csdn.net/cs_lwb/article/details/82016997](https://blog.csdn.net/cs_lwb/article/details/82016997)
简书:[https://www.jianshu.com/p/cb2c375c9105](https://www.jianshu.com/p/cb2c375c9105)
