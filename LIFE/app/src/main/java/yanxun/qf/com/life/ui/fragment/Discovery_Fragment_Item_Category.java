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
import yanxun.qf.com.life.adapter.DiscoveryCgtegoryAdapter;
import yanxun.qf.com.life.bean.DiscoveryCategoryBean;
import yanxun.qf.com.life.inter.IDiscoveryCategoryDataListener;
import yanxun.qf.com.life.utils.SpacesItemDecoration;

/**
 * Created by Administrator on 2017/6/6.
 */

public class Discovery_Fragment_Item_Category extends MyBaseFragment{
    //声明所需数据集合
    private List<DiscoveryCategoryBean.DataBean.CategoriesBean> categories;
    //声明适配器
    private DiscoveryCgtegoryAdapter adapter;
    private Handler mhandler = new Handler(){
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
         //super.onViewCreated(view, savedInstanceState);

        //间隔为零
        rv.addItemDecoration(new SpacesItemDecoration(0));
        //获取网络数据
        getData();
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mhandler.sendEmptyMessage(0);
            }
        });
    }

    private void getData() {
        //初始化Retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(APIManager.BASEURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        //获得接口对象
        IDiscoveryCategoryDataListener listener = retrofit.create(IDiscoveryCategoryDataListener.class);
        //通过接口方法获得数据
        Call<DiscoveryCategoryBean> data = listener.getDiscoveryCategoryData();
        //异步请求
        data.enqueue(new Callback<DiscoveryCategoryBean>() {




            @Override
            public void onResponse(Call<DiscoveryCategoryBean> call, Response<DiscoveryCategoryBean> response) {
                   if(response.isSuccess()){
                       categories = response.body().getData().getCategories();
                   }
                   if(null!=getContext()){
                       adapter = new DiscoveryCgtegoryAdapter(getContext(),categories);
                       rv.setAdapter(adapter);
                       mhandler.sendEmptyMessage(0);
                   }
            }

            @Override
            public void onFailure(Call<DiscoveryCategoryBean> call, Throwable t) {

            }
        });
    }
}
