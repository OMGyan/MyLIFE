package yanxun.qf.com.life.ui.activity;

import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import yanxun.qf.com.life.APIManager;
import yanxun.qf.com.life.R;
import yanxun.qf.com.life.adapter.HomeBannerAdapter;
import yanxun.qf.com.life.bean.HomeBannerBean;
import yanxun.qf.com.life.inter.IHomeBannerDataListener;

/**
 * Created by Administrator on 2017/6/3.
 */

public class Home_Banner_Item_Activity extends AppCompatActivity{
    private SwipeRefreshLayout swipeRefresh;
    private TextView tv_title;
    private RecyclerView rv;
    private List<HomeBannerBean.DataBean.PostsBean> posts;
    private HomeBannerAdapter homeBannerAdapter;
    private Handler mhandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if (null!=homeBannerAdapter){
                homeBannerAdapter.notifyDataSetChanged();
                swipeRefresh.setRefreshing(false);
            }
        }
    };
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            // 沉浸 状态栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_banner_item);
        initViews();
        swipeRefresh.setColorSchemeColors(getResources().getColor(R.color.myYellow));
        getData();
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mhandler.sendEmptyMessage(0);
            }
        });
    }

    private void getData() {
        String id = getIntent().getStringExtra("urlId");
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(APIManager.BASEURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        IHomeBannerDataListener bannerDataListener = retrofit.create(IHomeBannerDataListener.class);
        Call<HomeBannerBean> homeBannerData = bannerDataListener.getHomeBannerData(id);
        homeBannerData.enqueue(new Callback<HomeBannerBean>() {




            @Override
            public void onResponse(Call<HomeBannerBean> call, Response<HomeBannerBean> response) {
                 if(response.isSuccess()){
                     String title = response.body().getData().getTitle();
                     tv_title.setText(title);
                     posts = response.body().getData().getPosts();
                 }
                homeBannerAdapter = new HomeBannerAdapter(Home_Banner_Item_Activity.this,posts);
                LinearLayoutManager layoutManager = new LinearLayoutManager(Home_Banner_Item_Activity.this, LinearLayoutManager.VERTICAL, false);
                rv.setLayoutManager(layoutManager);
                rv.setAdapter( homeBannerAdapter);
                mhandler.sendEmptyMessage(0);
            }

            @Override
            public void onFailure(Call<HomeBannerBean> call, Throwable t) {
                Toast.makeText(Home_Banner_Item_Activity.this, "数据加载异常,请检查网络", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initViews() {
        swipeRefresh = (SwipeRefreshLayout) findViewById(R.id.swipeRefresh);
        tv_title = (TextView) findViewById(R.id.tv_title);
        rv = (RecyclerView) findViewById(R.id.rv);
    }
    public void backClick(View view) {
        finish();
    }

    public void share(View view) {
        Toast.makeText(this, "分享成功", Toast.LENGTH_SHORT).show();
    }
}
