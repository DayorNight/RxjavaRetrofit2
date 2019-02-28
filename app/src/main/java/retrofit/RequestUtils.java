package retrofit;

import com.trello.rxlifecycle2.components.RxActivity;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;
import com.trello.rxlifecycle2.components.support.RxFragment;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import io.reactivex.Observer;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit.Utils.MyObserver;
import retrofit.Utils.RetrofitUtils;
import retrofit.Utils.RxHelper;
import retrofit.bean.Demo;

/**
 * 提交参数方式
 */
public class RequestUtils {

    /**
     * Get 请求demo
     * @param context
     * @param observer
     */
    public static void getDemo(RxActivity context, MyObserver<Demo> observer){
        RetrofitUtils.getApiUrl()
                .getDemo().compose(RxHelper.observableIO2Main(context))
                .subscribe(observer);
    }

    /**
     * Get 请求demo
     * @param context
     * @param observer
     */
    public static void getDemoList(RxActivity context, MyObserver<List<Demo>> observer){
        RetrofitUtils.getApiUrl()
                .getDemoList().compose(RxHelper.observableIO2Main(context))
                .subscribe(observer);
    }
    /**
     * Post 请求demo
     * @param context
     * @param consumer
     */
    public static void postDemo(RxAppCompatActivity context, String name, String password, Observer<Demo> consumer){
        RetrofitUtils.getApiUrl()
                .postUser(name,password).compose(RxHelper.observableIO2Main(context))
                .subscribe(consumer);
    }
    /**
     * Put 请求demo
     * @param context
     * @param consumer
     */
    public static void putDemo(RxFragment context, String access_token,Observer<Demo> consumer){
        Map<String, String> headers = new HashMap<String, String>();
        headers.put("Accept","application/json");
        headers.put("Authorization",access_token);
        RetrofitUtils.getApiUrl()
                .put(headers,"厦门").compose(RxHelper.observableIO2Main(context))
                .subscribe(consumer);
    }
    /**
     * Delete 请求demo
     * @param context
     * @param consumer
     */
    public static void deleteDemo(RxFragment context, String access_token,Observer<Demo> consumer){
        RetrofitUtils.getApiUrl()
                .delete(access_token,1).compose(RxHelper.observableIO2Main(context))
                .subscribe(consumer);
    }

    /**
     * 上传图片
     * @param context
     * @param observer
     */
    public static void upImagView(RxFragment context, String  access_token,String str, Observer<Demo>  observer){
        File file = new File(str);
//        File file = new File(imgPath);
        Map<String,String> header = new HashMap<String, String>();
        header.put("Accept","application/json");
        header.put("Authorization",access_token);
//        File file =new File(filePath);
        RequestBody reqFile = RequestBody.create(MediaType.parse("image/*"), file);
//        RequestBody requestFile =
//                RequestBody.create(MediaType.parse("multipart/form-data"), file);
        MultipartBody.Part body =
                MultipartBody.Part.createFormData("file", file.getName(), reqFile);
        RetrofitUtils.getApiUrl().uploadImage(header,body).compose(RxHelper.observableIO2Main(context))
                .subscribe(observer);
    }

    /**
     * 上传多张图片
     * @param files
     */
    public static void upLoadImg(RxFragment context,String access_token,List<File> files, Observer<Demo>  observer1){
        Map<String,String> header = new HashMap<String, String>();
        header.put("Accept","application/json");
        header.put("Authorization",access_token);
        MultipartBody.Builder builder = new MultipartBody.Builder()
                .setType(MultipartBody.FORM);//表单类型
        for (int i = 0; i < files.size(); i++) {
            File file = files.get(i);
            RequestBody photoRequestBody = RequestBody.create(MediaType.parse("image/*"), file);
            builder.addFormDataPart("file", file.getName(), photoRequestBody);
        }
        List<MultipartBody.Part> parts = builder.build().parts();
        RetrofitUtils.getApiUrl().uploadImage1(header,parts).compose(RxHelper.observableIO2Main(context))
                .subscribe(observer1);
    }
}

