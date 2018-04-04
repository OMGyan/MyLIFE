package yanxun.qf.com.life.ui.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import yanxun.qf.com.life.APIManager;
import yanxun.qf.com.life.adapter.DiscoveryChannelAdapter;
import yanxun.qf.com.life.bean.DiscoveryChannelBean;
import yanxun.qf.com.life.inter.IDiscoveryChannelDataListener;
import yanxun.qf.com.life.utils.SpacesItemDecoration;

/**
 * Created by Administrator on 2017/6/6.
 */

public class Discovery_Fragment_Item_Channel extends MyBaseFragment{
    private DiscoveryChannelAdapter adapter;
    private List<DiscoveryChannelBean.DataBean.ChannelBean> channel;
    private Handler mh = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if(null!=adapter){
                adapter.notifyDataSetChanged();
                swipeRefresh.setRefreshing(false);
            }
        }
    };
    //视图创建完全
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        //获得网络数据
        getData();
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mh.sendEmptyMessage(0);
            }
        });
        rv.addItemDecoration(new SpacesItemDecoration(40));
    }

    private void getData() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(APIManager.BASEURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        IDiscoveryChannelDataListener listener = retrofit.create(IDiscoveryChannelDataListener.class);
        Call<DiscoveryChannelBean> data = listener.getDiscoveryChannelData();
        data.enqueue(new Callback<DiscoveryChannelBean>() {


            @Override
            public void onResponse(Call<DiscoveryChannelBean> call, Response<DiscoveryChannelBean> response) {
                 if(response.isSuccess()){
                     channel = response.body().getData().getChannel();
                 }
                 if(null!=getContext()){
                     adapter = new DiscoveryChannelAdapter(getContext(),channel);
                     rv.setAdapter(adapter);
                     mh.sendEmptyMessage(0);
                 }
            }

            @Override
            public void onFailure(Call<DiscoveryChannelBean> call, Throwable t) {

            }
        });
    }
}
