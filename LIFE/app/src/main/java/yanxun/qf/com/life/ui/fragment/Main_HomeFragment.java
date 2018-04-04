package yanxun.qf.com.life.ui.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import yanxun.qf.com.life.APIManager;
import yanxun.qf.com.life.R;
import yanxun.qf.com.life.bean.TitleBean;
import yanxun.qf.com.life.inter.IIitleDataListener;

/**
 * Created by Administrator on 2017/6/3.
 */

public class Main_HomeFragment extends Fragment{

    private View tooBarView;
    private View rootView;
    private FragmentStatePagerAdapter adapter;
    private Toolbar toolbar;
    private TabLayout tablayout;
    private ViewPager viewpager;
    private List<String> titles = new ArrayList<>();
    private List<Fragment> fragments = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        tooBarView = inflater.inflate(R.layout.toolbar_home,container,false);
        if(rootView==null){
            rootView = inflater.inflate(R.layout.fragment_main_home,container,false);
        }
        // 缓存的rootView需要判断是否已经被加过parent，如果有parent需要从parent删除，要不然会发生这个rootview已经有parent的错误。
        ViewGroup parent = (ViewGroup) rootView.getParent();
        if (parent != null) {
            parent.removeView(rootView);
        }
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
         if(adapter==null){
             initViews(view);
             initToolBarView();
             toolbar.addView(tooBarView);
             getTitleData();
         }else {
             adapter.notifyDataSetChanged();
         }
    }

    private void getTitleData() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(APIManager.BASEURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        IIitleDataListener listener = retrofit.create(IIitleDataListener.class);
        Call<TitleBean> data = listener.getTitleData();
        data.enqueue(new Callback<TitleBean>() {
            @Override
            public void onResponse(Call<TitleBean> call, Response<TitleBean> response) {
                if(response.isSuccess()){
                    List<TitleBean.DataBean.TimeAxisBean> time_axis = response.body().getData().getTime_axis();
                    for (int i = 0; i < time_axis.size(); i++) {
                        titles.add(time_axis.get(i).getName());
                    }
                    initData();
                    initAdapter();
                    viewpager.setAdapter(adapter);
                    tablayout.setupWithViewPager(viewpager);
                    tablayout.setSelectedTabIndicatorColor(getResources().getColor(R.color.myYellow));
                    tablayout.setTabTextColors(Color.GRAY, Color.BLACK);
                    tablayout.setTabMode(TabLayout.MODE_SCROLLABLE);
                }
            }

            @Override
            public void onFailure(Call<TitleBean> call, Throwable t) {

            }
        });
    }

    private void initAdapter() {
       adapter = new FragmentStatePagerAdapter(getChildFragmentManager()) {
           @Override
           public Fragment getItem(int position) {
               return fragments.get(position);
           }

           @Override
           public int getCount() {
               return fragments.size();
           }

           @Override
           public CharSequence getPageTitle(int position) {
               return titles.get(position);
           }
       };
    }

    private void initData() {
        for (int i = 0; i < titles.size(); i++) {
            Home_Fragment_Item home_fragment_item = new Home_Fragment_Item();
            Bundle bundle = new Bundle();
            bundle.putString("index",i+"");
            home_fragment_item.setArguments(bundle);
            fragments.add(home_fragment_item);
        }

    }

    private void initToolBarView() {
       TextView qd = (TextView) tooBarView.findViewById(R.id.tv_qiandao);
       ImageView iv = (ImageView) tooBarView.findViewById(R.id.search);

        qd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(),"签到",Toast.LENGTH_SHORT).show();
            }
        });
        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(),"搜索",Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initViews(View view) {
        toolbar = (Toolbar) view.findViewById(R.id.toolBar);
        tablayout = (TabLayout) view.findViewById(R.id.tabLayout);
        viewpager = (ViewPager) view.findViewById(R.id.viewPager);
    }
}
