package yanxun.qf.com.life.inter;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import yanxun.qf.com.life.bean.HomeBannerBean;

/**
 * Created by Administrator on 2017/6/4.
 */

public interface IHomeBannerDataListener {

    @GET("v2/collections/{id}/posts?limit=20&offset=0")
    Call<HomeBannerBean> getHomeBannerData(@Path("id") String id);

}
