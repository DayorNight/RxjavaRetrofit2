package retrofit.interfaces;

import io.reactivex.Observable;
import recycler.utils.lwb.blcs.rxjavaretrofit2.BaseResponse;
import recycler.utils.lwb.blcs.rxjavaretrofit2.Demo;
import retrofit.Bean;
import retrofit.Constans;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface ApiUrl {
    @GET(Constans.retrofit)
    Call<Bean> getRetrofit();

    @GET(Constans.retrofit)
    Observable<Bean> getRetrofit1();

    @GET(Constans.retrofit)
    Observable<BaseResponse<Demo>> getRetrofit2();
}
