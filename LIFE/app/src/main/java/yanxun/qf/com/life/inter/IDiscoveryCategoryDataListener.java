package yanxun.qf.com.life.inter;

import retrofit2.Call;
import retrofit2.http.GET;
import yanxun.qf.com.life.bean.DiscoveryCategoryBean;

/**
 * Created by Administrator on 2017/6/6.
 */

public interface IDiscoveryCategoryDataListener {

    @GET("v2/item_categories/tree")
    Call<DiscoveryCategoryBean> getDiscoveryCategoryData();

}
