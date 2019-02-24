package recycler.utils.lwb.blcs.rxjavaretrofit2;

/**
 * 统一响应
 * @param <T>
 */
public class BaseResponse<T> {

    /**
     * res_code : 200
     * err_msg : Web page does not exist
     * demo : {"id":"1001","appid":"1021","name":"sss","showtype":"text"}
     */

    private int res_code;
    private String err_msg;
    private T demo;

    public int getRes_code() {
        return res_code;
    }

    public void setRes_code(int res_code) {
        this.res_code = res_code;
    }

    public String getErr_msg() {
        return err_msg;
    }

    public void setErr_msg(String err_msg) {
        this.err_msg = err_msg;
    }

    public T getDemo() {
        return demo;
    }

    public void setDemo(T demo) {
        this.demo = demo;
    }

    public static class DemoBean {
    }
}
