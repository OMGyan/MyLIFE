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
import yanxun.qf.com.life.adapter.HomeAdapter;
import yanxun.qf.com.life.bean.HomeItemBean;
import yanxun.qf.com.life.inter.IHomeItemDataListener;
import yanxun.qf.com.life.utils.SpacesItemDecoration;

/**
 * Created by Administrator on 2017/6/3.
 */

public class Home_Fragment_Item extends MyBaseFragment{
    private List<HomeItemBean.DataBean.HomeListBean> list;
    private HomeAdapter adapter;
    private Handler mhandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
             switch (msg.what){
                 case 0:
                     if(adapter!=null){
                         adapter.notifyDataSetChanged();
                         swipeRefresh.setRefreshing(false);
                     }
                     break;
                 case 1:
                     swipeRefresh.setRefreshing(false);
                     break;
             }
        }
    };
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getData();
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mhandler.sendEmptyMessage(0);
            }
        });
        rv.addItemDecoration(new SpacesItemDecoration(40));
    }
    private void getData() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(APIManager.BASEURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        IHomeItemDataListener listener = retrofit.create(IHomeItemDataListener.class);
        Call<HomeItemBean> data = listener.getHomeItemData(getArguments().getString("index"), "20", "20");
        data.enqueue(new Callback<HomeItemBean>() {
            @Override
            public void onResponse(Call<HomeItemBean> call, Response<HomeItemBean> response) {
                if(response.isSuccess()){
                    list = response.body().getData().getHome_list();
                }
                if (null != getContext()&&list.size()>0) {

                    adapter = new HomeAdapter(getContext(), list);
                }
                rv.setAdapter(adapter);

               mhandler.sendEmptyMessage(0);
            }

            @Override
            public void onFailure(Call<HomeItemBean> call, Throwable t) {
                mhandler.sendEmptyMessage(1);
            }
        });
    }
}
