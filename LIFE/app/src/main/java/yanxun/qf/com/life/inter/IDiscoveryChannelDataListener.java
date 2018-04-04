package yanxun.qf.com.life.inter;

import retrofit2.Call;
import retrofit2.http.GET;
import yanxun.qf.com.life.bean.DiscoveryChannelBean;

/**
 * Created by Administrator on 2017/6/6.
 */

public interface IDiscoveryChannelDataListener {
    @GET("v2/special_channel?offset=0&limit=20")
    Call<DiscoveryChannelBean> getDiscoveryChannelData();
}
