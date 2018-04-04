package yanxun.qf.com.life.inter;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import yanxun.qf.com.life.bean.HomeItemBean;

/**
 * Created by Administrator on 2017/6/3.
 */

public interface IHomeItemDataListener {

    @GET("v2/home/{index}?")
    Call<HomeItemBean> getHomeItemData(@Path("index") String index, @Query("offset") String offset, @Query("limit") String limit);

}
