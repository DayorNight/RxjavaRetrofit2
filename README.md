# Rxjava+Retrofit封装
![Image text](https://github.com/DayorNight/RxjavaRetrofit2/blob/master/1.png)
####Get方法  调用
```
RequestUtils.getDemo(this, new MyObserver<Demo>(this) {
            @Override
            public void onSuccess(Demo result) {
                tv_retrofit.setText(result.toString());
            }
            @Override
            public void onFailure(Throwable e, String errorMsg) {
                tv_retrofit.setText(errorMsg);
            }
        });
```

[《Android Rxjava+Retrofit网络请求框架封装（一）》](https://blog.csdn.net/cs_lwb/article/details/82016997)
[《Android Rxjava+Retrofit网络请求框架封装（二）》](https://www.jianshu.com/p/cb2c375c9105)