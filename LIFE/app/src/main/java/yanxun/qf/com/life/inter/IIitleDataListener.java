package yanxun.qf.com.life.inter;


import retrofit2.Call;
import retrofit2.http.GET;
import yanxun.qf.com.life.bean.TitleBean;

/**
 * Created by Administrator on 2017/6/3.
 */

public interface IIitleDataListener {
    @GET("v2/time_axis")
    Call<TitleBean> getTitleData();
}
