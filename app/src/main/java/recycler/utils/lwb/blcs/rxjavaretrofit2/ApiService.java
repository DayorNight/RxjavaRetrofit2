package recycler.utils.lwb.blcs.rxjavaretrofit2;
import java.util.List;
import java.util.Map;
import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.HeaderMap;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

/**
 * retrofit2 Api接口 Demo
 */
public interface ApiService {

    /**
     * TODO Get请求
     */
    //第一种方式：GET不带参数
    @GET("retrofit.txt")
    Observable<Response<BaseResponse<Demo>>> getUser();//中间的BaseResponse 是自己根据后台数据封装的规范 可以进行修改
    @GET
    Observable<Response<Demo>> getUser(@Url String url); //正常情况   添加ResPonse可以根据Response.Code来判断错误类型
    @GET
    Observable<Demo> getUser1(@Url String url); //简洁方式   直接获取所需数据
    //第二种方式：GET带参数
    @GET("api/data/{type}/{count}/{page}")
    Observable<Response<Demo>> getUser(@Path("type") String type, @Path("count") int count, @Path("page") int page);
    //第三种方式：GET带请求参数：https://api.github.com/users/whatever?client_id=xxxx&client_secret=yyyy
    @GET("users/whatever")
    Observable<Response<Demo>> getUser(@Query("client_id") String id, @Query("client_secret") String secret);
    @GET("users/whatever")
    Observable<Response<Demo>> getUser(@QueryMap Map<String, String> info);

    /**
     * TODO POST请求
     */
    //第一种方式：@Body
    @Headers("Accept:application/json")
    @POST("login")
    Observable<Response<Demo>> postUser(@Body RequestBody body);
    //第二种方式：@Field

    @Headers("Accept:application/json")
    @POST("auth/login")
    @FormUrlEncoded
    Observable<retrofit2.Response<Demo>> postUser(@Field("username") String username, @Field("password") String password);
    //多个参数
    Observable<Response<Demo>> postUser(@FieldMap Map<String, String> map);



    /**
     * TODO DELETE
     */
    @DELETE("member_follow_member/{id}")
    Observable<Response<Demo>> delete(@Header("Authorization") String auth, @Path("id") int id);


    /**
     * TODO PUT
     */
    @PUT("member")
    Observable<Response<Demo>> put(@HeaderMap Map<String, String> headers,
                                   @Query("nickname") String nickname);


    /**
     * TODO 文件上传
     */
    @Multipart
    @POST("upload")
    Observable<ResponseBody> upload(@Part("description") RequestBody description,@Part MultipartBody.Part file);

    //亲测可用
    @Multipart
    @POST("member/avatar")
    Observable<Response<Demo>> uploadImage(@HeaderMap Map<String, String> headers, @Part MultipartBody.Part file);



    /**
     * 多文件上传
     * @param params
     * @param description
     * @return
     */
    @Multipart
    @POST("register")
    Observable<ResponseBody> upload(@PartMap Map<String, RequestBody> params, @Part("description") RequestBody description);
    //Observable<ResponseBody> upload(@Part() List<MultipartBody.Part> parts);


    @Multipart
    @POST("member/avatar")
    Observable<Response<Demo>> uploadImage1(@HeaderMap Map<String, String> headers, @Part List<MultipartBody.Part> file);


    /**
     * 来自https://blog.csdn.net/impure/article/details/79658098
     * @Streaming 这个注解必须添加，否则文件全部写入内存，文件过大会造成内存溢出
     */
    @Streaming
    @GET
    Observable<ResponseBody> download(@Header("RANGE") String start, @Url String url);

}
